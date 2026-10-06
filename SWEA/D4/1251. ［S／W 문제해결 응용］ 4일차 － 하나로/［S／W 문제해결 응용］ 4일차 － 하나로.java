import java.io.*;
import java.util.*;

public class Solution {
	static class Node {
		int to;
		double weight;
		Node next;
		Node(int to, double weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	static class Edge {
		int to;
		double weight;
		Edge (int to, double weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main (String args[]) throws Exception {
		// System.setIn(new FileInputStream("res/S1251/re_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			double result = 0.0;

			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][2];
			double E;

			// 새로운 노드가 들어오면 
			// 기존 노드가 새로운 노드의
			// next에 붙는 방식
			Node[] adjList = new Node[N];
			boolean[] visited = new boolean[N];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int r = Integer.parseInt(st.nextToken());
					arr[j][i] = r;
				}
			}

			E = Double.parseDouble(br.readLine());

			// 간선 추가할 필요 없이
			// 섬을 뽑을 때 바로 계산하면 됨
			double[] minEdge = new double[N];
			Arrays.fill(minEdge, Double.MAX_VALUE);

			// 아무 정점이나 시작으로 삼기
			Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a.weight));
			pq.offer(new Edge(0, 0.0));
			
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				int start = e.to;
				double cost = e.weight;
				if (visited[start] ||
						cost > minEdge[start]) continue;

				visited[start] = true;
				minEdge[start] = cost;
				result += cost;

				for (int i = 0; i < N; i++) {
					if (i == start) continue;
					double nCost = E * (Math.pow(arr[start][0] - arr[i][0], 2)
						+ Math.pow(arr[start][1] - arr[i][1], 2));

					if (nCost > minEdge[i]) continue;
					pq.offer(new Edge(i, nCost));
				}

			}

			long convertResult = Math.round(result);

			sb.append('#').append(tc).append(' ').append(convertResult).append('\n');
		}

		System.out.println(sb.toString());
	}
}
