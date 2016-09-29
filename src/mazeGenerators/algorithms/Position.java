/**
 * 
 */
package mazeGenerators.algorithms;

import java.io.Serializable;
/**
 * Interface of Maze3d generator
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class Position implements Serializable {
	private static final long serialVersionUID = -7043916336674527477L;
	private int x;
	private int y;
	private int z;
	
	/** C'tor
	 * @param x to set
	 * @param y to set
	 * @param z to set
	 */
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * C'tor
	 * @param p to copy from
	 */
	public Position(Position p){
		this.x = p.getX();
		this.y = p.getY();
		this.z = p.getZ();
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}
/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + x + ", " + y + ", " + z + "}";
	}
}