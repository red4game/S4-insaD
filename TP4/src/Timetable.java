import java.io.BufferedReader;
import java.io.FileReader;

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
		Timetable t = new Timetable();
		try {
			t.parseFile("D:/S4-insaD/TP4/timetable.txt");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("No file provided");
			return;
		}

		t.print();
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
		Course[] nc = new Course[nbExistingCourses+1];
		for (int i=0;i<nbExistingCourses;i++){
			nc[i] = existingCourses[i];
		}
		nc[nbExistingCourses] = c;
		existingCourses=nc;
		nbExistingCourses++;
	}


	private void addLesson(Lesson l) throws UnknownRoomException,UnknownCourseException,OccupiedRoomException {
		if(getCourse(l.getCourseId())==null) {
			throw new UnknownCourseException("probleme de course au niveau de " + l.getCourseId());
		}
		if(getRoom(l.getRoomNumber())==null) {
			throw new UnknownRoomException("probleme de room au niveau de "+l.getRoomNumber());
		}
		for (int i = 0;i<nbScheduledLessons;i++) {
			if (scheduledLessons[i].getRoomNumber() == l.getRoomNumber() & scheduledLessons[i].getStartTime() == l.getStartTime()) {
				throw new OccupiedRoomException("la room " + l.getRoomNumber() + " est déjà occupée à l'heure " + l.getStartTime());
			}
		}
		Lesson[] nl = new Lesson[nbScheduledLessons + 1];
		for (int i = 0; i < nbScheduledLessons; i++) {
			nl[i] = scheduledLessons[i];
		}
		nl[nbScheduledLessons] = l;
		scheduledLessons = nl;
		nbScheduledLessons++;
	}

	private void addRoom(Room r) {
		Room[] nr = new Room[nbExistingRooms+1];
		for (int i=0;i<nbExistingRooms;i++){
			nr[i] = existingRooms[i];
		}
		nr[nbExistingRooms] = r;
		existingRooms=nr;
		nbExistingRooms++;
	}

	private Course getCourse(int id) throws UnknownCourseException {
		for (int i=0;i<nbExistingCourses;i++){
			if (existingCourses[i].getId() == id){
				return new Course(id,existingCourses[i].getName(),existingCourses[i].getTeacherName());
			}
		}
		return null;
	}

	private Room getRoom(int num) throws UnknownRoomException {
		for (int i=0;i<nbExistingRooms;i++){
			if (existingRooms[i].getNumber() == num){
				return new Room(num,existingRooms[i].getCapacity());
			}
		}
		return null;
	}

	/**
	 * Parse the content of the {@link "D:/S4-insaD/TP4/timetable.txt"} designated by the given path. <br>
	 * If the path is not valid display an error message and return false. In
	 * this method, we assume that the format of the parsed file is always
	 * correct. (i.e. do not verify it in your code !)
	 * 
	 * @param path
	 *            the path of the parsed {@link "D:/S4-insaD/TP4/timetable.txt"}.
	 * @return <code>true</code> if the {@link "D:/S4-insaD/TP4/timetable.txt"} was successfully opened and
	 *         its information were readable, <code>false</code> otherwise.
	 */
	public boolean parseFile(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String[] tab = null;
			Boolean found = false;
			int ligne = 0;
			while (br.ready()){
				tab = (br.readLine()).split(",");
				ligne++;
				if (tab[0].equals("Room")){
					addRoom(new Room(Integer.parseInt(tab[1]), Integer.parseInt(tab[2])));
				}
				if (tab[0].equals("Course")){
					addCourse(new Course(Integer.parseInt(tab[1]),tab[2],tab[3]));
				}
				if (tab[0].equals("Lesson")) {
					try {
						addLesson(new Lesson(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3])));
					} catch (UnknownCourseException ue) {
						System.out.println(ue);
					}catch (OccupiedRoomException | UnknownRoomException oreu){
						//problème de réallouement illégaux et remplecement de vrai salles alloués plus tard dans le file.
						System.out.println("Conflict : " + oreu);
						System.out.println("la salle demandée est déjà occupé nous allons essayer d'en trouver une");
						for (int i=0;i<nbExistingRooms;i++){
							try{
								addLesson(new Lesson(Integer.parseInt(tab[1]),Integer.parseInt(tab[2]),existingRooms[i].getNumber()));
								System.out.println("une nouvelle room a été touvée :" +existingRooms[i].toString());
								found =true;
								break;
							} catch (OccupiedRoomException orei){
								System.out.println(orei);
							}
						}
						if (found==false){
							System.out.println("Aucune nouvelle room n'a pu être trouvée, toute étaient occupées");
						}
						found = false;
					}
				}
			}
			return true;
		} catch (Exception e){
			System.out.println(e + "erreur lors du parsing");

			return false;
		}
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
