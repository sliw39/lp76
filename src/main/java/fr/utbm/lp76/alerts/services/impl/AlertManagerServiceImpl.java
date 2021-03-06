package fr.utbm.lp76.alerts.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.model.Trigger;
import fr.utbm.lp76.alerts.services.AlertManagerService;
import fr.utbm.lp76.alerts.services.ArchivingService;
import fr.utbm.lp76.alerts.services.MessagingService;

@Service("AlertManagerService")
public class AlertManagerServiceImpl implements AlertManagerService 
{
	
	@Autowired
	MessagingService ms;
	@Autowired
	ArchivingService as;
	
	
	public void createAlert(Alert al, Trigger tg) 
	{
		java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
		
		AlertHis ah = new AlertHis();
		ah.setDate(date);
		ah.setState(true);
		ah.setTrigger(tg);
		
		ms.sendAlert(ah);
		as.logAlertChange(ah);
		
	}
	
	public void stopAlert(Alert al, Trigger tg)
	{
		java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
		AlertHis ah = new AlertHis();
		ah.setDate(date);
		ah.setState(false);
		ah.setTrigger(tg);
		
		ms.sendAlert(ah);
		as.logAlertChange(ah);
	}

}