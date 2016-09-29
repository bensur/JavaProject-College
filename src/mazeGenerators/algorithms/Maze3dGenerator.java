package mazeGenerators.algorithms;
/**
 * Interface of Maze3d generator
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public interface Maze3dGenerator {
	Maze3d generate(int rows, int cols, int fls);
	String measureAlgorithmTime(int rows, int cols, int fls);
}
