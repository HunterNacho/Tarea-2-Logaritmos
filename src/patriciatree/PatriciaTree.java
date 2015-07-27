package patriciatree;

import java.io.IOException;
import java.util.ArrayList;

public class PatriciaTree {
	private AbstractNode header;
	public PatriciaTree() {
		Edge rootEdge = new Edge("", new LeafNode(-1)/*, true*/);
		ArrayList<Edge> pathToRoot = new ArrayList<Edge>();
		pathToRoot.add(rootEdge);
		header = new InnerNode(pathToRoot);
	}
	
	public PatriciaTree(String parsedText) {
		this();
		String[] words = parsedText.split(" ");
		int currentIndex = 0;
		for (int i = 0; i < words.length; i++) {
			this.insert(words[i], currentIndex);
			currentIndex += words[i].length() + 1;
		}
	}
	
	public ArrayList<Integer> find(String pattern) {
		return getRoot().find(pattern);
	}
	
	private void insert(String word, int index) {
		String leafText = getRoot().findLongestMatch(word);
		reinsert(word, index, leafText);
	}
	
	private AbstractNode getRoot() {
		return header.getEdges().get(0).getNext();
	}

	private void reinsert(String word, int index, String leafText) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Math.min(word.length(), leafText.length()); i++) {
			if (word.charAt(i) != leafText.charAt(i))
				break;
			sb.append(word.charAt(i));
		}
		String longestMatch = sb.toString();
		String difference = word.substring(longestMatch.length());
		getRoot().insert(longestMatch, index, difference, header.getEdges().get(0));
	}
	
	/*
	 * Para propósitos de prueba
	 */
	public static void main(String[] args) throws IOException {
		String text, word;
		
		text = "romane romanus romulus rubens ruber rubicon rubicundus rubens";
		word = "romanus";
		
//		PatriciaTree testTree = new PatriciaTree(BookParser.parseTextFile("/home/nacho/Documentos/Algoritmos2/Tarea-2-Logaritmos/libros/anatomyOfMelancholy.txt"));
		PatriciaTree testTree = new PatriciaTree(text);  
		System.out.println(testTree.find(word));
	}
	
}
