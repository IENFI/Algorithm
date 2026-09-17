import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;

	static final int[] dx = { -1, 0, 0, 1 };
	static final int[] dy = { 0, -1, 1, 0 };

	static char[][] arr;
	static boolean[][] visited;
	static int sX = 1, sY = 1;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/S1227/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			arr = new char[100][100];
			visited = new boolean[100][100];

			for (int r = 0; r < 100; r++) {
				// 입력 편하게 하려고 char 배열로 선언
				arr[r] = br.readLine().toCharArray();
			}

			// dfs에 약하니까 dfs로 구현하기
			visited[sX][sY] = true;
			int result = dfs(sX, sY) ? 1 : 0;

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}

		System.out.println(sb.toString());
	}

	public static boolean dfs(int x, int y) {
		
		if (arr[x][y] == '3')
			return true;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!isValid(nx, ny)) continue;
			if (arr[nx][ny] == '1' || visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			if (dfs(nx, ny)) return true;
		}
		
		return false;
	}
	
	public static boolean isValid(int x, int y) {
		return x >= 0 && x < 100 && y >= 0 && y < 100;
	}

}
