package algorithms.search;

import java.util.ArrayList;

public interface Searchable<T> {
	/**
	 * 
	 * @return State start position
	 */
	public State<T> getStartState();
	/**
	 * 
	 * @return State goal position
	 */
	public State<T> getGoalState();
	/**
	 * 
	 * @param n state to get successor to
	 * @return ArrayList of all possible successors
	 */
	public ArrayList<State<T>> getSuccessors(State<T> n);
	/**
	 * 
	 * @param n
	 * @param s
	 * @return
	 */
	public int costBetween(State<T> n, State<T> s);
}
