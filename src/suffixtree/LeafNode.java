package suffixtree;

import java.util.ArrayList;

public class LeafNode extends AbstractNode {
	
	private int wordIndex;
	
	public LeafNode(int index) {
		super(new ArrayList<Edge>());
		wordIndex = index;
	}
	@Override
	public void insert(String word, int index) {
	}
	
	public int getWordIndex() {
		return wordIndex;
	}
}