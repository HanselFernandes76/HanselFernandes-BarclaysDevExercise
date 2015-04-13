package com.java.barc.feeds;

import java.util.ArrayList;
import java.util.List;

import com.java.barc.bo.ConveyorSystem;
import com.java.barc.bo.Departures;

public enum DenverAirportReferenceData implements ReferenceData {
	INSTANCE;

	@Override
	public List<Departures> getDepartureList() {

		List<Departures> departures = new ArrayList<Departures>(9);

		// this would be good to either read from a file or the database.
		// TO-DO
		Departures d1 = new Departures("UA10", "A1", "MIA", "08:00");
		Departures d2 = new Departures("UA11", "A1", "LAX", "09:00");
		Departures d3 = new Departures("UA12", "A1", "JFK", "09:45");
		Departures d4 = new Departures("UA13", "A2", "JFK", "08:30");
		Departures d5 = new Departures("UA14", "A2", "JFK", "09:45");
		Departures d6 = new Departures("UA15", "A2", "JFK", "10:00");
		Departures d7 = new Departures("UA16", "A3", "JFK", "09:00");
		Departures d8 = new Departures("UA17", "A4", "MHT", "09:15");
		Departures d9 = new Departures("UA18", "A5", "LAX", "10:15");
		departures.add(d1);
		departures.add(d2);
		departures.add(d3);
		departures.add(d4);
		departures.add(d5);
		departures.add(d6);
		departures.add(d7);
		departures.add(d8);
		departures.add(d9);
		return departures;
	}

	@Override
	public List<ConveyorSystem> getConveyorSystems() {

		// this would be good to either read from a file or the database.
		// TO-DO
		List<ConveyorSystem> convSys = new ArrayList<ConveyorSystem>(11);
		ConveyorSystem cs1 = new ConveyorSystem("Concourse_A_Ticketing", "A5",
				5);
		ConveyorSystem cs2 = new ConveyorSystem("A5", "BaggageClaim", 5);
		ConveyorSystem cs3 = new ConveyorSystem("A5", "A10", 4);
		ConveyorSystem cs4 = new ConveyorSystem("A5", "A1", 6);
		ConveyorSystem cs5 = new ConveyorSystem("A1", "A2", 1);
		ConveyorSystem cs6 = new ConveyorSystem("A2", "A3", 1);
		ConveyorSystem cs7 = new ConveyorSystem("A3", "A4", 1);
		ConveyorSystem cs8 = new ConveyorSystem("A10", "A9", 1);
		ConveyorSystem cs9 = new ConveyorSystem("A9", "A8", 1);
		ConveyorSystem cs10 = new ConveyorSystem("A8", "A7", 1);
		ConveyorSystem cs11 = new ConveyorSystem("A7", "A6", 1);
		convSys.add(cs1);
		convSys.add(cs2);
		convSys.add(cs3);
		convSys.add(cs4);
		convSys.add(cs5);
		convSys.add(cs6);
		convSys.add(cs7);
		convSys.add(cs8);
		convSys.add(cs9);
		convSys.add(cs10);
		convSys.add(cs11);

		return convSys;
	}

}
