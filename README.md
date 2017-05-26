# shadow-messenger

#  Red Hat Consulting Java EE Tutorial - shadow-messenger

#  Purpose
Java EE consists of several technologies that can work together. When learning Java EE, it can be difficult to see how to compose the tools of Java EE into a working application. This document outlines the requirements for a software application that can be implemented using Java EE and run on JBoss EAP. The intention is to present requirements that drive the usage of different Java EE technologies including JPA, JMS, and CDI.
 
#  Scenario
A customer within Red Hat wants to track mentions of Red Hat products on social media. The customer has provided a tool that will scrape social media for you, and will provide the text of the "mention" to you via JMS.
 
#  Assignment 1
Design and implement an application that does the following:
accepts messages via JMS that contain a product "mention" String
The product name will be preceded by a hash ('#')
Sample product mention: #JBoss is good!
listens to the JMS queue at: java:/jms/queue/shadowmessage
prints the message in the JBoss EAP server log
Also, do the following:
create a git repository for the project, and push the repo to either GitHub or the Red Hat Consulting GitLab
use Maven to build the application
create a client or test that can be used to verify the application works properly
