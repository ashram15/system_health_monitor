# Distributed System Health Monitor 📊

### [🎬 Click Here to Watch the Live Demo (30s)](https://youtu.be/eHVEebnDgbk)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)


### About this Project
This project is a distributed system designed to monitor the health (CPU, RAM, Disk usage) of multiple remote machines in real-time. It was architected to simulate enterprise-level infrastructure monitoring tools like Datadog or Prometheus.

It consists of two distinct microservices:

The Agent: A lightweight Java application that runs on client machines, harvesting system metrics via the Oshi library and transmitting them via HTTP.

The Server: A robust Spring Boot backend that ingests high-frequency data streams, persists them to a PostgreSQL database, and exposes them via a RESTful API.

### Technical Architecture

Backend: Java (Spring Boot)

Database: PostgreSQL (Time-series data storage)

Client: Java (Oshi SystemInfo Library)

Communication: RESTful API (JSON payloads)

Design Pattern: Client-Server / Distributed Nodes

### Key Features

Real-Time Monitoring: Ingests system metrics every 5 seconds from distributed agents.

Persistent Storage: Designed efficient database schemas to handle continuous time-series writes.

REST API: Exposes endpoints for frontend dashboards or external analysis tools to query historical health data.

### Project Structure

This is a monorepo containing both services:

```bash
system-health-monitor/
├── server/     # Spring Boot Application (The "Brain")
└── agent/      # Java Client Application (The "Sensor")
```