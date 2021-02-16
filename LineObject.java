import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LineObject {
	private ArrayList<String> content;
	private int columnCount;
	private boolean separator = false;
	
	public LineObject(String ... strings) {
		ArrayList<String> content = new ArrayList<String>();
		for(String s : strings)
			content.add(s);
		
		this.content = content;
		this.columnCount = content.size();
	}
	
	public LineObject(ArrayList<String> content) {
		this.content = content;
		this.columnCount = content.size();
	}
	
	public LineObject(boolean separator) {
		this.separator = separator;
	}
	
	public static LineObject fromArray(String[] content) {
		ArrayList<String> rtn = new ArrayList<String>();
		for(String s : content)
			rtn.add(s);
		return new LineObject(rtn);
	}
	
	public static LineObject fromHashMap(HashMap<Object,String> content) {
		ArrayList<String> rtn = new ArrayList<String>();
		for(String s : content.values())
			rtn.add(s);
		return new LineObject(rtn);
	}
	
	public static LineObject fromArrayList(ArrayList<String> content) {
		return new LineObject(content);
	}
	
	public static LineObject separatorLine() {
		return new LineObject(true);
	}
	
	public String asString(int[] maxLen, TableChars charSet) {
		String s = "";
		s += charSet.getChar(TableChars.VERTICAL);
		for(int i = 0; i < getColumnCount(); i ++)
			s += " " + getFilledColumn(i, maxLen, charSet) + " " + charSet.getChar(TableChars.VERTICAL);
		return s;
	}
	
	public String getColumn(int col) {
		return content.get(col);
	}
	
	public String getFilledColumn(int col, int[] maxLen, TableChars charSet) {
		String s = content.get(col);
		for(int i = getColumn(col).length(); i < maxLen[col]; i++)
			s += ' ';
		return s;
	}

	public int getColumnCount() {
		return columnCount;
	}
	
	@Override
	public String toString() {
		String str = "LineObject={";
		for(String s : content){
			str += s + ",";
		}
		
		return str+"}";
	}

	public boolean isSeparator() {
		return separator;
	}
	
	
}
