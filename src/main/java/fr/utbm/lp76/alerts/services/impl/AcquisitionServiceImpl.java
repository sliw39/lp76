package fr.utbm.lp76.alerts.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.Sensor;
import fr.utbm.lp76.alerts.model.Trigger;
import fr.utbm.lp76.alerts.services.AcquisitionService;

@Service("AcquisitionService")
public class AcquisitionServiceImpl implements AcquisitionService {

	public List<Alert> getAllAlerts() {
		// TODO Auto-generated method stub
		
		List<Alert> alertes = new ArrayList<Alert>();
		for(int i=0; i < 5; i++) {
			Alert a = new Alert();
			a.setCode("ALERT" + i);
			a.setLabel("ALERT" + i);
			a.setDesciption("ALERT" + i);
			alertes.add(a);
		}
		
		return alertes;
	}

	public List<Trigger> getAllTriggers() {
		// TODO Auto-generated method stub
		
		List<Alert> alerts = getAllAlerts();
		List<Sensor> sensors = new ArrayList<Sensor>();
		List<Trigger> triggers = new ArrayList<Trigger>();
		
		for(int i=0; i < 2; i++) {
			Sensor s = new Sensor();
			s.setId(i);
			s.setLabel("SENSOR"+i);
			s.setStation(null);
			
			for(int j=0; j < 2; j++) {
				Trigger t = new Trigger();
				t.setId(i);
				t.setAlert(alerts.get((int)Math.random()*5));
				t.setEdge(Math.random()<0.5);
				t.setHigh(20);
				t.setLow(-2);
				t.setSensor(s);
				triggers.add(t);
			}
			
			sensors.add(s);
		}
		
		return triggers;
	}
	
}
