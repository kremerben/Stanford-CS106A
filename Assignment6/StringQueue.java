import java.util.ArrayList;


public class StringQueue implements MinimalStringQueue {

	public StringQueue(){
		holdArray = new ArrayList<String>();

	}
	
	
	public void add(String s) {
		holdArray.add(s);
	}
	
	public int size() {
		return holdArray.size();
	}
	
	public String poll() {
		if (holdArray.size() > 0) {
			return holdArray.remove(0);
		}
		return null;
	}
	
	private ArrayList<String> holdArray;

	
}
