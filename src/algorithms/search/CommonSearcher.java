/**
 * 
 */
package algorithms.search;

import java.util.PriorityQueue;

/**
 * @author bensu
 *
 */
public abstract class CommonSearcher<T> implements Searcher<T> {
	protected PriorityQueue<State<T>> openList;
	protected int evaluatedNodes;
	/**
	 * C'tor
	 */
	public CommonSearcher(){
		openList = new PriorityQueue<State<T>>();
		evaluatedNodes = 0;
	}
	/**
	 * (non-Javadoc)
	 * @see algorithms.search.Searcher#search()
	 */
	@Override
	public abstract Solution<T> search(Searchable<T> s);
	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#getNumberOfNodesEvaluated()
	 */
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	/**
	 * 
	 * @return
	 */
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}
}
