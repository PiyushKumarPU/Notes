# Docker Compose (Deep Dive)

## Summary (Quick Revision)
- Docker Compose is a **declarative tool** for defining and running multi-container applications
- A single `docker-compose.yml` defines **services, networks, volumes, and configs**
- Compose is ideal for **local development, testing, and small deployments**
- Service names act as **DNS hostnames**
- Most Compose issues stem from **startup order and configuration mistakes**

---

## 1. Why Docker Compose Exists

Running multiple containers with long `docker run` commands is:
- Error-prone
- Hard to reproduce
- Difficult to share with teams

Docker Compose solves this by:
- Capturing the entire stack in **one YAML file**
- Making environments reproducible
- Enabling one-command startup and teardown

Compose is **orchestration-lite**, not a replacement for Kubernetes.

---

## 2. Core Concepts in Docker Compose

### Project
- A Compose project is a **set of services** defined in one file
- Project name defaults to directory name
- All resources are namespaced by project

### Service
- A service is a **container definition**
- One service can scale to multiple containers

### Network
- Compose creates a **default user-defined bridge network**
- Enables automatic DNS-based discovery

### Volume
- Used for data persistence across container restarts

---

## 3. Anatomy of docker-compose.yml

Minimal example:
```yaml
version: "3.9"
services:
  web:
    image: nginx
    ports:
      - "8080:80"
```

Key sections:
- `services`
- `volumes`
- `networks`

---

## 4. Service Configuration (In Depth)

### Image vs Build
```yaml
services:
  api:
    build: .
    image: my-api:1.0
```

- `build` → builds image locally
- `image` → names the built image

---

### Environment Variables
```yaml
environment:
  SPRING_PROFILES_ACTIVE: dev
```

Best practices:
- Prefer `.env` files
- Avoid hardcoding secrets

---

### Ports
```yaml
ports:
  - "8080:8080"
```

- Required only for **host access**
- Not required for inter-service communication

---

## 5. Networking & Service Discovery

Compose automatically:
- Creates a network
- Registers service names in DNS

Example:
```yaml
services:
  db:
    image: postgres
  api:
    image: my-api
```

`api` can connect to database using hostname `db`.

---

## 6. depends_on (Commonly Misunderstood)

```yaml
depends_on:
  - db
```

Important:
- Ensures **startup order**
- Does **NOT** wait for readiness

Correct solution:
- Use healthchecks
- Implement retry logic in app

---

## 7. Volumes in Compose

```yaml
volumes:
  pgdata:

services:
  db:
    volumes:
      - pgdata:/var/lib/postgresql/data
```

Volumes:
- Persist data
- Survive container recreation

---

## 8. Hands-on Lab (End-to-End)

### Objective
Run a multi-container app using Compose.

### Steps
1. Create Compose file with web + DB
2. Run `docker compose up`
3. Verify connectivity via service name
4. Restart stack and verify data persistence

Observe:
- Network auto-creation
- DNS resolution
- Volume persistence

---

## 9. Common Beginner Mistakes

- Expecting `depends_on` to ensure readiness
- Exposing all service ports unnecessarily
- Hardcoding environment values
- Using Compose for large-scale production orchestration

---

## 10. Interview Q&A (Practical)

**Q: What problem does Docker Compose solve?**  
Declarative multi-container management.

**Q: Does Compose replace Kubernetes?**  
No. It is for local/dev/small setups.

**Q: How do services discover each other?**  
Via DNS using service names.

---

## 11. Real Production Failure Scenario

### Scenario
Application started before database was ready.

### Root Cause
- Used `depends_on` without healthchecks

### Impact
- Application crash loop
- Manual restarts required

### Fix
- Add healthcheck to DB
- Add retry logic in application

---

## Final Takeaway

Docker Compose is best viewed as:
> **Infrastructure-as-code for local and small-scale Docker setups**

Use it to:
- Simplify development
- Ensure consistency
- Reduce human error