package fr.utbm.lp76.alerts.services;
import java.util.List;
import fr.utbm.lp76.alerts.model.*;

public interface AcquisitionService {
	
	/**
	 * Read all alerts from database
	 * @return the list of alerts
	 */
	public List<Alert> getAllAlerts();
	
	/**
	 * Read all active triggers from database
	 * @return the list of triggers
	 */
	public List<Trigger> getAllTriggers();
	
}
