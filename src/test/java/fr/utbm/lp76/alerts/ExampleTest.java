package fr.utbm.lp76.alerts;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

import fr.utbm.lp76.alerts.services.EdgeManagerService;

@ContextConfiguration(locations = { "classpath:tes-app-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleTest  {
	
	@Autowired
	EdgeManagerService service;
	
	@Test
	public void runner() {
		service.processTriggers();
	}
	
	@Test
	public void monTest() {
		int a=1;
		assertEquals(true, a==1);
	}
}
