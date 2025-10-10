---

# 🧠 High-Level Design — Trade-offs Reference

## 📘 What Are Trade-offs?

Every architectural decision in system design involves balancing competing priorities like **scalability, consistency, latency, reliability, and cost**.
Understanding these trade-offs is what differentiates great system designers from average ones.

---

## ⚖️ Core HLD Trade-offs

### 1. Consistency vs Availability (CAP Theorem)

* **Trade-off:** During network partitions, you can’t have both.
* **CP systems:** Prefer **consistency** (e.g., Banking, Ledger)
* **AP systems:** Prefer **availability** (e.g., Chat apps)
* **Example:** Payment gateway → Choose *consistency* over *availability*

---

### 2. Latency vs Throughput

* **Trade-off:** High throughput (batching) increases latency for individual requests.
* **Example:** Kafka batching improves throughput but delays delivery.
* **Fintech:** Ledger writes prioritize correctness (low throughput but low latency for core ops).

---

### 3. Read vs Write Optimization

* **Trade-off:** Denormalization/caching improves reads but complicates writes.
* **Read-heavy system:** Use CQRS, replicas, caching.
* **Write-heavy system:** Keep normalized schema, reduce replication lag.
* **Fintech:** Payment summary (read-optimized), Ledger update (write-optimized).

---

### 4. Simplicity vs Scalability

* **Trade-off:**

    * *Monolith:* Easier to develop/debug, limited scalability.
    * *Microservices:* Scalable but complex (distributed coordination, observability).
* **Example:** Start with monolith, evolve to microservices for PSP, Reconciliation, Ledger services.

---

### 5. Strong Consistency vs Eventual Consistency

* **Trade-off:** Eventual consistency improves performance and availability.
* **Strong consistency:** Used in core ledger, transactions.
* **Eventual consistency:** Used in reconciliation, notifications, analytics.
* **Example:** Ledger = ACID; Notification = eventual consistency via Kafka.

---

### 6. Durability vs Performance

* **Trade-off:** More durable (sync to disk/log) = slower performance.
* **Example:** Kafka (durable, slower) vs Redis (fast, volatile)
* **Fintech:** Prefer durability — no money loss on crash.

---

### 7. Sharding vs Complexity

* **Trade-off:**

    * *Sharding:* Improves scalability.
    * *Downside:* Complex rebalancing, cross-shard queries.
* **Example:** Shard ledger by user_id; adds rebalancing complexity but scales well.

---

### 8. Cost vs Performance

* **Trade-off:** Caching, replication, and redundancy improve performance but cost more.
* **Example:** Multi-region deployment → better latency, higher infra cost.
* **Fintech:** Use redundancy for core systems, cost-optimized infra for non-critical components.

---

### 9. Push vs Pull

* **Push:** Real-time, but infra-heavy.
* **Pull:** Scalable, but less responsive.
* **Example:**

    * Push = Mobile payment notifications
    * Pull = Transaction history page

---

### 10. Complexity vs Maintainability

* **Trade-off:**

    * Complex distributed algorithms = scalability
    * Simpler architecture = easier maintenance
* **Example:** Use Redis pub/sub for small-scale real-time updates instead of Kafka.

---

## 🧩 Fintech: Payment System Trade-offs

| Concern                         | Trade-off                                 | Explanation                                     | Decision                   |
| ------------------------------- | ----------------------------------------- | ----------------------------------------------- | -------------------------- |
| **Consistency vs Availability** | Prefer **Consistency**                    | Never allow duplicate/incorrect transactions    | Use ACID DB                |
| **Latency vs Reliability**      | Reliability > Latency                     | Payments can be slightly slow, but must succeed | Retry, Idempotency         |
| **Durability vs Performance**   | Durability > Speed                        | Transaction logs must survive crashes           | Write-ahead log            |
| **Atomicity vs Scalability**    | Split atomicity into steps                | Avoid distributed transactions                  | Saga pattern               |
| **Sync vs Async**               | Sync for initiation, Async for settlement | Good UX with background processing              | Use Kafka queues           |
| **Cost vs Redundancy**          | Redundancy increases cost                 | Active-active clusters for HA                   | Accept cost for uptime     |
| **Cache vs Consistency**        | Cache speeds up lookups                   | Risk of stale data                              | TTL or write-through cache |
| **Security vs Latency**         | Encryption adds latency                   | Required for compliance                         | AES + tokenization         |
| **Auditability vs Performance** | Append-only logs slower                   | Required for compliance                         | Immutable ledger           |
| **Complexity vs Debuggability** | More microservices → harder to trace      | Use centralized observability                   | Jaeger / OpenTelemetry     |

