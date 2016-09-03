package algorithms.search;

import java.util.List;
/**
 * Searchable interface to hold problem in algorithm
 * @param T State param
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public interface Searchable<T> {
	/**
	 * Getter for start state
	 * @return State start position
	 */
	public State<T> getStartState();
	/**
	 * Getter for goal state
	 * @return State goal position
	 */
	public State<T> getGoalState();
	/**
	 * Get list of successors for a give state
	 * @param n state to get successor to
	 * @return ArrayList of all possible successors
	 */
	public List<State<T>> getSuccessors(State<T> n);
	/**
	 * Return cost between two given states
	 * @param n First state
	 * @param s Second state
	 * @return Cost between two given states 
	 */
	public int costBetween(State<T> n, State<T> s);
}