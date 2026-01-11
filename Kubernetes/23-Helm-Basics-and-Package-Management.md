# Helm Basics & Package Management (Deep Dive)

## Summary (Quick Revision)
- **Helm** is the package manager for Kubernetes
- Helm packages are called **charts**
- Charts templatize Kubernetes manifests
- Helm enables **reuse, versioning, and rollback**
- Most Helm issues come from **over-templating and hidden complexity**

---

## 1. Why Helm Exists

Raw Kubernetes manifests:
- Are verbose
- Are hard to reuse
- Do not handle environments well

Helm solves:
- Parameterization
- Versioning
- Release management

Mental model:
> **Helm = apt/yum for Kubernetes + templating**

---

## 2. Helm Architecture

Key components:
- Helm CLI
- Chart repository
- Release metadata (stored in cluster)

Helm is **client-side**; no Tiller anymore.

---

## 3. What Is a Chart?

A chart contains:
- Templates (YAML with placeholders)
- values.yaml
- Chart.yaml (metadata)

Structure:
```
chart/
  templates/
  values.yaml
  Chart.yaml
```

Charts package application intent.

---

## 4. Values & Templating

Values allow:
- Environment-specific configuration
- Reusability

Danger:
- Excessive logic in templates
- Debugging becomes hard

Rule:
> **Prefer simple values, minimal logic**

---

## 5. Helm Install, Upgrade, Rollback

Key commands:
```bash
helm install
helm upgrade
helm rollback
```

Helm tracks releases:
- Versioned
- Reversible

This enables safer deployments.

---

## 6. Helm vs Kustomize

| Aspect | Helm | Kustomize |
|------|------|----------|
| Templating | Yes | No |
| Logic | Allowed | Minimal |
| Packaging | Strong | Weak |
| Complexity | Higher | Lower |

Use Helm for products, Kustomize for environments.

---

## 7. Hands-on Lab (Foundational)

### Objective
Deploy app via Helm.

### Steps
1. Install Helm
2. Add chart repo
3. Install chart
4. Override values
5. Roll back release

Key learning:
- Release lifecycle

---

## 8. Common Beginner Mistakes

- Over-templating charts
- Embedding secrets in values.yaml
- Using Helm as CI tool
- No chart versioning

Helm should simplify, not obscure.

---

## 9. Interview Q&A

**Q: What is Helm?**  
Kubernetes package manager.

**Q: Where is Helm state stored?**  
In Kubernetes secrets.

---

## Final Takeaway

If you remember one rule:
> **Use Helm to package intent, not complexity**