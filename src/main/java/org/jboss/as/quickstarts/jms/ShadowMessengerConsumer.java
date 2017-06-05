package org.jboss.as.quickstarts.jms;

import java.util.logging.Logger;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ShadowMessengerConsumer {
  private static final Logger log = Logger.getLogger(ShadowMessengerConsumer.class.getName());
  private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
  private static final String DEFAULT_DESTINATION = "jms/queue/shadowmessage";
  private static final String DEFAULT_MESSAGE_COUNT = "1";
  private static final String DEFAULT_USERNAME = "quickstartUser";
  private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
  private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
  private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
    
  public static void main(String[] args) {
	  Context namingContext = null;
	  
	  try {
		  String userName = System.getProperty("username", DEFAULT_USERNAME);
          String password = System.getProperty("password", DEFAULT_PASSWORD);
          // Set up the namingContext for the JNDI lookup
          final Properties env = new Properties();
          env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
          env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
          env.put(Context.SECURITY_PRINCIPAL, userName);
          env.put(Context.SECURITY_CREDENTIALS, password);
          namingContext = new InitialContext(env);
          
          // Perform the JNDI lookups
          String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
          log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
          ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
          log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");
          
          String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
          log.info("Attempting to acquire destination \"" + destinationString + "\"");
          Destination destination = (Destination) namingContext.lookup(destinationString);
          log.info("Found destination \"" + destinationString + "\" in JNDI");
          
          try (JMSContext context = connectionFactory.createContext(userName, password)) {
        	  // Create JMS Consumer
        	  JMSConsumer consumer = context.createConsumer(destination);
        	  for (int i = 0; i < 3; i++) {
        		  String text = consumer.receiveBody(String.class, 5000);
        		  log.info(text);
        	  }
          }
	  } catch (NamingException e) {
		  log.severe(e.getMessage());
		  
	  } finally {
		  if (namingContext != null) {
			  try {
				  namingContext.close();
			  } catch (NamingException e) {
				  log.severe(e.getMessage());
				  
			  }
		  }
	  }
  }
}
