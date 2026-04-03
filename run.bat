@echo off
cd /d "C:\Users\Ajay Kumar\OneDrive\Desktop\mini project"
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
echo Starting JalDhara Application...
echo.
call mvnw.cmd spring-boot:run
pause