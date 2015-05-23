package patriciatree;

import java.util.ArrayList;

public abstract class AbstractNode {
	private ArrayList<Edge> children;
	protected AbstractNode(ArrayList<Edge> edges) {
		this.children = edges;
	}
	public abstract LeafNode insert(String word);
	
//	public abstract 
}
