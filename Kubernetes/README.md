# ☸️ Kubernetes Handbook — Deep Dive Edition

A **production-grade, beginner-friendly Kubernetes handbook** designed to help you:
- Understand Kubernetes from **first principles**
- Confidently work with Kubernetes in real projects
- Prepare for **interviews and on-call scenarios**
- Avoid common **production failures and design mistakes**

This handbook follows the same philosophy as the Docker handbook:
> **Deep understanding over shallow commands**

---

## 📌 What Makes This Kubernetes Handbook Different

Each chapter will be written with **the same rigor and depth** as the Docker handbook and will include:

- ✅ **Quick revision summary** (for fast recall)
- 🧠 **Deep conceptual explanation** (why Kubernetes works the way it does)
- 🧩 **Mental models** to simplify complex internals
- 🛠 **Hands-on labs** (with what to observe and why)
- 🎯 **Interview-grade Q&A**
- 🚨 **Real production failure scenarios** with root-cause analysis
- 📎 Clean, copy-safe Markdown

No fluff. No vendor hype. Only **practical Kubernetes knowledge**.

---

## 🗂️ Planned Table of Contents

### Core Foundations
1. [Kubernetes Overview & Architecture](./01-Kubernetes-Overview-and-Architecture.md)
2. [Kubernetes Installation & Cluster Setup](./02-Kubernetes-Installation-and-Cluster-Setup.md)
3. [kubectl & API Server Basics](./03-kubectl-and-API-Server-Basics.md)

### Core Objects (Workload Fundamentals)
4. [Pods (The Smallest Unit) ](./04-Pods-The-Smallest-Unit.md)
5. [ReplicaSets & Deployments](./05-ReplicaSets-and-Deployments.md)
6. [Services (ClusterIP, NodePort, LoadBalancer) ](./06-Services-ClusterIP-NodePort-LoadBalancer.md)

### Configuration & State
7. [ConfigMaps & Secrets  ](./07-ConfigMaps-and-Secrets.md)
8. [Volumes & Persistent Volumes  ](./08-Volumes-and-Persistent-Volumes.md)
9. [StatefulSets vs Deployments  ](./09-StatefulSets-vs-Deployments.md)

### Networking & Traffic
10. [Kubernetes Networking Model  ](./10-Kubernetes-Networking-Model.md)
11. [Ingress & Ingress Controllers  ](./11-Ingress-and-Ingress-Controllers.md)
12. [DNS & Service Discovery  ](./12-DNS-and-Service-Discovery.md)

### Scheduling & Operations
13. [Scheduling, Requests & Limits  ](./13-Scheduling-Requests-and-Limits.md)
14. [Health Checks & Probes  ](./14-Health-Checks-and-Probes.md)
15. [Scaling (HPA, VPA, Autoscaling Concepts)  ](./15-Scaling-HPA-VPA-and-Autoscaling-Concepts.md)

### Security
16. [RBAC & Authentication  ](./16-RBAC-and-Authentication.md)
17. [Pod Security & Runtime Security  ](./17-Pod-Security-and-Runtime-Security.md)
18. [Network Policies  ](./18-Network-Policies.md)

### Observability & Troubleshooting
19. [Logging & Monitoring  ](./19-Logging-and-Monitoring.md)
20. [Debugging Kubernetes Workloads  ](./20-Debugging-and-Troubleshooting.md)
21. [Upgrades-Backups-and-Disaster-Recovery](./21-Upgrades-Backups-and-Disaster-Recovery.md)

### CI/CD & Advanced Topics
22. [Kubernetes in CI/CD Pipelines  ](./22-CICD-and-GitOps.md)
23. [Service-Mesh-Istio-Linkerd-and-Traffic-Management](./23-Service-Mesh-Istio-Linkerd-and-Traffic-Management.md)
23. [Helm Basics & Package Management  ](./23-Helm-Basics-and-Package-Management.md)
24. [When NOT to Use Kubernetes  ](./24-When-NOT-to-Use-Kubernetes.md)

---

## 🧭 How to Use This Handbook

### If you are new to Kubernetes
➡ Start from **Topic 1** and go sequentially.  
Do not skip fundamentals — Kubernetes is opinionated for a reason.

### If you already use Kubernetes
➡ Jump to:
- Scheduling & networking
- Security chapters
- Debugging and production failures

### If you are preparing for interviews
➡ Focus on:
- Mental models
- Failure scenarios
- “Why” questions, not YAML memorization

---

## ⚠️ Core Philosophy

> Kubernetes is a **distributed system**, not a deployment tool.

Most Kubernetes problems happen because:
- Fundamentals are skipped
- YAML is copied without understanding
- Docker mental models are incorrectly applied

This handbook fixes that.

---

## 📦 Planned Enhancements
- ASCII diagrams for architecture and scheduling
- Real `kubectl` debugging walkthroughs
- Kubernetes vs Docker mental model mappings
- Printable PDF version

---
