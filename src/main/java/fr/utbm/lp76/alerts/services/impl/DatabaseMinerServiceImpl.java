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
			
			PreparedStatement st = con.prepareStatement("SELECT are_id, are_label, are_road FROM area");
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Area a = new Area();
				a.setId(rs.getInt("are_id"));
				a.setLabel(rs.getString("are_label"));
				a.setRoad(rs.getString("are_road"));
				
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
		
		return (List<Area>) map;
	}

}
