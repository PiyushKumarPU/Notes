# Ingress & Ingress Controllers (Deep Dive)

## Summary (Quick Revision)
- **Ingress** defines HTTP/HTTPS routing rules into the cluster
- **Ingress Controller** is the actual component that enforces those rules
- Ingress works at **Layer 7 (HTTP)**, unlike Services
- TLS termination, host/path routing live at Ingress
- Most Ingress outages occur due to **missing controllers or mis-scoped rules**

---

## 1. Why Ingress Exists (The Real Problem)

Before Ingress, exposing apps meant:
- Many LoadBalancer Services
- High cloud cost
- No centralized TLS or routing
- Manual proxy configuration

Kubernetes introduced Ingress to:
- Centralize entry traffic
- Enable host/path-based routing
- Standardize external access

Mental model:
> **Service exposes Pods; Ingress exposes Services**

---

## 2. What Ingress Is (and Is NOT)

### Ingress IS
- A **set of routing rules**
- An API object
- A declaration of desired external access

### Ingress IS NOT
- ❌ A load balancer by itself
- ❌ A proxy
- ❌ A controller

Ingress without a controller does **nothing**.

---

## 3. Ingress Controller (Critical Component)

An Ingress Controller:
- Watches Ingress resources
- Configures a real proxy/load balancer
- Routes traffic to Services

Popular controllers:
- NGINX Ingress Controller
- Traefik
- HAProxy
- Cloud-native controllers (ALB, GCE)

Mental model:
> **Ingress = config, Controller = engine**

---

## 4. Basic Ingress Flow (End-to-End)

Flow:
1. External client sends HTTP request
2. Load balancer forwards to Ingress Controller
3. Controller evaluates host/path rules
4. Traffic routed to Service
5. Service routes to Pods

Ingress never talks directly to Pods.

---

## 5. Ingress Resource Anatomy

Example:
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: app-ingress
spec:
  rules:
  - host: app.example.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: app-service
            port:
              number: 80
```

Key elements:
- Host
- Path
- Backend Service

---

## 6. TLS & HTTPS Termination

Ingress supports:
- TLS termination
- Multiple certificates
- SNI-based routing

Example:
```yaml
tls:
- hosts:
  - app.example.com
  secretName: app-tls
```

TLS secrets must exist **before** Ingress works.

---

## 7. Ingress vs Service Types

| Feature | Service (LB) | Ingress |
|------|-------------|--------|
| Layer | L4 | L7 |
| TLS | Limited | Yes |
| Routing | No | Yes |
| Cost | High | Lower |
| Flexibility | Low | High |

Ingress is usually preferred for HTTP workloads.

---

## 8. Hands-on Lab (Foundational)

### Objective
Understand routing behavior.

### Steps
1. Deploy two Services
2. Create single Ingress with two paths
3. Access via browser or curl
4. Modify rules and observe behavior

Key learning:
- One entry point
- Multiple backends

---

## 9. Common Beginner Ingress Mistakes

- Forgetting to install an Ingress Controller
- Assuming Ingress works like Service
- Incorrect host/path matching
- TLS secret in wrong namespace
- Exposing sensitive services unintentionally

---

## 10. Interview Q&A (High Quality)

**Q: Difference between Ingress and Ingress Controller?**  
Ingress is config; controller enforces it.

**Q: Can Ingress work without a controller?**  
No.

**Q: Does Ingress route directly to Pods?**  
No, only to Services.

---

## 11. Real Production Failure Scenario

### Scenario
Ingress deployed but traffic returned 404.

### Root Cause
- Ingress Controller not installed
- Ingress resource existed only as config

### Impact
- External traffic outage
- Confusing debugging

### Fix
- Install controller
- Validate controller logs
- Add startup checks

---

## 12. Advanced Insight: Ingress Is a Contract

Ingress defines:
- Desired routing behavior

Controller decides:
- How to implement it

Different controllers:
- Support different annotations
- Have different performance profiles

Portability requires discipline.

---

## Final Takeaway

If you remember one rule:
> **Ingress is useless without an Ingress Controller**

Understanding this separation avoids **most Kubernetes ingress failures**.