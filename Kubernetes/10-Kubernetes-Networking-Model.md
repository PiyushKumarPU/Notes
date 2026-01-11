# Kubernetes Networking Model (Deep Dive)

## Summary (Quick Revision)
- Kubernetes networking follows a **flat, non-NATed model**
- Every Pod gets a **unique IP** reachable from every other Pod
- Kubernetes does not provide networking itself; it relies on **CNI plugins**
- Services, DNS, and Ingress build on top of the core model
- Most networking issues come from **violating the core assumptions**

---

## 1. Why Kubernetes Networking Is Different

Traditional systems:
- Servers have fixed IPs
- Applications bind directly to hosts
- Networking is static

Kubernetes assumes:
- Pods are ephemeral
- Nodes can change
- Applications scale dynamically

Therefore, Kubernetes enforces a **simple but strict networking contract**.

Mental model:
> **Flat network, no NAT between Pods**

---

## 2. The Kubernetes Networking Requirements (Canonical Rules)

Kubernetes mandates:

1. Pods can communicate with **all other Pods** without NAT  
2. Nodes can communicate with **all Pods** without NAT  
3. Pods see their own IP as their identity  

These rules apply regardless of:
- Node
- Namespace
- Workload type

CNI plugins must implement this contract.

---

## 3. Pod Networking Internals

Each Pod:
- Gets its own network namespace
- Gets a unique IP
- Shares IP across containers in the Pod

Inside a Pod:
- Containers communicate via `localhost`
Across Pods:
- Communication uses Pod IPs

This consistency simplifies application design.

---

## 4. Container Network Interface (CNI)

Kubernetes delegates networking to CNI plugins.

Responsibilities:
- Assign Pod IPs
- Configure routing
- Enforce network policies (in some CNIs)

Popular CNIs:
- Calico
- Cilium
- Flannel
- Weave

Kubernetes itself is **network-agnostic**.

---

## 5. Service Networking (Overlay on Pod Networking)

Services provide:
- Stable virtual IPs
- Load balancing across Pods

Under the hood:
- kube-proxy programs iptables or IPVS
- Traffic is redirected to Pod IPs

Services **do not break** the core Pod networking model.

---

## 6. DNS & Service Discovery

Kubernetes DNS:
- Runs as CoreDNS
- Resolves Service and Pod names

Examples:
```
service.namespace.svc.cluster.local
pod-ip.namespace.pod.cluster.local
```

DNS is a **critical dependency** for microservices.

---

## 7. North–South vs East–West Traffic

### East–West (Internal)
- Pod to Pod
- Service to Service
- Uses ClusterIP and Pod IPs

### North–South (External)
- Client to cluster
- Uses LoadBalancer / NodePort / Ingress

Confusing these leads to misconfiguration.

---

## 8. Hands-on Lab (Foundational)

### Objective
Observe Pod-to-Pod networking behavior.

### Steps
1. Deploy two Pods on different nodes
2. Exec into one Pod
3. Ping the other Pod IP
4. Access via Service DNS

Key learning:
- No NAT
- Flat address space

---

## 9. Common Beginner Networking Mistakes

- Assuming localhost works across Pods
- Hardcoding Pod IPs
- Using NodePort for internal traffic
- Ignoring CNI limitations
- Mixing Service and Ingress responsibilities

---

## 10. Interview Q&A (High Quality)

**Q: Does Kubernetes NAT Pod traffic?**  
No, Pod-to-Pod traffic is non-NATed.

**Q: Who provides Pod networking?**  
CNI plugins.

**Q: Can Pods communicate across nodes?**  
Yes, by design.

---

## 11. Real Production Failure Scenario

### Scenario
Pods on different nodes could not communicate.

### Root Cause
- Misconfigured CNI plugin
- Incorrect Pod CIDR routing

### Impact
- Partial cluster outage
- Microservices failure

### Fix
- Correct CNI configuration
- Validate routing rules
- Add networking tests

---

## 12. Advanced Insight: Networking Is the Foundation

Everything builds on Pod networking:
- Services
- Ingress
- Network Policies
- Service Meshes

If the core model is broken, **nothing works reliably**.

---

## Final Takeaway

If you remember one rule:
> **Kubernetes networking assumes a flat, routable Pod network**

Respecting this model prevents **most Kubernetes networking disasters**.