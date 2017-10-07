package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import components.BJT;
import components.Circuit;
import components.Mosfet;
import components.Node;

public class Parser {
	static List<Node> list = new LinkedList<>();


	public void parseFile(File inputFile) throws IOException {
		System.out.println("Parsing File");
		Parser p = new Parser();
		InputStream is = System.in;
		if (inputFile != null) {
			is = new FileInputStream(inputFile);
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		try {
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				if (!line.startsWith("*") && line.length() > 0) {
					String[] fields = line.split(" ");
					p.putComponents(fields);
				}
			}
			p.getResults(list);
		} finally {
			reader.close();
		}
	}

	// Get the component in the form of a graph

	public void putComponents(String[] tokens) throws FileNotFoundException {
		/*
		 * GraphCircuit g = new GraphCircuit(); Constraint c = new Constraint();
		 */
		switch (tokens[0].charAt(0)) {
		case 'M':
		case 'm':
			Mosfet m = new Mosfet(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
					Integer.parseInt(tokens[3]));
			m.setTerminal();
			list.add(m);
			break;
		case 'Q':
		case 'q':
			BJT b = new BJT(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
					Integer.parseInt(tokens[3]));
			list.add(b);
			break;

		case 'V':
		case 'v':
		case 'I':
		case 'i':
		case '.':
		case '+':
			break;
		default:
			System.out.println("Invalid device");
		}
		/*
		 * g.createGraph(list); c.toFile(c.getRuleBook());
		 */
	}

	public void getResults(List<Node> list1) throws FileNotFoundException {
		Circuit ckt = new Circuit();
		GraphCircuit g = new GraphCircuit();
		Constraint c = new Constraint();
		g.createGraph(list1);
		ckt.setBuildingBlocks(g.getBb_list());
		ListBB.getInstance().addCKT(ckt);
		/*
		 * List<String> l = ckt.getBuildingBlocks(); Iterator<String> i =
		 * l.iterator(); while (i.hasNext()) { System.out.println(i.next()); }
		 */
		c.toFile(c.getRuleBook());
	}
}
