package parser;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import clustering.Separationscores;
import components.Circuit;

public class Main {

	public static void main(String[] args) throws IOException {
		String target_dir = "./test_dir";
		File dir = new File(target_dir);
		File[] files = dir.listFiles();

		for (File f : files) {
			if (f.isFile()) {
				Parser p = new Parser();
				p.parseFile(f);
			}
		}
		List<Circuit> ckt_list = ListBB.getInstance().getCircuitList();
		for (int i = 0; i < ckt_list.size(); i++) {
			System.out.println(ckt_list.get(i).getBuildingBlocks());
		}
		Separationscores ss = new Separationscores();
		Iterator<Circuit> iter = ckt_list.iterator();
		while (iter.hasNext()) {
			ss.makeGroup(iter.next());
		}
		System.out.println("Entropy: " + ss.calculateEntropy());
		System.out.println("Item Characteristics: " + ss.itemCharacteristics());
		System.out.println("Category Characteristics: " + ss.categoryCharacteristics());
		System.out.println("Bayesian Classifier: " + ss.bayesianClassifier());
	}
}