---

## 🧱 Component-Wise Trade-offs in Payment System

### 🔹 API Gateway / Orchestrator

* **Stateless vs Stateful:** Stateless scales, but tracking retries is harder.
* **Sync vs Async:** Sync gives fast response; async adds reliability.
* **Mitigation:** Use idempotency keys + distributed store (Redis/DynamoDB).

---

### 🔹 Ledger Service

* **ACID vs Eventual consistency:** Always ACID.
* **Mutable vs Append-only:** Append-only ensures auditability.
* **Mitigation:** Optimize reads via materialized views.

---

### 🔹 Message Queue (Kafka/SQS)

* **At-least-once vs Exactly-once:** Choose at-least-once; handle idempotency.
* **Ordering vs Partitioning:** Partitioning scales; strict order adds complexity.
* **Mitigation:** Use transaction IDs for deduplication.

---

### 🔹 Cache (Redis)

* **Speed vs Durability:** Redis fast, volatile.
* **Stale data risk:** Accept stale reads; use short TTLs or invalidation events.
* **Mitigation:** Do not cache monetary balances.

---

### 🔹 Reconciliation Service

* **Real-time vs Batch:** Batch cheaper, real-time costlier.
* **Consistency vs Cost:** Eventual consistency is acceptable post-transaction.
* **Mitigation:** Nightly batch + async correction pipeline.

---

### 🔹 High Availability

* **Active-active vs Active-passive:** Active-active = zero downtime, higher cost.
* **Multi-region vs Single region:** Multi-region improves fault tolerance but adds compliance complexity.

---

## 🔒 Security Trade-offs (Fintech-Specific)

| Aspect                             | Trade-off                                  | Example                           |
| ---------------------------------- | ------------------------------------------ | --------------------------------- |
| **Encryption vs Performance**      | Stronger encryption = more CPU cost        | AES-256 over AES-128              |
| **Tokenization vs Direct storage** | Extra lookup latency but safer             | Store tokens, not PANs            |
| **Auth strictness vs UX**          | MFA adds friction but reduces fraud        | Required for large-value payments |
| **Logging vs Privacy**             | More logs help debugging but risk exposure | Mask sensitive data               |

---

## 🧩 HLD Trade-off Template for Interview Answers

Use this 4-step structure every time you justify a design choice:

```
**Goal:** What am I optimizing for?
**Options:** What alternatives did I consider?
**Trade-off:** What’s the downside of my choice?
**Mitigation:** How can I minimize that downside?
```

**Example:**

> Goal: Maintain correctness in ledger.
> Options: Eventual vs Strong consistency.
> Trade-off: Strong consistency slows down writes.
> Mitigation: Use batching and async settlement to improve throughput.

---

## 🧭 Trade-off Mental Map

```
Latency  ←→  Durability
Availability  ←→  Consistency
Simplicity  ←→  Scalability
Cost  ←→  Reliability
Sync UX  ←→  Async Processing
```

In every HLD problem, you’re adjusting these sliders to match the system’s goals.

---

## 🧮 Example Interview Summary — Payment System

> “In a payment system, I’d prioritize **consistency, durability, and auditability** over latency.
> The core ledger would be **ACID and append-only**, ensuring correctness and traceability.
> Supporting systems like reconciliation and notifications can be **eventually consistent** for better scalability.
> The orchestrator layer is **stateless** for horizontal scaling, and I’ll use **idempotency keys** for reliability.
> These trade-offs ensure correctness and compliance — which are more critical than raw speed in fintech.”

---

✅ **Pro Tip:**
In your interview, **don’t just list trade-offs** — **connect them to business context**:

* Payments → Consistency first
* Social media → Latency first
* Search → Scalability first
