# Docker Overview

## Summary (Quick Revision)

* Docker is an **OS-level containerization platform**, not a virtual machine platform
* Containers package **application + runtime + dependencies** in a portable unit
* Containers **share the host OS kernel**, making them lightweight and fast
* Docker standardizes how applications are built, shipped, and run
* Core building blocks: **Docker Engine, Images, Containers, Registry**

---

## 1. Why Docker Exists (Real Problem Statement)

Before Docker, application deployment typically involved:

* Manually configuring servers
* Installing language runtimes and system libraries by hand
* Environment-specific differences (dev ≠ test ≠ prod)

This caused recurring problems:

* "Works on my machine" failures
* Long onboarding time for new developers
* Fragile deployments
* Difficult rollbacks

Docker solves this by **standardizing the runtime environment**.
If it runs in Docker on your laptop, it will run the same way on:

* QA servers
* Cloud VMs
* CI/CD pipelines
* Production clusters

---

## 2. What Docker Is (And Is Not)

### What Docker IS

* A **platform** for building, packaging, and running applications
* A **container runtime** built on Linux kernel features
* A way to achieve **process isolation + reproducibility**

### What Docker IS NOT

* ❌ Not a virtual machine
* ❌ Not an operating system
* ❌ Not Kubernetes
* ❌ Not limited to microservices

A container is still just a **process**, not a machine.

---

## 3. Containers vs Virtual Machines (Critical Understanding)

| Aspect          | Containers       | Virtual Machines  |
| --------------- | ---------------- | ----------------- |
| OS Kernel       | Shared with host | Separate guest OS |
| Startup Time    | Seconds          | Minutes           |
| Resource Usage  | Low              | High              |
| Isolation Level | Process-level    | Hardware-level    |
| Image Size      | MBs              | GBs               |

### Mental Model

* **VM** = Independent house
* **Container** = Apartment in the same building

Containers trade **strong isolation** for **speed and efficiency**.

---

## 4. How Docker Actually Works (Internals – Beginner Friendly)

Docker relies on Linux kernel primitives:

### Namespaces (Isolation)

* PID namespace → isolates processes
* NET namespace → isolates networking
* MNT namespace → isolates filesystem
* UTS namespace → isolates hostname

Each container believes it is running alone.

### cgroups (Resource Control)

* CPU limits
* Memory limits
* Disk I/O limits

Without cgroups, one container could crash the entire host.

Docker combines **namespaces + cgroups** to safely run containers.

---

## 5. Docker Engine Architecture

Docker uses a **client–server model**.

### Components

* **Docker CLI** – `docker` command you type
* **Docker Daemon (dockerd)** – background service
* **Container Runtime** – actually runs containers

### Flow

```
Docker CLI → Docker Daemon → Container Runtime → Kernel
```

This explains why:

* Docker commands work even remotely
* Docker needs elevated privileges

---

## 6. Core Docker Concepts

### Image

* Immutable template
* Built using a Dockerfile
* Stored in a registry

### Container

* Running instance of an image
* Has lifecycle (start, stop, remove)
* Ephemeral by default

### Registry

* Central place to store images
* Docker Hub (public)
* Cloud registries (ECR, GCR, ACR)

---

## 7. Typical Docker Workflow (End-to-End)

1. Write application code
2. Write Dockerfile
3. Build image (`docker build`)
4. Push image to registry
5. Pull and run container anywhere

The **same image** is used in every environment.

---

## 8. Hands-on Lab (Foundational)

### Objective

Understand Docker’s client–server model and container execution.

### Steps

```bash
docker run hello-world
```

What happens:

* CLI contacts daemon
* Daemon pulls image (if missing)
* Container runs and exits

```bash
docker info
```

Observe:

* Storage driver
* Cgroup driver
* Number of containers and images

---

## 9. Common Beginner Misconceptions

* Containers are mini VMs ❌
* Container data persists by default ❌
* Docker automatically secures apps ❌
* Restarting container == rebuilding image ❌

Understanding these early prevents production issues.

---

## 10. Interview Q&A (Realistic)

**Q: Is Docker virtualization?**
A: No. Docker uses OS-level isolation, not hardware virtualization.

**Q: Why is Docker faster than VMs?**
A: No guest OS, shared kernel, fewer resources.

**Q: Why does Docker need root access?**
A: It interacts with kernel namespaces and cgroups.

---

## 11. Real Production Failure Scenario

### Scenario

A team deployed containers assuming they were permanent servers.
They stored uploaded files inside container filesystem.

### What Happened

* Containers were recreated during deployment
* Files were lost
* No backups existed

### Root Cause

* Containers are ephemeral by design
* Filesystem is destroyed on container removal

### Correct Approach

* Use volumes or object storage
* Treat containers as disposable

---

## 12. When Docker Is a Bad Fit

* GUI-heavy desktop applications
* Real-time kernel-level workloads
* Extremely latency-sensitive systems

Docker is powerful—but not universal.

---

## Final Mental Model (Revision Gold)

* Dockerfile = Blueprint
* Image = Packaged artifact
* Container = Running process
* Registry = Artifact repository

