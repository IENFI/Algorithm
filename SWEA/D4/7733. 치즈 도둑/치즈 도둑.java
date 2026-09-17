import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int[] dx = { -1, 0, 0, 1 };
	static final int[] dy = { 0, -1, 1, 0 };

	// 필요 변수 선언
	static int result, N, day, lastDay;
	static boolean[] visited;
	static List<Integer>[] cheeseDay;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S7733/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 변수 초기화
			result = 1;
			N = Integer.parseInt(br.readLine());
			
			// 어차피 맛 검사는 100일까지만 하니까
			cheeseDay = new ArrayList[101];
			parent = new int[N*N];
			lastDay = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					day = Integer.parseInt(st.nextToken());
					if (cheeseDay[day] == null) {
						cheeseDay[day] = new ArrayList<>();
					}
					int pos = i * N + j;
					cheeseDay[day].add(pos);
					parent[pos] = pos;
					lastDay = Math.max(lastDay, day);
				}
			}

			// day 0에는 무조건 1 덩어리
			int bundle = 0;
			visited = new boolean[N*N];
			
			for (day = lastDay; day >= 1; day--) {

				if (cheeseDay[day] == null)
					continue;
				
				List<Integer> posList = cheeseDay[day];
				for (int i = 0; i < posList.size(); i++) {
					int pos = posList.get(i);
					// 일단 추가
					bundle++;
					visited[pos] = true;

					// 주변 덩어리에 합쳐지는 경우 감소
					for (int d = 0; d < 4; d++) {
						int nx = pos / N + dx[d];
						int ny = pos % N + dy[d];

						// nPos로 합치고 좌표 valid 검사를 하면 
						// 다른 행으로 넘어가는 경우를 검사하지 못함
						if (!isValid(nx, ny))
							continue;
						
						int nPos = nx * N + ny;

						if (!visited[nPos]) continue;
						
						int rootA = find(pos);
						int rootB = find(nPos);
						
						if (rootA != rootB) {
							// union은 반드시 root와 root를 연결해야 한다.
							parent[rootB] = rootA;
							bundle--;
						}
					}
				}
				result = Math.max(result, bundle);
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb.toString());
	}

	static int find(int pos) {
		if (parent[pos] == pos)
			return pos;

		return parent[pos] = find(parent[pos]);
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
