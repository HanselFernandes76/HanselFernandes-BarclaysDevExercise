package com.java.barc.utils;

import java.util.List;
import java.util.Map;

import com.java.barc.bo.ConveyorSystem;
import com.java.barc.bo.Departures;
import com.java.barc.bo.RoutingResult;

/*
 * Utility class for get the optimized path, flight details etc..  
 * 
 */

public class RoutingUtils {

	/*
	 * get Flight gate given the flight no and departures list
	 */
	public static String getFlightGate(String flightNo,
			List<Departures> departures) throws Exception {

		if (flightNo == null || departures == null) {
			throw new Exception("Input data is null, Aborting..");
		}

		for (Departures dep : departures) {
			if (dep.getFlightId().equals(flightNo)) {
				return dep.getFlightGate();
			}
		}
		return null;

	}

	/*
	 * Update flight path, given the entry and destination points
	 */
	public static void updateFlightPath(String entryPoint,
			String destinationPoint, List<ConveyorSystem> conveyorSystems,
			RoutingResult routeRes, Map<String, ConveyorSystem> destinationMap,
			Map<String, ConveyorSystem> directRoute) throws Exception {

		// first check if a direct route exists..
		for (ConveyorSystem conveyorSystem : conveyorSystems) {
			if (conveyorSystem.getNode1().equals(destinationPoint)
					&& conveyorSystem.getNode2().equals(entryPoint)) {
				routeRes.addNode(conveyorSystem.getNode2());
				routeRes.setTotalTime(conveyorSystem.getTravelTime());
				routeRes.addNode(destinationPoint);
				return;
			}
		}

		if (checkIfEntryAndExitPointsExists(entryPoint, destinationPoint,
				conveyorSystems, destinationMap)) {

			getOptimizedPath(entryPoint, destinationPoint, routeRes, destinationMap,
					conveyorSystems);

		} else {

			if (checkIfEntryAndExitPointsExistsForReverse(destinationPoint,
					entryPoint, conveyorSystems, destinationMap)) {
				getOptimizedPathForReverse(destinationPoint, entryPoint, routeRes,
						destinationMap, conveyorSystems);
			} else {
				throw new Exception("Issue with path.. Entry point - "
						+ entryPoint + " Destination - " + destinationPoint);
			}

		}

	}

	/*
	 * Get optimized path
	 */
	private static String getOptimizedPath(String entryPoint, String destinationPoint,
			RoutingResult routeRes, Map<String, ConveyorSystem> destinationMap,
			List<ConveyorSystem> conveyorSystems) {
		ConveyorSystem cs;
		String destPt = destinationPoint;
		while (true) {

			cs = destinationMap.get(destinationPoint);

			if (cs != null) {

				if (cs.getNode1().equals(entryPoint)) {
					routeRes.addNode(cs.getNode1());
					routeRes.setTotalTime(routeRes.getTotalTime()
							+ cs.getTravelTime());
					routeRes.addNode(destPt);
					break;
				} else {

					if (!directRouteExists(cs.getNode1(), destinationPoint,
							routeRes, conveyorSystems)) {
						routeRes.addNode(cs.getNode1());
						routeRes.setTotalTime(routeRes.getTotalTime()
								+ cs.getTravelTime());
						destinationPoint = cs.getNode1();
					} else {
						if (routeRes != null && routeRes.getNodes().size() > 0) {

							boolean entryPointExists = false;
							boolean destinationExists = false;
							for (String nodes : routeRes.getNodes()) {
								if (nodes.equals(entryPoint)) {
									entryPointExists = true;
								}
								if (nodes.equals(destPt)) {
									destinationExists = true;
								}
							}

							if (entryPointExists && destinationExists) {
								break;

							}
							destinationPoint = cs.getNode1();
							if (directRouteExists(entryPoint, destinationPoint,
									routeRes, conveyorSystems)) {
								break;
							}

						}
					}

				}

			} else {
				break;
			}

		}
		return destinationPoint;
	}

	private static boolean directRouteExists(String entryPoint, String destPt,
			RoutingResult routeRes, List<ConveyorSystem> conveyorSystems) {

		// first check if a direct route exists..
		for (ConveyorSystem conveyorSystem : conveyorSystems) {
			if ((conveyorSystem.getNode1().equals(entryPoint) && conveyorSystem
					.getNode2().equals(destPt))
					|| (conveyorSystem.getNode2().equals(entryPoint) && conveyorSystem
							.getNode1().equals(destPt))) {
				routeRes.addNode(conveyorSystem.getNode2());
				routeRes.addNode(conveyorSystem.getNode1());
				routeRes.setTotalTime(routeRes.getTotalTime()
						+ conveyorSystem.getTravelTime());
				return true;
			}
		}

		return false;
	}

