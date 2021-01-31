/**
 * Class containing the characteristics of a timetable.
 * 
 * @author kdesnos
 */
public class Timetable {
	static final int MAX_LESSONS = 100;
	static final int MAX_ROOMS = 30;
	static final int MAX_COURSES = 30;

	public static void main(String[] args) {
		/* TODO */
	}

	private Lesson[] scheduledLessons;
	private int nbScheduledLessons;

	private Room[] existingRooms;
	private int nbExistingRooms;

	private Course[] existingCourses;
	private int nbExistingCourses;

	public Timetable() {
		scheduledLessons = new Lesson[MAX_LESSONS];
		nbScheduledLessons = 0;
		existingRooms = new Room[MAX_ROOMS];
		nbExistingRooms = 0;
		existingCourses = new Course[MAX_COURSES];
		nbExistingCourses = 0;
	}

	private void addCourse(Course c) {
		/* TODO */
	}


	private void addLesson(Lesson l){
		/* TODO */
	}

	private void addRoom(Room r) {
		/* TODO */
	}

	private Course getCourse(int id) {
		/* TODO */
		return null;
	}

	private Room getRoom(int num) {
		/* TODO */
		return null;
	}

	/**
	 * Parse the content of the {@link File} designated by the given path. <br>
	 * If the path is not valid display an error message and return false. In
	 * this method, we assume that the format of the parsed file is always
	 * correct. (i.e. do not verify it in your code !)
	 * 
	 * @param path
	 *            the path of the parsed {@link File}.
	 * @return <code>true</code> if the {@link File} was successfully opened and
	 *         its information were readable, <code>false</code> otherwise.
	 */
	public boolean parseFile(String path) {
		/* TODO */
		return false;
	}

	public void print() {
		System.out.println("Timetable");

		// Print list of Rooms
		System.out.print(nbExistingRooms + " Room(s): ");
		for (int i = 0; i < nbExistingRooms; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(existingRooms[i]);
		}
		System.out.println("");

		// Print list of Courses
		System.out.println(nbExistingCourses + " Course(s): ");
		for (int i = 0; i < nbExistingCourses; i++) {
			System.out.println("\t" + existingCourses[i]);
		}

		// Print the scheduled lessons
		System.out.println(nbScheduledLessons + " Lessons are scheduled:");
		for (int i = 0; i < nbScheduledLessons; i++) {
			System.out.println("\t" + scheduledLessons[i]);
		}
	}
}
