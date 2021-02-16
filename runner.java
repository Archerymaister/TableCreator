import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class runner {

	public static void main(String[] args) {
		ArrayList<String> headline = new ArrayList<String>();
		headline.add("Abk.");
		headline.add("Ausgeschrieben");
		headline.add("Erklärung");
		
		String[] line1 = {"WA","WhatsApp","Instant Messenger"};
		
		HashMap<Object, String> line2 = new HashMap<Object,String>();
		line2.put(1,"IG");
		line2.put(2,"Instagram");
		line2.put(3,"App für Bilder");
		
		LineObject LOheadline = LineObject.fromArrayList(headline);
		LineObject LOline1 = LineObject.fromArray(line1);
		LineObject LOline2 = LineObject.fromHashMap(line2);
		LineObject LOline3 = LineObject.fromArray(new String[]{"SC","Snapchat","Instant Messenger für Bilder"});
		LineObject LOline4 = new LineObject("WV","WandaVision","Serie");
		LineObject sepLine = LineObject.separatorLine();
		
		
		TableObject table = null;
		try {
			table = new TableObject(true,LOheadline, LOline1, LOline2, LOline3);
		} catch (UnbalancedColumnCountException e) {
			e.printStackTrace();
		}
		
		try {
			table.addLines(LOline4);
		} catch (UnbalancedColumnCountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			table.addLines(new LineObject("HIMYM","How I Met Your Mother","Serie"));
			table.addLines(sepLine);
			table.addLines(sepLine);
			table.addLines(sepLine);
			table.addLines(sepLine);
			table.addLines(sepLine);
		} catch (UnbalancedColumnCountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		table.setEncoding("ANSI");
		table.print();
		
		table.setEncoding("UTF-8");
		
		StringSelection selection = new StringSelection(table.asString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		
	}

}
