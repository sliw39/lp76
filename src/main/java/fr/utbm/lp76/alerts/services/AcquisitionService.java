package fr.utbm.lp76.alerts.services;
import java.util.List;
import fr.utbm.lp76.alerts.model.*;

public interface AcquisitionService {
	public List<Alert> getAllAlerts();
	public List<Trigger> getAllTriggers();
	
}
