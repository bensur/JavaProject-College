/**
 * 
 */
package mazeGenerators.algorithms;

import java.util.List;

/**
 * Choose last Position from given list
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class LastPositionChooser implements PositionChooser {
	/* (non-Javadoc)
	 * @see mazeGenerators.algorithms.PositionChooser#choose(java.util.List)
	 */
	@Override
	public Position choose(List<Position> cellsList) {
		return cellsList.get(cellsList.size() - 1);
	}
}
