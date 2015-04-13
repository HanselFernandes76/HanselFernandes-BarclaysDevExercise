package com.java.barc.feeds;

import java.util.ArrayList;
import java.util.List;

import com.java.barc.bo.BagList;

public enum DenverAirportLiveData implements LiveData {
	INSTANCE;

	@Override
	public List<BagList> getBagList() {
		List<BagList> bags = new ArrayList<BagList>(5);

		// this would be good to either read from a file or the database.
		// TO-DO
		BagList bl1 = new BagList("0001", "Concourse_A_Ticketing", "UA12");
		BagList bl2 = new BagList("0002", "A5", "UA17");
		BagList bl3 = new BagList("0003", "A2", "UA10");
		BagList bl4 = new BagList("0004", "A8", "UA18");
		BagList bl5 = new BagList("0005", "A7", "ARRIVAL");
		bags.add(bl1);
		bags.add(bl2);
		bags.add(bl3);
		bags.add(bl4);
		bags.add(bl5);

		return bags;
	}

}
