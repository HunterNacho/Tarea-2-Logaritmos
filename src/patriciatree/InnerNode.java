package patriciatree;

import java.util.ArrayList;

public class InnerNode extends AbstractNode {
	public InnerNode(ArrayList<Edge> edges) {
		super(edges);
	}
	
	@Override
	public ArrayList<Integer> find(String pattern) {
		for (Edge edge : edges) {
			String path = edge.getString();
			if (path.length() > pattern.length())
				continue;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < path.length(); i++) {
				if (pattern.charAt(i) != path.charAt(i))
					break;
				sb.append(pattern.charAt(i));
			}
			String commonString = sb.toString();
			if (commonString.length() == 0 && (path.length() > 0 || pattern.length() > 0))
				continue;
			String nextWord = pattern.length() > path.length() ? pattern.substring(path.length()) : "";
			return edge.getNext().find(nextWord);
		}
		return new ArrayList<Integer>();
	}
	
	@Override
	public String findLongestMatch(String word) {
		StringBuilder result = new StringBuilder();
		for (Edge edge : edges) {
			String path = edge.getString();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < Math.min(word.length(), path.length()); i++) {
				if (word.charAt(i) != path.charAt(i))
					break;
				sb.append(word.charAt(i));
			}
			String commonString = sb.toString();
			if (commonString.length() == 0)
				continue;
			result.append(path);
			String nextWord = word.length() > path.length() ? word.substring(path.length()) : "";
			result.append(edge.getNext().findLongestMatch(nextWord));
			return result.toString();
		}
		return getAnyWord();
	}

	@Override
	public String getAnyWord() {
		StringBuilder sb = new StringBuilder();
		sb.append(edges.get(0).getString());
		sb.append(edges.get(0).getNext().getAnyWord());
		return sb.toString();
	}

	@Override
	public void insert(String match, int index, String difference, Edge origin) {
		if (match.equals("")) {
			if (difference.equals("")) {
				for (Edge edge : edges) {
					if (edge.getString().equals("")) {
						if (!edge.getNext().isLeaf()) throw new RuntimeException("Esto no debió pasar!!");
						edge.getNext().insert("", index, "", edge);
						return;
					}
				}
			}
			LeafNode newNode = new LeafNode(index);
			Edge newEdge = new Edge(difference, newNode);
			edges.add(newEdge);
			return;
		}
		for (Edge edge : edges) {
			String path = edge.getString();
			if (path.length() > 0 && match.length() >= path.length() && match.substring(0, path.length()).equals(path)) {
				edge.getNext().insert(match.substring(path.length()), index, difference, edge);
				return;
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < Math.min(match.length(), path.length()); i++) {
				if (match.charAt(i) != path.charAt(i))
					break;
				sb.append(match.charAt(i));
			}
			String commonString = sb.toString();
			if (commonString.length() == 0)
				continue;
			edge.split(commonString, index, match.substring(commonString.length()).concat(difference));
			return;
		}
		throw new RuntimeException("Esto no debió pasar!!");
	}	
}
