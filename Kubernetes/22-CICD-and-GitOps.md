# CI/CD & GitOps in Kubernetes (Argo CD, Flux, Declarative Delivery) — Deep Dive

## Summary (Quick Revision)
- **CI builds artifacts**; **CD deploys desired state**
- **GitOps** uses Git as the single source of truth for cluster state
- Argo CD and Flux are **pull-based** deployment systems
- Declarative delivery enables **auditability, rollback, and drift detection**
- Most delivery failures come from **imperative changes and unclear ownership**

---

## 1. Why CI/CD Changes in Kubernetes

Traditional CI/CD:
- Push-based deployments
- Scripts mutate servers
- State lives outside version control

Kubernetes reality:
- Desired state is declarative
- Controllers reconcile continuously
- Drift is inevitable without enforcement

Mental model:
> **CI produces artifacts; Kubernetes reconciles state**

GitOps aligns delivery with Kubernetes’ control-loop design.

---

## 2. CI vs CD (Clear Separation of Concerns)

### Continuous Integration (CI)
Responsibilities:
- Build container images
- Run tests
- Scan for vulnerabilities
- Publish artifacts

CI ends when:
- Image is immutable and versioned

### Continuous Delivery (CD)
Responsibilities:
- Deploy manifests
- Manage rollouts
- Enforce desired state
- Roll back safely

CD begins when:
- Desired state is declared

Never mix CI and CD responsibilities.

---

## 3. GitOps: The Core Principles

GitOps requires:
1. **Declarative configuration**
2. **Git as source of truth**
3. **Automated reconciliation**
4. **Observable drift and rollbacks**

Mental model:
> **Git is the API for operations**

Humans change Git, controllers change clusters.

---

## 4. Push-Based vs Pull-Based CD

### Push-Based (Anti-Pattern in Kubernetes)
- CI system has cluster credentials
- Scripts run kubectl apply
- Hard to audit and secure

### Pull-Based (GitOps)
- Cluster pulls desired state
- No external credentials needed
- Drift detected automatically

Pull-based aligns with zero-trust principles.

---

## 5. Argo CD Architecture

Argo CD components:
- API Server
- Repository Server
- Application Controller
- UI / CLI

Flow:
1. Watch Git repository
2. Compare desired vs live state
3. Sync differences
4. Report drift

Argo CD focuses on **application delivery**.

---

## 6. Flux Architecture

Flux components:
- Source Controller
- Kustomize Controller
- Helm Controller
- Notification Controller

Flow:
1. Fetch Git sources
2. Reconcile manifests
3. Apply changes continuously

Flux emphasizes **composability and automation**.

---

## 7. Argo CD vs Flux (Practical Comparison)

| Aspect | Argo CD | Flux |
|------|--------|------|
| UX | Rich UI | CLI-first |
| Model | Application-centric | Toolkit |
| Sync | Manual/Auto | Continuous |
| Learning curve | Lower | Higher |
| Use case | App teams | Platform teams |

Both implement GitOps correctly.

---

## 8. Declarative Delivery Patterns

Common patterns:
- Kustomize overlays
- Helm charts
- Environment directories
- Promotion via PRs

Anti-patterns:
- kubectl apply from laptops
- Hotfixes outside Git

Git history is your audit log.

---

## 9. Hands-on Lab (Foundational)

### Objective
Deploy using GitOps.

### Steps
1. Create Git repo with manifests
2. Install Argo CD or Flux
3. Register repository
4. Deploy application
5. Change Git state
6. Observe automatic sync

Key learning:
- Git change → cluster change
- No kubectl needed

---

## 10. Security in GitOps

Key practices:
- Read-only Git credentials
- Namespace-scoped access
- RBAC on CD controllers
- Signed commits (optional)

GitOps reduces:
- Credential sprawl
- Human error
- Undocumented changes

---

## 11. Common Beginner & Production Mistakes

- Mixing CI and CD logic
- Allowing manual kubectl changes
- No drift alerts
- One repo for everything
- No environment separation

GitOps requires **discipline**, not just tools.

---

## 12. Interview Q&A (High Quality)

**Q: What is GitOps?**  
Git-driven declarative delivery with reconciliation.

**Q: Why pull-based CD?**  
Security and drift detection.

**Q: Argo CD vs Flux?**  
Different UX and models, same principles.

---

## 13. Real Production Failure Scenario

### Scenario
Hotfix applied via kubectl reverted unexpectedly.

### Root Cause
- GitOps controller reconciled desired state from Git

### Impact
- Confusion
- Perceived “rollback bug”

### Fix
- Enforce Git-only changes
- Educate teams
- Disable manual access

GitOps is strict by design.

---

## 14. Advanced Insight: GitOps as Operating Model

GitOps impacts:
- Team workflows
- Incident response
- Compliance
- Auditing

It is not just CD — it is **how operations are done**.

---

## Final Takeaway

If you remember one rule:
> **In GitOps, Git changes the cluster — nothing else should**

Declarative delivery is the natural, safest way to run **Kubernetes at scale**.