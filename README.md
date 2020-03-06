# WebServer

This is REST-API server created with Spring Boot, deployed on Digital Ocean and with data keeped in SQL-host.

Now serving this mobile app:
https://play.google.com/store/apps/details?id=com.anxim.gameofthronesquiz

Instruction to install (recommended):

1. Copy .git link
2. Click "Get from Version Control", paste link and clone project
3. When project initializing complete, set up build settings: "Add Configuration -> Spring Boot -> Apply"
4. Set up main class as harelchuk.maxim.throneserver.ServerApplication
5. In your MySQL server create database based on template named "web_server_database"
6. Fill file application.properties with this lines:
    spring.jpa.hibernate.ddl-auto=none
    spring.datasource.url=jdbc:mysql://127.0.0.1:8080/YOUR_DATABASE_NAME?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
    spring.datasource.username=YOUR_DATABASE_USERNAME
    spring.datasource.password=YOUR_DATABASE_PASSWORD
7. Run project and test it in browser (add to url lines like 127.0.0.1:8080)
8. Done

