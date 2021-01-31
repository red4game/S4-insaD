/**
 * Class containing the characteristics of a course.
 * 
 * @author kdesnos
 */
public class Course {

	/**
	 * Number used to uniquely identify a {@link Course}. It is assumed that two
	 * courses will never be created with the same id.
	 */
	private final int id;

	/**
	 * Name of the {@link Course}.
	 */
	private final String name;

	/**
	 * Name of the teacher responsible for this {@link Course}. The same teacher
	 * can be responsible of several courses.
	 */
	private final String teacherName;

	/**
	 * Constructor for the {@link Course} class.
	 * 
	 * @param i
	 *            the {@link #id} of the created {@link Course}.
	 * @param n
	 *            the {@link #name} of the created {@link Course}.
	 * @param tName
	 *            the {@link #teacherName} of the created {@link Course}.
	 */
	public Course(int i, String n, String tName) {
		id = i;
		name = n;
		teacherName = tName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			return (getId() == ((Course) obj).getId())
					&& (getName().equals(((Course) obj).getName()))
					&& (getTeacherName()
							.equals(((Course) obj).getTeacherName()));
		} else {
			return false;
		}
	}

	/**
	 * Getter for the {@link #id} of a {@link Course}.
	 * 
	 * @return the {@link #id} of the {@link Course}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for the {@link #name} of a {@link Course}.
	 * 
	 * @return the {@link #name} of the {@link Course}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the {@link #teacherName} of a {@link Course}.
	 * 
	 * @return the {@link #teacherName} of the {@link Course}.
	 */
	public String getTeacherName() {
		return teacherName;
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getName() + " by " + getTeacherName();
	}
}
