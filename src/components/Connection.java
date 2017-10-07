package components;

import java.util.ArrayList;

public class Connection {
	private ArrayList<Node> connectPoints = new ArrayList<>();
	private ArrayList<String> arr = new ArrayList<>();
	private boolean b = false;

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public void addNodes(Node j) {
		if(!connectPoints.contains(j))
			connectPoints.add(j);
	}

	public ArrayList<Node> getNodes() {
		return connectPoints;
	}

	public void addConnection(String source1, String source2) {
		arr.add(source1);
		arr.add(source2);
	}
	
	public ArrayList<String> getConnection(){
		return arr;
	}

}
