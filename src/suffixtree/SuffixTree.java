package suffixtree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import bookparser.BookParser;


public class SuffixTree {
	
	private InnerNode head;
	
	public static int INSERTTICKS = 100000;
	public static int FINDTICKS = 1000;
	static String book;
	static int bookLen;
	
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
		SuffixTree sufTree = new SuffixTree("/home/ekauffma/Documents/2015-1/logaritmos/Tarea-2-Logaritmos/anatomyOfMelancholy.txt");
		int bookLen = book.length();
		String[] words = book.substring(0, bookLen-1).split(" ");
		ArrayList<String> wordsToSearch = new ArrayList<String>();
		Random randWordIndex = new Random();
		for(int i = 0; i < words.length/10; i++) {
			int index = randWordIndex.nextInt(words.length);
			wordsToSearch.add(words[index]);						
		}
		
		System.out.println("Book: Anatomy of Melancholy");
		System.out.println("Size: 3,5 MB");
		System.out.println("Total number of words: " + bookLen);
		System.out.println("Number of words to search: " + wordsToSearch.size());
//		System.out.println(wordsToSearch.toString());
		
		System.out.println("Building suffix tree...");
		long startBuild_time = System.currentTimeMillis(); 
		for(int i=0; i < bookLen; i++) {
			sufTree.insert(i);	
			if(i%INSERTTICKS == 0) System.out.println("....inserting index " + i);
		}
		long endBuild_time = System.currentTimeMillis();
		long build_time = endBuild_time - startBuild_time;
		
		System.out.println("Searching words...");
		long startSearch_time = System.currentTimeMillis();
		for(int i = 0; i < wordsToSearch.size(); i++) {
			String word = " " + wordsToSearch.get(i) + " ";
			sufTree.find(word);
			if(i%FINDTICKS == 0) System.out.println("....searching for " + i +"th word");
		}
		long endSearch_time = System.currentTimeMillis();
		long search_time = endSearch_time - startSearch_time;
		
		System.out.println("Build time took: " + build_time/1000 + " seconds = " + (build_time/1000)/60 + " minutes");
		System.out.println("Search time took: " + search_time/1000 + " seconds = " + (search_time/1000)/60 + " minutes");
		
//		SuffixTree sufTree = new SuffixTree(" b an an as $");
//		for(int i=0; i < bookLen; i++) {
//			sufTree.insert(i);
//		}
//		
//		System.out.println(sufTree.find(" an "));
	}

}
