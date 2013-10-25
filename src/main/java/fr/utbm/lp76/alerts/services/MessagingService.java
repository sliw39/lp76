package fr.utbm.lp76.alerts.services;

import fr.utbm.lp76.alerts.model.AlertHis;

public interface MessagingService {
	
	/**
	 * sendAlert is used to send a new message to the ActivMQ server. It takes an AlertHis object with will be use to fill the new message.
	 * @param ah The alert history to publish to the ActivMQ server. If it's null the method will generate an JavaNullPointerException.
	 */
	public void sendAlert(AlertHis ah);
}
