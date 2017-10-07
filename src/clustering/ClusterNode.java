package clustering;

import java.util.Set;

import components.Circuit;

public abstract class ClusterNode {
		public abstract void add(Circuit ckt, String str);
		
		public abstract int getCount();
	/*	
		public abstract void setCount(int count);*/

		public abstract Set<Circuit> getCircuits();
}
