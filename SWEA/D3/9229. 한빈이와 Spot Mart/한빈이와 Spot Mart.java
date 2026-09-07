import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, M, maxWeight, weight;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S9229/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			weight = 0;
			maxWeight = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 조합으로 풀기
			combination(0, 0);
			
			sb.append("#" + tc + " ");
			
			if (maxWeight == 0) sb.append(-1 + "\n");
			else sb.append(maxWeight + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void combination(int cnt, int start) {
		if (cnt == 2) {
			if (weight > M) return;
			maxWeight = Math.max(maxWeight, weight);
			return;
		}
		
		for (int i = start; i < N; i++) {
			weight += arr[i];
			combination(cnt + 1, i + 1);
			weight -= arr[i];
		}
	}

}

