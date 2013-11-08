package fr.utbm.lp76.alerts.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.Trigger;
import fr.utbm.lp76.alerts.services.AcquisitionService;

@Service("AcquisitionService")
public class AcquisitionServiceImpl implements AcquisitionService {

	public List<Alert> getAllAlerts() {
		
		Connection con = null;
		ArrayList<Alert> map = new ArrayList<Alert>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql:localhost", "admin", "admin");
			PreparedStatement st = con.prepareStatement("SELECT alr_code, alr_label, alr_description FROM alert");
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Alert a = new Alert();
				a.setCode(rs.getString("alr_code"));
				a.setLabel(rs.getString("alr_label"));
				a.setDesciption("alr_description");
				
				map.add(a);
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return map;
	}

	public List<Trigger> getAllTriggers() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
