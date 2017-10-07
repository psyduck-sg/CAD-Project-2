package components;

import java.util.ArrayList;

public class Mosfet extends Node {

	private String name;
	private int drain;
	private int gate;
	private int source;
	private final int sourceID = 1;
	private final int drainID = 2;
	private final int gateID = 3;

	private ArrayList<Integer> terminals = new ArrayList<>();
	
	public Mosfet(String name, int drain, int gate, int source) {
		this.drain = drain;
		this.gate = gate;
		this.source = source;
		this.name = name;
	}

	public Mosfet() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public int getDrain() {
		return drain;
	}

	public int getGate() {
		return gate;
	}

	public int getSource() {
		return source;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDrain(int drain) {
		this.drain = drain;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getSourceID() {
		return sourceID;
	}

	public int getDrainID() {
		return drainID;
	}

	public int getGateID() {
		return gateID;
	}

	public void setTerminal() {
		terminals.add(this.drain);
		terminals.add(this.gate);
		terminals.add(source);
	}

}
