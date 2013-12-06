package fr.utbm.lp76.alerts.model;

import java.sql.Date;
import java.sql.Timestamp;

public class AlertHis {
	private int id;
	private Timestamp date;
	private boolean state;
	private Trigger trigger;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
}
