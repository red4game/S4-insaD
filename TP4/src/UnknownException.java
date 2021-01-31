public class UnknownException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8012490940417426832L;

	public UnknownException(String msg) {
		super(msg);
	}

}

class UnknownCourseException extends UnknownException {

	private static final long serialVersionUID = -2132827248827748406L;

	public UnknownCourseException(String msg) {
		super(msg);
	}

}

class UnknownRoomException extends UnknownException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2549848096430085617L;

	public UnknownRoomException(String msg) {
		super(msg);
	}

}
