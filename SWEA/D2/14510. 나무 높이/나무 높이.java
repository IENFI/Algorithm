import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int date, N;
	// 앞은 남은 키의 크기, 뒤는 인덱스
	static Map<Integer, ArrayList<Integer>> treeMap;
	static int[] trees;
	static int tallestHeight;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/S14510/Sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			tallestHeight = 0;
			trees = new int[N];
			treeMap = new HashMap<>();
			
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				tallestHeight = Math.max(tallestHeight, trees[i]);
			}
			
			for (int i = 0; i < N; i++) {
				int difference = tallestHeight - trees[i];
				if (difference == 0) continue;
				else if (difference == 1) {
					treeMap.computeIfAbsent(1, k -> new ArrayList<>()).add(i);
				}
				else if (difference == 2) {
					treeMap.computeIfAbsent(2, k -> new ArrayList<>()).add(i);
				}
				else {
					treeMap.computeIfAbsent(0, k -> new ArrayList<>()).add(i);
				}
			}
			
			date = 0;
			
			water();
			// 남는게 2뿐이면 짝수인 날을 기다렸다가 주기
			// 남는게 1뿐이면 홀수인 날을 기다렸다가 주기
			// 
			
			sb.append('#').append(test_case).append(' ').append(date).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	static void water() {
		while(treeMap.size() > 0) {
			date++;
			if (date % 2 == 0 && treeMap.containsKey(2) && treeMap.get(2).size() > 0) {
				treeMap.get(2).remove(treeMap.get(2).size() - 1);
				if (treeMap.get(2).size() == 0) 
					treeMap.remove(2);
			}
			else if (date % 2 == 1 && treeMap.containsKey(1) && treeMap.get(1).size() > 0) {
				treeMap.get(1).remove(treeMap.get(1).size() - 1);
				if (treeMap.get(1).size() == 0) 
					treeMap.remove(1);
			} else if (treeMap.containsKey(0) && treeMap.get(0).size() > 0) {
				int idx = treeMap.get(0).remove(treeMap.get(0).size() - 1);
				trees[idx] += 2 - (date % 2);
				int difference = tallestHeight - trees[idx];
				if (difference == 0) continue;
				else if (difference == 1) {
					treeMap.computeIfAbsent(1, k -> new ArrayList<>()).add(idx);
				}
				else if (difference == 2) {
					treeMap.computeIfAbsent(2, k -> new ArrayList<>()).add(idx);
				}
				else {
					treeMap.computeIfAbsent(0, k -> new ArrayList<>()).add(idx);
				}
				if (treeMap.get(0).size() == 0) 
					treeMap.remove(0);
			}
		}
	}

}
