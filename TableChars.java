
public class TableChars{
	private String encoding;
	
	private char horizontal;
	private char vertical;
	private char upperLeftCorner;
	private char upperMiddleEdge;
	private char upperRightCorner;
	private char rightMiddleEdge;
	private char lowerRightCorner;
	private char lowerMiddleEdge;
	private char lowerLeftCorner;
	private char leftMiddleEdge;
	private char middleCross;
	
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int UPPER_LEFT_CORNER = 2;
	public static final int UPPER_MIDDLE_EDGE = 3;
	public static final int UPPER_RIGHT_CORNER = 4;
	public static final int RIGHT_MIDDLE_EDGE = 5;
	public static final int LOWER_RIGHT_CORNER = 6;
	public static final int LOWER_MIDDLE_EDGE = 7;
	public static final int LOWER_LEFT_CORNER = 8;
	public static final int LEFT_MIDDLE_EDGE = 9;
	public static final int MIDDLE_CROSS = 10;
	
	public TableChars() {
		this("UTF-8");
	}
	
	public TableChars(String encoding) {
		this(encoding, '─', '│', '┌', '┬' , '┐', '┤', '┘', '┴', '└', '├', '┼');
	}
	
	public TableChars(char horizontal, char vertical, char upperLeftCorner, char upperMiddleEdge, char upperRightCorner,
			char rightMiddleEdge, char lowerRightCorner, char lowerMiddleEdge, char lowerLeftCorner,
			char leftMiddleEdge, char middleCross) {
		this();
	}
	
	public TableChars(String encoding, char horizontal, char vertical, char upperLeftCorner, char upperMiddleEdge, char upperRightCorner,
			char rightMiddleEdge, char lowerRightCorner, char lowerMiddleEdge, char lowerLeftCorner,
			char leftMiddleEdge, char middleCross) {
		
		this.encoding = encoding;
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.upperLeftCorner = upperLeftCorner;
		this.upperMiddleEdge = upperMiddleEdge;
		this.upperRightCorner = upperRightCorner;
		this.rightMiddleEdge = rightMiddleEdge;
		this.lowerRightCorner = lowerRightCorner;
		this.lowerMiddleEdge = lowerMiddleEdge;
		this.lowerLeftCorner = lowerLeftCorner;
		this.leftMiddleEdge = leftMiddleEdge;
		this.middleCross = middleCross;
	}
	
	public char getChar(int position) {
		if(encoding.equals("UTF-8")) {
			switch(position) {
			case HORIZONTAL:
				return horizontal;
			case VERTICAL:
				return vertical;
			case UPPER_LEFT_CORNER:
				return upperLeftCorner;
			case UPPER_MIDDLE_EDGE:
				return upperMiddleEdge;
			case UPPER_RIGHT_CORNER:
				return upperRightCorner;
			case RIGHT_MIDDLE_EDGE:
				return rightMiddleEdge;
			case LOWER_RIGHT_CORNER:
				return lowerRightCorner;
			case LOWER_MIDDLE_EDGE:
				return lowerMiddleEdge;
			case LOWER_LEFT_CORNER: 
				return lowerLeftCorner;
			case LEFT_MIDDLE_EDGE:
				return leftMiddleEdge;
			case MIDDLE_CROSS:
				return middleCross;
				
			}
		}else if (encoding.equals("ANSI")){
			switch(position) {
			case 1:
				return '|';
			case 0:
			case 2:
			case 3:	
			case 4:
			case 5:
			case 6:
			case 7:
			case 8: 
			case 9:
			case 10:
				return '-';
			}
		}
		return ' ';
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
}
