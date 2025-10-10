# 🧭 URL Shortener System Design — Full Day 1 Interview Walkthrough

---

## 🧩 Q1 — Requirements & Scope

**Question:**  
> What functional and non-functional requirements would you clarify before starting your design?

**Answer:**  
Before designing, I’d confirm both **functional** and **non-functional** aspects.

**Functional Requirements:**
1. Maximum size of input URL.
2. Desired short URL length (e.g., 6–8 characters).
3. Support for expiry (optional).
4. Support for user-defined aliases (optional).
5. Metadata tracking (clicks, creation date, owner).
6. Expected QPS and growth target — likely a read-heavy system.

**Non-Functional Requirements:**
1. Scalability – Design for high QPS and low latency.
2. Availability – ≥99.9% uptime.
3. Latency – <100ms for redirection (p95), <500ms for creation.
4. Multi-tenancy – Separate logical data per tenant if needed.
5. Security – URL validation, rate limiting, data encryption.

---

## 🧮 Q2 — Assumptions & Scale Estimation

**Question:**  
> If the interviewer doesn’t provide numbers, what assumptions would you make for sizing and scaling?

**Answer:**  
Assume:  
- 10,000 requests/minute (~166 QPS average).  
- 12M requests/day.  
- 10× growth potential → 100k requests/min (~1,600 QPS).  
- Payload size: ~1 KB per request.  
- Daily bandwidth: ~12 GB/day.  
- 1,000 new URLs/day → ~1 MB/day → ~365 MB/year.  
- 90% reads, 10% writes.  
- Peak read traffic = cache-first design.

---

## 🧱 Q3 — High-Level Components

**Question:**  
> What are the main components in your system and their roles?

**Answer:**  
1. **API Gateway + Load Balancer:** Entry point for all requests; handles routing, rate limiting, and load distribution.  
2. **Application Service (Stateless):** Core logic for URL shortening (`POST /shorten`) and redirection (`GET /{code}`).  
3. **Cache Layer (Redis):** Hot storage for short→long mappings to ensure low-latency reads.  
4. **Database (SQL/NoSQL with Read Replicas):** Durable storage; primary for writes, replicas for read-heavy traffic.  
5. **ID Generator (Snowflake/ULID):** Time-based, distributed unique key generation.  
6. **Analytics Service (Async):** Collects click metrics asynchronously.  

---

## 🌐 Q4 — API Design

**Question:**  
> Define your APIs, request/response structure, and error handling.

**Answer:**  

**1️⃣ POST /shorten**
- **Payload:**
```json
{
  "url": "https://openai.com/research",
  "alias": "myLink", // optional
  "expiry": "2025-12-31T00:00:00Z" // optional
}
```
- **Responses:**
  - `201 Created` – success.
  - `409 Conflict` – alias already taken.
  - `400 Bad Request` – invalid input.
  - `429 Too Many Requests` – rate limited.

**2️⃣ GET /{code}**
- **Responses:**
  - `302 Found` – redirect to long URL.
  - `404 Not Found` – code missing.
  - `410 Gone` – expired link.
  - `429 Too Many Requests` – abuse detected.

**3️⃣ PATCH /expire/{code}**
- **Purpose:** manually expire a short URL.
- **Responses:** 200 OK, 404 Not Found, 403 Forbidden.

---

## 💾 Q5 — Data Model

**Question:**  
> What does your DB schema look like?

**Answer:**  

**Table: `url_mapping`**  
| Field | Type | Description |
|--------|------|-------------|
| `id` | UUID | Primary key |
| `short_url` | VARCHAR(10) | Unique code (indexed) |
| `long_url` | VARCHAR(2000) | Original URL |
| `expiry` | TIMESTAMP | Optional |
| `created_at` | TIMESTAMP DEFAULT NOW() | Creation time |
| `click_count` | BIGINT | Optional analytics field |

- **Indexes:** `UNIQUE(short_url)`  
- **Read scaling:** read replicas + cache  
- **Cleanup:** TTL or cron job for expired URLs.

---

## ⚙️ Q6 — Short Code Generation

**Question:**  
> How would you generate short, unique URLs?

**Answer:**  
Use a **Snowflake-style ID generator** that encodes:
- Timestamp → ensures ordering.  
- Machine ID → prevents collisions across instances.  
- Sequence bits → unique within the same millisecond.

Then **Base62 encode** the generated number to get an 8-char code.  
Custom aliases bypass generation but validated for uniqueness.

---

## 🧵 Q7 — Concurrency & Uniqueness

**Question:**  
> How do you ensure two instances don’t generate the same code?

**Answer:**  
Each service instance has a **unique machine ID** in the Snowflake algorithm.  
This ensures two parallel requests produce distinct codes.  
The DB enforces `UNIQUE(short_url)` as a final safeguard.  
No centralized lock or counter needed → low latency, high concurrency.

---

## ⚡ Q8 — Read Path (Redirect Flow)

**Question:**  
> Describe the flow when a user clicks a short link.

