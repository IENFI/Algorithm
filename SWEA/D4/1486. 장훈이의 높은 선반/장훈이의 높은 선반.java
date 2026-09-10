import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2^20은 감당가능할 것 같아서 재귀
// 가지치기 하면 됨
// 이미 탑보다 높으면 탐색 멈추기
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, top, minHeight;
	static int[] employees;
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S1486/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			employees = new int[N];
			top = Integer.parseInt(st.nextToken());
			minHeight = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				employees[i] = Integer.parseInt(st.nextToken());
			}
						
			dfs(0, 0);
			
			// 항상 탑은 직원 키 합보다 작거나 같음`
			int answer = minHeight - top;
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb.toString());
	}

	static void dfs(int idx, int height) {
		if (height >= top) {
			minHeight = Math.min(minHeight, height);
			return;
		}
		if (idx >= N) return;
		
		dfs(idx + 1, height + employees[idx]);
		dfs(idx + 1, height);
	}
}
