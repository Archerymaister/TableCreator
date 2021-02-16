import java.util.HashMap;

public class TableObject {
	private LineObject headline;
	private HashMap<Integer,LineObject> lines = new HashMap<Integer,LineObject>();
	private int columnCount;
	private TableChars charSet;
	private String encoding = "UTF-8";
	
	public TableObject(LineObject ... lines) throws UnbalancedColumnCountException {
		this(false, lines);
	}
	
	public TableObject(boolean headline, LineObject ... lines) throws UnbalancedColumnCountException {
		this.charSet = new TableChars(encoding);
		this.columnCount = lines[0].getColumnCount();
		if(headline)
			this.headline = lines[0];
		for(int i = (headline ? 1 : 0);i < lines.length; i++) {
			if(!lines[i].isSeparator() && lines[0].getColumnCount() != lines[i].getColumnCount())
				throw new UnbalancedColumnCountException(lines[0].getColumnCount(), lines[i].getColumnCount());
			this.lines.put(this.lines.size(), lines[i]);
		}
	}
	
	public void addLines(LineObject ... lines) throws UnbalancedColumnCountException {
		for(LineObject line : lines) {
			if(!line.isSeparator() && this.lines.get(0).getColumnCount() != line.getColumnCount())
				throw new UnbalancedColumnCountException(this.lines.get(0).getColumnCount(), line.getColumnCount());
			this.lines.put(this.lines.size(),line);
		}
	}
	
	public void setLine(int lineNumber, LineObject line) {
		this.lines.put(lineNumber, line);
	}
	
	private int[] colLengths() {
		int[] rtn = new int[columnCount];
		
		if(hasHeadline())
			for(int i = 0; i < columnCount; i++)
				if(headline.getColumn(i).length() > rtn[i])
					rtn[i] = headline.getColumn(i).length();
		
		for(LineObject lo : lines.values())
			if(!lo.isSeparator())
				for(int i = 0; i < columnCount; i++)
					if(lo.getColumn(i).length() > rtn[i])
						rtn[i] = lo.getColumn(i).length();
		
		return rtn;
	}
	
	public boolean hasHeadline() {
		return headline != null;
	}
	
	public String getHoritontalLinePiece(int len) {
		String s = "";
		for(int i = 0; i<len;i++)
			s += charSet.getChar(TableChars.HORIZONTAL);
		return s;
	}
	/**
	 * 
	 * @param type 1 = first line; 2 = middle line; 3 = end line
	 * @return
	 */
	public String getHorizontalLine(int type) {
		String s = "";
		if(type == 0) {
			s += charSet.getChar(TableChars.UPPER_LEFT_CORNER);
			for(int i = 0; i < columnCount; i++) {
				s += charSet.getChar(TableChars.HORIZONTAL) +  getHoritontalLinePiece(colLengths()[i]) + charSet.getChar(TableChars.HORIZONTAL);
				if((i+1) != columnCount)
					s += charSet.getChar(TableChars.UPPER_MIDDLE_EDGE);
				else
					s += charSet.getChar(TableChars.UPPER_RIGHT_CORNER);
			}
		}else if(type == 1) {
			s += charSet.getChar(TableChars.LEFT_MIDDLE_EDGE);
			for(int i = 0; i < columnCount; i++) {
				s += charSet.getChar(TableChars.HORIZONTAL) +  getHoritontalLinePiece(colLengths()[i]) + charSet.getChar(TableChars.HORIZONTAL);
				if((i+1) != columnCount)
					s += charSet.getChar(TableChars.MIDDLE_CROSS);
				else
					s += charSet.getChar(TableChars.RIGHT_MIDDLE_EDGE);
			}
		}else if(type == 2) {
			s += charSet.getChar(TableChars.LOWER_LEFT_CORNER);
			for(int i = 0; i < columnCount; i++) {
				s += charSet.getChar(TableChars.HORIZONTAL) +  getHoritontalLinePiece(colLengths()[i]) + charSet.getChar(TableChars.HORIZONTAL);
				if((i+1) != columnCount)
					s += charSet.getChar(TableChars.LOWER_MIDDLE_EDGE);
				else
					s += charSet.getChar(TableChars.LOWER_RIGHT_CORNER);
			}
		}
		return s;
	}
	
	public String asString() {
		String s = "";
		s += getHorizontalLine(0) + "\n";
		if(hasHeadline()) {
			s += headline.asString(colLengths(), charSet) + "\n";
			s += getHorizontalLine(1) + "\n";
		}
		for(LineObject line : lines.values())
			if(line.isSeparator())
				s += getHorizontalLine(1) + "\n";
			else
				s += line.asString(colLengths(), charSet) + "\n";
		s += getHorizontalLine(2);
		
		return s;
	}
	
	public void print() {
		System.out.println(asString());
	}
	
	public int getLineCount() {
		return this.lines.size();
	}

	public TableChars getCharSet() {
		return charSet;
	}

	public void setCharSet(TableChars charSet) {
		this.charSet = charSet;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
		this.charSet = new TableChars(encoding);
	}
	
	
	
	
}
