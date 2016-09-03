/**
 * 
 */
package mazeGenerators.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author bensu
 *
 */
public class GrowingTreeGenerator extends CommonMaze3dGenerator {
	private List<Position> cellsList;
	private Random rand = new Random();
	cellChooser chooser;
	
	public GrowingTreeGenerator(cellChooser chooser) {
		cellsList = new ArrayList<Position>();
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
		cellsList.add(start);
		while (! cellsList.isEmpty())
			deepDive(maze, cellsList, chooser);
		return maze;
	}
	/**
	 * 
	 * @param maze
	 * @param cellsList
	 * @param chooser
	 */
	private void deepDive(Maze3d maze, List<Position> cellsList, cellChooser chooser) {
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