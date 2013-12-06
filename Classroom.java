import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Classroom {

	public static String pickRandom(String[] ordered, Random r){
		return ordered[r.nextInt(ordered.length) + 1];
	}

	public static int[] test(String[] ordered, Random r){
		int[] picked = new int[ordered.length];
		for (int i = 0; i<ordered.length; i++) {
			picked[i] = i;
		}
		for (int i = 0; i<ordered.length; i++) {
			int n = r.nextInt(picked.length) + 1;
			int temp = picked[i];
			picked[i] = picked[n];
			picked[n] = temp;
		}
		return picked;
	}

	private static String[] group(int size, String[] ordered, Random r){
		String[] groups = new String[size];
		int[] picked = test(ordered, r);
		for (int i =0; i<picked.length; i++) {
			if (i<=size) {
				groups[i % size] = ordered[picked[i]] + ", ";
			} else{
				groups[i % size] += ordered[picked[i]] + ", ";
			}
		}
		return groups;
	}

	public static void main(String[] args) {
		File f;
		Scanner s;
		String classz = " ";
		String[] ordered;
		Random r = new Random();
		int[] picked;
		try {
			f = new File("class.csv");
			s = new Scanner(f);
			int p;
			p = s.nextInt();
			System.out.println(p);
			ordered = new String[p];
			for (int j=0; j< p; j++) {
				String g = s.next();
				if (j==0) {
					ordered[j] = g + " ";
				} else if(j==p - 1){
					ordered[j-1] += g.substring(0, 1);
					ordered[j] = g.substring(2, g.length());
					ordered[j] += s.next();
				} else{
					ordered[j-1] += g.substring(0, 1);
					ordered[j] = g.substring(2, g.length()) + " ";
				}
			}
			// for (int i=0; i<ordered.length; i++) {
			// 	System.out.println(ordered[i]);
			// }
		}
		catch(FileNotFoundException e){
			System.exit(1);
			ordered = new String[1];
		}
		picked = new int[ordered.length];

		//This printline is a random student.

		//System.out.println("Random Student: "+pickRandom(ordered , r));

		//This statement prints the groups choose the side in place of the current 4 to change the amount of groups.

		String[] g = group(4, ordered, r);
		for (int i=0; i<g.length; i++) {
			System.out.println("Group#"+(i+1)+": "+ g[i]);
		}
	}

}