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
	
	public void insert(String word, int index) {
		head.insert(word, index);		
	}
	
	public static void main(String[] args) {
		SuffixTree sufTree = new SuffixTree();
		sufTree.insert("banana$", 0);
		sufTree.insert("anana$", 1);
		sufTree.insert("nana$", 2);
		sufTree.insert("ana$", 3);
		sufTree.insert("na$", 4);
		sufTree.insert("a$", 5);
		sufTree.insert("$", 6);
		
		System.out.println("breakpoint");
	}

}
