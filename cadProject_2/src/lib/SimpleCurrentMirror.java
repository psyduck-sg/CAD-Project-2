package lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import components.Connection;
import components.Mosfet;
import components.Node;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import parser.Constraint;
import parser.ListBB;

public class SimpleCurrentMirror extends BuildingBlocks {

	private Mosfet m1 = new Mosfet();
	private Mosfet m2 = new Mosfet();
	private final Connection c1 = new Connection();
	private final Connection c2 = new Connection();

	public SimpleCurrentMirror() {

	}

	@Override
	public <V, E> Graph<Node, Connection> createBuildingBlock() {

		Graph<Node, Connection> target = new UndirectedSparseGraph<>();

		target.addVertex(m1);

		c2.addNodes(m1);
		c2.addNodes(m2);
		c2.addConnection("1_" + m1.getDrainID(), "2_" + m2.getGateID());
		c2.addConnection("1_" + m1.getGateID(), "2_" + m2.getGateID());
		c2.addConnection("1_" + m1.getDrainID(), "1_" + m1.getGateID());

		c1.addNodes(m1);
		c1.addNodes(m2);
		c1.addConnection("1_" + m1.getSourceID(), "2_" + m2.getSourceID());

		target.addEdge(c1, c1.getNodes(), EdgeType.UNDIRECTED);
		target.addEdge(c2, c2.getNodes(), EdgeType.UNDIRECTED);

		return target;

	}

	@Override
	public boolean isBuildingBlock(Graph<Node, Connection> g) {
		boolean b1 = true;
		Collection<Connection> cc = g.getEdges();
		for (Connection connection : cc) {
			if (!(connection.getConnection().equals(c1.getConnection())
					|| connection.getConnection().equals(c2.getConnection()))) {
				b1 = false;
				break;
			}
		}
		return b1;

	}

	@Override
	public boolean getSizingRule(Mosfet n1) {
		List<String> list = new ArrayList<>();
		list.add("id1/id2 = (w1/l1)/(w2/l2)");
		list.add("l1 = l2");
		list.add("|vds2 - vds1 | <= diff(Vdsmax(cm))");
		list.add("vgs1,2 - Vth1,2 >= Vgsmin");
		Constraint c = new Constraint();
		c.writeRules(list, n1);
		return true;

	}

	public Mosfet getM1() {
		return m1;
	}

	public Mosfet getM2() {
		return m2;
	}

	public int getPriority() {
		return ListBB.getPriorityMap().get("Current Mirror");
	}

}
