package suffixtree;

import java.io.IOException;
import java.util.ArrayList;

import bookparser.BookParser;

public class SuffixTree {
	
	private InnerNode head;
	private String book;
	
	public SuffixTree(String path) {
		
		book = path;
//		book = BookParser.parseTextFile("/home/ekauffma/Documents/94ufos.txt");
		
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
	
	public void insert(String text, int index) {
		head.insert(text, index, index);		
	}
	
	public ArrayList<Integer> find(String word) {
		return head.find(word, book);
	}
	
	public static void main(String[] args) throws IOException {
		SuffixTree sufTree = new SuffixTree("abracadabra$");
		
		for(int i=0; i < sufTree.book.length(); i++) {
			sufTree.insert(sufTree.book, i);			
		}
		
		ArrayList<Integer> indexes = sufTree.find("a");
		
		System.out.println(indexes.toString());
		
	}

}
