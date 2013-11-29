package fr.utbm.lp76.alerts.services;

public interface EdgeManagerService {
	
	/**
	 * read the triggers from the database, calculate alerts changes and send changes to JMS and database
	 */
	public void processTriggers();
}
