# 🧭 **Day 1: System Design Fundamentals**

---

## 🎯 **Objective**

Understand how to:

* Approach an HLD interview systematically
* Clarify requirements
* Decompose the system
* Handle trade-offs
* Evaluate your design with metrics

---

## 🧩 **1. What is an HLD Interview Testing?**

**Core Evaluation Areas:**

* System decomposition (how you break the problem)
* Scalability & reliability design
* Communication between components (sync/async)
* Trade-off reasoning
* Clarity & communication

**1-Line Summary:**

> “They test if you can design a system that scales, stays consistent, and you can clearly explain your reasoning.”

---

## 🧠 **2. First Step — Clarifying Requirements**

Before designing, ask:

1. QPS (Queries per second)
2. Total & active users
3. Read/write ratio
4. Consistency vs availability
5. Sync vs async communication
6. Latency & SLA expectations

**Goal:**
Get clarity on system scale and priorities before picking tech.

---

## ⚙️ **3. Estimation Step (If Requirements Not Given)**

Make assumptions and estimate:

* **Bandwidth:** payload size × QPS
* **Storage:** daily + monthly data growth
* **Throughput:** expected peak load
* **Data retention:** backup and archival plan

**Purpose:**
Helps justify why you picked a certain DB, cache, or architecture.

---

## 🧱 **4. Breaking Down the System**

Start from the **data layer**, then move outward.

| Layer             | Purpose                          | Example                                         |
| ----------------- | -------------------------------- | ----------------------------------------------- |
| **Database**      | Store transactions/data          | Use partitioning or sharding for large datasets |
| **Cache**         | Reduce read latency              | Redis / Memcached                               |
| **Gateway**       | Handle auth, routing, rate limit | NGINX / Kong / API Gateway                      |
| **Load Balancer** | Distribute traffic, ensure HA    | ALB / NLB                                       |
| **Async Queue**   | Decouple services                | Kafka / RabbitMQ                                |

**1-Line Summary:**

> “I start from the data — scale it, secure it, and build services around it.”

---

## ⚖️ **5. Non-Functional Requirements (NFRs)**

| NFR                 | Design Consideration                             |
| ------------------- | ------------------------------------------------ |
| **Scalability**     | Horizontal scaling, stateless services           |
| **Availability**    | Multi-AZ deployment, health checks               |
| **Consistency**     | Strong for core, eventual for peripheral systems |
| **Security**        | Encryption, auth, masking                        |
| **Performance**     | Caching, DB indexing, async jobs                 |
| **Observability**   | Logging, metrics, tracing                        |
| **Fault Tolerance** | Replication, retries, failover                   |

**Acronym to Remember:** 🧩 **SCALERS**
→ *Scalability, Consistency, Availability, Latency, Extensibility, Reliability, Security*

---

## ⚔️ **6. Handling Trade-offs**

> “Every design is about balancing what matters most.”

| Trade-off                       | Approach                                                         |
| ------------------------------- | ---------------------------------------------------------------- |
| **Consistency vs Availability** | Strong for payments, eventual for analytics                      |
| **Latency vs Reliability**      | Lower latency for user flows, higher reliability for back-office |
| **Cost vs Scalability**         | Auto-scale critical, batch non-critical                          |
| **Simplicity vs Flexibility**   | Start simple, modularize later                                   |
| **Durability vs Performance**   | Always prioritize durability for financial data                  |

**Pro Tip:**
Always **mention mitigation** — e.g., “Using queues to make async flow reliable.”

---

## 🔄 **7. Sync vs Async Trade-off Example**

| Flow Type                              | Mode         | Reason                      |
| -------------------------------------- | ------------ | --------------------------- |
| **Payment initiation / ledger update** | Synchronous  | Needs immediate consistency |
| **Notification / report generation**   | Asynchronous | Can tolerate slight delay   |

**1-Line Summary:**

> “If it affects money or user trust, it’s sync; everything else can be async.”

---

## 📊 **8. Evaluating Your Design**

> “A good design is observable, measurable, and correct.”

| Metric Category   | Key Metrics                                            |
| ----------------- | ------------------------------------------------------ |
| **Performance**   | Latency (p95/p99), QPS, error rate                     |
| **Reliability**   | Uptime %, MTTR, replication lag                        |
| **Observability** | Logs, metrics, traces                                  |
| **Fintech KPIs**  | Payment success %, ledger accuracy, duplicate txn rate |

---

## 🧩 **9. Diagram (Generic HLD Skeleton)**

```
          ┌────────────┐
Client →  │ API Gateway│
          └────┬───────┘
               │
         ┌─────▼──────┐
         │Load Balancer│
         └─────┬──────┘
               │
     ┌─────────▼──────────┐
     │  Application Layer  │
     │(Stateless Services) │
     └─────────┬──────────┘
               │
 ┌─────────────▼───────────────┐
 │ Cache Layer (Redis)         │
 └─────────────┬───────────────┘
               │
 ┌─────────────▼───────────────┐
 │ Database (Sharded / Replica)│
 └─────────────┬───────────────┘
               │
 ┌─────────────▼───────────────┐
 │ Message Queue (Kafka)       │
 └─────────────────────────────┘
```

---

## 🧠 **Day 1 Summary — Key Phrases to Use**

* “Let’s clarify scale and consistency first.”
* “I’ll start from the data layer — it dictates the rest of the design.”
* “In fintech, correctness outweighs latency.”
* “We can mitigate that trade-off using queues or caching.”
* “Success for me means low latency, high reliability, and no data loss.”

---
