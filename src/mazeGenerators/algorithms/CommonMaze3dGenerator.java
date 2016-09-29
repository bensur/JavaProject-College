package mazeGenerators.algorithms;
/**
 * Abstract class to generate Maze3d object
 * Implements only measureAlgorithmTime, as it is similar to all implementations
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	@Override
	public abstract Maze3d generate(int rows, int cols, int fls);
	@Override
	public String measureAlgorithmTime(int rows, int cols, int fls) {
		long startTime = System.currentTimeMillis();
		generate(rows, cols, fls);
		long finishTime = System.currentTimeMillis();
		return String.valueOf(finishTime - startTime);
	}
}