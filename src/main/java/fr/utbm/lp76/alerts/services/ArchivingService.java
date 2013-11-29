package fr.utbm.lp76.alerts.services;

import fr.utbm.lp76.alerts.model.AlertHis;

public interface ArchivingService {
	
	/**
	 * log into database a new change of state of an alert
	 * @param ah
	 */
	public void logAlertChange(AlertHis ah);
}
