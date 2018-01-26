# Poker Trial Task

Notes:
The repository contains the source code (in src folder) and the war file (poker.war) generated.


Deployment Instructions:
1. Install JDK/JRE 1.8
2. Set the environment variables JAVA_HOME = <path where the JDK is installed>
3. Install apache-tomcat-9.0.2 (Eg: D:\apache-tomcat-9.0.2)
4. Set the environment variable CATALINA_HOME
Eg: CATALINA_HOME = C:\apache-tomcat-9.0.2
5. Place the poker.war (downloaded from the Github repository) under the webapps folder (Eg: D:\apache-tomcat-9.0.2\webapps)
6. Edit the web.xml to include the home.jsp 
 <welcome-file-list>
	<welcome-file>home.jsp</welcome-file>
 </welcome-file-list>
7. If port 8080 (default port) is already in use, change it to a different port (7070). Edit server.xml and change the port
<Connector port="7070" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
8. Start the tomcat (you may use startup.bat under D:\apache-tomcat-9.0.2\bin


