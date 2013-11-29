package fr.utbm.lp76.alerts.services.impl;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.services.MessagingService;

@Service("MessagingService")
public class MessagingServiceImpl implements MessagingService {
	@Autowired
	private JmsTemplate template = null;

	// @PostConstruct
	public void sendAlert(final AlertHis ah) {
		template.send(new MessageCreator() {
			public Message createMessage(final Session session)
					throws JMSException {
				// Create and send JMS MapMessage
				final MapMessage message = session.createMapMessage();

				// Filling the message
				message.setString("ALR_CODE", ah.getTrigger().getAlert()
						.getCode());
				message.setString("ALR_LABEL", ah.getTrigger().getAlert()
						.getLabel());
				message.setString("ALR_DESCRIPTION", ah.getTrigger().getAlert()
						.getDesciption());
				message.setBoolean("ALR_STATE", ah.getState());
				message.setInt("SEN_ID", ah.getTrigger().getSensor().getId());
				message.setString("SEN_LABEL", ah.getTrigger().getSensor()
						.getLabel());
				return message;
			}
		});
	}
}