package mazeGenerators.algorithms;

import java.util.List;

public interface cellChooser {
	Position choose(List<Position> cellsList);
}
