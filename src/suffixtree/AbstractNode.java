package suffixtree;

import java.util.ArrayList;

import suffixtree.Edge;
import suffixtree.LeafNode;

public abstract class AbstractNode {
	protected ArrayList<Edge> children;
	protected AbstractNode(ArrayList<Edge> edges) {
		this.children = edges;
	}
	public abstract void insert(String word, int index, int currentIndex);
	
//	public abstract 
}