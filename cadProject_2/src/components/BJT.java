package components;

import java.util.ArrayList;

public class BJT extends Node {
	private String name;
	private int collector;
	private int base;
	private int emitter;
	private final int baseID = 1;
	private final int emitterID = 2;
	private final int collectorID = 3;
	
	private ArrayList<Integer> a = new ArrayList<>();

	public BJT(String name, int collector, int base, int emitter) {
		this.name = name;
		this.collector = collector;
		this.base = base;
		this.emitter = emitter;
	}

	public String getName() {
		return name;
	}

	public int getCollector() {
		return collector;
	}

	public int getBase() {
		return base;
	}

	public int getEmitter() {
		return emitter;
	}

	public ArrayList<Integer> getA() {
		return a;
	}
	
	public int getBaseID() {
		return baseID;
	}

	public int getEmitterID() {
		return emitterID;
	}

	public int getCollectorID() {
		return collectorID;
	}

}
