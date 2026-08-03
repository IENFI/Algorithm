import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
	// 1868. 파핑파핑 지뢰찾기
	static BufferedReader br;
	static StringBuilder sb;
	static int N;
	static int result;
	static char[][] arr;
	static boolean[][] visited;
	static int[][] bombArr;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	static Queue<Pos> q;
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/1868/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new char[N][N];
			visited = new boolean[N][N];
			bombArr = new int[N][N];
			q = new ArrayDeque<>();
			
			// 입력
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			
			result = clickCount();
			
			sb.append("#")
				.append(test_case)
				.append(" ")
				.append(result)
				.append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 클릭 개수를 세는 함
	static int clickCount() {
 		int count = 0;
		calcuateBombCount();
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c] && bombArr[r][c] == 0) {
					q.offer(new Pos(r, c));
					bfs();
					count++;
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(!visited[r][c] && arr[r][c] != '*')
					count++;
			}
		}
		
		return count;
	}
	
	// 주변의 0를 찾아 주변을 계속 밝히는 함수
	static void bfs() {
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int nx;
			int ny;
			visited[x][y] = true;
			
			for (int d = 0; d < 8; d++) {
				nx = x + dx[d];
				ny = y + dy[d];
				if (!isValid(nx, ny) || visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if (bombArr[nx][ny] == 0) q.offer(new Pos(nx, ny));
			}
		}
	}
	
	// 폭탄 개수를 세서 입력하는 함수
	static void calcuateBombCount() {
		int bombCount = 0;
		int nx;
		int ny;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				bombCount = 0;
				if (arr[r][c] == '*') {
					bombArr[r][c] = -1;
					continue;
				}
				for (int d = 0; d < 8; d++) {
					nx = r + dx[d];
					ny = c + dy[d];
					
					if(!isValid(nx, ny)) continue;
					if(arr[nx][ny] == '*') bombCount++;
				}
				bombArr[r][c] = bombCount;
			}
		}
	}
	
	static boolean isValid(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
	
	static class Pos {
		int x;
		int y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
