import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S3499/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] arr = new String[N];
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				arr[i] = st.nextToken();
			}
			
			sb.append('#').append(t).append(' ');
			
			int center = (N + 1) / 2;
			
			for(int i = 0; i < center; i++) {
				sb.append(arr[i]).append(' ');
				if (i + center >= N) break;
				sb.append(arr[i + center]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

}
