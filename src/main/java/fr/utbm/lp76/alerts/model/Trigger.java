package fr.utbm.lp76.alerts.model;

public class Trigger {
	private int id;
	private float high;
	private float low;
	private boolean edge;
	private Alert alert;
	private Sensor sensor;
	private AlertHis lastAlertHis;

	public AlertHis getLastAlertHis() {
		return lastAlertHis;
	}

	public void setLastAlertHis(AlertHis lastAlertHis) {
		this.lastAlertHis = lastAlertHis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public boolean isUpriseEdge() {
		return edge;
	}

	public void setEdge(boolean edge) {
		this.edge = edge;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
}
