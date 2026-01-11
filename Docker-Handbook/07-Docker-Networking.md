# Docker Networking (Deep Dive)

## Summary (Quick Revision)
- Docker networking enables **container-to-container** and **container-to-host** communication
- Docker provides **network isolation by default**
- User-defined networks include **automatic DNS-based service discovery**
- Most networking issues come from **misunderstanding ports vs networks**
- Correct networking design is critical for multi-container apps

---

## 1. Why Docker Networking Is Different from VM Networking

Containers:
- Share the host kernel
- Do NOT get their own full OS network stack
- Use Linux network namespaces

This makes Docker networking:
- Lightweight
- Fast
- Easy to misconfigure

Mental model:
- VM networking = full virtual NIC
- Container networking = isolated process network namespace

---

## 2. Core Docker Networking Concepts

### Network Namespace
Each container gets:
- Its own IP address
- Its own routing table
- Its own network interfaces

Containers cannot see each other unless connected to the same network.

---

## 3. Default Docker Network Types

Docker provides several built-in network drivers.

### 1. Bridge (Default)
- Default network for containers
- Containers get private IPs
- NAT used to reach host

Use case:
- Local development
- Simple container communication

---

### 2. Host Network
- Container shares host network stack
- No isolation
- No port mapping needed

Use case:
- Performance-critical workloads
- Debugging networking issues

⚠ Risky in production.

---

### 3. None Network
- No network access
- Fully isolated

Use case:
- Batch jobs
- Security-sensitive workloads

---

## 4. User-Defined Bridge Networks (Very Important)

```bash
docker network create app-net
```

Benefits:
- Automatic DNS resolution
- Container name = hostname
- Better isolation than default bridge

Example:
```bash
docker run -d --name db --network app-net postgres
docker run -d --name api --network app-net my-api
```

`api` can connect to `db` using hostname `db`.

---

## 5. Ports vs Networks (Common Confusion)

### Exposing Ports
```bash
docker run -p 8080:80 nginx
```

- Makes container accessible from host
- NOT required for container-to-container communication

### Internal Communication
- Containers on same network communicate via private IP / DNS
- No port publishing needed

---

## 6. DNS-Based Service Discovery

Docker runs an embedded DNS server.

Features:
- Container names resolve to IPs
- Dynamic IP changes handled automatically

This eliminates:
- Hardcoded IPs
- Manual host configuration

---

## 7. Hands-on Lab (Essential)

### Objective
Understand container communication.

### Steps
1. Create a user-defined network
2. Run two containers on it
3. Exec into one container
4. Ping the other by name

Observe:
- DNS resolution
- Network isolation

---

## 8. Common Networking Mistakes

- Publishing ports unnecessarily
- Using default bridge instead of user-defined network
- Hardcoding IP addresses
- Exposing internal services publicly
- Assuming localhost works across containers

---

## 9. Docker Networking in Production

Best practices:
- One network per application stack
- Minimal port exposure
- Use reverse proxies (NGINX, Traefik)
- Avoid host networking unless required

---

## 10. Interview Q&A (Practical)

**Q: How do containers discover each other?**  
Via Docker’s embedded DNS on user-defined networks.

**Q: Difference between bridge and host network?**  
Bridge provides isolation; host shares host network stack.

**Q: Does EXPOSE publish ports?**  
No. It is documentation only.

---

## 11. Real Production Failure Scenario

### Scenario
Microservices could not communicate after deployment.

### Root Cause
- Containers were attached to default bridge
- DNS resolution unavailable

### Impact
- Service downtime
- Manual IP debugging

### Fix
- Switched to user-defined bridge network
- Used container names for communication

---

## Final Takeaway

If you remember one rule:
> **Use user-defined networks and container names — not IPs**

Correct Docker networking prevents:
- Connectivity bugs
- Security exposures
- Production outages