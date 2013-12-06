package fr.utbm.lp76.alerts.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.model.Sensor;
import fr.utbm.lp76.alerts.model.Temperature;
import fr.utbm.lp76.alerts.model.Trigger;
import fr.utbm.lp76.alerts.services.AcquisitionService;

@Service("AcquisitionService")
public class AcquisitionServiceImpl implements AcquisitionService {

	public List<Alert> getAllAlerts() {
		
		Connection con = null;
		ArrayList<Alert> map = new ArrayList<Alert>();
		
		try {
			Context namingContext = new InitialContext();
			DataSource datasource = (DataSource)namingContext.lookup("java:comp/env/jdbc/LP76DS");
			con = datasource.getConnection();
			
			PreparedStatement st = con.prepareStatement("SELECT alr_code, alr_label, alr_description FROM alert");
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Alert a = new Alert();
				a.setCode(rs.getString("alr_code"));
				a.setLabel(rs.getString("alr_label"));
				a.setDesciption(rs.getString("alr_description"));
				
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
		
		return (List<Alert>) map;
	}

	public List<Trigger> getAllTriggers() {
		Connection con = null;
		ArrayList<Trigger> map = new ArrayList<Trigger>();
		
		try {	
			Context namingContext = new InitialContext();
			DataSource datasource = (DataSource)namingContext.lookup("java:comp/env/jdbc/LP76DS");
			con = datasource.getConnection();
			
			HashMap<Integer, Sensor> sensors = new HashMap<Integer, Sensor>();
			Statement query = con.createStatement();
			ResultSet rs = query.executeQuery("SELECT sen_id, sen_label FROM sensor");
			while (rs.next()) {
				Sensor s = new Sensor();
				s.setId(rs.getInt("sen_id"));
				s.setLabel(rs.getString("sen_label"));
				
				sensors.put(s.getId(), s);
				
				PreparedStatement query2 = con.prepareStatement("SELECT tmp_id, tmp_value, tmp_date FROM temperature WHERE sen_id=? ORDER BY tmp_date DESC LIMIT 1");
				query2.setInt(1, s.getId());
				ResultSet rs2 = query2.executeQuery();
				while (rs2.next()) {
					Temperature t = new Temperature();
					if(s != null) {
						s.addTemperature(t);
						t.setSensor(s);
						
						t.setId(rs2.getInt("tmp_id"));
						t.setValue(rs2.getFloat("tmp_value"));
						t.setDate(rs2.getDate("tmp_date"));
					}
				}
			}
			
			
			
			
			List<Alert> alerts = getAllAlerts();
			
			query = con.createStatement();
			rs = query.executeQuery("SELECT trg_id, trg_high, trg_low, trg_edge, alr_code, sen_id FROM lp76.trigger");
			while (rs.next()) {
				Trigger t = new Trigger();
				Sensor s = sensors.get(rs.getInt("sen_id"));
				if(s != null) {
					t.setId(rs.getInt("trg_id"));
					t.setSensor(s);
					t.setEdge(rs.getBoolean("trg_edge"));
					t.setHigh(rs.getFloat("trg_high"));
					t.setLow(rs.getFloat("trg_low"));
					
					String code = rs.getString("alr_code");
					for (Alert alert : alerts) {
						if(alert.getCode().equals(code)) {
							t.setAlert(alert);
							break;
						}
					}
					
					PreparedStatement query2 = con.prepareStatement("SELECT alh_id, alh_date, alh_state FROM alerthis WHERE trg_id=? ORDER BY alh_date DESC");
					query2.setInt(1, t.getId());
					ResultSet rs2 = query2.executeQuery();
					if(rs2.next()) {
						AlertHis ah = new AlertHis();
						ah.setDate(rs2.getTimestamp("alh_date"));
						ah.setId(rs2.getInt("alh_id"));
						ah.setState(rs2.getBoolean("alh_state"));
						ah.setTrigger(t);
						t.setLastAlertHis(ah);
					}
					
					map.add(t);
				}
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
		
		return (List<Trigger>) map;
	}	
	
}
