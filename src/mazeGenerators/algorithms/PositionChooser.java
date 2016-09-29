package mazeGenerators.algorithms;

import java.util.List;
/**
 * Choose one Position from given list
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public interface PositionChooser {
	/**
	 * Choose one Position from given list
	 * @param positionList to choose from
	 * @return Position chosen
	 */
	Position choose(List<Position> positionList);
}
