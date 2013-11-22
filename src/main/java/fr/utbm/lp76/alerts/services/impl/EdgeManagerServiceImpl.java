package fr.utbm.lp76.alerts.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.model.Temperature;
import fr.utbm.lp76.alerts.model.Trigger;
import fr.utbm.lp76.alerts.services.AcquisitionService;
import fr.utbm.lp76.alerts.services.AlertManagerService;
import fr.utbm.lp76.alerts.services.EdgeManagerService;

@Service("EdgeManagerService")
public class EdgeManagerServiceImpl implements EdgeManagerService {
	@Autowired
	AcquisitionService as;
	
	@Autowired
	AlertManagerService am;

	public void processTriggers() {
		List<Trigger> triggers = as.getAllTriggers();
		
		for (Trigger trigger : triggers) {
			AlertHis oldstate = trigger.getLastAlertHis();
			List<Temperature> temperatures = trigger.getSensor().getTemperatures();
			
			if(trigger.isUpriseEdge()) {
				if(!temperatures.isEmpty()){
					Temperature t = temperatures.get(0);
					if(oldstate == null || oldstate.getState() == false) {
						if(t.getValue() >= trigger.getHigh()) {
							am.createAlert(trigger.getAlert(), trigger);
						}
					}
					else if(oldstate == null || oldstate.getState() == true) {
						if(t.getValue() <= trigger.getLow()) {
							am.stopAlert(trigger.getAlert(),trigger);
						}
					}
				}
			}
			else {
				if(!temperatures.isEmpty()){
					Temperature t = temperatures.get(0);
					if(oldstate == null || oldstate.getState() == false) {
						if(t.getValue() <= trigger.getLow()) {
							am.createAlert(trigger.getAlert(), trigger);
						}
					}
					else if(oldstate == null || oldstate.getState() == true) {
						if(t.getValue() >= trigger.getHigh()) {
							am.stopAlert(trigger.getAlert(),trigger);
						}
					}
				}
			}
		}
		
	}

}
