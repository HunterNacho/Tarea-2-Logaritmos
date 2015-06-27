package patternsearchautomaton;

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
		return (int) (c - 'a');
	}
	
	private char indexToChar(int i) {
		return (char) ( i + 'a');
	}
	
	public boolean isInFinalState() {
		return state == pattern.length();
	}
	private void buildNextStateChart() {
		nextStateChart = new int[pattern.length() + 1][26];
		for (int i = 0; i <= pattern.length(); i++) {
			for (int j = 0; j < 26; j++) {
				nextStateChart[i][j]  = computeNextState(i, indexToChar(j));
			}
		}
	}

	private int computeNextState(int currentState, char nextChar) {
		if (currentState < pattern.length() && pattern.getBytes()[currentState] == nextChar)
			return currentState + 1;
		int newState;
		for (int state = currentState + 1; state > 0; state--) {
			//TODO
		}
	}
}
