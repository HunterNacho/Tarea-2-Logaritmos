package patriciatree;

import java.util.ArrayList;

public class LeafNode extends AbstractNode {
	private ArrayList<Integer> occurrences;
	public LeafNode(int index) {
		super(new ArrayList<Edge>());
		occurrences = new ArrayList<Integer>();
		if (index >= 0)
			occurrences.add(index);
	}
	
	@Override
	public String findLongestMatch(String word) {
		return "";
	}

	@Override
	public String getAnyWord() {
		return "";
	}

	@Override
	public void insert(String match, int index, String difference, Edge origin) {
		assert(match.equals(""));
		if (difference.equals(""))
			occurrences.add(index);
		else
			origin.split(match, index, difference);
	}
	
}