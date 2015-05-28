package suffixtree;

public class Edge {
	private String value;
	private AbstractNode next;
	
	public Edge(String value, AbstractNode next) {
		this.value = value;
		this.next = next;
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
