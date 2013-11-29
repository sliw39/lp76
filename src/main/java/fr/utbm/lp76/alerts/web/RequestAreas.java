package fr.utbm.lp76.alerts.web;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.utbm.lp76.alerts.services.DatabaseMinerService;
import fr.utbm.lp76.alerts.model.*;

/**
 * Servlet implementation class RequestAreas
 */
@Component("RequestAreas")
public class RequestAreas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private DatabaseMinerService database;
	
    /**
     * Default constructor. 
     */
    public RequestAreas() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Area> areas = database.getAllAreas();
		JSONArray array = new JSONArray();
		
		for (Area area : areas) {
			array.put(area.getLabel());
		}
		
		JSONObject object = new JSONObject();
		object.append("areas", array);
		
		response.getWriter().write(object.toString());
	}

}
