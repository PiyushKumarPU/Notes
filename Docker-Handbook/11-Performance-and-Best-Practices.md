# Docker Performance & Best Practices (Deep Dive)

## Summary (Quick Revision)
- Docker performance issues usually stem from **image design, resource limits, and I/O patterns**
- Containers share host resources; **limits and isolation must be explicit**
- Small images, correct caching, and right base images matter
- Performance tuning spans **build-time and runtime**
- Best practices prevent noisy-neighbor and stability issues

---

## 1. Performance Mental Model

Containers are **fast by default**, but not free.

Key realities:
- Containers share CPU, memory, disk, and network
- One misconfigured container can affect others
- Performance tuning is about **predictability**, not raw speed

Think in terms of:
> throughput, latency, and resource isolation

---

## 2. Image Size & Startup Performance

### Why Image Size Matters
- Faster pulls (CI/CD, autoscaling)
- Faster cold starts
- Smaller attack surface

### Best Practices
- Use slim or distroless base images
- Remove build tools via multi-stage builds
- Clean package caches

Example:
```dockerfile
RUN apt-get update && apt-get install -y curl  && rm -rf /var/lib/apt/lists/*
```

---

## 3. CPU Management

### Default Behavior
- Containers can use all host CPUs

### Best Practices
```bash
docker run --cpus="1.5" myapp
```
- Prevents noisy neighbors
- Improves predictability

CPU pinning:
```bash
--cpuset-cpus="0,1"
```

Use only when necessary.

---

## 4. Memory Management (Critical)

### Why Memory Issues Are Common
- JVMs, Node.js, Python assume unlimited memory
- Docker limits are invisible unless configured

### Best Practices
```bash
docker run -m 512m myapp
```

Also tune application memory:
- JVM `-Xmx`
- Node `--max-old-space-size`

Exit code `137` usually means **OOM kill**.

---

## 5. Disk I/O Performance

### Container Filesystem
- Overlay filesystem
- Slower than native disk

### Best Practices
- Use volumes for heavy I/O
- Avoid writing large files inside container FS
- Use tmpfs for temporary data

---

## 6. Networking Performance

Tips:
- Avoid unnecessary port publishing
- Prefer user-defined networks
- Minimize hops (reverse proxy design)

Host networking may improve latency but reduces isolation.

---

## 7. Build-Time Performance

Key levers:
- Layer caching
- `.dockerignore`
- Instruction ordering
- BuildKit

Slow builds are often self-inflicted.

---

## 8. Runtime Configuration Best Practices

- One process per container
- Externalize configuration
- Healthchecks for visibility
- Proper restart policies

Example:
```dockerfile
HEALTHCHECK CMD curl -f http://localhost:8080/health || exit 1
```

---

## 9. Hands-on Lab (Performance Tuning)

### Objective
Observe performance impact of limits.

### Steps
1. Run app without limits
2. Add CPU and memory limits
3. Compare behavior under load

Observe:
- Stability
- Response time
- Resource usage

---

## 10. Common Performance Anti-Patterns

- Running everything in one container
- No resource limits
- Huge base images
- Excessive logging
- Writing logs to container filesystem

---

## 11. Interview Q&A (Practical)

**Q: Why set resource limits?**  
To prevent noisy neighbors and OOM kills.

**Q: Why are containers fast to start?**  
No guest OS boot.

**Q: What causes slow Docker builds?**  
Poor caching and Dockerfile order.

---

## 12. Real Production Failure Scenario

### Scenario
One container caused node-wide outage.

### Root Cause
- No memory limits
- Memory leak in application

### Impact
- OOM killer terminated multiple containers

### Fix
- Set memory limits
- Tune application memory
- Monitor usage

---

## Final Takeaway

Performance best practices are about **control and predictability**.

If you consistently apply:
> small images, limits, volumes, and caching

Docker becomes **fast, stable, and production-safe**.