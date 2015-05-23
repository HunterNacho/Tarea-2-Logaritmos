package suffixtree;

import java.util.ArrayList;

public class SuffixTree {
	
	InnerNode head;
	
	public SuffixTree() {
		
		LeafNode dummyLeafNode;
		InnerNode firstNode;
		Edge dummyEdge, headerEdge;
		ArrayList<Edge> headChildren = new ArrayList<Edge>();
		ArrayList<Edge> firstNodeChildren = new ArrayList<Edge>();
		
		dummyLeafNode = new LeafNode(-1);
		dummyEdge = new Edge("$", dummyLeafNode); 
		
		firstNodeChildren.add(dummyEdge);
		firstNode = new InnerNode(firstNodeChildren);
		headerEdge = new Edge("", firstNode);
		
		headChildren.add(headerEdge);
		head = new InnerNode(headChildren);		
	}

}