**Answer:**  
1. Client calls `GET /{code}`.  
2. API Gateway routes to app service.  
3. App checks **cache (Redis)** for `short:{code}`.  
4. If hit → return `302 Found` (fast, ~1–2ms).  
5. If miss → fetch from **DB read replica**, update cache asynchronously.  
6. Use **LRU + TTL eviction** for cache optimization.  
7. Log the click asynchronously for analytics.

**Result:** ~95% requests served from cache, <10ms latency.

---

## ✍️ Q9 — Write Path (Shorten Flow)

**Question:**  
> What happens during a POST /shorten request?

**Answer:**  
1. Validate input URL.  
2. Generate short code using Snowflake/ULID.  
3. Write mapping to **cache** immediately (instant availability).  
4. Push event to **queue** for **async DB persistence**.  
5. Return short URL (`201 Created`).  
6. Handle duplicates using an **idempotency hash** of the long URL.

**Benefits:** low latency, async durability, idempotent creation.

---

## 🚀 Q10 — Handling 10× Traffic Spike

**Question:**  
> How do you handle a sudden read surge?

**Answer:**  
- Scale **stateless app servers** horizontally.  
- Add **cache nodes (Redis cluster)**.  
- Add **DB read replicas**.  
- If hot key (single viral URL): cache alone handles traffic — serve from memory.  
- Use autoscaling triggers on CPU %, cache hit ratio, latency thresholds.

**Result:** Maintain <20ms latency at 10× load.

---

## 🧱 Q11 — Data Growth & Partitioning

**Question:**  
> How do you scale DB for billions of URLs?

**Answer:**  
- Start with **time-based partitions (by month)** for operational benefits — cleanup, smaller indexes, easy backup.  
- Evolve to **hash-based sharding** on `short_code` for performance scaling.  
- Use **Shard Registry / Consistent Hashing** to map short_code → shard.  
- Lookup becomes O(1) → query hits correct shard directly.

---

## 💡 Cross-Question: Partitioning vs Lookup

**Question:**  
> Partitioning by creation date doesn’t help lookups by `short_code`, right?

**Answer:**  
Correct — time partitions help with **data lifecycle management**, not **query speed**.  
For performance, use **hash(short_code)** sharding.  
Time partitions are operationally convenient; hash-based sharding provides lookup performance.

---

## 🏗️ Q12 — Availability & Disaster Recovery

**Question:**  
> How do you ensure availability and recover from failures?

**Answer:**  

### 1. Replication & Failover
- **DB:** Primary + async replicas across AZs. Automatic promotion on failure.  
- **Cache:** Redis cluster with replicas (Sentinel or managed).  
- **App:** Stateless; auto-healed behind load balancer.

### 2. Multi-AZ & Multi-Region
- **Multi-AZ:** Default for all tiers.  
- **Multi-Region (Warm Standby):** WAL/binlog shipping to secondary region; DNS failover triggers reroute.

### 3. Backups & PITR
- Daily full + hourly incremental snapshots.  
- Continuous WAL streaming for **Point-in-Time Recovery**.  
- Cross-region encrypted backup storage.

### 4. RPO / RTO Targets
- RPO ≤ 5 mins.  
- RTO ≤ 30 mins for regional failover.  
- Automated failover scripts + health-based DNS rerouting.

### 5. Testing & Validation
- DR drills monthly, backup restore validation weekly.  
- Chaos testing for AZ loss simulation.

---

## ⚙️ Q13 — HA & DR Runbook

**AZ-Level Failover**
1. Detect AZ failure (health checks).  
2. LB removes unhealthy nodes.  
3. Auto-scale new instances in healthy AZs.  
4. Promote cache replica if needed.  
5. Promote DB replica if primary down.  
**RTO:** <5 min.

**Regional Failover**
1. Detect region failure (global LB).  
2. Reroute DNS to standby region.  
3. Promote DB + rehydrate cache.  
4. Resume service in <30 mins.  
**RPO:** ≤5 mins.

**Backups**
- Daily full + hourly incremental → stored cross-region.  
- PITR using WAL shipping.  

**Monitoring**
- Replication lag, cache failover, latency p95/p99, backup validation, queue backlog.  

**Chaos Testing**
- Monthly regional failover simulation.  
- Quarterly full restore validation.

**Targets:**
| Metric | Target |
|--------|--------|
| SLA | 99.99% |
| RPO | ≤5 min |
| RTO | ≤30 min |
| Replica Lag | <10s |
| Cache Hit | ≥90% |

**Summary (spoken form):**  
> “We run multi-AZ active mode with a warm standby region for DR. DB and cache are replicated, health checks trigger auto-failover, and backups with WAL logs support PITR. DNS rerouting restores service in under 30 minutes with minimal data loss.”

---

## ✅ End of Day 1 — Key Takeaways

- Structured, quantifiable requirement clarification.  
- Cache-first, async-write architecture for low latency.  
- Partition vs sharding trade-off mastery.  
- HA/DR planning and operational awareness.  
