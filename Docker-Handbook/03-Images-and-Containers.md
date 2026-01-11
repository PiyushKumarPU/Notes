# Images & Containers

## Summary (Quick Revision)
- **Docker Image**: Immutable, read-only template used to create containers
- **Docker Container**: Runtime instance of an image with a writable layer
- One image can spawn **multiple containers**
- Containers are **ephemeral by default**
- Data persistence requires **volumes or bind mounts**
- Image rebuilds rely heavily on **layer caching**

---

## 1. What Is a Docker Image?

A Docker image is a **packaged artifact** that contains:
- Application code
- Runtime (JDK, Node, Python, etc.)
- System libraries and dependencies
- Default configuration

### Key Properties
- **Immutable**: Cannot be changed once built
- **Layered**: Built step-by-step from a Dockerfile
- **Reusable**: Same image across dev, test, prod

### Mental Model
- Image = Class
- Container = Object

---

## 2. What Is a Docker Container?

A container is a **running process** created from an image.

It includes:
- The image layers (read-only)
- A **thin writable layer**
- Isolated process space
- Isolated network interface

Important:
- If the main process exits, the container stops
- Containers do **not** restart automatically unless configured

---

## 3. Image vs Container (Critical Distinction)

| Aspect | Image | Container |
|------|------|-----------|
| Mutability | Immutable | Mutable (runtime only) |
| State | Static | Running / Stopped |
| Storage | Disk | Disk + Memory |
| Count | One | Many |
| Lifecycle | Build → Store | Create → Run → Stop → Remove |

Common beginner mistake:
> Restarting a container does **not** rebuild the image.

---

## 4. Image Layers & Filesystem

Each Dockerfile instruction creates a **layer**.

Example:
```dockerfile
FROM openjdk:17
WORKDIR /app
COPY app.jar app.jar
CMD ["java","-jar","app.jar"]
```

Layers are:
- Cached
- Shared across images
- Read-only

Containers add:
- One writable layer on top

---

## 5. Why Containers Are Ephemeral

When a container is removed:
- Writable layer is deleted
- Logs and files inside container are lost

This is **by design**.

Correct approaches:
- Volumes (recommended)
- Bind mounts (local development)
- External services (S3, DBs)

---

## 6. Working with Images (Hands-on)

### Pull an Image
```bash
docker pull nginx:1.25
```

### List Images
```bash
docker images
```

### Inspect Image
```bash
docker inspect nginx
```

### Remove Image
```bash
docker rmi nginx
```

---

## 7. Working with Containers (Hands-on)

### Run a Container
```bash
docker run -d -p 8080:80 --name web nginx
```

### List Containers
```bash
docker ps
docker ps -a
```

### Stop / Start / Remove
```bash
docker stop web
docker start web
docker rm web
```

---

## 8. Debugging Containers

### View Logs
```bash
docker logs web
```

### Exec into Container
```bash
docker exec -it web /bin/sh
```

Use cases:
- Inspect environment variables
- Validate config files
- Debug crashes

---

## 9. Image Rebuilds & Layer Caching (Important Nuance)

Docker reuses layers **only if**:
- Instruction text is unchanged
- Files involved are unchanged
- Previous layers are unchanged

If one layer changes:
- All layers **below** it are rebuilt

This is why Dockerfile order matters.

---

## 10. Hands-on Lab (End-to-End)

### Objective
Understand image vs container behavior.

### Steps
1. Run a container
2. Create a file inside container
3. Stop and remove container
4. Recreate container from same image

Observe:
- File is lost
- Image remains unchanged

---

## 11. Interview Q&A

**Q: Can one image create multiple containers?**  
Yes. Images are templates.

**Q: Why are containers considered ephemeral?**  
Writable layer is destroyed on removal.

**Q: Does restarting a container rebuild the image?**  
No. Rebuild requires `docker build`.

---

## 12. Real Production Failure Scenario

### Scenario
Application logs were written inside container filesystem.

### Impact
- Containers restarted during deployment
- Logs permanently lost
- No audit trail

### Root Cause
- Assumption that container storage is persistent

### Correct Approach
- Use volumes
- Use centralized logging (ELK, CloudWatch)
- Treat containers as disposable

---

## Final Takeaway

If you clearly understand:
- Image vs container
- Immutability
- Ephemeral nature
- Layer caching

You avoid **80% of Docker production mistakes**.
