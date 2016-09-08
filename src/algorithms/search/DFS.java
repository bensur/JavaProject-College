/**
 * 
 */
package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * DFS algorithm to solve any searchable object
 * @param T State param
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class DFS<T> extends CommonSearcher<T> {
	private HashSet<State<T>> visited;
	private Stack<State<T>> stack;
	/**
	 * C'tor
	 */
	public DFS(){
		visited = new HashSet<State<T>>();
		stack = new Stack<State<T>>();
	}
	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> s) { //TODO not working!
		stack.add(s.getStartState());
		visited.add(s.getStartState());
		while (!stack.isEmpty()) {
			State<T> state = popOpenList();
			if (state.equals(s.getGoalState()))
				return state.getSolution();
			List<State<T>> neighbors = s.getSuccessors(state);
			for (State<T> neighbor : neighbors) {
				if ((neighbor != null) && (!visited.contains(neighbor))) {
					neighbor.setCost(state.getCost() + s.costBetween(state, neighbor));
					neighbor.setFrom(state);
					stack.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
		return null;
	}
	/**
	 * Return State from stack (openList) and increase evaluated nodes
	 * @return State from stack
	 */
	@Override
	protected State<T> popOpenList() {
		evaluatedNodes++;
		return stack.pop();
	}
}