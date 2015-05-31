package alda.sl;

import java.util.ArrayList;

public class Station {

	private String name;
	private ArrayList<Journey> journeys = new ArrayList<Journey>();
	
	public Station(String name) {
		this(name, new ArrayList<Journey>());
	}
	
	private Station(String name, ArrayList<Journey> journeys) {
		if (name.equals("") || name == null)
			throw new IllegalArgumentException("Name can't be empty or null");
		this.name = name;
		if (journeys == null)
			throw new IllegalArgumentException("Journeys can't be null");
		this.journeys = journeys;
	}
	
	public String getName() {
		return name;
	}
	
	public void addJourneyTo(Station station, int time) {
		if (station == null)
			throw new IllegalArgumentException("Station can't be null");
		for (Journey j : journeys)
			if (j.getStation().equals(station) && j.getTime() == time)
				return;
		journeys.add(new Journey(station, time));
	}
	
	public boolean removeJourneyTo(Station station) {
		if (station == null)
			return false;
		for (Journey j : journeys)
			if (j.getStation().equals(station)) {
				journeys.remove(j);
				return true;
			}
		return false;
	}
	
	public String toString() {
		return name;
	}
	
	private class Journey {
		Station station;
		int time;
		public Journey(Station station, int time) {
			this.station = station;
			this.time = time;
		}
		public Station getStation() {
			return station;
		}
		public int getTime() {
			return time;
		}
	}
	
}