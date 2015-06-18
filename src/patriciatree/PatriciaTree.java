package patriciatree;

import java.util.ArrayList;

public class PatriciaTree {
	private AbstractNode header;
	public PatriciaTree() {
		Edge rootEdge = new Edge("", new LeafNode(-1));
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
			currentIndex += words[i].length();
		}
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
	public static void main(String[] args) {
		String text = "romane romanus romulus rubens ruber rubicon rubicundus";
		PatriciaTree testTree = new PatriciaTree(text);
		System.out.println(testTree);
	}
	
}
