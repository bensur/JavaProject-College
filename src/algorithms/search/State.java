/**
 * 
 */
package algorithms.search;

import java.io.Serializable;

/**
 * @author Ben Surkiss & Yovel Shchori
 *
 */
public class State<T> implements Comparable<State<T>>, Serializable {
	private static final long serialVersionUID = -2717538413230275394L;
	private int cost;
	private T state;
	private State<T> cameFrom;
	/*
	 * 
	 * @param t state <T>
	 */
	public State(T t) {
		this.state = t;
		this.cost = 0;
		this.cameFrom = null;
	}
	/*
	 * 
	 * @param t state <T>
	 * @param cost to set
	 */
	public State(T t, int cost) {
		this.state = t;
		this.cost = cost;
		this.cameFrom = null;
	}
	/**
	 * 
	 * @return Solution to this state
	 */
	public Solution<T> getSolution() {
		Solution<T> solution = new Solution<T>();
		if (cameFrom != null)
			solution.add(cameFrom.getSolution());
		solution.add(this);
		return solution;
	}
	/**
	 * 
	 * @param n State came from
	 */
	public void setFrom(State<T> n) {
		this.cameFrom = n;
	}
	/**
	 * 
	 * @return state cost
	 */
	public int getCost() {
		return this.cost;
	}
	/**
	 * 
	 * @param cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	/**
	 * 
	 * @return state
	 */
	public T getState() {
		return this.state;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((cameFrom == null) ? 0 : cameFrom.hashCode());
//		result = prime * result + cost;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		@SuppressWarnings("unchecked")
		State<T> other = (State<T>) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(state.toString());
		return sb.toString();
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable<T>#compareTo(State<T> o)
	 */
	@Override
	public int compareTo(State<T> o) {
		return this.cost - o.getCost();
	}
}