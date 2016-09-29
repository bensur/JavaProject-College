/**
 * 
 */
package boot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import mazeGenerators.algorithms.RandomPositionChooser;

/**
 * @author bensu
 *
 */
public class Run {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearchableMaze3d smaze = new SearchableMaze3d((new GrowingTreeGenerator(new RandomPositionChooser())).generate(20, 20, 20));
		System.out.println("Start: " + smaze.getStartState());
		System.out.println("Goal: " + smaze.getGoalState());
		searchMaze(new BFS<Position>(), smaze);
		searchMaze(new DFS<Position>(), smaze);
		Maze3d maze = new GrowingTreeGenerator(new RandomPositionChooser()).generate(10, 10, 10); //... generate it
		// save it to a file
		OutputStream out;
		try {
			out = new MyCompressorOutputStream(
			new FileOutputStream("1.maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream in;
		try {
			in = new MyDecompressorInputStream(
			new FileInputStream("1.maz"));
			byte[] sizeArr = new byte[4];
			for (int i = 0 ; i < sizeArr.length ; i++)
				sizeArr[i] = (byte)in.read();
			int size = ((sizeArr[0] * sizeArr[1]) + (sizeArr[2] * sizeArr[3]));
			byte b[]=new byte[size];
			in.read(b);
			in.close();
			Maze3d loaded=new Maze3d(b);
			System.out.println(loaded.equals(maze));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Search given searchable with given searcher and generate output with time took, class used and evaluated nodes
	 * @param searcher to search with
	 * @param searchable to search
	 */
	public static void searchMaze(Searcher<Position> searcher, Searchable<Position> searchable) {
		System.out.println("=====" + searcher.getClass() + "=====");
		long startTime = System.currentTimeMillis();
		System.out.println(searcher.search(searchable));
		long finishTime = System.currentTimeMillis(); 
		System.out.println("Search took " + String.valueOf(finishTime - startTime) + "ms");
		System.out.println(searcher.getNumberOfNodesEvaluated() + " States evaluated");
	}
}