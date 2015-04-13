package com.java.barc.bo;

import java.util.ArrayList;
import java.util.List;

/*
 * Results for the path finder will be encapsulated here 
 * 
 */
public class RoutingResult {

	private String bagNumber;
	private List<String> nodes;
	private int totalTime;

	public String getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}

	public List<String> getNodes() {
		return nodes;
	}

	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	public void addNode(String nodeName) {
		if (nodes == null) {
			nodes = new ArrayList<String>();
		}

		if (!nodes.contains(nodeName)) {
			nodes.add(nodeName);
		}

	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bagNumber == null) ? 0 : bagNumber.hashCode());
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		result = prime * result + totalTime;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoutingResult other = (RoutingResult) obj;
		if (bagNumber == null) {
			if (other.bagNumber != null)
				return false;
		} else if (!bagNumber.equals(other.bagNumber))
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		if (totalTime != other.totalTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoutingResult [bagNumber=" + bagNumber + ", nodes=" + nodes
				+ ", totalTime=" + totalTime + "]";
	}
}
