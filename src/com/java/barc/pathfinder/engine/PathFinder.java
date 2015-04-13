package com.java.barc.pathfinder.engine;

/*
 * Interface that all specific path finder solutions will implement
 * 
 */
import java.util.List;

import com.java.barc.bo.RoutingResult;

public interface PathFinder {
	List<RoutingResult> compute() throws Exception;
}
