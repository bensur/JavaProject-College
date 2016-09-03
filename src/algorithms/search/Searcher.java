/**
 * 
 */
package algorithms.search;

/**
 * Searchable interface to solve any searchable
 * @param T State param
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public interface Searcher<T> {
	/**
	 * Main object method, solve the given problem and return it's solution
	 * @param s Problem to solve
	 * @return solution for given problem
	 */
	public Solution<T> search(Searchable<T> s);
	/**
	 * Returned number of nodes algorithm evaluated
	 * @return Number of nodes algorithm evaluated
	 */
	public int getNumberOfNodesEvaluated();
}
