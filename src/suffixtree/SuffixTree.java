package suffixtree;

import java.io.IOException;
import java.util.ArrayList;
import bookparser.BookParser;


public class SuffixTree {
	
	private InnerNode head;
	
	public static int INSERTTICKS = 100000;
	public static int FINDTICKS = 1000;
	public static String book;
	public static int bookLen;
	
	public SuffixTree(String path) throws IOException {
		
		book = " " + BookParser.parseTextFile(path) + " $";
//		book = path;
		bookLen = book.length();
		LeafNode dummyLeafNode;
		InnerNode firstNode;
		Edge dummyEdge, headerEdge;
		ArrayList<Edge> headChildren = new ArrayList<Edge>();
		ArrayList<Edge> firstNodeChildren = new ArrayList<Edge>();
		
		dummyLeafNode = new LeafNode(-1);
		dummyEdge = new Edge(bookLen-1, bookLen, dummyLeafNode); 
		
		firstNodeChildren.add(dummyEdge);
		firstNode = new InnerNode(firstNodeChildren);
		headerEdge = new Edge(0, 0, firstNode);
		
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
		SuffixTree sufTree = new SuffixTree(" hola hola hola hola hola hola hola hola hola hola hola hola $");
		for(int i=0; i < bookLen; i++) {
			sufTree.insert(i);
		}
		
		System.out.println(sufTree.find(" hola "));

	}

}


