import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int result;
	static int N;
	static int[][] arr;
	static boolean[][][] visited;
	static int startX, startY, endX, endY;
	static final int[] dx = {-1, 0, 0, 1};
	static final int[] dy = {0, -1, 1, 0};
	static Queue<Pos> q;
	
	static int leastTime = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/4193/sample.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N][3];
			leastTime = Integer.MAX_VALUE;
			q = new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				String row = br.readLine();
				st = new StringTokenizer(row);
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			String startRow = br.readLine();
			st = new StringTokenizer(startRow);
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			
			String endRow = br.readLine();
			st = new StringTokenizer(endRow);
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			q.offer(new Pos(startX, startY, 0));
			visited[startX][startY][0] = true;
			calculateLeastDistance();
			if (leastTime == Integer.MAX_VALUE) 
				leastTime = -1;
			
			sb.append("#")
				.append(test_case)
				.append(" ")
				.append(leastTime)
				.append("\n");
		}
		
		System.out.println(sb);
		

	}
	
	static void calculateLeastDistance() {
		while (!q.isEmpty()) {
			// 이동 비용이 다르다면 BFS가 아니라 PriorityQueue를 사용해서
			// 작은 상태를 꺼내는 다익스트라를 사용해야 한다.
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int time = p.time;
			
			if (leastTime <= time) continue;
			if (endX == x && endY == y) {
				leastTime = Math.min(time, leastTime);
				continue;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				// 갈 수 없는 칸이거나
				// 이미 같거나 더 이른 시간에 같은 곳을 방문했거나
				// 장애물이 있으면 패스
				

				int next_time = time + 1;
				
				// 0, 1, 2 모듈로별로 따로 관리하는게 좋기 때문에
				// dist만을 사용해서 빨리 도착한것만 통과시키는 것은 문제가 있다고 판단됨
//				if (!(isValid(nx, ny)) || dist[nx][ny] <= time || arr[nx][ny] == 1)
				if (!(isValid(nx, ny)) || visited[nx][ny][next_time % 3] || arr[nx][ny] == 1)
					continue;
				
				// 소용돌이가 없어지지 않았을 때
				else if ((arr[nx][ny] == 2 && time % 3 != 2)) {
					Pos p1 = new Pos(x, y, next_time);
					visited[x][y][next_time%3] = true;
					q.offer(p1);
				}
				
				// 소용돌이 지나갈 수 있거나 그냥 지나갈 수 있는 길일 때
				else {
					Pos p1 = new Pos(nx, ny, next_time);
					visited[nx][ny][next_time % 3] = true;
					q.offer(p1);
				}
			}
		}
	}
	
	static boolean isValid(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
}
	
class Pos {
	int x;
	int y;
	int time;
	
	Pos(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}
