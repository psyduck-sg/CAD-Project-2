package clustering;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import components.Circuit;

public class DifferentialPairNode extends ClusterNode {

	private Map<Circuit, String> group = new HashMap<>();

	@Override
	public void add(Circuit ckt, String str) {
		group.put(ckt, str);
	}

	@Override
	public int getCount() {
		return group.size();
	}

	@Override
	public Set<Circuit> getCircuits() {
		// TODO Auto-generated method stub
		return null;
	}

}
