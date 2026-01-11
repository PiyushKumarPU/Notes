# RBAC & Authentication in Kubernetes (Deep Dive)

## Summary (Quick Revision)
- Kubernetes security starts with **authentication** and **authorization**
- **Authentication answers: Who are you?**
- **RBAC answers: What are you allowed to do?**
- Kubernetes has **no users stored internally**; it trusts external identity
- Most security incidents are caused by **over-permissive RBAC**

---

## 1. Why RBAC Matters in Kubernetes

Kubernetes exposes a powerful API.

Without proper access control:
- Anyone can delete workloads
- Secrets can be exfiltrated
- Entire clusters can be compromised

Security principle:
> **Everything in Kubernetes is an API call**

RBAC controls every single one of them.

---

## 2. Authentication vs Authorization (Mental Model)

### Authentication
- Verifies identity
- Answers: *Who are you?*

### Authorization
- Evaluates permissions
- Answers: *What can you do?*

Flow:
1. User authenticates
2. API Server authorizes request
3. Request allowed or denied

Authentication without authorization is meaningless.

---

## 3. Authentication in Kubernetes

Kubernetes does NOT manage users.

Instead, it integrates with:
- Client certificates
- Tokens (ServiceAccounts)
- OIDC providers
- Cloud IAM (EKS/GKE/AKS)

The API Server trusts identity providers.

---

## 4. ServiceAccounts (In-Cluster Identity)

Pods authenticate using **ServiceAccounts**.

Characteristics:
- Namespaced
- Automatically mounted as tokens
- Used by controllers and applications

Mental model:
> **ServiceAccount = Pod identity**

Never run Pods with default ServiceAccount in production.

---

## 5. RBAC Core Objects

RBAC consists of:

### Role
- Permissions within a namespace

### ClusterRole
- Permissions across the cluster

### RoleBinding
- Binds Role to subject

### ClusterRoleBinding
- Binds ClusterRole cluster-wide

Subjects:
- Users
- Groups
- ServiceAccounts

---

## 6. RBAC Rules (How Permissions Are Defined)

Example:
```yaml
rules:
- apiGroups: [""]
  resources: ["pods"]
  verbs: ["get", "list"]
```

Rules define:
- API groups
- Resources
- Allowed verbs

RBAC is **explicit deny by default**.

---

## 7. Namespaced vs Cluster-Wide Permissions

Use:
- Role + RoleBinding → namespace scope
- ClusterRole + ClusterRoleBinding → cluster scope

Overusing ClusterRoleBindings is a common mistake.

Principle:
> **Least privilege always**

---

## 8. Hands-on Lab (Foundational)

### Objective
Observe RBAC enforcement.

### Steps
1. Create a ServiceAccount
2. Create Role with limited permissions
3. Bind Role to ServiceAccount
4. Attempt allowed and denied actions

Key learning:
- Permissions are explicit
- Denials are informative

---

## 9. Common Beginner RBAC Mistakes

- Using `cluster-admin`
- Binding ClusterRole to all namespaces
- Running Pods as default ServiceAccount
- Forgetting to rotate credentials
- Testing permissions manually without tools

---

## 10. Interview Q&A (High Quality)

**Q: Does Kubernetes store users?**  
No.

**Q: Difference between Role and ClusterRole?**  
Namespace vs cluster scope.

**Q: How does a Pod authenticate?**  
Using ServiceAccount token.

**Q: What is least privilege?**  
Grant only required permissions.

---

## 11. Real Production Failure Scenario

### Scenario
Application leaked Secrets across namespaces.

### Root Cause
- ClusterRoleBinding granted Secrets access cluster-wide

### Impact
- Sensitive data exposure
- Security incident

### Fix
- Replace ClusterRoleBinding with RoleBinding
- Restrict scope
- Audit RBAC regularly

---

## 12. Advanced Insight: RBAC Is Not Optional

RBAC protects:
- CI/CD pipelines
- Controllers
- Applications
- Humans

Clusters without strict RBAC are **already compromised**.

---

## Final Takeaway

If you remember one rule:
> **Authenticate identities, then authorize with least privilege**

Correct RBAC is foundational to **secure Kubernetes clusters**.