# Debugging & Troubleshooting Docker (Deep Dive)

## Summary (Quick Revision)
- Most Docker issues are **application issues surfaced by containers**
- Debugging containers requires understanding **lifecycle, logs, and isolation**
- Containers often stop because the **main process exits**
- Effective debugging combines **logs, exec, inspect, and events**
- Production troubleshooting must be **repeatable and low-risk**

---

## 1. Docker Debugging Mental Model

Docker does not hide problems — it **exposes them earlier**.

Common misconceptions:
- Docker “crashed” my app ❌
- Container stopped randomly ❌

Reality:
- Application exited
- Dependency unavailable
- Configuration incorrect

Docker simply reflects process behavior.

---

## 2. Understanding Container Lifecycle (Why Containers Stop)

A container runs **one main process**.

If that process:
- Exits normally
- Crashes
- Receives SIGTERM

➡ Container stops

Example:
```bash
docker run ubuntu
```
The container exits immediately because no long-running process exists.

---

## 3. First Step: Check Container State

```bash
docker ps -a
```

Look for:
- Exited containers
- Exit codes
- Restart loops

Exit codes matter:
- `0` → normal exit
- `1` → application error
- `137` → killed (often OOM)

---

## 4. Logs: Your Primary Debugging Tool

```bash
docker logs <container>
```

Use logs to:
- Identify startup failures
- Inspect stack traces
- Detect misconfiguration

Tips:
- Use `--tail`
- Use `-f` for streaming logs

---

## 5. Exec Into a Running Container

```bash
docker exec -it <container> /bin/sh
```

Use cases:
- Inspect environment variables
- Check file paths
- Test connectivity

⚠ If container exits immediately, exec will not work.

---

## 6. Inspect: The Most Underused Command

```bash
docker inspect <container>
```

Inspect reveals:
- Environment variables
- Mounts and volumes
- Network settings
- Restart policy
- Exit reason

This is critical for production debugging.

---

## 7. Debugging Crash Loops

Symptoms:
- Container repeatedly restarts
- Logs repeat startup messages

Steps:
1. Check logs
2. Disable restart policy temporarily
3. Run container interactively
4. Fix root cause

---

## 8. Resource-Related Failures

### OOM (Out of Memory)
Symptoms:
- Exit code 137
- No clear error logs

Fix:
- Increase memory limit
- Fix memory leak
- Optimize JVM/Node settings

---

## 9. Networking Debugging

Common problems:
- Service unreachable
- Connection refused

Checklist:
- Correct network
- Correct ports
- DNS resolution
- Firewall rules

Commands:
```bash
docker network inspect
ping <service>
```

---

## 10. Hands-on Lab (Essential)

### Objective
Debug a failing container.

### Steps
1. Run a container with wrong config
2. Observe logs
3. Inspect container
4. Fix configuration
5. Restart container

Focus on:
- Repeatability
- Minimal changes

---

## 11. Common Beginner Debugging Mistakes

- Rebuilding image blindly
- Restarting container repeatedly without logs
- Debugging inside container only
- Ignoring exit codes

---

## 12. Interview Q&A (Practical)

**Q: Why does a container stop immediately?**  
Main process exited.

**Q: How do you debug a crashloop?**  
Logs → inspect → interactive run.

**Q: Exit code 137 means?**  
OOM kill.

---

## 13. Real Production Failure Scenario

### Scenario
Application containers entered crash loop after deployment.

### Root Cause
- Missing environment variable
- Restart policy hid root cause

### Impact
- Service outage
- Delayed diagnosis

### Fix
- Checked logs and inspect output
- Added config validation
- Improved startup logging

---

## Final Takeaway

Effective Docker debugging requires:
- Calm analysis
- Proper tooling
- Understanding container behavior

If you master:
> **logs, exec, inspect, and lifecycle**,

you can debug **90% of Docker issues quickly**.