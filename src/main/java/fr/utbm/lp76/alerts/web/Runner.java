package fr.utbm.lp76.alerts.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.utbm.lp76.alerts.services.EdgeManagerService;

/**
 * Servlet implementation class Runner
 */
@Component("Runner")
public class Runner {
	private static final long serialVersionUID = 1L;

	@Autowired
	private EdgeManagerService service;
	
	/**
     * Default constructor. 
     */
    public Runner() {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service.processTriggers();
	}
}
