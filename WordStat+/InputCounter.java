import java.util.*;
import java.io.*;
public class InputCounter {
	private IntList positions = new IntList();
	private int lastStr = -1;
	private int k = 0;

	public void append(int x, int str) {
		k++;
		if (str != lastStr) {
			positions.append(x);
			lastStr = str;
		}
	}
	
	public String getAnswer() {
		StringBuilder str = new StringBuilder(Integer.toString(k));
		for (int i = 0; i < positions.size(); i++) {
				str.append(" ").append(positions.get(i));
		}
		return str.toString();
	}
}