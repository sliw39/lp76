package fr.utbm.lp76.alerts.services;

import fr.utbm.lp76.alerts.model.AlertHis;

public interface ArchivingService {
	public void logAlertChange(AlertHis ah);
}
