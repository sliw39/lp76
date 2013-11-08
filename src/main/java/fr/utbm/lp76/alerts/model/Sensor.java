package fr.utbm.lp76.alerts.model;

import java.util.ArrayList;

public class Sensor {
	private int id;
	private String label;
	private Station station;
	private ArrayList<Temperature> temperatures; 
	
	public Sensor() {
		temperatures = new ArrayList<Temperature>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	public void addTemperature(Temperature t) {
		temperatures.add(t);
	}
	
	public ArrayList<Temperature> getTemperatures() {
		return temperatures;
	}
	
}
