# Scheduling, Requests & Limits (Deep Dive)

## Summary (Quick Revision)
- Kubernetes scheduling is **resource-aware**, not random
- **Requests** determine scheduling decisions
- **Limits** enforce runtime constraints
- Incorrect requests/limits cause **evictions, throttling, and outages**
- Most performance incidents stem from **missing or wrong resource definitions**

---

## 1. Why Scheduling Matters in Kubernetes

Kubernetes runs many workloads on shared nodes.

Without scheduling rules:
- One Pod can starve others
- Nodes can be overcommitted
- Critical services can be evicted

Scheduling ensures:
- Fair resource allocation
- Predictable performance
- Cluster stability

Mental model:
> **Scheduler places Pods; kubelet enforces limits**

---

## 2. The Kubernetes Scheduler (What It Does)

The scheduler:
- Watches for unscheduled Pods
- Finds suitable nodes
- Considers constraints and resources
- Binds Pod to a node

Scheduler does NOT:
- Start containers
- Monitor runtime usage
- Evict Pods (mostly)

---

## 3. Resource Requests (Scheduling Input)

### What Requests Are
Requests define:
- Minimum CPU and memory required

Example:
```yaml
resources:
  requests:
    cpu: "500m"
    memory: "512Mi"
```

Scheduler uses requests to:
- Decide Pod placement
- Prevent overcommit beyond capacity

Mental model:
> **Requests = reservation**

---

## 4. Resource Limits (Runtime Enforcement)

### What Limits Are
Limits define:
- Maximum CPU and memory a container can use

Example:
```yaml
resources:
  limits:
    cpu: "1"
    memory: "1Gi"
```

Mental model:
> **Limits = hard boundary**

Exceeding limits:
- CPU → throttling
- Memory → OOMKill

---

## 5. CPU vs Memory Behavior (Critical Difference)

### CPU
- Compressible
- Throttled when limit exceeded
- Pod usually survives

### Memory
- Non-compressible
- Exceeding limit triggers OOMKill
- Pod restarts

This asymmetry surprises many engineers.

---

## 6. QoS Classes (Hidden but Important)

Kubernetes assigns QoS class automatically:

| QoS Class | Requests | Limits |
|---------|----------|--------|
| Guaranteed | Equal | Equal |
| Burstable | Set | Different |
| BestEffort | None | None |

QoS affects:
- Eviction priority
- Node stability

Guaranteed Pods are evicted last.

---

## 7. Node Pressure & Evictions

When a node runs low on resources:
- kubelet evicts Pods
- Lower QoS Pods evicted first

Common triggers:
- Memory pressure
- Disk pressure

Evictions are **normal**, not failures.

---

## 8. Hands-on Lab (Foundational)

### Objective
Observe scheduling and eviction behavior.

### Steps
1. Deploy Pods with and without requests
2. Simulate memory pressure
3. Observe Pod eviction order
4. Adjust requests/limits

Key learning:
- Requests influence placement
- Limits influence survival

---

## 9. Common Beginner Mistakes

- No requests or limits
- CPU limits too low
- Memory limits too tight
- Assuming limits affect scheduling
- Ignoring QoS classes

Requests and limits are **not optional** in production.

---

## 10. Interview Q&A (High Quality)

**Q: What does the scheduler use to place Pods?**  
Requests and constraints.

**Q: What happens when a Pod exceeds memory limit?**  
OOMKill.

**Q: Difference between requests and limits?**  
Reservation vs enforcement.

**Q: What is Guaranteed QoS?**  
Requests equal limits.

---

## 11. Real Production Failure Scenario

### Scenario
Critical service restarted repeatedly.

### Root Cause
- Memory limit set too low
- JVM exceeded limit during GC

### Impact
- Crash loops
- Service instability

### Fix
- Increase memory limits
- Align JVM heap with limits
- Monitor usage

---

## 12. Advanced Insight: Overcommit Is a Feature

Kubernetes allows:
- CPU overcommit
- Memory overcommit (to a degree)

Benefits:
- Higher utilization

Risk:
- Evictions if misused

Overcommit requires **discipline**.

---

## Final Takeaway

If you remember one rule:
> **Requests decide where Pods run; limits decide how they survive**

Correct resource configuration is essential for **stable, predictable Kubernetes clusters**.