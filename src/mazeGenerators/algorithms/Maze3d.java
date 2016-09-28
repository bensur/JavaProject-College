package mazeGenerators.algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Maze3d implements Serializable {
	private static final long serialVersionUID = -8060980233909744023L;
	public static final int WALL = 1;
	public static final int FREE = 0;
	private int cols;
	private int rows;
	private int flos;
	private int[][][] maze;
	private Position startPosition;
	private Position goalPosition;
	
	//Methods
	/**
	 * 
	 * @param cols
	 * @param rows
	 * @param flos
	 */
	public Maze3d(int cols, int rows, int flos) {
		if ((cols < 1) || (rows < 1) || (flos < 1))
			throw new IllegalArgumentException();
		this.cols = cols;
		this.rows = rows;
		this.flos = flos;
		this.maze = new int[flos][rows][cols];
		for (int[][] fDimMaze : this.maze)
			for (int[] rDimMaze : fDimMaze)
				Arrays.fill(rDimMaze, Maze3d.WALL);
		this.startPosition = null;
		this.goalPosition = null;
	}
	/**
	 * 
	 * @param arr byte array to build maze from
	 */
	public Maze3d(byte[] arr) {
		int k = 0;
		this.flos = arr[k++];
		this.rows = arr[k++];
		this.cols = arr[k++];
		maze = new int[flos][rows][cols];		
		
		Position startPos = new Position(arr[k++],arr[k++], arr[k++]);
		this.setStartPosition(startPos);
		Position goalPos = new Position(arr[k++],arr[k++], arr[k++]);
		this.setGoalPosition(goalPos);
		
		for (int z = 0; z < flos; z++) {
			for (int x = 0; x < rows; x++) {
				for (int y = 0; y < cols; y++) {
					maze[z][x][y] = arr[k++];
				}			
			}
		}
	}
	/**
	 * 
	 * @return byte array to represent the maze
	 */
	public byte[] toByteArray() {
		ArrayList<Byte> arr = new ArrayList<Byte>();
		arr.add((byte)flos);
		arr.add((byte)rows);
		arr.add((byte)cols);
		arr.add((byte)startPosition.getX());
		arr.add((byte)startPosition.getY());
		arr.add((byte)startPosition.getZ());
		arr.add((byte)goalPosition.getX());
		arr.add((byte)goalPosition.getY());
		arr.add((byte)goalPosition.getZ());
		
		for (int z = 0; z < flos; z++) {
			for (int x = 0; x < rows; x++) {
				for (int y = 0; y < cols; y++) {
					arr.add((byte)maze[z][x][y]);
				}
			}
		}
		
		byte[] bytes = new byte[arr.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)arr.get(i);
		}
		return bytes;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @return the flos
	 */
	public int getFlos() {
		return flos;
	}
	/**
	 * @return the maze
	 */
	public int[][][] getMaze() {
		return maze;
	}
	/**
	 * 
	 * @return startPosition
	 */
	public Position getStartPosition() {
		return this.startPosition;
	}
	/**
	 * 
	 * @return goalPosition
	 */
	public Position getGoalPosition() {
		return this.goalPosition;
	}
	/**
	 * 
	 * @param p
	 */
	public void setStartPosition(Position p) {
		this.startPosition = p;
	}
	/**
	 * 
	 * @param p
	 */
	public void setGoalPosition(Position p) {
		this.goalPosition = p;
	}
	/**
	 * 
	 * @param x
	 * @return
	 */
	public int[][] getCrossSectionByX(int x) {
		if ((x >= this.rows) || (x < 0))
			throw new IndexOutOfBoundsException();
		int[][] maze = new int[flos][cols];
		for (int i = 0 ; i < flos ; i++)
			for (int j = 0 ; j < cols ; j++)
				maze[i][j] = this.maze[i][x][j];
		return maze;
	}
	/**
	 * 
	 * @param y
	 * @return
	 */
	public int[][] getCrossSectionByY(int y) {
		if ((y >= this.cols) || (y < 0))
			throw new IndexOutOfBoundsException();
		int[][] maze = new int[flos][rows];
		for (int i = 0 ; i < flos ; i++)
			for (int j = 0 ; j < rows ; j++)
				maze[i][j] = this.maze[i][j][y];
		return maze;
	}
	/**
	 * 
	 * @param z
	 * @return
	 */
	public int[][] getCrossSectionByZ(int z) {
		if ((z >= this.flos) || (z < 0))
			throw new IndexOutOfBoundsException();
		int[][] maze = new int[rows][cols];
		for (int i = 0 ; i < rows ; i++)
			for (int j = 0 ; j < cols ; j++)
				maze[i][j] = this.maze[z][i][j];
		return maze;
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @param flo
	 * @return
	 */
	public int getValue(int row, int col, int flo) {
		if (isValidPosition(row, col, flo))
			return maze[flo][row][col];
		else
			throw new IndexOutOfBoundsException();
	}
	/**
	 * 
	 * @param p
	 * @return
	 */
	public int getValue(Position p) {
		return getValue(p.getX(), p.getY(), p.getZ());
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @param flo
	 */
	public void setWall(int row, int col, int flo) {
		if (isValidPosition(row, col, flo))
			maze[flo][row][col] = Maze3d.WALL;
		else
			throw new IndexOutOfBoundsException();
	}
	/**
	 * 
	 * @param p
	 */
	public void setWall(Position p) {
		setWall(p.getX(), p.getY(), p.getZ());
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @param flo
	 */
	public void setFree(int row, int col, int flo) {
		if (isValidPosition(row, col, flo))
			maze[flo][row][col] = Maze3d.FREE;
		else
			throw new IndexOutOfBoundsException();
	}
	/**
	 * 
	 * @param p
	 */
	public void setFree(Position p){
		setFree(p.getX(), p.getY(), p.getZ());
	}
	/**
	 * 
	 * @param p1
	 * @param p2
	 */
	public void setPathFree(Position p1, Position p2) {
		if (!(isValidPosition(p1) && isValidPosition(p2)))
			throw new IndexOutOfBoundsException();
		if ((p1.getX() == p2.getX()) && (p1.getY() == p2.getY()) && (p1.getZ() != p2.getZ())) {
			int start,end;
			int x = p1.getX();
			int y = p1.getY();
			setFree(p1);
			setFree(p2);
			if (p1.getZ() < p2.getZ()) {
				start = p1.getZ();
				end = p2.getZ();
			}
			else {
				start = p2.getZ();
				end = p1.getZ();
			}
			for (int i = start + 1 ; i < end ; i++)
				maze[i][x][y] = Maze3d.FREE;
		}
		else if ((p1.getX() == p2.getX()) && (p1.getY() != p2.getY()) && (p1.getZ() == p2.getZ())) {
			int start, end;
			int x = p1.getX();
			int z = p1.getZ();
			setFree(p1);
			setFree(p2);
			if (p1.getY() < p2.getY()) {
				start = p1.getY();
				end = p2.getY();
			}
			else {
				start = p2.getY();
				end = p1.getY();
			}
			for (int i = start + 1 ; i < end ; i++)
				maze[z][x][i] = Maze3d.FREE;
		}
		else if ((p1.getX() != p2.getX()) && (p1.getY() == p2.getY()) && (p1.getZ() == p2.getZ())) {
			int start, end;
			int y = p1.getY();
			int z = p1.getZ();
			setFree(p1);
			setFree(p2);
			if (p1.getX() < p2.getX()) {
				start = p1.getX();
				end = p2.getX();
			}
			else {
				start = p2.getX();
				end = p1.getX();
			}
			for (int i = start + 1 ; i < end ; i++)
				maze[z][i][y] = Maze3d.FREE;
		}
		else
			throw new IllegalArgumentException();
	}
	/**
	 * 
	 * @param p
	 * @return
	 */
	public List<Position> getPossibleMoves(Position p) {
		List<Position> positions = new ArrayList<Position>();
		Position candidate;
		candidate = getRightPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getLeftPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getAbovePosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getBelowPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getForwardPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getBackwardPosition(p);
		if (candidate != null)
			positions.add(candidate);
		return positions;
	}
	/**
	 * 
	 * @param p
	 * @return
	 */
	public List<Position> getBuildPossibleMoves(Position p) {
		List<Position> positions = new ArrayList<Position>();
		Position candidate;
		candidate = getBuildRightPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getBuildLeftPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getBuildAbovePosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getBuildBelowPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getBuildForwardPosition(p);
		if (candidate != null)
			positions.add(candidate);
		candidate = getBuildBackwardPosition(p);
		if (candidate != null)
			positions.add(candidate);
		return positions;
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @param flo
	 * @return true if the position is valid, else false
	 */
	public boolean isValidPosition(int row, int col, int flo) {
		//If out of bound or in the "walls" or "ceilings"
		if ((row < 1) || (row > (this.rows - 2)) || (col < 1) || (col > (this.cols - 2)) || (flo < 1) || (flo > (this.flos - 2)))
			return false;
		return true;
	}
	/**
	 * 
	 * @param p
	 * @return true if position is valid, else false
	 */
	public boolean isValidPosition(Position p) {
		return isValidPosition(p.getX(), p.getY(), p.getZ());
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @param flo
	 * @return true if the position is valid, else false
	 */
	public boolean isValidBuildPosition(int row, int col, int flo) {
		//If out of bound or in the "walls" or "ceilings"
		if ((row < 1) || (row > (this.rows - 2)) || (col < 1) || (col > (this.cols - 2)) || (flo < 1) || (flo > (this.flos - 2)))
			return false;
		if (((row % 2) == 0) || ((col % 2) == 0) || ((flo % 2) == 0))
			return false;
		return true;
	}
	/**
	 * 
	 * @param p
	 * @return true if position is valid, else false
	 */
	public boolean isValidBuildPosition(Position p) {
		return isValidBuildPosition(p.getX(), p.getY(), p.getZ());
	}
	/**
	 * Return Position right to position given, if it is a valid one, else return null
	 * @param p
	 * @return Position right to given position if it is valid, else return null
	 */
	public Position getRightPosition(Position p) {
		if ((p == null) || (! isValidPosition(p)))
			throw new IllegalArgumentException();
		Position pRight = new Position(p.getX(), (p.getY() + 1), p.getZ());
		if (isValidPosition(pRight) && (getValue(pRight) == Maze3d.FREE))
			return pRight;
		return null;
	}
	/**
	 * Return Position left to position given, if it is a valid one, else return null
	 * @param p
	 * @return Position left to given position if it is valid, else return null
	 */
	public Position getLeftPosition(Position p) {
		if ((p == null) || (! isValidPosition(p)))
			throw new IllegalArgumentException();
		Position pLeft = new Position(p.getX(), (p.getY() - 1), p.getZ());
		if (isValidPosition(pLeft) && (getValue(pLeft) == Maze3d.FREE))
			return pLeft;
		return null;
	}
	/**
	 * Return Position above the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position above the given position if it is valid, else return null
	 */
	public Position getAbovePosition(Position p) {
		if ((p == null) || (! isValidPosition(p)))
			throw new IllegalArgumentException();
		Position pAbove = new Position(p.getX(), p.getY(), (p.getZ() + 1));
		if (isValidPosition(pAbove) && (getValue(pAbove) == Maze3d.FREE))
			return pAbove;
		return null;
	}
	/**
	 * Return Position below the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position below the given position if it is valid, else return null
	 */
	public Position getBelowPosition(Position p) {
		Position pBelow = new Position(p.getX(), p.getY(), (p.getZ() - 1));
		if ((p == null) || (! isValidPosition(p)))
			throw new IllegalArgumentException();
		if (isValidPosition(pBelow) && (getValue(pBelow) == Maze3d.FREE))
			return pBelow;
		return null;
	}
	/**
	 * Return Position in front the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position in front the given position if it is valid, else return null
	 */
	public Position getForwardPosition(Position p) {
		if ((p == null) || (! isValidPosition(p)))
			throw new IllegalArgumentException();
		Position pForward = new Position((p.getX() + 1), p.getY(), p.getZ());
		if (isValidPosition(pForward) && (getValue(pForward) == Maze3d.FREE))
			return pForward;
		return null;
	}
	/**
	 * Return Position out back the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position out back the given position if it is valid, else return null
	 */
	public Position getBackwardPosition(Position p) {
		if ((p == null) || (! isValidPosition(p)))
			throw new IllegalArgumentException();
		Position pBackward = new Position((p.getX() - 1), p.getY(), p.getZ());
		if (isValidPosition(pBackward) && (getValue(pBackward) == Maze3d.FREE))
			return pBackward;
		return null;
	}
	/**
	 * Return Position right to position given, if it is a valid one, else return null
	 * @param p
	 * @return Position right to given position if it is valid, else return null
	 */
	public Position getBuildRightPosition(Position p) {
		if ((p == null) || (! isValidBuildPosition(p)))
			throw new IllegalArgumentException();
		Position pRight = new Position(p.getX(), (p.getY() + 2), p.getZ());
		if (! isValidBuildPosition(pRight))
			return null;
		return pRight;
	}
	/**
	 * Return Position left to position given, if it is a valid one, else return null
	 * @param p
	 * @return Position left to given position if it is valid, else return null
	 */
	public Position getBuildLeftPosition(Position p) {
		if ((p == null) || (! isValidBuildPosition(p)))
			throw new IllegalArgumentException();
		Position pLeft = new Position(p.getX(), (p.getY() - 2), p.getZ());
		if (! isValidBuildPosition(pLeft))
			return null;
		return pLeft;
	}
	/**
	 * Return Position above the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position above the given position if it is valid, else return null
	 */
	public Position getBuildAbovePosition(Position p) {
		if ((p == null) || (! isValidBuildPosition(p)))
			throw new IllegalArgumentException();
		Position pAbove = new Position(p.getX(), p.getY(), (p.getZ() + 2));
		if (! isValidBuildPosition(pAbove))
			return null;
		return pAbove;
	}
	/**
	 * Return Position below the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position below the given position if it is valid, else return null
	 */
	public Position getBuildBelowPosition(Position p) {
		Position pBelow = new Position(p.getX(), p.getY(), (p.getZ() - 2));
		if ((p == null) || (! isValidBuildPosition(p)))
			throw new IllegalArgumentException();
		if (! isValidBuildPosition(pBelow))
			return null;
		return pBelow;
	}
	/**
	 * Return Position in front the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position in front the given position if it is valid, else return null
	 */
	public Position getBuildForwardPosition(Position p) {
		if ((p == null) || (! isValidBuildPosition(p)))
			throw new IllegalArgumentException();
		Position pForward = new Position((p.getX() + 2), p.getY(), p.getZ());
		if (! isValidBuildPosition(pForward))
			return null;
		return pForward;
	}
	/**
	 * Return Position out back the position given, if it is a valid one, else return null
	 * @param p
	 * @return Position out back the given position if it is valid, else return null
	 */
	public Position getBuildBackwardPosition(Position p) {
		if ((p == null) || (! isValidBuildPosition(p)))
			throw new IllegalArgumentException();
		Position pBackward = new Position((p.getX() - 2), p.getY(), p.getZ());
		if (! isValidBuildPosition(pBackward))
			return null;
		return pBackward;
	}
	/**
	 * 
	 * @param maze
	 * @return
	 */
	public Position getRandValidPosition(Random rand) {
		int x, y, z;
		do {
			z = rand.nextInt(flos);
			x = rand.nextInt(rows);
			y = rand.nextInt(cols);
		} while (! isValidBuildPosition(x, y, z));
		return new Position(x, y, z);
	}

	/**
	 * 
	 * @param maze
	 * @param startPosition
	 * @param goalPosition
	 */
	public void verifyPath(Position startPosition, Position goalPosition) {
		if ((maze == null) || (startPosition == null) || (goalPosition == null))
			throw new IllegalArgumentException();
		setFree(startPosition);
		setFree(goalPosition);
		Position start = startPosition;
		Position end = goalPosition;
		if (end.getZ() < start.getZ()) {
			Position temp = start;
			start = end;
			end = temp;
		}
		while (start.getZ() < end.getZ()) {
			setPathFree(start, new Position(start.getX(), start.getY(), end.getZ()));
			start = getBuildAbovePosition(start);
		}
		if (end.getX() < start.getX()) {
			Position temp = start;
			start = end;
			end = temp;
		}
		while (start.getX() < end.getX()) {
			setPathFree(start, new Position(end.getX(), start.getY(), start.getZ()));
			start = getBuildForwardPosition(start);
		}
		if (end.getY() < start.getY()) {
			Position temp = start;
			start = end;
			end = temp;
		}
		while (start.getY() < end.getY()) {
			setPathFree(start, new Position(start.getX(), end.getY(), start.getZ()));
			start = getBuildRightPosition(start);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cols;
		result = prime * result + flos;
		result = prime * result + ((goalPosition == null) ? 0 : goalPosition.hashCode());
		result = prime * result + Arrays.deepHashCode(maze);
		result = prime * result + rows;
		result = prime * result + ((startPosition == null) ? 0 : startPosition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maze3d other = (Maze3d) obj;
		if (cols != other.cols)
			return false;
		if (flos != other.flos)
			return false;
		if (goalPosition == null) {
			if (other.goalPosition != null)
				return false;
		} else if (!goalPosition.equals(other.goalPosition))
			return false;
		if (!Arrays.deepEquals(maze, other.maze))
			return false;
		if (rows != other.rows)
			return false;
		if (startPosition == null) {
			if (other.startPosition != null)
				return false;
		} else if (!startPosition.equals(other.startPosition))
			return false;
		return true;
	}
}