package com.java.barc.pathfinder.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.barc.bo.BagList;
import com.java.barc.bo.ConveyorSystem;
import com.java.barc.bo.Departures;
import com.java.barc.bo.RoutingResult;
import com.java.barc.feeds.DenverAirportLiveData;
import com.java.barc.feeds.DenverAirportReferenceData;
import com.java.barc.utils.RoutingUtils;

/*
 * Class used to implement the Denver Baggage Path finder, Any specific implementation 
 * of the path finder would need to implement the interface  
 * 
 */
public enum DenverBaggagePathFinder implements PathFinder {
	INSTANCE;

	@Override
	public List<RoutingResult> compute() throws Exception {

		List<RoutingResult> results = new ArrayList<RoutingResult>();
		List<ConveyorSystem> conveyorSystems = DenverAirportReferenceData.INSTANCE
				.getConveyorSystems();
		List<Departures> departures = DenverAirportReferenceData.INSTANCE
				.getDepartureList();

		Map<String, ConveyorSystem> directRoute = new HashMap<String, ConveyorSystem>();

		for (ConveyorSystem conveyorSystem : conveyorSystems) {
			directRoute.put(conveyorSystem.getNode1(), conveyorSystem);
		}

		Map<String, ConveyorSystem> destinationMap = new HashMap<String, ConveyorSystem>();

		for (ConveyorSystem conveyorSystem : conveyorSystems) {
			destinationMap.put(conveyorSystem.getNode2(), conveyorSystem);
		}

		List<BagList> bagList = DenverAirportLiveData.INSTANCE.getBagList();

		for (BagList baglist : bagList) {
			RoutingResult routeRes = new RoutingResult();
			routeRes.setBagNumber(baglist.getBagNumber());

			String flightGate = "";
			if (baglist.getFlightId().equals("ARRIVAL")) {
				// flightGate = "BaggageClaim";
				flightGate = baglist.getEntryPoint();
				baglist.setEntryPoint("BaggageClaim");
			} else {
				flightGate = RoutingUtils.getFlightGate(baglist.getFlightId(),
						departures);
			}

			if (flightGate != null) {
				// routeRes.addNode(flightGate);

				RoutingUtils.updateFlightPath(baglist.getEntryPoint(),
						flightGate, conveyorSystems, routeRes, destinationMap,
						directRoute);

				results.add(routeRes);

			}

		}

		return results;
	}

}
