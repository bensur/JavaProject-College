/**
 * 
 */
package algorithms.search;

import java.util.ArrayList;

/**
 * @author bensu
 *
 */
public class Maze3dSolution<T> extends Solution<T> {
	
	/**
	 * C'tor
	 */
	public Maze3dSolution() {
		this.solution = new ArrayList<State<T>>();
	}
	/**
	 * 
	 * @param s
	 */
	public void addToSolution(State<T> s) {
		solution.add(s);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : solution)
			sb.append(s.getState());
		return "Maze3dSolution [solution=" + solution + "]";
	}
	
}
