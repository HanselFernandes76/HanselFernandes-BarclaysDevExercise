package com.java.barc.reports.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.java.barc.bo.RoutingResult;
import com.java.barc.reports.DenverPathFinderReportService;

public class PathFinderReportsTest {

	@Test
	public void testPathFinderForDenverAirport() {
		try {

			List<RoutingResult> results = DenverPathFinderReportService.INSTANCE
					.execute();
			
			assertNotNull(results);
			

			for (RoutingResult routingResult : results) {
				System.out.println("routingResult - " + routingResult);
			}
			assertTrue(results.get(3).getNodes().size()==4);
			
		} catch (Exception ex) {
			fail("Something bad has happened, time to investigate .." + ex);

		}
	}

}
