# ConfigMaps & Secrets (Deep Dive)

## Summary (Quick Revision)
- **ConfigMaps** and **Secrets** externalize configuration from containers
- They enable **immutable images** with environment-specific behavior
- ConfigMaps are for **non-sensitive** configuration
- Secrets are for **sensitive data**, but require careful handling
- Most security incidents involve **misused Secrets**, not Kubernetes flaws

---

## 1. Why Configuration Management Matters in Kubernetes

Hardcoding configuration inside container images causes:
- Environment drift
- Frequent rebuilds
- Security risks
- Poor separation of concerns

Kubernetes promotes:
> **Build once, configure at runtime**

ConfigMaps and Secrets make this possible.

---

## 2. ConfigMaps: Purpose & Mental Model

### What a ConfigMap Is
A ConfigMap stores:
- Key–value pairs
- Configuration files
- Command-line arguments

Used for:
- Feature flags
- Application configs
- Non-sensitive settings

Mental model:
> **ConfigMap = externalized application config**

---

## 3. Creating and Using ConfigMaps

### Example ConfigMap
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  LOG_LEVEL: INFO
  APP_MODE: prod
```

### Using ConfigMaps in Pods

As environment variables:
```yaml
envFrom:
  - configMapRef:
      name: app-config
```

As files:
```yaml
volumeMounts:
  - name: config
    mountPath: /etc/config
```

---

## 4. Secrets: Purpose & Reality Check

### What Secrets Are
Secrets store:
- Passwords
- API keys
- Tokens
- Certificates

Reality check:
- Secrets are **base64-encoded**, not encrypted by default
- Anyone with access can decode them

Mental model:
> **Secret = sensitive config, not magic security**

---

## 5. Creating and Using Secrets

### Example Secret
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: db-secret
type: Opaque
data:
  password: cGFzc3dvcmQ=
```

### Using Secrets

As environment variables:
```yaml
env:
  - name: DB_PASSWORD
    valueFrom:
      secretKeyRef:
        name: db-secret
        key: password
```

As files:
- Mounted into containers
- Often used for TLS certs

---

## 6. ConfigMaps & Secrets Update Behavior (Important Nuance)

- Environment variables **do not update automatically**
- Mounted files **can update** (with delay)

Implications:
- App restart often required
- Design apps for reloadability if needed

---

## 7. Security Best Practices for Secrets

Do NOT:
- Commit Secrets to Git
- Log Secret values
- Expose Secrets via env dumps

Do:
- Use RBAC strictly
- Enable encryption at rest for etcd
- Use external secret managers where possible

---

## 8. Hands-on Lab (Foundational)

### Objective
Observe configuration injection behavior.

### Steps
1. Create ConfigMap
2. Inject as env vars
3. Update ConfigMap
4. Observe Pod behavior
5. Restart Pod and re-check

Repeat with Secret.

Key learning:
- Config updates vs Pod lifecycle

---

## 9. Common Beginner Mistakes

- Storing passwords in ConfigMaps
- Assuming Secrets are encrypted automatically
- Expecting env vars to update dynamically
- Rebuilding images for config changes

---

## 10. Interview Q&A (High Quality)

**Q: Difference between ConfigMap and Secret?**  
Sensitivity and handling.

**Q: Are Secrets encrypted?**  
No, only base64 unless configured.

**Q: Can ConfigMaps update running Pods?**  
Only mounted files, not env vars.

---

## 11. Real Production Failure Scenario

### Scenario
Database credentials leaked via logs.

### Root Cause
- Secret injected as env var
- Application logged full environment

### Impact
- Credential compromise
- Security incident

### Fix
- Avoid env dumps
- Use file-based Secrets
- Restrict RBAC

---

## 12. Advanced Insight: External Secrets

In production, teams often use:
- Vault
- Cloud secret managers
- CSI Secret Store

Kubernetes Secrets become:
- References, not storage

---

## Final Takeaway

If you remember one rule:
> **ConfigMaps for config, Secrets for secrets — and neither belong in images**

Correct config management is essential for **secure, scalable Kubernetes systems**.