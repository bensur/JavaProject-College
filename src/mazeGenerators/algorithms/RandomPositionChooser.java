/**
 * 
 */
package mazeGenerators.algorithms;

import java.util.List;
import java.util.Random;

/**
 * Choose random Position from given list
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class RandomPositionChooser implements PositionChooser {
	private Random rand = new Random();
	/* (non-Javadoc)
	 * @see mazeGenerators.algorithms.PositionChooser#choose(java.util.List)
	 */
	@Override
	public Position choose(List<Position> cellsList) {
		return cellsList.get(rand.nextInt(cellsList.size()));
	}
}
