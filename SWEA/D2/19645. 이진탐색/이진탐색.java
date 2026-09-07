import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int cnt, end, A, B;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/S19645/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			end = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			cnt = 0;
			A = binarySearch(1, end, A);
			cnt = 0;
			B = binarySearch(1, end, B);
			String result = A == B ? "0" : A < B ? "A" : "B";
			sb.append("#" + tc + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// binarySearch가 1을 반환함으로써 총 재귀가 몇번돌았는지 검사할 수는 없나?
	public static int binarySearch(int start, int end, int num) {
		int mid = (start + end) / 2;
		cnt++;
		if (mid == num) {
			return cnt;
		}
		else if (num < mid) {
			return binarySearch(start, mid, num);
		}
		else {
			return binarySearch(mid, end, num);
		}
	}

}
