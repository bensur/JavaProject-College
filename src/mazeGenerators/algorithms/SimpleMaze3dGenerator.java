package mazeGenerators.algorithms;

import java.util.Random;

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {
	private Random rand = new Random();
	//Define ratio for free cells in the maze
	private static final float FREE_CELLS_RATIO = 0.5F;
	@Override
	public Maze3d generate(int rows, int cols, int fls) {
		Maze3d maze = new Maze3d(((rows * 2) + 1), ((cols * 2) + 1), ((fls * 2) + 1));
		int walls = (int) ((rows * cols * fls) * FREE_CELLS_RATIO);
		for (int i = 0 ; i < walls ; i++) {
			Position p = maze.getRandValidPosition(rand);
			if (maze.getValue(p) == Maze3d.WALL)
				maze.setFree(p);
			else
				i++;
		}
		Position startPosition = maze.getRandValidPosition(rand);
		Position endPosition;
		do {
			endPosition = maze.getRandValidPosition(rand);
		} while (endPosition.equals(startPosition));
		maze.setStartPosition(startPosition);
		maze.setGoalPosition(endPosition);
		maze.verifyPath(startPosition, endPosition);
		return maze;
	}
}