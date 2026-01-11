# DNS & Service Discovery in Kubernetes (Deep Dive)

## Summary (Quick Revision)
- Kubernetes provides **built-in DNS-based service discovery**
- **CoreDNS** is the default DNS server
- Services get **stable DNS names**, Pods generally should not be addressed directly
- DNS works hand-in-hand with **Services and Endpoints**
- Many production outages come from **DNS misunderstandings or misconfigurations**

---

## 1. Why DNS Matters in Kubernetes

In traditional systems:
- Hosts have fixed IPs
- Applications hardcode addresses

In Kubernetes:
- Pods are ephemeral
- IPs change frequently
- Scaling is dynamic

Therefore:
> **Names must be stable even when IPs are not**

DNS is the glue that makes Kubernetes networking usable.

---

## 2. Kubernetes DNS Mental Model

Kubernetes DNS provides:
- Name → Service IP resolution
- Optional Pod DNS resolution
- Namespace-aware naming

Mental model:
> **Applications talk to Services by name, never to Pods by IP**

---

## 3. CoreDNS (The DNS Engine)

CoreDNS:
- Runs as a Deployment in `kube-system`
- Watches Kubernetes API
- Automatically updates records

If CoreDNS is down:
- Service discovery breaks
- Applications appear “unreachable”

DNS is a **critical control-plane dependency**.

---

## 4. Service DNS Names (Most Important)

For a Service:
```
<service>.<namespace>.svc.cluster.local
```

Examples:
```
backend.default.svc.cluster.local
db.prod.svc.cluster.local
```

Inside the same namespace:
```
backend
```

This simplicity is intentional.

---

## 5. Pod DNS Names (Advanced & Limited)

Pods may get DNS entries:
```
<pod-ip>.<namespace>.pod.cluster.local
```

Important:
- Pod DNS is rarely used
- Pod IPs are ephemeral
- Avoid Pod-based addressing

Exception:
- StatefulSets with headless Services

---

## 6. Headless Services & DNS

Headless Service:
```yaml
clusterIP: None
```

Behavior:
- No virtual IP
- DNS returns **Pod IPs directly**
- Used by StatefulSets

This enables:
- Direct Pod-to-Pod discovery
- Leader-based systems

---

## 7. DNS Resolution Flow (Internals)

Flow:
1. Application makes DNS query
2. Query hits CoreDNS
3. CoreDNS checks Kubernetes API
4. Service IPs or Pod IPs returned
5. Client connects

DNS responses update dynamically as Pods change.

---

## 8. Hands-on Lab (Foundational)

### Objective
Observe DNS behavior.

### Steps
1. Create a Service
2. Exec into a Pod
3. Run `nslookup service-name`
4. Scale Pods up/down
5. Observe DNS consistency

Key learning:
- DNS name stays stable
- Backend IPs change

---

## 9. Common Beginner DNS Mistakes

- Hardcoding Pod IPs
- Using `localhost` across Pods
- Assuming DNS issues are app bugs
- Forgetting namespace in DNS name
- Overusing headless Services

---

## 10. Interview Q&A (High Quality)

**Q: What provides DNS in Kubernetes?**  
CoreDNS.

**Q: Why use Service DNS instead of Pod IPs?**  
Stability.

**Q: When are headless Services used?**  
For StatefulSets and direct Pod discovery.

---

## 11. Real Production Failure Scenario

### Scenario
Microservices randomly failed to connect.

### Root Cause
- CoreDNS pods evicted due to memory pressure
- No resource limits set

### Impact
- Intermittent outages
- Hard-to-debug failures

### Fix
- Set resource requests/limits for CoreDNS
- Monitor DNS latency
- Protect kube-system components

---

## 12. Advanced Insight: DNS Is a Shared Dependency

DNS affects:
- Services
- Ingress
- Service Meshes
- External integrations

Treat DNS as **production-critical infrastructure**.

---

## Final Takeaway

If you remember one rule:
> **Always communicate via Service DNS, never via Pod IPs**

Correct DNS usage is foundational for **reliable Kubernetes systems**.