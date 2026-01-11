# Health Checks & Probes in Kubernetes (Deep Dive)

## Summary (Quick Revision)
- Probes tell Kubernetes **when to route traffic and when to restart containers**
- **Liveness** restarts containers
- **Readiness** controls traffic flow
- **Startup** protects slow-starting applications
- Misconfigured probes are a **top cause of production outages**

---

## 1. Why Probes Exist (The Real Problem)

In traditional deployments:
- Load balancers guess health
- Processes may be running but unusable
- Manual restarts are common

Kubernetes needs **machine-readable health signals** to:
- Stop sending traffic to unhealthy Pods
- Restart broken containers automatically
- Handle slow startups gracefully

Mental model:
> **Probes translate application health into control-plane actions**

---

## 2. The Three Types of Probes

### Liveness Probe
Purpose:
- Detect broken containers
- Restart them automatically

If liveness probe fails:
➡ Container is restarted

Use when:
- Application can get stuck
- Restart is safe

---

### Readiness Probe
Purpose:
- Control traffic routing
- Signal when Pod is ready to serve requests

If readiness probe fails:
➡ Pod removed from Service endpoints (no restart)

Use when:
- Startup is slow
- Dependencies are required
- Traffic should be paused

---

### Startup Probe
Purpose:
- Delay liveness checks during startup
- Prevent premature restarts

Use when:
- Application takes long to initialize
- Liveness would fail during startup

---

## 3. Probe Execution Methods

Probes can be:

### HTTP GET
```yaml
httpGet:
  path: /health
  port: 8080
```

### TCP Socket
```yaml
tcpSocket:
  port: 3306
```

### Exec Command
```yaml
exec:
  command:
  - cat
  - /tmp/healthy
```

Choose based on application nature.

---

## 4. Probe Timing Parameters (Critical Nuance)

Key fields:
- `initialDelaySeconds`
- `periodSeconds`
- `timeoutSeconds`
- `failureThreshold`
- `successThreshold`

Misconfiguration causes:
- Flapping Pods
- Endless restarts
- Traffic black holes

---

## 5. How Probes Affect Services

Readiness probes:
- Directly control Service endpoints
- Enable zero-downtime deployments

Without readiness probes:
- Traffic may hit unhealthy Pods
- Rolling updates break

Probes are **deployment-critical**.

---

## 6. Hands-on Lab (Foundational)

### Objective
Observe probe behavior.

### Steps
1. Deploy app with readiness probe
2. Simulate dependency outage
3. Observe traffic stopping
4. Fix dependency
5. Observe traffic resuming

Repeat with liveness probe to observe restarts.

---

## 7. Common Beginner Probe Mistakes

- Using liveness for readiness checks
- Setting aggressive timeouts
- Probing deep business logic
- Ignoring startup time
- No probes at all

Probes should be:
- Simple
- Fast
- Reliable

---

## 8. Interview Q&A (High Quality)

**Q: Difference between liveness and readiness?**  
Restart vs traffic control.

**Q: When should startup probes be used?**  
For slow-starting applications.

**Q: Can readiness failures restart Pods?**  
No.

---

## 9. Real Production Failure Scenario

### Scenario
Deployment entered crash loop after release.

### Root Cause
- Liveness probe hit slow dependency
- Probe timed out during startup

### Impact
- Continuous restarts
- Service outage

### Fix
- Add startup probe
- Simplify liveness checks
- Increase initial delay

---

## 10. Advanced Insight: Health Endpoints Design

Best practices:
- Separate health endpoints from business logic
- Avoid database calls in liveness
- Use shallow checks for liveness
- Deeper checks for readiness

Health endpoints are **control signals**, not diagnostics.

---

## Final Takeaway

If you remember one rule:
> **Liveness restarts containers; readiness controls traffic**

Correct probe design is essential for **stable, zero-downtime Kubernetes deployments**.