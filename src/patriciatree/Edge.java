package patriciatree;

import java.util.ArrayList;

public class Edge {
	private String string;
	private AbstractNode next;
	
	public Edge(String string, AbstractNode next) {
		this.string = string;
		this.next = next;
	}
	
	public String getString() {
		return string;
	}
	
	public AbstractNode getNext() {
		return next;
	}

	void split(String match, int index, String difference) {
		String oldText = string.substring(match.length());
		String newText = string.substring(0, match.length());
		Edge oldEdge = new Edge(oldText, this.next);
		Edge newEdge = new Edge(difference, new LeafNode(index));
		ArrayList<Edge> newEdges = new ArrayList<Edge>();
		newEdges.add(oldEdge);
		newEdges.add(newEdge);
		this.string = newText;
		this.next = new InnerNode(newEdges);
		
	}
}
