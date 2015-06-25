package suffixtree;

import java.util.ArrayList;
import java.util.Collections;

import utils.StringUtils;

public class InnerNode extends AbstractNode {
	
	public InnerNode(ArrayList<Edge> edges) {
		super(edges);
	}

	@Override
	public void insert(int index, int currentIndex) {
		
		for(Edge edge : children) {
			String edgeValue = SuffixTree.book.substring(edge.getInitIndex(), edge.getEndIndex());
			String longestComPref = StringUtils.longestCommonPrefix(edgeValue, SuffixTree.book.substring(currentIndex));
			
			//Matches the complete value in the edge. Continue inserting on child
			if(longestComPref.equals(edgeValue)) {
				edge.getNext().insert(index, currentIndex + longestComPref.length());
				return;
			}
			
			//No matching. Continue with next sibling
			else if(longestComPref.equals("")) continue;
			
			//Partial match. Need to cut the edge
			else {
				LeafNode newLeaf = new LeafNode(index);
				InnerNode newNode;
				ArrayList<Edge> newNodeChildren = new ArrayList<Edge>();
				
				int edgeCutIndex = edge.getInitIndex() + longestComPref.length();
				int restOfTheTextIndex = currentIndex + longestComPref.length();
				String textSecondHalf = SuffixTree.book.substring(restOfTheTextIndex);
				Edge newLeafEdge = new Edge(restOfTheTextIndex, SuffixTree.book.length(), textSecondHalf, newLeaf);
				
				String edgeSecondHalf = SuffixTree.book.substring(edgeCutIndex, edge.getEndIndex());
				Edge newEdge = new Edge(edgeCutIndex, edge.getEndIndex(), edgeSecondHalf, edge.getNext());
				
				newNodeChildren.add(newEdge);
				newNodeChildren.add(newLeafEdge);
				
				newNode = new InnerNode(newNodeChildren);
				
				edge.setNext(newNode);
				edge.setValue(longestComPref);
				edge.setEndIndex(edgeCutIndex);
				return;
			}
		}
		
		//Couldn't find any match at this level. Create new sibling
		LeafNode newLeaf = new LeafNode(index);
		Edge newEdge = new Edge(currentIndex, SuffixTree.book.length(), SuffixTree.book.substring(currentIndex), newLeaf);
		children.add(newEdge);
	}
	
	public ArrayList<Integer> find(String word) {
		
		for(Edge edge : children) {
			
			String edgeValue = SuffixTree.book.substring(edge.getInitIndex(), edge.getEndIndex());
			String longestComPref = StringUtils.longestCommonPrefix(edgeValue, word);
			
			//Word is completely consumed. Found the word. Traverse the tree to find all indexes in leaves
			if(word.equals(longestComPref)) {
				return edge.getNext().getAllIndexes();
			}
//			
//			//No matching. Continue with next sibling
//			else if(longestComPref.equals("")) continue;
			
			//Partial match. Continue searching for the rest of the word
			else if(longestComPref.length()!=0 || edgeValue.equals("")) {
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

}
