/**
 * 
 */
package algorithms.search;

import java.util.ArrayList;

import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;

/**
 * @author bensu
 *
 */
public class SearchableMaze3d implements Searchable<Position> {
	private Maze3d maze;
	private State<Position> startState;
	private State<Position> goalState;
	
	
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
	public ArrayList<State<Position>> getSuccessors(State<Position> n) {
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
