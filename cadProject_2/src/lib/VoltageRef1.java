package lib;

import java.util.Collection;

import components.Connection;
import components.Mosfet;
import components.Node;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import parser.ListBB;

public class VoltageRef1 extends BuildingBlocks {

	private static Mosfet m1 = new Mosfet();
	private static Mosfet m2 = new Mosfet();
	private final Connection c1 = new Connection();
	private final Connection c2 = new Connection();

	@Override
	public <V, E> Graph<Node, Connection> createBuildingBlock() {

		Graph<Node, Connection> target = new UndirectedSparseGraph<>();

		target.addVertex(m1);
		target.addVertex(m2);

		c1.addNodes(m1);
		c1.addNodes(m2);
		c1.addConnection("1_" + m1.getSourceID(), "2_" + m2.getDrainID());

		target.addEdge(c1, c1.getNodes(), EdgeType.UNDIRECTED);

		c2.addNodes(m1);
		c2.addNodes(m2);
		c2.addConnection("1_" + m1.getDrainID(), "1_" + m1.getGateID());
		c2.addConnection("1_" + m1.getGateID(), "2_" + m2.getGateID());
		c2.addConnection("1_" + m1.getDrainID(), "2_" + m2.getGateID());

		target.addEdge(c2, c2.getNodes(), EdgeType.UNDIRECTED);

		return target;

	}

	@Override
	public boolean isBuildingBlock(Graph<Node, Connection> g) {
		Collection<Connection> cc = g.getEdges();
		for (Connection connection : cc) {
			if (!(connection.getConnection().equals(c1.getConnection())
					|| connection.getConnection().equals(c2.getConnection())))
				return false;
		}
		return true;

	}

	@Override
	public boolean getSizingRule(Mosfet m1) {
		return false;
	}

	
	@Override
	public int getPriority() {
		return ListBB.getPriorityMap().get("Voltage Ref 1");
	}
	
	public Mosfet getM1() {
		return m1;
	}
	public Mosfet getM2() {
		return m2;
	}

}
