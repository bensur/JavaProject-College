/**
 * 
 */
package mazeGenerators.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generate maze with 'DFS' like algorithm. Algorithm depend on given PositionChooser
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class GrowingTreeGenerator extends CommonMaze3dGenerator {
	private List<Position> positionList;
	private Random rand = new Random();
	PositionChooser chooser;
	/**
	 * C'tor
	 * @param chooser to dictate how to choose from list
	 */
	public GrowingTreeGenerator(PositionChooser chooser) {
		positionList = new ArrayList<Position>();
		this.chooser = chooser;
	}
	/* (non-Javadoc)
	 * @see mazeGenerators.algorithms.CommonMaze3dGenerator#generate(int, int, int)
	 */
	@Override
	public Maze3d generate(int rows, int cols, int fls) {
		// TODO Auto-generated method stub
		Maze3d maze = new Maze3d(((rows * 2) + 1), ((cols * 2) + 1), ((fls * 2) + 1));
		Position start = maze.getRandValidPosition(rand);
		maze.setStartPosition(start);
		positionList.add(start);
		while (! positionList.isEmpty())
			deepDive(maze, positionList, chooser);
		return maze;
	}
	/**
	 * Move to the next given Position in order to generate the maze
	 * @param maze to work with
	 * @param cellsList to choose from
	 * @param chooser to choose with
	 */
	private void deepDive(Maze3d maze, List<Position> cellsList, PositionChooser chooser) {
		Position c = chooser.choose(cellsList);
		List<Position> moves = getUnvisited(maze, c);
		if (moves.isEmpty()) {
			cellsList.remove(c);
			if (cellsList.isEmpty())
				maze.setGoalPosition(c);
		}
		else {
			Position move = chooser.choose(moves);
			maze.verifyPath(c, move);
			cellsList.add(move);
		}
	}
	/**
	 * Get unvisited neighbors for given Position 
	 * @param maze to work with
	 * @param p Position to get neighbors of
	 * @return List of unvisited neighbors
	 */
	private List<Position> getUnvisited(Maze3d maze, Position p) {
		List<Position> moves = maze.getBuildPossibleMoves(p);
		ArrayList<Position> visited = new ArrayList<Position>();
		for (Position move : moves)
			if (maze.getValue(move) == Maze3d.FREE)
				visited.add(move);
		moves.removeAll(visited);
		return moves;
	}
}