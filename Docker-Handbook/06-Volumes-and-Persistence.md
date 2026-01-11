# Volumes & Data Persistence (Deep Dive)

## Summary (Quick Revision)
- Containers are **ephemeral by design**; data inside them is lost on removal
- **Volumes** are the preferred way to persist data in Docker
- **Bind mounts** are mainly for local development
- Data persistence is a **runtime concern**, not an image concern
- Most production Docker failures involve **misunderstood persistence**

---

## 1. Why Data Persistence Is a Hard Problem in Docker

Docker containers are designed to be:
- Disposable
- Re-creatable
- Stateless

This directly conflicts with applications that need to store:
- Databases
- Logs
- User uploads
- Caches

Understanding persistence is therefore **mandatory for production Docker usage**.

---

## 2. Container Filesystem vs Persistent Storage

### Container Filesystem
- Writable layer on top of image
- Exists only while container exists
- Deleted when container is removed

### Persistent Storage
- Exists independently of container lifecycle
- Can be shared between containers
- Survives restarts, upgrades, redeployments

Mental model:
- Container FS = RAM + temp disk
- Volume = external hard drive

---

## 3. Types of Data Mounts in Docker

Docker supports **three** persistence mechanisms.

### 1. Volumes (Recommended)
- Managed by Docker
- Stored under Docker’s data directory
- Best for production

### 2. Bind Mounts
- Maps a host path into container
- Tight host coupling
- Ideal for local development

### 3. tmpfs Mounts
- Stored in memory
- Lost on restart
- Used for secrets or temporary data

---

## 4. Docker Volumes (In Depth)

### What Is a Volume?
A volume is:
- A Docker-managed storage location
- Independent of containers
- Accessible by name

### Create and Use a Volume
```bash
docker volume create app-data

docker run -d   -v app-data:/var/lib/app   nginx
```

### Key Properties
- Survives container deletion
- Can be backed up and restored
- Can be shared across containers

---

## 5. Bind Mounts (When and Why)

```bash
docker run -v $(pwd):/app node
```

Use bind mounts when:
- Developing locally
- Live code reload is needed
- Debugging files

Avoid in production because:
- Host path dependency
- Permission issues
- Lower portability

---

## 6. Volume Lifecycle (Critical Concept)

Volumes:
- Are created explicitly or implicitly
- Are **not deleted** when containers are removed
- Must be cleaned manually

Commands:
```bash
docker volume ls
docker volume inspect app-data
docker volume rm app-data
```

⚠ `docker system prune` can delete unused volumes.

---

## 7. Data Initialization & Ownership

Common problem:
- Application runs as non-root
- Volume owned by root
- App cannot write data

Solutions:
- Init containers
- `chown` during startup
- Proper UID/GID strategy

This issue appears **frequently in production**.

---

## 8. Hands-on Lab (Critical)

### Objective
Observe persistence behavior.

### Steps
1. Create volume
2. Run container and write file
3. Stop and remove container
4. Re-run container with same volume
5. Verify file still exists

Then:
- Repeat without volume
- Observe data loss

---

## 9. Common Beginner Mistakes

- Storing DB data inside container FS
- Assuming restart preserves data
- Using bind mounts in production
- Forgetting to back up volumes
- Deleting volumes unintentionally

---

## 10. Interview Q&A (Practical)

**Q: Why are containers stateless by default?**  
To enable fast recreation and scaling.

**Q: Volume vs bind mount?**  
Volumes are Docker-managed; bind mounts map host paths.

**Q: Can volumes be shared across containers?**  
Yes.

---

## 11. Real Production Failure Scenario

### Scenario
A database container was redeployed.

### What Happened
- Container removed
- No volume attached
- All production data lost

### Root Cause
- Assumed container filesystem was persistent

### Correct Approach
- Always use volumes for stateful services
- Backup volumes regularly
- Treat containers as disposable

---

## 12. Backup & Restore Strategy (High-Level)

Common approaches:
- Stop container and tar volume data
- Use cloud volume snapshots
- Replicate data externally

Persistence without backup is **false safety**.

---

## Final Takeaway

If you remember one rule:
> **Containers are ephemeral; volumes are not**

Mastering Docker persistence prevents the **most expensive Docker mistakes**.