package fr.utbm.lp76.alerts.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import fr.utbm.lp76.alerts.model.Alert;
import fr.utbm.lp76.alerts.model.Area;
import fr.utbm.lp76.alerts.services.DatabaseMinerService;

@Service("DatabaseMinerService")
public class DatabaseMinerServiceImpl implements DatabaseMinerService {

	public List<Area> getAllAreas() {
		Connection con = null;
		ArrayList<Area> map = new ArrayList<Area>();
		
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
				
				//map.add(a);
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
		
		//return (List<Alert>) map;
		return null;
	}

}
