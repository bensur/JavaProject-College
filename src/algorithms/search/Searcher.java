/**
 * 
 */
package algorithms.search;

/**
 * @author bensu
 *
 */
public interface Searcher<T> {
	/**
	 * 
	 * @param s
	 * @return
	 */
	public Solution<T> search(Searchable<T> s);
	/**
	 * 
	 * @return Number of nodes algorithm evaluated
	 */
	public int getNumberOfNodesEvaluated();
}
