package parser;

import java.util.HashMap;
import java.util.Map;

import components.Mosfet;
import components.Node;

public class PriorityList {

	public void getBuildingBlocks(HashMap<Node, Integer> map) {
		for (Map.Entry<Node, Integer> entry : map.entrySet()) {
			if (!ListBB.getHm().containsKey(((Mosfet) entry.getKey()))) {
				ListBB.getHm().put(((Mosfet) entry.getKey()), entry.getValue());
			}
		}
	}
}
