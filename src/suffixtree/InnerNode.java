package suffixtree;

import java.util.ArrayList;

import utils.StringUtils;

public class InnerNode extends AbstractNode {
	
	public InnerNode(ArrayList<Edge> edges) {
		super(edges);
	}

	@Override
	public void insert(String word, int index) {
		
		for(Edge edge : children) {
			String longestComPref = StringUtils.longestCommonPrefix(edge.getValue(), word);
			
			if(longestComPref.equals(edge.getValue())) {
				edge.getNext().insert(word.substring(longestComPref.length()), index);
				return;
			}
			else if(longestComPref.equals("")) continue;
			else {
				LeafNode newLeaf = new LeafNode(index);
				InnerNode newNode;
				ArrayList<Edge> newNodeChildren = new ArrayList<Edge>();
				
				String wordSecondHalf = word.substring(longestComPref.length());
				Edge newLeafEdge = new Edge(wordSecondHalf, newLeaf);
				
				String edgeSecondHalf = edge.getValue().substring(longestComPref.length());
				Edge newEdge = new Edge(edgeSecondHalf, edge.getNext());
				
				newNodeChildren.add(newEdge);
				newNodeChildren.add(newLeafEdge);
				
				newNode = new InnerNode(newNodeChildren);
				
				edge.setNext(newNode);
				edge.setValue(longestComPref);
				return;
			}
		}
		
		LeafNode newLeaf = new LeafNode(index);
		Edge newEdge = new Edge(word, newLeaf);
		children.add(newEdge);
	}
	
	public ArrayList<Integer> find(String word) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		//TODO
		return indexes;
	}
}
