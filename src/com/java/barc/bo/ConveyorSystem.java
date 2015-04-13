package com.java.barc.bo;

/*
 * Conveyor System entity
 * 
 */
public class ConveyorSystem {

	private String node1;
	private String node2;
	private int travelTime;

	public ConveyorSystem(String node1, String node2, int travelTime) {
		
		this.node1 = node1;
		this.node2 = node2;
		this.travelTime = travelTime;
	}

	public String getNode1() {
		return node1;
	}

	public void setNode1(String node1) {
		this.node1 = node1;
	}

	public String getNode2() {
		return node2;
	}

	public void setNode2(String node2) {
		this.node2 = node2;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
		result = prime * result + travelTime;
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
		ConveyorSystem other = (ConveyorSystem) obj;
		if (node1 == null) {
			if (other.node1 != null)
				return false;
		} else if (!node1.equals(other.node1))
			return false;
		if (node2 == null) {
			if (other.node2 != null)
				return false;
		} else if (!node2.equals(other.node2))
			return false;
		if (travelTime != other.travelTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConveyorSystem [node1=" + node1 + ", node2=" + node2
				+ ", travelTime=" + travelTime + "]";
	}

}
