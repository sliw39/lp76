package fr.utbm.lp76.alerts.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.model.Sensor;
import fr.utbm.lp76.alerts.services.AlertManagerService;
import fr.utbm.lp76.alerts.services.ArchivingService;
import fr.utbm.lp76.alerts.services.MessagingService;

@Service("AlertManagerService")
public class AlertManagerServiceImpl implements AlertManagerService {
	
	@Autowired
	MessagingService ms;
	@Autowired
	ArchivingService as;
	
	
	public void createAlert(Alert al, Sensor se) {
		
		AlertHis ah = new AlertHis();
		//
		//
		//ms.methode;
		as.logAlertChange(ah);
		
	}

}
