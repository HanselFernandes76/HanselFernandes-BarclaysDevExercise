package com.java.barc.feeds;

import java.util.List;

import com.java.barc.bo.ConveyorSystem;
import com.java.barc.bo.Departures;

public interface ReferenceData {

	List<Departures> getDepartureList();
	List<ConveyorSystem> getConveyorSystems();
}
