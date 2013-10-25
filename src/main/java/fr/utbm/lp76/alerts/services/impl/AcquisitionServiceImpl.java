package fr.utbm.lp76.alerts.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.Trigger;
import fr.utbm.lp76.alerts.services.AcquisitionService;

@Service("AcquisitionService")
public class AcquisitionServiceImpl implements AcquisitionService {

	public List<Alert> getAllAlerts() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Trigger> getAllTriggers() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Utiliser un service :
	 * 
	 * @Autowired
	 * private XXX object;
	 * 
	 * !!!!PAS DE NEW, PAS BESOIN!!!
	 */
	
}
