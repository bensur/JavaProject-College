/**
 * 
 */
package mazeGenerators.algorithms;

import java.util.List;
import java.util.Random;

/**
 * @author bensu
 *
 */
public class randomCellChooser implements cellChooser {
	private Random rand = new Random();
	/* (non-Javadoc)
	 * @see mazeGenerators.algorithms.cellChooser#choose(java.util.List)
	 */
	@Override
	public Position choose(List<Position> cellsList) {
		return cellsList.get(rand.nextInt(cellsList.size()));
	}
}
