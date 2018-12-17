**Project Title**  
Catmash

**Description**  
This application uses java ee servlets for showing cat pictures, and their scoreboard about the number of votes they get from visitors.
The pictures are retrieved from JSON webservice. The two branches are showing two different persistence solutions.
Used technologies: java ee web api, JSON api, JDBC api, HTML, CSS, JSP, JavaScript

**Prerequisites**  
For installing this application, you need maven (version 3.6-0) for building and install. Tomcat servlet (version: 7.0.92) is used
for deploying. For master branch, ojdbc driver is needed from Oracle and an Oracle 10g database (or similar version). 
For "file_persitence" branch, database related tools are not needed.
(https://www.oracle.com/technetwork/apps-tech/jdbc-10201-088211.html).

**Install**  
Just download the source and the ojdbc driver for the master branch. In the database, a "T_SCORE" table is needed with an "ID"(varchar) and a "SCORE"(number) column. For the file_persistence branch, just download it. Use maven install command for install.

**Deploy**  
Use Tomcat web application manager to deploy the .war file

**Authors**  
Gabor Madarasz

**License**  
The ojdbc driver is under Oracle license (https://www.oracle.com/technetwork/licenses/distribution-license-152002.html) 
