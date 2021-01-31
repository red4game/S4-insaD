/**
 * Class containing the characteristics of a lesson.
 * 
 * @author kdesnos
 */
public class Lesson {

	/**
	 * Starting time of the {@link Lesson}. All slots last 1 hour and start on
	 * the hour !
	 */
	private final int startTime;

	/**
	 * {@link Course#id} for this {@link Lesson}.
	 */
	private final int courseId;

	/**
	 * {@link Room#number} where the {@link Lesson} is taking place.
	 */
	private final int roomNumber;

	/**
	 * Constructor for the {@link Lesson} class.
	 * 
	 * @param time
	 *            the {@link #time} of the created {@link Lesson}.
	 * @param c
	 *            the {@link #courseId} of the created {@link Lesson}.
	 * @param r
	 *            the {@link #roomNumber} of the created {@link Lesson}.
	 */
	public Lesson(int time, int c, int r) {
		startTime = time;
		courseId = c;
		roomNumber = r;
	}

	/**
	 * Getter for the {@link #startTime} of a {@link Lesson}.
	 * 
	 * @return the {@link #startTime} of the {@link Lesson}.
	 */
	public int getStartTime() {
		return startTime;
	}


	/**
	 * Getter for the {@link #courseId} of a {@link Lesson}.
	 * 
	 * @return the {@link #courseId} of the {@link Lesson}.
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * Getter for the {@link #roomNumber} of a {@link Lesson}.
	 * 
	 * @return the {@link #roomNumber} of the {@link Lesson}.
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	@Override
	public String toString() {
		return "Course " + getCourseId() + ", Room "
				+ getRoomNumber() + " at " + getStartTime();
	}
}
