package fr.utbm.lp76.alerts.services;

import java.util.List;

import fr.utbm.lp76.alerts.model.*;

public interface DatabaseMinerService {
	
	/**
	 * Read all areas from database
	 * @return the list of areas
	 */
	public List<Area> getAllAreas();
}
