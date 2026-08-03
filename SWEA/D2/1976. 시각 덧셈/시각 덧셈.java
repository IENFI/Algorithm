import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer tk;
	static int N;
	static int hour;
	static int min;
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/1976/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			hour = 0;
			min = 0;
			
			calculateTime();
			sb.append("#")
			.append(test_case)
			.append(" ")
			.append(hour)
			.append(" ")
			.append(min)
			.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void calculateTime() throws IOException {
		tk = new StringTokenizer(br.readLine());
		hour += Integer.parseInt(tk.nextToken());
		min += Integer.parseInt(tk.nextToken());
		hour += Integer.parseInt(tk.nextToken());
		min += Integer.parseInt(tk.nextToken());
		if (min >= 60) {
			hour++;
			min %= 60;
		}
		if (hour % 12 == 0) hour = 12;
		
		if (hour > 12) hour %= 12;
	}

}
