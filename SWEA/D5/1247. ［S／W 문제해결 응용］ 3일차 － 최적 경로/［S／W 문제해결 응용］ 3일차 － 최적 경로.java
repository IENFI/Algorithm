import java.io.*;
import java.util.*;

public class Solution {
	static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, result;
	static boolean[] visited; 
	static int visitCount;
	static Node company, home;
	static Node[] nodes;

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("res/S1247/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			nodes = new Node[N];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			
			company = new Node(
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())
					);

			home = new Node(
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())
					);

			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				nodes[i] = new Node(x, y);
			}

			dfs(company, 0);

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}

		System.out.println(sb.toString());
	}

	static void dfs(Node start, int cost) {
		// 종료 조건
		if (start.equals(home) && cost < result) {
			result = cost;
		}	

		if (visitCount == N) {
			int dist = getDist(start, home);
			int nextCost = cost + dist;

			if (nextCost >= result) return;
			dfs(home, nextCost);
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;

			Node next = nodes[i];
			int dist = getDist(start, next);
			int nextCost = cost + dist;

			if (nextCost >= result) continue;

			visited[i] = true;
			visitCount++;

			dfs(next, nextCost);

			visited[i] = false;
			visitCount--;
		}
	}
	
	static int getDist(Node pre, Node post) {
		return Math.abs(pre.x - post.x) + Math.abs(pre.y - post.y);
	}
}
