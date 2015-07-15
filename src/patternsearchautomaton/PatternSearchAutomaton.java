package patternsearchautomaton;

import java.util.ArrayList;

public class PatternSearchAutomaton {
	int state;
	String pattern;
	int[][] nextStateChart;
	
	public PatternSearchAutomaton(String pattern) {
		this.pattern = pattern;
		this.state = 0;
		this.buildNextStateChart();
	}
	
	private int charToIndex(char c) {
		if (c == ' ')
			return 26;
		return (int) (c - 'a');
	}
	
	private char indexToChar(int i) {
		if (i == 26)
			return ' ';
		return (char) (i + 'a');
	}
	
	public boolean isInFinalState() {
		return state == pattern.length();
	}
	private void buildNextStateChart() {
		nextStateChart = new int[pattern.length() + 1][27];
		for (int i = 0; i <= pattern.length(); i++) {
			for (int j = 0; j < 27; j++) {
				nextStateChart[i][j]  = computeNextState(i, indexToChar(j));
			}
			
		}
	}

	private int computeNextState(int currentState, char nextChar) {
		String newWord = pattern.substring(0, currentState).concat(Character.toString(nextChar));
		if (currentState < pattern.length() && pattern.charAt(currentState) == nextChar)
			return currentState + 1;
		for (int state = currentState; state > 0; state--) {
			if (newWord.endsWith(pattern.substring(0, state))) {
				return state;
			}
		}
		return 0;
	}
	
	private void advanceState(char c) {
		state = nextStateChart[state][charToIndex(c)];
	}
	
	public ArrayList<Integer> find(String text) {
		this.state = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < text.length(); i++) {
			advanceState(text.charAt(i));
			if (isInFinalState())
				result.add(i - pattern.length() + 1);
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		PatternSearchAutomaton pSA1 = new PatternSearchAutomaton(" na ");
		PatternSearchAutomaton pSA2 = new PatternSearchAutomaton("na");
		
		/* Para efectos de como usaremos el autómata,
		 * la primera búsqueda es la única que tiene
		 * sentido.
		 */
		System.out.println(pSA1.find(" banana na "));
		System.out.println(pSA2.find(" banana na "));
		
		System.out.println(pSA1.find("banana na"));
		System.out.println(pSA2.find("banana na"));
	}
}
