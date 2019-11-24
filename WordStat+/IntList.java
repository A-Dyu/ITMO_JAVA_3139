import java.util.*;
import java.io.*;

public class IntList {
	private int p = 0;
	private int[] a = new int[1];

	public void append(int x) {
		if (p == a.length) {
			a = Arrays.copyOf(a, a.length * 2);
		}
		a[p++] = x;
	}

	public int get(int i) {
		return a[i];
	}

	public int size() {
		return p;
	}
}