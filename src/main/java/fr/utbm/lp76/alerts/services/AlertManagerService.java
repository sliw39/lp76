package fr.utbm.lp76.alerts.services;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.Sensor;

public interface AlertManagerService {
	public void createAlert(Alert al, Sensor se);
}
