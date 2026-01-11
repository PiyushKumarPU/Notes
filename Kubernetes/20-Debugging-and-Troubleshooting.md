# Debugging & Troubleshooting in Kubernetes (Deep Dive)

## Summary (Quick Revision)
- Debugging Kubernetes is about **systematic isolation**, not guesswork
- Most issues fall into **config, resource, networking, or platform** categories
- Kubernetes failures are often **symptoms, not root causes**
- kubectl is the primary debugging tool
- Fast recovery matters more than perfect diagnosis

---

## 1. Why Debugging Kubernetes Is Different

Traditional debugging:
- Single host
- Long-lived processes
- Static configuration

Kubernetes debugging:
- Distributed systems
- Ephemeral Pods
- Declarative state
- Multiple abstraction layers

Mental model:
> **Debug the control plane decisions, not just containers**

---

## 2. A Practical Debugging Framework

Always debug top-down:

1. Is the **object created**?
2. Is it **scheduled**?
3. Is it **running**?
4. Is it **ready**?
5. Is traffic reaching it?
6. Is the application healthy?

Skipping steps wastes time.

---

## 3. kubectl: Your Primary Tool

Key commands:
```bash
kubectl get
kubectl describe
kubectl logs
kubectl exec
kubectl events
```

Golden rule:
> **Describe before logs**

Events often explain *why* something failed.

---

## 4. Pod-Level Debugging

Common Pod states:
- Pending → scheduling issue
- CrashLoopBackOff → runtime failure
- ImagePullBackOff → registry/auth issue

Debug flow:
1. Describe Pod
2. Check events
3. Inspect logs
4. Exec if running

---

## 5. Debugging Scheduling Issues

Symptoms:
- Pod stuck in Pending

Common causes:
- Insufficient resources
- Node selectors/taints
- PVC binding failures

Scheduler errors are explicit — read them.

---

## 6. Networking Debugging

Common symptoms:
- Timeouts
- Connection refused
- DNS failures

Steps:
1. Check Service
2. Check Endpoints
3. Test DNS
4. Verify NetworkPolicies
5. Check CNI health

Never assume networking works.

---

## 7. Debugging Configuration Issues

Symptoms:
- App starts but behaves incorrectly

Common causes:
- Wrong ConfigMap values
- Missing Secrets
- Stale environment variables

Verify:
- Mounted files
- Env vars
- Reload behavior

---

## 8. Debugging Resource Issues

Symptoms:
- Restarts
- Slowness
- Evictions

Check:
- Requests vs limits
- OOMKilled events
- Node pressure

Resource issues are the **top cause of instability**.

---

## 9. Hands-on Lab (Foundational)

### Objective
Practice systematic debugging.

### Scenario
Application fails after deployment.

### Steps
1. Inspect Deployment
2. Describe Pod
3. Check events
4. Review logs
5. Identify root cause

Key learning:
- Method beats intuition

---

## 10. Common Beginner Debugging Mistakes

- Jumping straight to logs
- Ignoring events
- Debugging Pods instead of controllers
- Restarting blindly
- Changing multiple things at once

Discipline reduces MTTR.

---

## 11. Interview Q&A (High Quality)

**Q: First step when a Pod is failing?**  
kubectl describe pod.

**Q: What does CrashLoopBackOff mean?**  
Repeated container crashes.

**Q: Where do you find scheduling errors?**  
Pod events.

---

## 12. Real Production Failure Scenario

### Scenario
Service intermittently unavailable.

### Root Cause
- Readiness probe failing under load
- Pods removed from Service endpoints

### Impact
- Traffic drops
- Hard-to-diagnose flakiness

### Fix
- Tune probes
- Add metrics
- Load test probes

---

## 13. Advanced Insight: Debugging Is Observability-Driven

Good debugging relies on:
- Logs
- Metrics
- Events

Without observability:
- Debugging becomes guesswork

---

## Final Takeaway

If you remember one rule:
> **Debug Kubernetes layer by layer, starting from the control plane**

A structured approach turns complex outages into **manageable problems**.