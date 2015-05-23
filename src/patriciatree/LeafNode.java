package patriciatree;

import java.util.ArrayList;

public class LeafNode extends AbstractNode {
	private ArrayList<Integer> occurrences;
	public LeafNode(int index) {
		super(new ArrayList<Edge>());
		if (index > 0)
			occurrences.add(index);
	}
	@Override
	public LeafNode insert(String word) {
		// TODO Auto-generated method stub
		return null;
	}
}