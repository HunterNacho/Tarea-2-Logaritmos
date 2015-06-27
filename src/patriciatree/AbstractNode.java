package patriciatree;

import java.util.ArrayList;

public abstract class AbstractNode {
	protected ArrayList<Edge> edges;
	protected AbstractNode(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	public abstract String findLongestMatch(String word);
	public abstract String getAnyWord();
	public abstract ArrayList<Integer> find(String pattern);
	public abstract void insert(String match, int index, String difference, Edge origin);
	public ArrayList<Edge> getEdges() {
		return edges;
	}
}
