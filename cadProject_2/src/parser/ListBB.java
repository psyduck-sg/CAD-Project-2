package parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import clustering.ClusterNode;
import components.Circuit;
import components.Node;

public class ListBB {
	private static ListBB instance = null;

	private static HashMap<Node, Integer> hm = new HashMap<>();
	private static HashMap<String, Integer> priorityMap = new HashMap<>();
	private static List<Circuit> ckt_list = new LinkedList<>();
	private static HashMap<ClusterNode, Integer> nodeCount = new HashMap<>();

	public static HashMap<Node, Integer> getHm() {
		return hm;
	}

	protected ListBB() {

	}

	public static ListBB getInstance() {
		if (instance == null) {
			instance = new ListBB();
		}
		return instance;
	}

	public boolean priorityList() {
		priorityMap.put("Voltage Ref 1", 8);
		priorityMap.put("Voltage Ref 2", 7);
		priorityMap.put("Current Mirror Load", 6);
		priorityMap.put("Cascode Pair", 5);
		priorityMap.put("Current Mirror", 4);
		priorityMap.put("Level Shifter", 3);
		priorityMap.put("Cross Coupled Pair", 2);
		priorityMap.put("Differential Pair", 1);
		return true;
	}

	public static HashMap<String, Integer> getPriorityMap() {
		return priorityMap;
	}

	public void addBB(Node n, int value) {
		hm.put(n, value);
	}

	public void addCKT(Circuit ckt) {
		ckt_list.add(ckt);
	}

	public List<Circuit> getCircuitList() {
		return ckt_list;
	}

	public void addNodeCount(ClusterNode c, int i) {
		nodeCount.put(c, i);
	}
	
	public static int getNodeCount(){
		return nodeCount.size();
	}
	
	public Collection<Integer> getValue(){
		return nodeCount.values();
	}
	
	public HashMap<ClusterNode, Integer> getClusters(){
		return nodeCount;
	}
}
