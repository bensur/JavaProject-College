/**
 * 
 */
package boot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Searchable;
import algorithms.search.SearchableMaze3d;
import algorithms.search.Searcher;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerators.algorithms.GrowingTreeGenerator;
import mazeGenerators.algorithms.Maze3d;
import mazeGenerators.algorithms.Position;
import mazeGenerators.algorithms.randomCellChooser;

/**
 * @author bensu
 *
 */
public class Run {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearchableMaze3d smaze = new SearchableMaze3d((new GrowingTreeGenerator(new randomCellChooser())).generate(20, 20, 20));
		System.out.println("Start: " + smaze.getStartState());
		System.out.println("Goal: " + smaze.getGoalState());
		searchMaze(new BFS<Position>(), smaze);
		searchMaze(new DFS<Position>(), smaze);
	}
	/**
	 * 
	 * @param searcher
	 * @param searchable
	 */
	public static void searchMaze(Searcher<Position> searcher, Searchable<Position> searchable) {
		System.out.println("=====" + searcher.getClass() + "=====");
		long startTime = System.currentTimeMillis();
		System.out.println(searcher.search(searchable));
		long finishTime = System.currentTimeMillis(); 
		System.out.println("Search took " + String.valueOf(finishTime - startTime) + "ms");
		System.out.println(searcher.getNumberOfNodesEvaluated() + " States evaluated");
	}
	
	
	
	//compression test
	
	
	
	
	
	
}