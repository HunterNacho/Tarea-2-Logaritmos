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
		for (int i = 0; i < words.length; i++) {
			this.insert(words[i]);
		}
	}

	private void insert(String string) {
		// TODO Auto-generated method stub
	}
}
