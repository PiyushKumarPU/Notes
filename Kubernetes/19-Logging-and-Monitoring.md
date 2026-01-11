# Logging & Monitoring in Kubernetes (Deep Dive)

## Summary (Quick Revision)
- Kubernetes is **distributed**; logs and metrics must be centralized
- Containers are ephemeral; **local logs are not reliable**
- Monitoring answers **what is happening**; logging answers **why**
- Kubernetes provides signals, not solutions — tooling completes the picture
- Most outages last longer due to **poor observability**, not bad code

---

## 1. Why Observability Is Critical in Kubernetes

In Kubernetes:
- Pods restart frequently
- Nodes come and go
- Services scale dynamically

Without observability:
- Failures are invisible
- Root cause analysis is guesswork
- MTTR increases dramatically

Mental model:
> **If you cannot observe it, you cannot operate it**

---

## 2. Logging vs Monitoring vs Tracing

### Logging
- Discrete events
- Error messages
- Stack traces

### Monitoring
- Metrics over time
- Resource usage
- Alerting

### Tracing
- Request flows
- Latency breakdowns

All three are required for production systems.

---

## 3. Kubernetes Logging Model

Kubernetes assumes:
- Containers log to stdout/stderr
- Logs are not managed by Kubernetes itself

Implications:
- Log retention is external
- Pod restarts lose local logs
- Centralization is mandatory

---

## 4. Log Collection Architecture

Typical flow:
1. Container writes logs to stdout
2. Node runtime stores logs
3. Log agent collects logs
4. Logs sent to centralized backend

Common agents:
- Fluent Bit
- Fluentd
- Vector

Never log only to files inside containers.

---

## 5. Monitoring Architecture

Kubernetes exposes metrics via:
- kubelet
- API Server
- cAdvisor
- Metrics Server

Monitoring stacks usually include:
- Prometheus
- Alertmanager
- Grafana

Kubernetes provides data; tools make it useful.

---

## 6. Key Metrics to Monitor (Practical)

### Cluster-Level
- Node CPU/memory
- Disk pressure
- API Server latency

### Workload-Level
- Pod restarts
- Resource usage vs limits
- HPA scaling events

### Application-Level
- Request rate
- Error rate
- Latency

Golden signals apply.

---

## 7. Hands-on Lab (Foundational)

### Objective
Observe logs and metrics.

### Steps
1. Deploy sample app
2. View logs via kubectl
3. Trigger error
4. Observe logs
5. Observe metrics via Metrics Server

Key learning:
- Logs are ephemeral
- Metrics persist

---

## 8. Common Beginner Observability Mistakes

- Logging to files in containers
- No centralized logging
- Monitoring only infrastructure
- No alerts
- Alert fatigue

Observability is a system, not a tool.

---

## 9. Alerts & SLOs (Production Reality)

Good alerts:
- Are actionable
- Indicate user impact
- Avoid noise

Bad alerts:
- Trigger on symptoms only
- Fire too frequently

Monitoring without alert discipline causes burnout.

---

## 10. Interview Q&A (High Quality)

**Q: Where should containers log?**  
stdout/stderr.

**Q: Does Kubernetes store logs?**  
No.

**Q: Difference between logging and monitoring?**  
Events vs metrics.

---

## 11. Real Production Failure Scenario

### Scenario
Outage took hours to diagnose.

### Root Cause
- No centralized logs
- Pod restarted multiple times

### Impact
- Extended downtime
- Customer impact

### Fix
- Centralized logging
- Retained metrics
- Proper alerts

---

## 12. Advanced Insight: Observability Is Design-Time

Logs and metrics must be:
- Designed into applications
- Tested before production
- Reviewed regularly

Retrofitting observability is expensive.

---

## Final Takeaway

If you remember one rule:
> **Logs explain failures; metrics detect them early**

Strong observability is essential for **operating Kubernetes reliably**.