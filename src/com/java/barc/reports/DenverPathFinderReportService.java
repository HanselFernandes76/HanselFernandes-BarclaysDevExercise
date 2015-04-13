package com.java.barc.reports;

import java.util.List;

import com.java.barc.bo.RoutingResult;
import com.java.barc.pathfinder.engine.DenverBaggagePathFinder;

/*
 * All client look ups will need to go through this
 * 
 */

public enum DenverPathFinderReportService implements
		ReportService<List<RoutingResult>> {
	INSTANCE;

	@Override
	public List<RoutingResult> execute() throws Exception {
		return DenverBaggagePathFinder.INSTANCE.compute();

	}

}
