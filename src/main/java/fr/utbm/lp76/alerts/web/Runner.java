package fr.utbm.lp76.alerts.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.utbm.lp76.alerts.services.EdgeManagerService;

/**
 * Servlet implementation class Runner
 */
public class Runner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EdgeManagerService service;
	
	/**
     * Default constructor. 
     */
    public Runner() {
    	ApplicationContext context= new ClassPathXmlApplicationContext("app-context.xml");
    	service = (EdgeManagerService) context.getBean("EdgeManagerService");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service.processTriggers();
	}
}
