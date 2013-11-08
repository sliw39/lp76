package fr.utbm.lp76.alerts.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.services.ArchivingService;


@Service("ArchivingService")
public class ArchivingServiceImpl implements ArchivingService
{
	
	public void logAlertChange(AlertHis ah) {
		Connection con = null;
		Properties prop = new Properties();
		
		try {
			prop.load(getClass().getResourceAsStream("config.ini"));
			con = DriverManager.getConnection(prop.getProperty("database/server"), prop.getProperty("database/login"), prop.getProperty("database/password"));
			PreparedStatement st = con.prepareStatement("INSERT INTO AlertHis (Alh_Date, Alh_State, Trg_Id) VALUES (?,?,?)");
			
			st.setDate(1, ah.getDate());
			st.setBoolean(2, ah.getState());
			st.setInt(3, ah.getTrigger().getId());
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
		
	}
	
	

}
