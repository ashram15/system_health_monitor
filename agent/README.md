# Java System Health Monitor (Agent)

This is the **Agent** (or "sensor") component of a two-part system health monitoring application. This is a lightweight, standalone Java program designed to run on any machine you want to monitor.

Its only job is to:
1.  Collect real-time system metrics (CPU, RAM, Disk).
2.  Send these metrics to the central backend server.

The corresponding **Backend Server** (built with Spring Boot and PostgreSQL) can be found in its own repository here:

**[https://github.com/ashram15/system-monitor-sensor](https://github.com/ashram15/system-mointor-sensor)**

---

### Tech Stack

* **Language:** Java 17
* **Build Tool:** Maven
* **Key Libraries:**
    * **[Oshi](https://github.com/oshi/oshi):** A cross-platform library for collecting system hardware and software information.
    * **Jackson:** For serializing the Java `SystemMetric` object into a JSON string.
    * **Java 11+ `HttpClient`:** For sending the metrics to the server's RESTful API.

---