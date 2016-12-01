# JSP-CodeCommunity
JSP Servlet MVC Demo - social platform

Feature: server and client side security for login/ registration/and adminstrative privilages.<br/>
Feature: some dynamic content based on user interactions, login to logout, submitted questions shown in a test page.<br/>
Feature: should fail gracefully?<br/><br/>
IMPORTANT: change the "role" in the DataBase below to ADM or MDT to access secure locations.<br/>

ScreenShots: https://github.com/morlinski/JSP-CodeCommunity/issues/1

(DataBase For MySQL)

CREATE DATABASE dbcodecomm;
USE dbcodecomm;
CREATE TABLE  dbcommUsers ( username VARCHAR(30) NOT NULL PRIMARY KEY, password VARCHAR(64) NOT NULL, salt VARCHAR(128) NOT NULL, points INT(255) NOT NULL DEFAULT 0, removed BOOLEAN DEFAULT false, reason VARCHAR(1000) NOT NULL DEFAULT "", role VARCHAR(10) NOT NULL DEFAULT "usr");
