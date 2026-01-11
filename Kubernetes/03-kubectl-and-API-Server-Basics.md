# kubectl & API Server Basics (Deep Dive)

## Summary (Quick Revision)
- `kubectl` is a **client**, not Kubernetes itself
- The **API Server** is the front door to the Kubernetes control plane
- Every Kubernetes action is an **API request**
- YAML files are just **API payloads**
- Most Kubernetes misunderstandings come from ignoring the API-centric design

---

## 1. Why kubectl and API Server Matter

Kubernetes is not a CLI-driven system.

It is an **API-driven distributed system**.

Key truth:
> If you understand the API Server, you understand Kubernetes.

`kubectl` is just one of many clients (others include controllers, schedulers, operators, and UIs).

---

## 2. kubectl Mental Model

### What kubectl IS
- A command-line **API client**
- A convenience wrapper over HTTP requests
- A YAML sender and response printer

### What kubectl IS NOT
- ❌ Not the Kubernetes engine
- ❌ Not a state store
- ❌ Not a scheduler

Mental model:
- kubectl = `curl` for Kubernetes APIs (with auth + formatting)

---

## 3. The Kubernetes API Server (Core Component)

The API Server:
- Validates requests
- Authenticates users
- Authorizes actions
- Persists state to etcd

Everything goes through the API Server:
- kubectl
- Controllers
- Scheduler
- kubelets

If API Server is down → **cluster is effectively down**.

---

## 4. End-to-End Request Flow

Example:
```bash
kubectl apply -f pod.yaml
```

Flow:
1. kubectl reads YAML
2. Converts YAML → JSON
3. Sends REST request to API Server
4. API Server authenticates request
5. Authorization (RBAC)
6. Schema validation
7. Object stored in etcd
8. Controllers react asynchronously

Important:
- API Server does NOT create Pods itself
- It only records desired state

---

## 5. Declarative vs Imperative Commands

### Imperative
```bash
kubectl run nginx --image=nginx
```

- Immediate action
- Hard to reproduce

### Declarative (Preferred)
```bash
kubectl apply -f deployment.yaml
```

- Version-controlled
- Reproducible
- Aligns with Kubernetes design

Kubernetes is optimized for **declarative workflows**.

---

## 6. Understanding kubeconfig

`kubeconfig` defines:
- Cluster endpoint
- User credentials
- Contexts

Common command:
```bash
kubectl config get-contexts
kubectl config use-context my-cluster
```

Mistakes here often cause:
- “Wrong cluster” deployments
- Production outages

---

## 7. Namespaces & API Scoping

Namespaces:
- Provide logical isolation
- Scope API objects

Key nuance:
- Namespaces are **API-level isolation**, not security by default

Always specify namespace explicitly:
```bash
kubectl get pods -n prod
```

---

## 8. Inspecting the API (Hands-on Lab)

### Objective
Observe Kubernetes as an API system.

### Steps
1. Use `kubectl get --raw /api`
2. Explore `/apis`
3. List available resources

This reveals:
- Kubernetes is versioned APIs
- CRDs extend the API

---

## 9. Common Beginner kubectl Mistakes

- Assuming kubectl changes state directly
- Using imperative commands in production
- Forgetting namespace context
- Editing live objects without version control

---

## 10. Interview Q&A (High Quality)

**Q: What is kubectl?**  
An API client for Kubernetes.

**Q: Does API Server create Pods?**  
No. Controllers do.

**Q: Why is declarative preferred?**  
Aligns with reconciliation model.

**Q: What happens if API Server is down?**  
No state changes possible.

---

## 11. Real Production Failure Scenario

### Scenario
Production deployment accidentally applied to wrong cluster.

### Root Cause
- kubeconfig context not verified
- Same command used across clusters

### Impact
- Unintended production changes
- Service instability

### Fix
- Context-aware prompts
- Separate kubeconfigs
- Explicit namespaces

---

## 12. Advanced Insight: Everything Is an API Object

Pods, Deployments, Services, ConfigMaps:
- All are API resources
- Versioned
- Validated by schemas

This enables:
- Operators
- GitOps
- Automation

---

## Final Takeaway

If you remember one thing:
> **Kubernetes is an API-driven system; kubectl is just a client**

Once this clicks, Kubernetes becomes predictable instead of mysterious.