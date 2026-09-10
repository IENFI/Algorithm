import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static final int[] dx = { -1, 0, 0, 1 };
	static final int[] dy = { 0, -1, 1, 0 };

	static int N;
	static int[][] arr;
	// 전선이 지나갈 수 있으면 false
	static boolean[][] visited;
	static List<Core> cores;
	static int[] answer;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S1767/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N];
			cores = new ArrayList<>();
			// 0: 연결한 코어의 수, 1: 전선 길이
			answer = new int[2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1) {
						// 코어가 있는 자리도 전선이 못 지나감
						visited[i][j] = true;
						
						// 만약 가장자리면 순회 후보로도 안 넣음
						if (isEdge(i, j)) continue;
						cores.add(new Core(i, j));
					}
				}
			}

			// 재귀
			dfs(0, 0, 0);

			sb.append('#').append(tc).append(' ').append(answer[1]).append('\n');
		}
		System.out.println(sb.toString());
	}

	static void dfs(int coreIdx, int validCoreNum, int length) {
		if (coreIdx == cores.size()) {
			if (validCoreNum > answer[0]) {
				answer[0] = validCoreNum;
				answer[1] = length;
			} else if (validCoreNum == answer[0] && length < answer[1]) {
				answer[1] = length;
			}
			return;
		}
		Core core = cores.get(coreIdx);

		for (int d = 0; d < 4; d++) {
			if (canConnect(core.x, core.y, d)) {
				dfs(coreIdx + 1, validCoreNum + 1, length + installWire(core.x, core.y, d));
				removeWire(core.x, core.y, d);
			}
		}
		dfs(coreIdx + 1, validCoreNum, length);
	}

	static boolean isEdge(int x, int y) {
		// x, y가 가장자리에 있으면 전선 연결됐다고 치면 됨
		return x == 0 || x == N - 1 || y == 0 || y == N - 1;
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static boolean canConnect(int x, int y, int d) {
		// 현재 좌표와 방향을 받아서 연결가능한지 체크
		int nx, ny, cnt = 1;
		// 가장자리는 그 전에 거르기 때문에 괜찮음
		nx = x + dx[d];
		ny = y + dy[d];

		do {
			if (visited[nx][ny])
				return false;
			cnt++;
			nx = x + dx[d] * cnt;
			ny = y + dy[d] * cnt;
		} while (isValid(nx, ny));
		return true;
	}

	static int installWire(int x, int y, int d) {
		int nx, ny, cnt = 1;
		nx = x + dx[d];
		ny = y + dy[d];

		do {
			visited[nx][ny] = true;
			cnt++;
			nx = x + dx[d] * cnt;
			ny = y + dy[d] * cnt;
		} while (isValid(nx, ny));

		return cnt - 1;
	}

	static void removeWire(int x, int y, int d) {
		int nx, ny, cnt = 1;
		nx = x + dx[d];
		ny = y + dy[d];

		do {
			visited[nx][ny] = false;
			cnt++;
			nx = x + dx[d] * cnt;
			ny = y + dy[d] * cnt;
		} while (isValid(nx, ny));
	}

	static class Core {
		int x;
		int y;

		Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
