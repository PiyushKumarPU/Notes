# Kubernetes Installation & Cluster Setup (Deep Dive)

## Summary (Quick Revision)
- Kubernetes can run **locally, on-prem, or in the cloud**
- Installation choice affects **operations, security, and reliability**
- Production clusters require **HA control plane and backups**
- Most beginner issues stem from **treating local setups like production**
- Installation is an **operational decision**, not just a technical one

---

## 1. Why Installation Strategy Matters

Kubernetes is a **distributed system**.

Your installation choice determines:
- Who manages control-plane failures
- How upgrades happen
- Who handles security patches
- How much operational burden you carry

Bad installation decisions lead to:
- Fragile clusters
- Upgrade fear
- Unrecoverable outages

---

## 2. Kubernetes Deployment Models

### 1. Local Clusters (Learning & Dev)

Tools:
- Minikube
- kind
- Docker Desktop Kubernetes

Characteristics:
- Single-node or pseudo multi-node
- Simplified networking
- No HA

Use for:
- Learning
- Development
- CI testing

Never treat these as production clusters.

---

### 2. Self-Managed Clusters (On-Prem / Cloud VMs)

Examples:
- kubeadm-based clusters
- Bare-metal clusters

Characteristics:
- Full control
- Full responsibility
- Requires deep Kubernetes knowledge

You manage:
- etcd
- Certificates
- Networking
- Upgrades

---

### 3. Managed Kubernetes (Recommended for Production)

Examples:
- EKS
- GKE
- AKS

Characteristics:
- Managed control plane
- Automated upgrades (optional)
- Higher reliability

Tradeoff:
- Less control
- Vendor-specific integrations

---

## 3. Local Cluster Setup (Hands-on)

### Minikube (Beginner Friendly)

```bash
minikube start
kubectl get nodes
```

What to observe:
- Single node acting as control plane + worker
- Limited HA
- Simplified networking

---

### kind (CI-Friendly)

```bash
kind create cluster
kubectl get nodes
```

Used heavily in:
- CI pipelines
- Automated testing

---

## 4. Cluster Components Installed During Setup

Every cluster includes:
- API Server
- etcd
- Scheduler
- Controller Manager
- kubelet
- kube-proxy

Installation ensures:
- Secure communication
- Certificate generation
- Component bootstrapping

---

## 5. Control Plane High Availability (Production Critical)

Production clusters require:
- Multiple API servers
- Multi-node etcd
- Load-balanced access

Why:
- Single control-plane node = single point of failure

HA is **not optional** in production.

---

## 6. Networking During Installation

Key decisions:
- CNI plugin (Calico, Cilium, Flannel)
- Pod CIDR ranges
- Service CIDR ranges

Wrong networking choices cause:
- Pod communication failures
- Scaling issues
- Security limitations

---

## 7. Storage Setup Considerations

During installation, plan for:
- PersistentVolume support
- CSI drivers
- Backup strategy

Storage decisions are hard to change later.

---

## 8. Security Setup at Install Time

Critical security aspects:
- RBAC enabled
- Secure API server access
- Certificate rotation
- Node authentication

Never run:
- Anonymous API access
- Insecure ports

---

## 9. Hands-on Lab (Foundational)

### Objective
Understand cluster differences.

### Steps
1. Create a local cluster
2. Inspect control-plane pods
3. Identify node roles
4. Compare with managed cluster docs

Focus:
- What is abstracted vs exposed

---

## 10. Common Beginner Installation Mistakes

- Treating Minikube as production
- No backups for etcd
- Ignoring upgrade strategy
- Hardcoding networking ranges
- Skipping security defaults

---

## 11. Interview Q&A (Practical)

**Q: Difference between Minikube and EKS?**  
Minikube is local dev; EKS is managed production.

**Q: Why is HA control plane important?**  
Avoids cluster-wide outages.

**Q: What does kubeadm do?**  
Bootstraps a Kubernetes cluster.

---

## 12. Real Production Failure Scenario

### Scenario
Entire cluster went down during node reboot.

### Root Cause
- Single control-plane node
- No HA setup

### Impact
- All workloads inaccessible
- API server unavailable

### Fix
- Multi-node control plane
- Load balancer
- Managed Kubernetes

---

## Final Takeaway

Kubernetes installation is a **design decision**.

If you remember one rule:
> **Use local clusters for learning, managed clusters for production**

Everything else flows from that choice.