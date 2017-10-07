package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import components.Mosfet;

public class Constraint {
	private static List<String> ruleBook = new LinkedList<>();

	public void writeRules(List<String> list, Mosfet n1) {
		ruleBook.add(n1.getName());
		Iterator<String> str = list.iterator();
		while (str.hasNext()) {
			ruleBook.add(str.next());
		}
	}

	public void toFile(List<String> list) throws FileNotFoundException {
		PrintWriter f = new PrintWriter(new File("Constraint.txt"));
		f.write("Constraint Rules:");
		Iterator<String> str = list.iterator();
		while (str.hasNext()) {
			f.println();
			f.write(str.next());
		}
		f.close();
	}

	public List<String> getRuleBook() {
		return ruleBook;
	}

	public void writeRules(List<String> list1) {
		Iterator<String> str = list1.iterator();
		while (str.hasNext()) {
			ruleBook.add(str.next());
		}
	}

}
