# Pod Security & Runtime Security (Deep Dive)

## Summary (Quick Revision)
- Kubernetes security does not stop at RBAC; **workloads must be secured at runtime**
- **Pod Security** controls what Pods are allowed to do
- **Runtime security** detects and prevents malicious behavior after deployment
- Most container breakouts occur due to **excessive privileges**
- Defense-in-depth is mandatory

---

## 1. Why Pod & Runtime Security Matter

Even with perfect RBAC:
- A compromised Pod can attack the cluster
- A vulnerable container can escape its sandbox
- Supply-chain attacks execute at runtime

Security must answer:
> “What can this Pod do *after* it starts?”

RBAC controls *who can deploy*.  
Pod & runtime security control *what runs and how it behaves*.

---

## 2. Pod Security Mental Model

Pod security governs:
- Linux privileges
- Filesystem access
- Kernel attack surface
- Capability usage

Mental model:
> **Least privilege for containers, enforced by the platform**

---

## 3. Pod Security Standards (PSS)

Kubernetes defines three Pod Security Standards:

### 1. Privileged
- Unrestricted
- Full host access
- Rarely acceptable

### 2. Baseline
- Prevents common privilege escalations
- Allows most workloads

### 3. Restricted (Recommended)
- Strong hardening
- Enforces non-root, minimal capabilities

Restricted is the **production default**.

---

## 4. Pod Security Admission (PSA)

PSA enforces Pod Security Standards.

Modes:
- Enforce → blocks violating Pods
- Audit → logs violations
- Warn → warns users

Configured at namespace level via labels.

PSA replaced deprecated PodSecurityPolicies.

---

## 5. Security Contexts (Practical Controls)

Security contexts define container-level restrictions.

Common fields:
- `runAsNonRoot`
- `readOnlyRootFilesystem`
- `allowPrivilegeEscalation`
- `capabilities`

Example:
```yaml
securityContext:
  runAsNonRoot: true
  readOnlyRootFilesystem: true
  allowPrivilegeEscalation: false
```

These settings drastically reduce attack surface.

---

## 6. Privileged Containers (High Risk)

Privileged Pods:
- Can access host devices
- Can escape containers
- Bypass isolation

Use ONLY for:
- CNI plugins
- Storage drivers
- Node-level agents

Never allow privileged Pods in application namespaces.

---

## 7. Runtime Security (After the Pod Starts)

Runtime security focuses on:
- Process execution
- File access
- Network behavior

Because attacks happen **after deployment**.

Static scanning is not enough.

---

## 8. Runtime Security Tools

Common approaches:
- Syscall monitoring
- Behavioral baselines
- eBPF-based detection

Popular tools:
- Falco
- Cilium Tetragon
- Cloud-native runtime protections

Runtime security detects:
- Unexpected shells
- Suspicious file access
- Privilege escalation attempts

---

## 9. Hands-on Lab (Foundational)

### Objective
Reduce Pod attack surface.

### Steps
1. Deploy a Pod with default privileges
2. Harden securityContext
3. Attempt forbidden actions
4. Observe denials

Key learning:
- Restrictions are enforced automatically
- Security is proactive

---

## 10. Common Beginner Security Mistakes

- Running containers as root
- Allowing privileged Pods
- Writable root filesystem
- Ignoring runtime detection
- Assuming images are safe

Containers are **not VMs**.

---

## 11. Interview Q&A (High Quality)

**Q: What replaced PodSecurityPolicy?**  
Pod Security Admission.

**Q: What is runtime security?**  
Monitoring and enforcement after deployment.

**Q: Why avoid privileged containers?**  
They break isolation.

---

## 12. Real Production Failure Scenario

### Scenario
Attacker gained node access via compromised Pod.

### Root Cause
- Container ran as root
- Privileged escalation allowed

### Impact
- Node compromise
- Lateral movement

### Fix
- Enforce restricted Pod Security
- Non-root containers
- Runtime detection

---

## 13. Advanced Insight: Defense in Depth

Pod security + runtime security + RBAC + network policies =
**layered protection**.

No single control is sufficient.

---

## Final Takeaway

If you remember one rule:
> **Assume containers can be compromised and limit their power accordingly**

Pod and runtime security are essential for **production Kubernetes clusters**.