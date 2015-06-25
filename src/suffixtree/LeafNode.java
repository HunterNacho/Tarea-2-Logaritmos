package suffixtree;

import java.util.ArrayList;

public class LeafNode extends AbstractNode {
	
	private int wordIndex;
	
	public LeafNode(int index) {
		super(new ArrayList<Edge>());
		wordIndex = index;
	}
	@Override
	public void insert(String word, int index, int currentIndex) {
	}
	
	public int getWordIndex() {
		return wordIndex;
	}
	@Override
	public ArrayList<Integer> find(String word, String text) {
		return new ArrayList<Integer>();
	}
	
	@Override
	public ArrayList<Integer> getAllIndexes() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		indexes.add(getWordIndex());
		return indexes;
	}
}