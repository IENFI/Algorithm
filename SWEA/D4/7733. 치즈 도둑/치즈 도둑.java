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
	static int[][] arr;
	static boolean[][] visited;
	static Map<Integer, List<Pos>> cheeseDay;
	static Map<Pos, Pos> union;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S7733/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 변수 초기화
			result = 1;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			cheeseDay = new HashMap<>();
			union = new HashMap<>();
			lastDay = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					day = Integer.parseInt(st.nextToken());
					List<Pos> list = cheeseDay.computeIfAbsent(day, k -> new ArrayList<>());
					Pos p = new Pos(i, j);
					list.add(p);
					union.put(p, p);
					lastDay = Math.max(lastDay, day);
				}
			}

			// day 0에는 무조건 1 덩어리
			int bundle = 0;
			visited = new boolean[N][N];
			for (day = lastDay; day >= 1; day--) {

				if (!cheeseDay.containsKey(day))
					continue;
				if (cheeseDay.get(day).size() == 0)
					continue;
				for (Pos p : cheeseDay.get(day)) {

					// 일단 추가
					bundle++;
					visited[p.x][p.y] = true;

					// 주변 덩어리에 합쳐지는 경우 감소
					for (int d = 0; d < 4; d++) {
						int nx = p.x + dx[d];
						int ny = p.y + dy[d];

						if (!isValid(nx, ny))
							continue;
						Pos q = new Pos(nx, ny);
						if (visited[nx][ny]) {
							if (!find(p).equals(find(q))) {
								// union은 반드시 root와 root를 연결해야 한다.
								union.put(find(q), find(p));
								bundle--;
							}
						}
					}
				}
				result = Math.max(result, bundle);
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb.toString());
	}

	static Pos find(Pos p) {
		if (union.get(p).equals(p))
			return p;

		Pos root = find(union.get(p));
		union.put(p, root);
		return root;
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return 31 * x * y;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (obj == null)
				return false;
			if (obj.getClass() != this.getClass())
				return false;
			Pos pos = (Pos) obj;
			return this.x == pos.x && this.y == pos.y;
		}
	}
}
