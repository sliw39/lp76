package fr.utbm.lp76.alerts.services.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.services.MessagingService;

@Service("MessagingService")
public class MessagingServiceImpl implements MessagingService {

	public void sendAlert(AlertHis ah) {
		Context context = null;
		ConnectionFactory factory = null;
		Connection connection = null;
		Destination destination = null;
		Session session = null;
		MessageProducer sender = null;
		
		try {
			context = new InitialContext();
			factory = (ConnectionFactory) context.lookup("ConnectionFactory");
			destination = (Destination) context.lookup("AlertQueue");
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createProducer(destination);
			connection.start();

			// Create and send JMS MapMessage
			final MapMessage message = session.createMapMessage();

			// Filling header
			
			
			// Filling the message
			message.setString("ALR_CODE", ah.getTrigger().getAlert().getCode());
			message.setString("ALR_LABEL", ah.getTrigger().getAlert()
					.getLabel());
			message.setString("ALR_DESCRIPTION", ah.getTrigger().getAlert()
					.getDesciption());
			message.setBoolean("ALR_STATE", ah.getState());
			message.setInt("SEN_ID", ah.getTrigger().getSensor().getId());
			message.setString("SEN_LABEL", ah.getTrigger().getSensor()
					.getLabel());

			sender.send(message);
			//session.commit();
			
			System.out.println("Message envoyé");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
