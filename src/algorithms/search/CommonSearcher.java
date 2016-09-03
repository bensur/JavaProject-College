/**
 * 
 */
package algorithms.search;

import java.util.PriorityQueue;

/**
 * Abstract class to contain all shared resources and method for Searcher
 * @param T State param
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
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
	 * Return a State from priority queue and increase evaluated nodes
	 * @return State from openList
	 */
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}
}
