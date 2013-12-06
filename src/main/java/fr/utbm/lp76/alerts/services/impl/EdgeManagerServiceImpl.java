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

		// Read the list of Trigger
		for (Trigger trigger : triggers) {
			// Getting all the alerts historic of a trigger
			AlertHis oldstate = trigger.getLastAlertHis();
			// Getting all the temperature historic of a trigger
			List<Temperature> temperatures = trigger.getSensor()
					.getTemperatures();

			// If the trigger is Uprise Edge
			if (trigger.isUpriseEdge()) {
				// If temperatures historic list is not empty
				if (!temperatures.isEmpty()) {
					// Getting the last temperature historic entry
					Temperature t = temperatures.get(0);

					// If these is no alert historic entry or the last alert
					// history entry state is inactivate
					if (oldstate == null || oldstate.getState() == false) {
						// If the temperature value is higher than the trigger
						// high edge
						if (t.getValue() >= trigger.getHigh()) {
							// Creating a new AlertHis
							am.createAlert(trigger.getAlert(), trigger);
						}
						// If the last alert history entry state is activate
					} else if (oldstate == null ||oldstate.getState() == true) {
						// If the temperature value is lower than the trigger
						// low edge
						if (t.getValue() <= trigger.getLow()) {
							// Creating a new AlertHis
							am.stopAlert(trigger.getAlert(), trigger);
						}
					}
				}
				// If the trigger is Uprise Edge
			} else {
				// If temperatures historic list is not empty
				if (!temperatures.isEmpty()) {
					// Getting the last temperature historic entry
					Temperature t = temperatures.get(0);

					// If these is no alert historic entry or the last alert
					// history entry state is inactivate
					if (oldstate == null || oldstate.getState() == false) {
						// If the temperature value is lower than the trigger
						// low edge
						if (t.getValue() <= trigger.getLow()) {
							// Creating a new AlertHis
							am.createAlert(trigger.getAlert(), trigger);
						}
						// If the last alert history entry state is activate
					} else if (oldstate == null ||oldstate.getState() == true) {
						// If the temperature value is higher than the trigger
						// high edge
						if (t.getValue() >= trigger.getHigh()) {
							// Creating a new AlertHis
							am.stopAlert(trigger.getAlert(), trigger);
						}
					}
				}
			}
		}
	}
}
