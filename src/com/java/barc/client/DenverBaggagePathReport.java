package com.java.barc.client;

import java.util.List;

import com.java.barc.bo.RoutingResult;
import com.java.barc.reports.DenverPathFinderReportService;

public class DenverBaggagePathReport {

	public static void main(String[] args) {

		try {
			
			List<RoutingResult> results = DenverPathFinderReportService.INSTANCE
					.execute();

			if (results != null) {
				for (RoutingResult routingResult : results) {
					System.out.print("" + routingResult.getBagNumber() + " ");
					for (String nodes : routingResult.getNodes()) {
						System.out.print("" + nodes + " ");
					}
					System.out.print(":" + routingResult.getTotalTime() + "\n");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
