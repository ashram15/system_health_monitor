package com.first.system_mointor_sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemMointorSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemMointorSensorApplication.class, args);
	}

}

//How to Understand command line text
//# This is the database connection pool (Hikari) starting up
//com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
//
//        # This is the most beautiful line in the log. It's proof!
//        # It successfully connected to your Postgres database.
//        com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@...
//
//        # The connection pool is ready!
//com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
//
//        # ... (other stuff)
//
//        # The web server (Tomcat) started successfully.
//o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
//
//        # The final, beautiful "You did it!" message.
//        c.f.s.SystemMointorSensorApplication     : Started SystemMointorSensorApplication in 1.21 seconds