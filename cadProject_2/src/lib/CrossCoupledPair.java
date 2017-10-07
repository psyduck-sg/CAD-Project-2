package lib;

import java.util.Collection;

import components.Connection;
import components.Mosfet;
import components.Node;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import parser.ListBB;

public class CrossCoupledPair extends BuildingBlocks {

	private Mosfet m1 = new Mosfet();
	private Mosfet m2 = new Mosfet();
	private Connection c1 = new Connection();
	private Connection c2 = new Connection();
	private Connection c3 = new Connection();

	@Override
	public <V, E> Graph<Node, Connection> createBuildingBlock() {
		Graph<Node, Connection> target = new UndirectedSparseGraph<>();

		target.addVertex(m1);
		target.addVertex(m2);

		c1.addNodes(m1);
		c1.addNodes(m2);
		c1.addConnection("1_" + m1.getSource(), "2_" + m2.getSource());

		c2.addNodes(m1);
		c2.addNodes(m2);
		c2.addConnection("1_" + m1.getDrain(), "2_" + m2.getGate());

		c3.addNodes(m1);
		c3.addNodes(m2);
		c3.addConnection("1_" + m1.getGate(), "2_" + m2.getDrain());

		target.addEdge(c1, c1.getNodes(), EdgeType.UNDIRECTED);
		target.addEdge(c2, c2.getNodes(), EdgeType.UNDIRECTED);
		target.addEdge(c3, c3.getNodes(), EdgeType.UNDIRECTED);

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
		return true;
	}

	@Override
	public int getPriority() {
		return ListBB.getPriorityMap().get("Cross Coupled Pair");
	}
}
