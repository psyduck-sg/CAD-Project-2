package clustering;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import components.Circuit;
import parser.ListBB;

public class Separationscores {
	private DifferentialPairNode dp = new DifferentialPairNode();
	private CurrentMirrorNode cm = new CurrentMirrorNode();
	private LevelShifterNode ls = new LevelShifterNode();

	public void makeGroup(Circuit ckt) {
		ClusterNode c;
		List<String> bb_list = ckt.getBuildingBlocks();
		Iterator<String> i = bb_list.iterator();
		while (i.hasNext()) {
			switch (i.next()) {
			case "Differential Pair":
				dp.add(ckt, "Differential Pair");
				ListBB.getInstance().addNodeCount(dp, dp.getCount());
				break;

			case "Current Mirror":
				cm.add(ckt, "Current Mirror");
				ListBB.getInstance().addNodeCount(cm, cm.getCount());
				break;

			case "Level Shifter":
				ls.add(ckt, "Level Shifter");
				ListBB.getInstance().addNodeCount(ls, ls.getCount());
				break;

			case "Wilson Current Mirror":
				c = new WilsonCurrentMirrorNode();
				c.add(ckt, "Wilson Current Mirror");
				ListBB.getInstance().addNodeCount(c, c.getCount());
				break;

			case "Cross Coupled Pair":
				c = new CrossCoupledPairNode();
				c.add(ckt, "Cross Coupled Pair");
				ListBB.getInstance().addNodeCount(c, c.getCount());
				break;

			case "Cascode Current Mirror":
				c = new LevelShifterNode();
				c.add(ckt, "Cascode Current Mirror");
				// nodeCount.put(c, nodeCount.getOrDefault(c, 0) + 1);
				break;

			case "Improved Wilson Current Mirror":
				c = new LevelShifterNode();
				c.add(ckt, "Improved Wilson Current Mirror");
				// nodeCount.put(c, nodeCount.getOrDefault(c, 0) + 1);
				break;

			case "Voltage Ref 1":
				c = new LevelShifterNode();
				c.add(ckt, "Voltage Ref 1");
				// nodeCount.put(c, nodeCount.getOrDefault(c, 0) + 1);
				break;

			case "Voltage Ref 2":
				c = new LevelShifterNode();
				c.add(ckt, "Level Shifter");
				// nodeCount.put(c, nodeCount.getOrDefault(c, 0) + 1);
				break;
			}

		}
	}

	public double calculateEntropy() {
		double numCluster = ListBB.getNodeCount();
		double prob = 1 / numCluster;
		double entropy = 0;
		Collection<Integer> n_k = ListBB.getInstance().getValue();
		Iterator<Integer> n_ki = n_k.iterator();
		while (n_ki.hasNext()) {
			double newValue = n_ki.next();
			entropy += (-1 * ((prob * newValue) * (Math.log(prob * newValue) / Math.log(numCluster))));
			System.out.println("Entropy: " + entropy);
		}
		return entropy;
	}

	public double itemCharacteristics() {
		double numCluster = ListBB.getNodeCount();
		System.out.println("ITEM" + numCluster);
		double itemChar = 0;
		double prod = 1;
		Collection<Integer> n_k = ListBB.getInstance().getValue();
		Iterator<Integer> n_ki = n_k.iterator();
		double numCircuit = ListBB.getInstance().getCircuitList().size();
		while (n_ki.hasNext()) {
			double prob_k = 0;
			int nextValue = n_ki.next();
			prod *= nextValue;
			prob_k = (double) (1 / (numCluster * numCircuit));
			prod = prod / numCluster;
			itemChar = ((prob_k * numCluster) / ((1 - prob_k) + prob_k * numCluster) * prod);
			System.out.println("Item Characteristics: " + itemChar);
		}
		/*
		 * double prob_k = 0; prob_k = (double) (1 / (numCluster * numCircuit));
		 * prod = prod / numCluster; itemChar = ((prob_k * numCluster) / ((1 -
		 * prob_k) + prob_k * numCluster) * prod);
		 */
		return itemChar;
	}

	public double categoryCharacteristics() {
		double numCluster = ListBB.getNodeCount();
		double categoryChar = 0, sum = 0;
		double prob = 0;
		prob = 1 / numCluster;
		Collection<Integer> n_k = ListBB.getInstance().getValue();
		Iterator<Integer> n_ki = n_k.iterator();
		double numCircuit = ListBB.getInstance().getCircuitList().size();
		while (n_ki.hasNext()) {
			double nextValue = n_ki.next();
			sum += nextValue;
			sum = sum / numCircuit;
			categoryChar = (double) (prob * Math.pow(sum, 2));
			System.out.println("Category Charateristics" + categoryChar);
		}
		/*
		 * sum = sum / numCircuit; categoryChar = (double) (prob * Math.pow(sum,
		 * 2));
		 */
		return categoryChar;
	}

	public double bayesianClassifier() {
		double numCluster = ListBB.getNodeCount();
		double score = 0;
		double prod = 1;
		Collection<Integer> n_k = ListBB.getInstance().getValue();
		Iterator<Integer> n_ki = n_k.iterator();
		double numCircuit = n_k.size();
		while (n_ki.hasNext()) {
			prod *= n_ki.next();
			prod = (prod / Math.pow(numCircuit, numCluster));
			score = prod * (1 / numCluster);
			System.out.println("Bayesian Classification: " + score);
		}
		/*
		 * prod = (prod / Math.pow(numCircuit, numCluster)); score = prod * (1 /
		 * numCluster);
		 */
		return score;
	}
}
