package suffixtree;

import java.util.ArrayList;

import utils.StringUtils;

public class InnerNode extends AbstractNode {
	
	public InnerNode(ArrayList<Edge> edges) {
		super(edges);
	}

	@Override
	public void insert(String text, int index, int currentIndex) {
		
		for(Edge edge : children) {
			String edgeValue = text.substring(edge.getInitIndex(), edge.getEndIndex());
			String longestComPref = StringUtils.longestCommonPrefix(edgeValue, text.substring(currentIndex));
			
			//Matches the complete value in the edge. Continue inserting on child
			if(longestComPref.equals(edgeValue)) {
				edge.getNext().insert(text, index, currentIndex + longestComPref.length());
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
				String textSecondHalf = text.substring(restOfTheTextIndex);
				Edge newLeafEdge = new Edge(restOfTheTextIndex, text.length(), textSecondHalf, newLeaf);
				
				String edgeSecondHalf = text.substring(edgeCutIndex, edge.getEndIndex());
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
		Edge newEdge = new Edge(currentIndex, text.length(), text.substring(currentIndex), newLeaf);
		children.add(newEdge);
	}
	
	public ArrayList<Integer> find(String word) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(Edge edge : children) {
			
		}
		//TODO
		return indexes;
	}
}
