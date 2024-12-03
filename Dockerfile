# Use the latest official Tomcat image
FROM tomcat:latest

# Copy the WAR file into the webapps directory of Tomcat
# Replace 'your-app.war' with the name of your WAR file
COPY ./target/capstone.war /usr/local/tomcat/webapps/Capstone.war

# Expose the default Tomcat port (8080)
EXPOSE 8080

# Run Tomcat in the foreground
CMD ["catalina.sh", "run"]