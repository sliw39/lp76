package fr.utbm.lp76.alerts.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.utbm.lp76.alerts.model.Area;
import fr.utbm.lp76.alerts.services.DatabaseMinerService;

/**
 * Servlet implementation class RequestAreas
 */
public class RequestAreas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseMinerService database;

	/**
	 * Default constructor.
	 */
	public RequestAreas() {
		ApplicationContext context= new ClassPathXmlApplicationContext("app-context.xml");
		database = (DatabaseMinerService) context.getBean("DatabaseMinerService");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
