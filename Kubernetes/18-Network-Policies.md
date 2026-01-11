# Network Policies in Kubernetes (Deep Dive)

## Summary (Quick Revision)
- **NetworkPolicies** control Pod-to-Pod and Pod-to-external traffic
- Kubernetes networking is **allow-all by default**
- NetworkPolicies provide **L3/L4 firewalling** at the Pod level
- Policies are **additive** and **deny-by-default once applied**
- Most security breaches involve **missing or misunderstood NetworkPolicies**

---

## 1. Why Network Policies Exist

By default, Kubernetes allows:
- Any Pod to talk to any Pod
- Any Pod to talk to the outside world

This is convenient but dangerous.

Real systems need:
- Microservice isolation
- Zero-trust networking
- Blast-radius reduction

Mental model:
> **NetworkPolicies are Kubernetes firewalls for Pods**

---

## 2. Network Policy Mental Model

Key ideas:
- Policies select **Pods**, not Services
- Policies define **allowed traffic**
- Anything not explicitly allowed is denied (once a policy applies)

Important nuance:
- Policies are **namespace-scoped**
- They do nothing unless a **CNI supports them**

---

## 3. Prerequisite: CNI Support

NetworkPolicies require:
- Calico
- Cilium
- Weave
- Other policy-aware CNIs

They do NOT work with:
- Basic Flannel (without extensions)

Always verify CNI capability first.

---

## 4. Anatomy of a NetworkPolicy

Basic structure:
```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-app
spec:
  podSelector:
    matchLabels:
      app: backend
  policyTypes:
  - Ingress
  - Egress
```

This selects Pods but allows nothing yet.

---

## 5. Ingress Rules (Incoming Traffic)

Example:
```yaml
ingress:
- from:
  - podSelector:
      matchLabels:
        app: frontend
  ports:
  - protocol: TCP
    port: 80
```

Meaning:
- Only frontend Pods can reach backend Pods on port 80

Everything else is denied.

---

## 6. Egress Rules (Outgoing Traffic)

Example:
```yaml
egress:
- to:
  - ipBlock:
      cidr: 10.0.0.0/8
  ports:
  - protocol: TCP
    port: 443
```

Meaning:
- Backend Pods can only talk to internal services over HTTPS

Without egress rules:
- All outbound traffic may be blocked

---

## 7. Default Deny Patterns (Critical)

To implement zero-trust:
1. Create default deny policy
2. Add explicit allow policies

Example:
```yaml
podSelector: {}
policyTypes:
- Ingress
- Egress
```

This affects **all Pods in the namespace**.

---

## 8. Hands-on Lab (Foundational)

### Objective
Implement Pod isolation.

### Steps
1. Deploy frontend and backend Pods
2. Verify connectivity
3. Apply default deny policy
4. Observe failures
5. Add allow rules
6. Restore connectivity

Key learning:
- Policies are incremental
- Order does not matter

---

## 9. Common Beginner NetworkPolicy Mistakes

- Assuming policies work without CNI support
- Forgetting DNS egress rules
- Blocking kube-system traffic
- Thinking Services are filtered
- Applying policies cluster-wide accidentally

NetworkPolicies filter **Pod traffic**, not Services.

---

## 10. Interview Q&A (High Quality)

**Q: Are NetworkPolicies deny-by-default?**  
Only after a policy selects a Pod.

**Q: Do NetworkPolicies work without CNI support?**  
No.

**Q: Can NetworkPolicies filter traffic by Service?**  
No, only by Pod/IP/namespace.

---

## 11. Real Production Failure Scenario

### Scenario
Application lost access to database after security hardening.

### Root Cause
- Default deny egress policy applied
- DNS traffic not allowed

### Impact
- Application startup failures
- Service outage

### Fix
- Explicit DNS egress rules
- Test policies in staging
- Document dependencies

---

## 12. Advanced Insight: NetworkPolicies Are L3/L4

They cannot:
- Inspect HTTP paths
- Enforce TLS
- Perform auth

For L7 control, use:
- Service Mesh
- API Gateways

---

## Final Takeaway

If you remember one rule:
> **Once you apply a NetworkPolicy, you must explicitly allow all required traffic**

Correct NetworkPolicies dramatically improve **Kubernetes security posture**.