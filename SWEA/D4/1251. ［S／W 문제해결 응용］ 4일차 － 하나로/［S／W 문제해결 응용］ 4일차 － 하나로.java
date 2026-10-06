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

			// 모든 정점에 대해 간선 연결
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					double cost = E * (Math.pow(arr[i][0] - arr[j][0],2)
						+ Math.pow(arr[i][1] - arr[j][1],2));	
					adjList[i] = new Node(j, cost, adjList[i]);
					adjList[j] = new Node(i, cost, adjList[j]);
				}
			}

			// 아무 정점이나 시작으로 삼기
			Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a.weight));
			pq.offer(new Edge(0, 0.0));
			
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				int start = e.to;
				double cost = e.weight;
				if (visited[start]) continue;

				visited[start] = true;
				result += cost;

				for (Node temp = adjList[start]; temp != null; temp = temp.next) {
					pq.offer(new Edge(temp.to, temp.weight));	
				}
			}
			long convertResult = (long) Math.round(result);

			sb.append('#').append(tc).append(' ').append(convertResult).append('\n');
		}

		System.out.println(sb.toString());
	}
}
