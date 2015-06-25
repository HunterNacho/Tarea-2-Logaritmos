package suffixtree;

public class Edge {
	private String value;
	private AbstractNode next;
	int initIndex, endIndex;
	
	public Edge(int init, int end, String value, AbstractNode next) {
		this.value = value;
		this.next = next;
		this.initIndex = init;
		this.endIndex = end;
	}
	
	public int getInitIndex() {
		return initIndex;
	}

	public void setInitIndex(int initIndex) {
		this.initIndex = initIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getValue() {
		return value;
	}
	
	public AbstractNode getNext() {
		return next;
	}
	
	public void setValue(String val) {
		value = val;
	}
	
	public void setNext(AbstractNode node) {
		next = node;
	}
}
