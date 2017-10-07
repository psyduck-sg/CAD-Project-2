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

public class CascodeCurrentMirror extends BuildingBlocks {
	
	private LevelShifter ls = new LevelShifter();
	private SimpleCurrentMirror scm = new SimpleCurrentMirror();
	private Connection c1 = new Connection();
	private Connection c2 = new Connection();

	@Override
	public <V, E> Graph<Node, Connection> createBuildingBlock() {

		Graph<Node, Connection> g = new UndirectedSparseGraph<>();
		
		c1.addConnection("1_"+ ls.getM1().getSourceID(), "2_"+scm.getM1().getDrainID());
		c1.addNodes(ls.getM1());
		c1.addNodes(scm.getM1());
		
		g.addEdge(c1, c1.getNodes(), EdgeType.UNDIRECTED);
		
		c2.addConnection("1_"+ ls.getM2().getSourceID(), "2_"+ scm.getM2().getDrainID());
		c2.addNodes(ls.getM1());
		c2.addNodes(scm.getM2());
		
		g.addEdge(c2, c2.getNodes(), EdgeType.UNDIRECTED);
		return g;
	
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
		List<String> list = new ArrayList<>();
		list.add("wls(1) = wcm(1)");
		list.add("wls(2) = wcm(2)");
		Constraint c = new Constraint();
		c.writeRules(list, m1);
		return true;
	
	}

	@Override
	public int getPriority() {
		return ListBB.getPriorityMap().get(this);
	}

}
