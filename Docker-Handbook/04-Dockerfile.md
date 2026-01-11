# Dockerfile – Deep Dive (Production-Grade)

## Summary (Quick Revision)
- A **Dockerfile** is a declarative recipe to build Docker images
- Each instruction creates an **immutable image layer**
- Instruction **order directly affects build speed** via caching
- Poor Dockerfiles are a top cause of **slow CI/CD pipelines**
- Dockerfile mistakes often surface only in production

---

## 1. Why Dockerfile Matters More Than You Think

A Dockerfile is not just a build script.

It determines:
- Image size
- Build time
- Security posture
- Runtime behavior
- CI/CD performance

A badly written Dockerfile can:
- Increase image size by GBs
- Slow builds by minutes
- Leak secrets
- Break reproducibility

---

## 2. Dockerfile Is Declarative, Not Imperative

Dockerfile instructions **describe desired state**, not execution logic.

Example misconception:
> Docker executes instructions like a shell script ❌

Reality:
- Each instruction creates a snapshot (layer)
- Layers are cached and reused
- Docker does not “remember” runtime state

---

## 3. Core Dockerfile Instructions (In Depth)

### FROM
Defines the base image.

```dockerfile
FROM openjdk:17-jdk-slim
```

Key points:
- First instruction (except ARG)
- Sets OS + runtime
- Changing FROM invalidates **all layers**

Best practices:
- Avoid `latest`
- Prefer slim or distroless images

---

### WORKDIR
Sets working directory.

```dockerfile
WORKDIR /app
```

Why it matters:
- Avoids hardcoded paths
- Cleaner than `RUN cd ...`

---

### COPY vs ADD (Common Interview Trap)

```dockerfile
COPY src/ /app/src/
```

Use **COPY** by default.

ADD should be used only when:
- You need auto-extraction of tar files
- You fetch remote URLs (rare, discouraged)

---

### RUN
Executes commands at **build time**.

```dockerfile
RUN apt-get update && apt-get install -y curl
```

Key nuance:
- Each RUN creates a layer
- Combine commands to reduce layers

---

### CMD vs ENTRYPOINT (Critical Concept)

```dockerfile
ENTRYPOINT ["java","-jar","app.jar"]
CMD ["--server.port=8080"]
```

Difference:
- ENTRYPOINT → fixed executable
- CMD → default arguments (overrideable)

Interview mental model:
- ENTRYPOINT = executable
- CMD = parameters

---

### EXPOSE (Often Misunderstood)

```dockerfile
EXPOSE 8080
```

Important:
- EXPOSE does **not** publish ports
- It is documentation + metadata

---

## 4. How Dockerfile Layer Caching Actually Works

Docker builds images **top to bottom**.

Cache reuse rule:
- Instruction text unchanged
- Files involved unchanged
- All previous layers unchanged

If one layer changes:
> All layers **below** are rebuilt

---

## 5. Instruction Ordering (Real Performance Impact)

### Bad Dockerfile
```dockerfile
COPY . .
RUN mvn package
```

Any code change → full rebuild.

### Optimized Dockerfile
```dockerfile
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package
```

Result:
- Dependencies cached
- Faster rebuilds

---

## 6. .dockerignore (Mandatory for Real Projects)

Without `.dockerignore`:
- Git history copied
- IDE files included
- Secrets accidentally leaked

Example:
```text
.git
node_modules
target
.env
```

---

## 7. Multi-Stage Builds (Production Standard)

```dockerfile
FROM maven:3.9 AS build
WORKDIR /app
COPY . .
RUN mvn package

FROM eclipse-temurin:17-jre
COPY --from=build /app/target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

Benefits:
- Smaller images
- No build tools in production
- Improved security

---

## 8. Hands-on Lab (Critical Learning)

### Objective
Understand caching and layer rebuild behavior.

### Steps
1. Create a Dockerfile with COPY . .
2. Build image
3. Modify one source file
4. Rebuild image
5. Observe cache invalidation

Then:
- Reorder instructions
- Rebuild
- Compare build times

---

## 9. Common Beginner Dockerfile Mistakes

- Using `latest` tag
- Copying entire project too early
- Installing packages without cleanup
- Storing secrets in Dockerfile
- One huge RUN instruction without purpose

---

## 10. Interview Q&A (Realistic)

**Q: Why does Docker rebuild layers below a change?**  
Because each layer depends on the previous snapshot.

**Q: Difference between CMD and ENTRYPOINT?**  
CMD is overrideable; ENTRYPOINT is not.

**Q: Why prefer multi-stage builds?**  
Smaller, safer, cleaner images.

---

## 11. Real Production Failure Scenario

### Scenario
CI pipeline slowed from 5 min to 25 min.

### Root Cause
- `COPY . .` before dependency install
- Cache invalidated on every commit

### Impact
- Developer productivity loss
- Slower releases

### Fix
- Reordered Dockerfile
- Introduced multi-stage builds

---

## Final Takeaway

A Dockerfile is **code**.

Treat it with the same discipline as:
- Application logic
- Infrastructure code
- CI/CD pipelines

A good Dockerfile:
- Builds fast
- Runs predictably
- Fails safely