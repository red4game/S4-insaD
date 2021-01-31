/**
 * Class containing the characteristics of a classroom.
 * 
 * @author kdesnos
 */
public class Room {

	/**
	 * {@link Room} unique number, as displayed on the door. It is assumed that
	 * two rooms will never be created with the same number.
	 */
	private final int number;

	/**
	 * Number of seats in the {@link Room}. Broken chairs are taken into account
	 * in the room capacity.
	 */
	private final int capacity;

	/**
	 * Constructor for the {@link Room} class.
	 * 
	 * @param n
	 *            {@link #number} of the created {@link Room}.
	 * @param c
	 *            {@link #capacity} of the created {@link Room}.
	 */
	public Room(int n, int c) {
		number = n;
		capacity = c;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Room) {
			return (getCapacity() == ((Room) obj).getCapacity())
					&& (getNumber() == ((Room) obj).getNumber());
		} else {
			return false;
		}
	}

	/**
	 * Getter for the {@link #capacity} of a {@link Room}.
	 * 
	 * @return the {@link #capacity} of the {@link Room}.
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Getter for the {@link #number} of a {@link Room}.
	 * 
	 * @return the {@link #number} of the {@link Room}.
	 */
	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Room " + getNumber() + " (" + getCapacity() + " seats)";
	}
}
