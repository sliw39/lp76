package fr.utbm.lp76.alerts.services;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.Trigger;

public interface AlertManagerService {
	
	/**
	 * createAlert is use for create a new alert. It takes a parameter "al" and a paramater "tg"  
	 * @param al
	 * @param se
	 */
	public void createAlert(Alert al, Trigger tg);
	
	public void stopAlert(Alert al, Trigger tg);
}
