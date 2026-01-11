# Docker in CI/CD (Deep Dive)

## Summary (Quick Revision)
- Docker enables **build once, deploy everywhere** in CI/CD pipelines
- Images are immutable artifacts promoted across environments
- Proper tagging, caching, and scanning are critical for speed and safety
- Most CI/CD failures come from **poor tagging, cache misuse, or secrets handling**
- Docker fits both **build pipelines** and **runtime deployment pipelines**

---

## 1. Why Docker Is Central to Modern CI/CD

Before Docker, CI/CD pipelines:
- Built binaries per environment
- Faced dependency drift
- Had inconsistent runtime behavior

Docker fixes this by:
- Packaging app + runtime into an image
- Promoting the same image from dev → test → prod
- Making rollbacks deterministic

Key principle:
> **CI builds images; CD deploys images**

---

## 2. CI vs CD Responsibilities (Clear Separation)

### Continuous Integration (CI)
CI should:
- Build Docker images
- Run unit/integration tests
- Scan images for vulnerabilities
- Tag images immutably
- Push images to registry

CI should NOT:
- Deploy to production directly

---

### Continuous Delivery / Deployment (CD)
CD should:
- Pull pre-built images
- Deploy to environments
- Roll back by image tag
- Avoid rebuilding images

Rebuilding images in CD breaks reproducibility.

---

## 3. Image Tagging Strategy (Critical)

### Bad Tagging
```text
latest
```
Problems:
- Non-deterministic
- Breaks rollbacks
- Causes environment drift

### Recommended Tagging
- Git commit SHA
- Semantic version
- Build number

Example:
```text
myapp:1.4.2
myapp:commit-9f3a21c
```

---

## 4. Docker Build in CI Pipelines

Typical CI steps:
1. Checkout code
2. Build Docker image
3. Run tests inside container
4. Scan image
5. Push to registry

Example:
```bash
docker build -t myapp:${GIT_SHA} .
docker push myapp:${GIT_SHA}
```

---

## 5. Caching in CI (Performance Booster)

CI environments are often ephemeral.

Cache options:
- Docker layer cache (where supported)
- Remote build cache
- Dependency caching

Best practices:
- Optimize Dockerfile order
- Use BuildKit
- Cache dependency layers

---

## 6. Secrets Management in CI/CD

Never:
- Hardcode secrets in Dockerfile
- Commit secrets to Git
- Bake secrets into images

Correct approaches:
- CI secret stores
- Environment injection at runtime
- External secret managers

Secrets must be **runtime-only**.

---

## 7. Security in CI/CD Pipelines

Security checks should include:
- Image vulnerability scanning
- License checks
- Base image updates

Fail the pipeline on:
- Critical vulnerabilities
- Policy violations

Security belongs **early in CI**, not later.

---

## 8. Hands-on Lab (CI/CD Flow)

### Objective
Understand image promotion workflow.

### Steps
1. Build image with commit tag
2. Push to registry
3. Deploy same image to two environments
4. Roll back by tag

Observe:
- No rebuilds
- Identical behavior across environments

---

## 9. Common CI/CD Anti-Patterns

- Using `latest` everywhere
- Rebuilding images in CD
- No image scanning
- Mixing CI and CD responsibilities
- No rollback strategy

---

## 10. Interview Q&A (Practical)

**Q: Why avoid rebuilding images in CD?**  
It breaks immutability and reproducibility.

**Q: How do you roll back using Docker?**  
Redeploy previous image tag.

**Q: Where should security scanning happen?**  
In CI, before images are promoted.

---

## 11. Real Production Failure Scenario

### Scenario
Production deployment used wrong image version.

### Root Cause
- Used `latest` tag
- Image overwritten in registry

### Impact
- Undetected regression
- Difficult rollback

### Fix
- Immutable tagging
- Explicit version promotion
- Registry retention policies

---

## Final Takeaway

Docker brings **discipline and repeatability** to CI/CD.

If you follow:
> immutable images, proper tags, caching, and scanning

your pipelines become **fast, safe, and predictable**.