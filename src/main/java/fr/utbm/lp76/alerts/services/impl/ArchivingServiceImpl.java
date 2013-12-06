package fr.utbm.lp76.alerts.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.AlertHis;
import fr.utbm.lp76.alerts.services.ArchivingService;


@Service("ArchivingService")
public class ArchivingServiceImpl implements ArchivingService
{
	
	public void logAlertChange(AlertHis ah) {
		Connection con = null;
		
		try {
			Context namingContext = new InitialContext();
			DataSource datasource = (DataSource)namingContext.lookup("java:comp/env/jdbc/LP76DS");
			con = datasource.getConnection();
			PreparedStatement st = con.prepareStatement("INSERT INTO AlertHis (Alh_Date, Alh_State, Trg_Id) VALUES (?,?,?)");
			
			st.setTimestamp(1, ah.getDate());
			st.setBoolean(2, ah.getState());
			st.setInt(3, ah.getTrigger().getId());
			
			st.executeUpdate();
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
