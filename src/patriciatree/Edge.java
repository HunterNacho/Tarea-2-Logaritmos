package patriciatree;

import java.util.ArrayList;

public class Edge {
	private String string;
	private AbstractNode next;
	//private boolean toRoot;
	
	/*public Edge(String string, AbstractNode next) {
		this(string, next, false);
	}*/
	
	public Edge(String string, AbstractNode next/*, boolean toRoot*/) {
		this.string = string;
		this.next = next;
		//this.toRoot = toRoot;
		//check();
	}
	
	public String getString() {
		return string;
	}
	
	public AbstractNode getNext() {
		return next;
	}
	/*public void check() {
		if (!toRoot && string.equals("") && !next.isLeaf()) {
			throw new RuntimeException("Please debug");
		}
	}*/
	
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
		//check();
	}
}
