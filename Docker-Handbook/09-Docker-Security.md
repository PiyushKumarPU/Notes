# Docker Security (Deep Dive)

## Summary (Quick Revision)
- Docker security is a **shared responsibility** between platform and user
- Containers are **not secure by default**
- Most Docker security incidents are due to **misconfiguration**, not Docker itself
- Security must be applied at **build time, runtime, and host level**
- Least privilege is the guiding principle

---

## 1. Docker Security Mental Model

Docker provides **isolation**, not absolute security.

Think of containers as:
- Strong process isolation
- Weaker than full VMs
- Safe only when properly configured

Security layers:
1. Host OS security
2. Docker daemon security
3. Image security
4. Container runtime security
5. Network security

Breaking any layer weakens the whole system.

---

## 2. Host-Level Security (Foundation)

Docker runs **on top of the host OS**.

Critical host practices:
- Keep OS patched
- Harden SSH access
- Use firewall rules
- Restrict sudo access

If the host is compromised, **all containers are compromised**.

---

## 3. Docker Daemon Security

### Why Docker Needs Root
- Manages namespaces and cgroups
- Controls networking and mounts

### Hardening Measures
- Protect Docker socket (`/var/run/docker.sock`)
- Never expose Docker socket publicly
- Use TLS for remote Docker API access

⚠ Anyone with Docker socket access effectively has **root access**.

---

## 4. Image Security (Very Important)

### Trusted Images
- Prefer official images
- Avoid unknown publishers
- Pin image versions

### Image Scanning
Scan images for vulnerabilities:
- OS packages
- Language dependencies

Common tools:
- Docker Scout
- Trivy
- Clair

---

## 5. Running Containers as Non-Root

Default behavior:
- Containers run as root inside container

Risk:
- Container breakout impact increases

Solution:
```dockerfile
RUN adduser appuser
USER appuser
```

This significantly reduces blast radius.

---

## 6. Capabilities & Privileges

Linux capabilities limit container power.

Bad practice:
```bash
--privileged
```

Better approach:
- Drop unused capabilities
- Add only required ones

Principle:
> Least privilege always

---

## 7. Secrets Management

Never store secrets in:
- Dockerfile
- Image layers
- Git repository

Better options:
- Environment variables (basic)
- Docker secrets
- External secret managers

Secrets baked into images **cannot be removed**.

---

## 8. Network Security

Best practices:
- Minimal port exposure
- Use private networks
- Use reverse proxies
- Avoid host networking

Containers should not be reachable unless required.

---

## 9. Hands-on Lab (Security Basics)

### Objective
Reduce container attack surface.

### Steps
1. Run container as non-root
2. Drop unnecessary capabilities
3. Scan image for vulnerabilities

Observe:
- Reduced permissions
- Scan results

---

## 10. Common Docker Security Mistakes

- Running containers as root
- Using `--privileged`
- Using outdated base images
- Hardcoding secrets
- Exposing Docker socket

---

## 11. Interview Q&A (Practical)

**Q: Is Docker secure by default?**  
No. Security depends on configuration.

**Q: Why is Docker socket dangerous?**  
It provides root-equivalent access.

**Q: Containers vs VMs for security?**  
VMs offer stronger isolation.

---

## 12. Real Production Failure Scenario

### Scenario
Container was compromised via vulnerable dependency.

### Root Cause
- Unscanned image
- Running as root

### Impact
- Lateral movement on host
- Data exposure

### Fix
- Image scanning in CI
- Non-root containers
- Regular patching

---

## Final Takeaway

Docker security is not optional.

If you remember one rule:
> **Secure the host, scan images, run as non-root, and minimize privileges**