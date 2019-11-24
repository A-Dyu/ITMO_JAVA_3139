import java.util.Arrays;
import java.util.Comparator;
import java.lang.*;
import java.io.*;
public class ReverseSort {
	public static class Arr {
		private long s;
		private int p;
		private int[] a;
		public Arr(long sum, int pos, int[] arr) {
			this.p = pos;
			this.a = arr;
			this.s = sum;
		}
		public long getSum() {
			return s;
		}
		public int getP() {
			return p;
		}
		public int get(int i) {
			return a[i];
		}
		public int size() {
			return a.length;
		}
	}

	public static void main(String[] args) {

		try {
			MyScanner sc = new MyScanner();
			Arr[] a = new Arr[1];
			int i = 0;
			while (!sc.isEOF()) {
				int[] b = new int[1];
				int j = 0;
				long sum = 0L;
				while (sc.hasIntBeforeSeparation()) {
					if (j == b.length) {
						b = Arrays.copyOf(b, b.length * 2);
					}
					b[j] = sc.nextInt();
					sum += b[j];
					j++;
				}
				sc.skipLine();
				b = Arrays.copyOf(b, j);
				Arrays.sort(b);
				if (i == a.length) {
					a = Arrays.copyOf(a, a.length * 2);
				}
				a[i] = new Arr(sum, i, b);
				i++;
			}
			a = Arrays.copyOf(a, i);
			Arrays.sort(a, Comparator.comparingLong(Arr::getSum).thenComparing(Arr::getP));
			for (i = a.length - 1; i >= 0; i--) {
				for (int j = a[i].size() - 1; j >= 0; j--) {
					System.out.print(a[i].get(j) + " ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			System.out.println("I/O Exception: " + e.getMessage());
		}
	}
}
