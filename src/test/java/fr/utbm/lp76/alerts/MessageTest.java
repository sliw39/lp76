package fr.utbm.lp76.alerts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.model.Sensor;
import fr.utbm.lp76.alerts.model.Trigger;
import fr.utbm.lp76.alerts.services.MessagingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
public class MessageTest {
	
	@Autowired
	private MessagingService service;
	
	@Test
	public void sendMessageTest() {
		Alert al = new Alert();
		Sensor sn = new Sensor();
		Trigger tg = new Trigger();
		AlertHis ah = new AlertHis();
		java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
		
		al.setCode("AT");
		al.setDesciption("Alerte de test");
		al.setLabel("Alert Test");
		
		sn.setId(1);
		sn.setLabel("Sensor Test");

		tg.setId(2);
		tg.setAlert(al);
		tg.setHigh(30);
		tg.setLow(25);
		tg.setSensor(sn);
		
		ah.setDate(date);
		ah.setState(false);
		ah.setTrigger(tg);
		
		service.sendAlert(ah);
	}
}
