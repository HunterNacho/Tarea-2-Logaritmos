package suffixtree;

import java.util.ArrayList;
import java.util.Collections;

public class InnerNode extends AbstractNode {
	
	public InnerNode(ArrayList<Edge> edges) {
		super(edges);
	}

	@Override
	public void insert(int index, int currentIndex) {
		
		for(Edge edge : children) {
//			String edgeValue = SuffixTree.book.substring(edge.getInitIndex(), edge.getEndIndex());
			String longestComPref = insertLongestCommonPrefix(edge.getInitIndex(), edge.getEndIndex(), currentIndex);
			int longestComPrefLen = longestComPref.length();
			//Matches the complete value in the edge. Continue inserting on child
			if(longestComPrefLen == (edge.getEndIndex() - edge.getInitIndex())) {
				edge.getNext().insert(index, currentIndex + longestComPrefLen);
				return;
			}
			
			//No matching. Continue with next sibling
			else if(longestComPref.equals("")) continue;
			
			//Partial match. Need to cut the edge
			else {
				LeafNode newLeaf = new LeafNode(index+1);
				InnerNode newNode;
				ArrayList<Edge> newNodeChildren = new ArrayList<Edge>();
				
				int edgeCutIndex = edge.getInitIndex() + longestComPrefLen;
				int restOfTheTextIndex = currentIndex + longestComPrefLen;
				Edge newLeafEdge = new Edge(restOfTheTextIndex, SuffixTree.bookLen, newLeaf);
				
				Edge newEdge = new Edge(edgeCutIndex, edge.getEndIndex(), edge.getNext());
				
				newNodeChildren.add(newEdge);
				newNodeChildren.add(newLeafEdge);
				
				newNode = new InnerNode(newNodeChildren);
				
				edge.setNext(newNode);
				edge.setEndIndex(edgeCutIndex);
				return;
			}
		}
		
		//Couldn't find any match at this level. Create new sibling
		LeafNode newLeaf = new LeafNode(index+1);
		Edge newEdge = new Edge(currentIndex, SuffixTree.bookLen, newLeaf);
		children.add(newEdge);
	}
	
	public ArrayList<Integer> find(String word) {
		
		for(Edge edge : children) {
			
//			String edgeValue = SuffixTree.book.substring(edge.getInitIndex(), edge.getEndIndex());
			String longestComPref = findLongestCommonPrefix(edge.getInitIndex(), edge.getEndIndex(), word);
			
			//Word is completely consumed. Found the word. Traverse the tree to find all indexes in leaves
			if(word.equals(longestComPref)) {
				return edge.getNext().getAllIndexes();
			}
//			
//			//No matching. Continue with next sibling
//			else if(longestComPref.equals("")) continue;
			
			//Partial match. Continue searching for the rest of the word
			else if(longestComPref.length()!=0 || (edge.getEndIndex() - edge.getInitIndex() == 0)) {
				return edge.getNext().find(word.substring(longestComPref.length()));
			}
									
		}
		
		//Could not go deeper. Not found
		return new ArrayList<Integer>();
	}

	@Override
	public ArrayList<Integer> getAllIndexes() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for(Edge edge : children) {
			indexes.addAll(edge.getNext().getAllIndexes());
		}
		
		Collections.sort(indexes);
		return indexes;
	}
	
	public String insertLongestCommonPrefix(int edgeInitIndex, int edgeEndIndex, int currentIndex) {
		StringBuilder longestCommPref = new StringBuilder();
		int edgeIndex = edgeInitIndex;
		int textIndex = currentIndex;
		
		while(edgeIndex < edgeEndIndex && SuffixTree.book.charAt(edgeIndex) == SuffixTree.book.charAt(textIndex)) {
			longestCommPref.append(SuffixTree.book.charAt(edgeIndex));
			edgeIndex+=1;
			textIndex+=1;
		}
		
		return longestCommPref.toString();
	}
	
	public String findLongestCommonPrefix(int edgeInitIndex, int edgeEndIndex, String word) {
		StringBuilder longestCommPref = new StringBuilder();
		int edgeIndex = edgeInitIndex;
		int wordIndex = 0;
		int wordLen = word.length();
		
		while(wordIndex < wordLen && edgeIndex < edgeEndIndex && word.charAt(wordIndex) == SuffixTree.book.charAt(edgeIndex)) {
			longestCommPref.append(word.charAt(wordIndex));
			wordIndex+=1;
			edgeIndex+=1;
		}
		
		return longestCommPref.toString();
		
	}

}