	/*
	 * Check if entry and exit points exists..
	 */
	private static boolean checkIfEntryAndExitPointsExists(String entryPoint,
			String destinationPoint, List<ConveyorSystem> conveyorSystems,
			Map<String, ConveyorSystem> destinationMap) {

		String destinationPt = destinationPoint;

		while (true) {

			ConveyorSystem cs = destinationMap.get(destinationPoint);

			if (cs != null) {

				if (cs.getNode1().equals(entryPoint)) {
					destinationPt = cs.getNode1();

					break;
				} else {

					if (!directRouteExists(cs.getNode1(), destinationPoint,
							conveyorSystems)) {

						destinationPoint = cs.getNode1();
					} else {
						destinationPoint = cs.getNode1();
						if (directRouteExists(entryPoint, destinationPoint,
								conveyorSystems)) {
							destinationPt = entryPoint;
							break;

						}
					}
				}

			} else {
				break;
			}

		}

		if (destinationPt.equals(entryPoint)) {
			return true;
		}

		return false;
	}

	/*
	 * Check if entry and exit points exists for reverse direction
	 */
	private static boolean checkIfEntryAndExitPointsExistsForReverse(
			String entryPoint, String destinationPoint,
			List<ConveyorSystem> conveyorSystems,
			Map<String, ConveyorSystem> destinationMap) {

		String destinationPt = destinationPoint;

		while (true) {

			ConveyorSystem cs = destinationMap.get(destinationPoint);

			if (cs != null) {

				if (cs.getNode1().equals(entryPoint)) {
					destinationPt = cs.getNode1();

					break;
				} else {

					// 1st check if direct route exists
					directRouteExists(cs.getNode1(), destinationPoint,
							conveyorSystems);

					destinationPoint = cs.getNode1();

				}

			} else {
				break;
			}

		}

		if (destinationPt.equals(entryPoint)) {
			return true;
		}

		return false;
	}

	/*
	 * 
	 * // * Check if direct route exists
	 */
	private static boolean directRouteExists(String entryPoint, String destPt,
			List<ConveyorSystem> conveyorSystems) {

		// first check if a direct route exists..
		for (ConveyorSystem conveyorSystem : conveyorSystems) {
			if ((conveyorSystem.getNode1().equals(entryPoint) && conveyorSystem
					.getNode2().equals(destPt))
					|| (conveyorSystem.getNode2().equals(entryPoint) && conveyorSystem
							.getNode1().equals(destPt))) {

				return true;
			}
		}

		return false;
	}

	/*
	 * Get the optimized path for reverse direction
	 */
	private static String getOptimizedPathForReverse(String entryPoint,
			String destinationPoint, RoutingResult routeRes,
			Map<String, ConveyorSystem> destinationMap,
			List<ConveyorSystem> conveyorSystems) {
		ConveyorSystem cs;
		String destPt = destinationPoint;
		while (true) {

			cs = destinationMap.get(destinationPoint);

			if (cs != null) {

				if (cs.getNode1().equals(entryPoint)) {
					routeRes.addNode(cs.getNode1());
					routeRes.setTotalTime(routeRes.getTotalTime()
							+ cs.getTravelTime());
					break;
				} else {

					if (!directRouteExists(cs.getNode1(), destinationPoint,
							routeRes, conveyorSystems)) {
						routeRes.addNode(cs.getNode1());
						routeRes.setTotalTime(routeRes.getTotalTime()
								+ cs.getTravelTime());
						destinationPoint = cs.getNode1();
					} else {
						if (routeRes != null && routeRes.getNodes().size() > 0) {

							boolean entryPointExists = false;
							boolean destinationExists = false;
							for (String nodes : routeRes.getNodes()) {
								if (nodes.equals(entryPoint)) {
									entryPointExists = true;
								}
								if (nodes.equals(destPt)) {
									destinationExists = true;
								}
							}

							if (entryPointExists && destinationExists) {
								break;

							}
							routeRes.addNode(cs.getNode1());
							destinationPoint = cs.getNode1();
						}
					}

				}

			} else {
				break;
			}

		}
		return destinationPoint;
	}

}
