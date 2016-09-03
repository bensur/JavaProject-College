package mazeGenerators.algorithms;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	public abstract Maze3d generate(int rows, int cols, int fls);
	@Override
	public String measureAlgorithmTime(int rows, int cols, int fls) {
		long startTime = System.currentTimeMillis();
		generate(rows, cols, fls);
		long finishTime = System.currentTimeMillis();
		return String.valueOf(finishTime - startTime);
	}
}