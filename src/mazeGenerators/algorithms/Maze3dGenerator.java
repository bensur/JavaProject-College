package mazeGenerators.algorithms;

public interface Maze3dGenerator {
	Maze3d generate(int rows, int cols, int fls);
	String measureAlgorithmTime(int rows, int cols, int fls);
}
