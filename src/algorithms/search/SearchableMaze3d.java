/**
 * 
 */
package algorithms.search;
import java.util.ArrayList;
import java.util.List;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;

/**
 * Searchable adapter for Maze3d
 * @param T State param
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class SearchableMaze3d implements Searchable<Position> {
	private Maze3d maze;
	private State<Position> startState;
	private State<Position> goalState;
	
	/**
	 * C'tor
	 * @param maze to hold as the searchable maze
	 */
	public SearchableMaze3d(Maze3d maze) {
		this.maze = maze;
		this.startState = new State<Position>(maze.getStartPosition());
		this.goalState = new State<Position>(maze.getGoalPosition());
	}
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getStartState()
	 */
	@Override
	public State<Position> getStartState() {
		return startState;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	@Override
	public State<Position> getGoalState() {
		return goalState;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getSuccessors(algorithms.search.State)
	 */
	@Override
	public List<State<Position>> getSuccessors(State<Position> n) {
		if (n instanceof State<?>) {
			ArrayList<State<Position>> list = new ArrayList<State<Position>>();
			for (Position p : maze.getPossibleMoves(((State<Position>)n).getState()))
				list.add(new State<Position>(p));
			return list;
		} else {
			return null;
		}
	}
	/** (non-Javadoc)
	 * @see algorithms.search.Searchable#costBetween(algorithms.search.State, algorithms.search.State)
	 */
	@Override
	public int costBetween(State<Position> n, State<Position> s) {
		return 1;
	}
}
