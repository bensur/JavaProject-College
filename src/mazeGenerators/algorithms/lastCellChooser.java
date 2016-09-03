/**
 * 
 */
package mazeGenerators.algorithms;

import java.util.List;

/**
 * @author bensu
 *
 */
public class lastCellChooser implements cellChooser {

	/* (non-Javadoc)
	 * @see mazeGenerators.algorithms.cellChooser#choose(java.util.List)
	 */
	@Override
	public Position choose(List<Position> cellsList) {
		return cellsList.get(cellsList.size() - 1);
	}
}
