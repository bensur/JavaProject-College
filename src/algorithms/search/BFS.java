/**
 * 
 */
package algorithms.search;

import java.util.List;
import java.util.HashSet;

/**
 * BFS algorithm to solve any searchable object
 * @param T State param
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class BFS<T> extends CommonSearcher<T> {
	private HashSet<State<T>> closedList;
	/**
	 * C'tor
	 */
	public BFS(){
		closedList = new HashSet<State<T>>();
	}
	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	@Override
	public Solution<T> search(Searchable<T> searchable) {
		State<T> goalState = searchable.getGoalState();
		openList.add(searchable.getStartState());
		while (!openList.isEmpty()) {
			State<T> n = popOpenList();
			closedList.add(n);
			if (n.equals(goalState))
				return n.getSolution();
			else {
				List<State<T>> successors = searchable.getSuccessors(n);
				for (State<T> s : successors) {
					s.setFrom(n);
					s.setCost(n.getCost() + searchable.costBetween(n, s));
					if (openList.contains(s)) { //s is in openList
						for (State<T> cur : openList) {
							if (s.equals(cur)) {
								if (s.getCost() < cur.getCost()) {
									closedList.remove(cur);
									closedList.add(s);
								}
							}
						}
					}
					else if (closedList.contains(s)) { //s is in closedList
						State<T> e = null;
						for (State<T> cur : closedList)
							if (s.equals(cur))
								e = cur;
						if ((e != null) && (s.getCost() < e.getCost())) {
							closedList.remove(e);
							closedList.add(s);
						}
					}
					else { //s is not in openList or closedList
						openList.add(s);
					}
				}
			}
		}
		return null;
	}
}