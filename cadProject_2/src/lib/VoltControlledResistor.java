package lib;

import java.util.ArrayList;
import java.util.List;

import components.Connection;
import components.Mosfet;
import components.Node;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import parser.Constraint;
import parser.ListBB;

public class VoltControlledResistor extends BuildingBlocks{
	private int priority = ListBB.getPriorityMap().get(this);
	Mosfet m1 = new Mosfet();

	@Override
	public <V, E> Graph<Node, Connection> createBuildingBlock() {
		Graph<Node, Connection> g = new UndirectedSparseGraph<>();
		g.addVertex(m1);
		return g;
	}

	@Override
	public boolean isBuildingBlock(Graph<Node, Connection> g) {
		return true;
	}

	public Mosfet getM1() {
		return m1;
	}

	@Override
	public boolean getSizingRule(Mosfet m1) {
		List<String> list = new ArrayList<>();
		list.add("vds - (vgs - Vth) >= VSATmin");
		list.add("vds >= 0");
		list.add("vgs - Vth >= 0");
		list.add("w * l >= AminSAT");
		list.add("w >= WminSAT");
		list.add("l >= LminSAT");
		Constraint c = new Constraint();
		c.writeRules(list, m1);
		return true;
	
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int getPriority() {
		return priority;
	}
	
}
