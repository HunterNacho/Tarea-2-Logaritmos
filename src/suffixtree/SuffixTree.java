package suffixtree;

import java.io.IOException;
import java.util.ArrayList;

import bookparser.BookParser;

public class SuffixTree {
	
	private InnerNode head;
	
	public static int TICKS = 1000;
	static String book;
	
	public SuffixTree(String path) throws IOException {
		
		book = BookParser.parseTextFile("/home/ekauffma/Documents/94ufos.txt") + "$";
		
		LeafNode dummyLeafNode;
		InnerNode firstNode;
		Edge dummyEdge, headerEdge;
		ArrayList<Edge> headChildren = new ArrayList<Edge>();
		ArrayList<Edge> firstNodeChildren = new ArrayList<Edge>();
		
		dummyLeafNode = new LeafNode(-1);
		dummyEdge = new Edge(book.length()-1, book.length(), "$", dummyLeafNode); 
		
		firstNodeChildren.add(dummyEdge);
		firstNode = new InnerNode(firstNodeChildren);
		headerEdge = new Edge(0, 0, "", firstNode);
		
		headChildren.add(headerEdge);
		head = new InnerNode(headChildren);		
		

	}
	
	public void insert(int index) {
		head.insert(index, index);		
	}
	
	public ArrayList<Integer> find(String word) {
		return head.find(word);
	}
	
	public static void main(String[] args) throws IOException {
		SuffixTree sufTree = new SuffixTree("ccccccccccc$");
		
		for(int i=0; i < book.length(); i++) {
			sufTree.insert(i);	
			if(i%TICKS == 0) System.out.println("....inserting index " + i);
		}
		
		ArrayList<Integer> indexes = sufTree.find("ufo");
		
		System.out.println(indexes.toString());
		
	}

}
