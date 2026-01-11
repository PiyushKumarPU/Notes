# Docker Installation & Internals

## Summary (Quick Revision)
- Docker runs **natively on Linux**, but uses a **lightweight Linux VM** on Windows and macOS
- Docker follows a **client–server architecture** (CLI ↔ daemon)
- `dockerd` manages images, containers, networks, and volumes
- Incorrect installation or permissions are the **#1 beginner issue** with Docker

---

## 1. Why Installation Differs by Operating System

Docker relies on **Linux kernel features** such as:
- namespaces
- cgroups

### OS-wise behavior
- **Linux**: Has these features natively → Docker runs directly on host
- **Windows/macOS**: Do not have a Linux kernel → Docker runs inside a Linux VM

This explains:
- Why Docker Desktop is required on Windows/macOS
- Why performance and filesystem behavior may differ

---

## 2. Official Installation Reference (Authoritative)

Docker installation steps vary by OS and distribution.

For **official, up-to-date, platform-specific installation instructions**, always refer to:

👉 https://docs.docker.com/engine/install/

This documentation covers:
- Linux distributions (Ubuntu, Debian, RHEL, CentOS, Amazon Linux, etc.)
- Windows installation
- macOS installation
- Post-install configuration
- Package manager–based installs
- Uninstall instructions

⚠️ **Best practice**: Always prefer official documentation over third-party blogs for installation.

---

## 3. Docker on Linux (Native Installation)

### How It Works
- `dockerd` runs directly on the Linux host
- Containers share the host Linux kernel
- Lowest overhead and best performance

### Permissions (Very Important)

By default:
- Docker requires **root privileges**

Common beginner fix:
```bash
sudo usermod -aG docker $USER
```

⚠️ Requires **logout/login** to take effect.

---

## 4. Docker on Windows & macOS (Docker Desktop)

### What Docker Desktop Actually Does
- Creates a **minimal Linux VM**
- Runs Docker Engine inside that VM
- Integrates CLI with your host OS

Conceptual flow:
```
Your OS → Docker CLI → Linux VM → Docker Daemon → Kernel
```

---

## 5. Docker Engine Internals

- Docker CLI
- Docker Daemon (`dockerd`)
- Container Runtime

---