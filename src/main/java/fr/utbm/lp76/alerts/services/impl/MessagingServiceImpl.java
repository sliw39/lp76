package fr.utbm.lp76.alerts.services.impl;

import java.util.Hashtable;

import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.services.MessagingService;

@Service("MessagingService")
public class MessagingServiceImpl implements MessagingService {
	private static final String JNDI_PREFIX = "cn=";
	private static final String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.fscontext.RefFSContextFactory";
	private static final String PROVIDER_URL = "tcp://localhost:61616";
	private static final String QUEUE_CONNECTION_FACTORY = "QueueConnectionFactory";
	private static final String QUEUE = "Alertes";
    
	@SuppressWarnings("rawtypes")
	private Hashtable env;
	private InitialContext context;
	private QueueConnectionFactory qcf;
	private QueueConnection connection;
    
	private QueueSession session;
	private Queue queue;
	private QueueSender sender;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sendAlert(AlertHis ah) {
		MapMessage message;
        env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, PROVIDER_URL);

        // Create a JNDI context and a JMS connexion
        try {
            context = new InitialContext(env);
            qcf = (javax.jms.QueueConnectionFactory)context.lookup(JNDI_PREFIX + QUEUE_CONNECTION_FACTORY);
            connection = qcf.createQueueConnection();

            // Create JMS session and QueueSender
            try {
    		    session = connection.createQueueSession(true, 0);
    		    queue = (javax.jms.Queue)context.lookup(JNDI_PREFIX + QUEUE);
    		    sender = session.createSender(queue);
    		    
    		    // Create and send JMS MapMessage
    		    message = session.createMapMessage();
    		    
    		    // Filling header
    		    
    		    
    		    // Filling the message
    		    message.setString("ALR_CODE", ah.getTrigger().getAlert().getCode());
    		    message.setString("ALR_LABEL", ah.getTrigger().getAlert().getLabel());
    		    message.setString("ALR_DESCRIPTION", ah.getTrigger().getAlert().getDesciption());
    		    message.setBoolean("ALR_STATE", ah.getState());
    		    message.setInt("SEN_ID", ah.getTrigger().getSensor().getId());
    		    message.setString("SEN_LABEL", ah.getTrigger().getSensor().getLabel());
    		    
    		    sender.send(message);
    		    session.commit();
    		    
    		    // Close JNDI context
    		    try {
    		        context.close();
    		    } catch (javax.naming.NamingException ne) {
    		        ne.printStackTrace();
    		    }
    		    
    		    // Close the JMS connection
    		    try {
    		        connection.close();
    		    } catch (javax.jms.JMSException jms) {
    		        jms.printStackTrace();
    		    }
    		} catch (javax.naming.NamingException ne) {
    		    ne.printStackTrace();
    		} catch (javax.jms.JMSException jms) {
    		    jms.printStackTrace();
    		}
        } catch (javax.naming.NamingException ne) {
            ne.printStackTrace();
        } catch (javax.jms.JMSException jms) {
            jms.printStackTrace();
        }
	}
}
