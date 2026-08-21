import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution {
	static List<Integer> result;
	static int maxDifference = 0;
	
	public static int[] solution(int n, int[] info) {
		int[] lionInfo = new int[info.length];
		Deque<Pos> q = new ArrayDeque<>();
		Pos candidate = null;
		q.offer(new Pos(new ArrayList<>(), n, 0));
		
		while (!q.isEmpty()) {
			Pos p = q.poll();
			List<Integer> list = p.list;
			int idx = list.size();
			int remain = p.remain;
			int sum = p.sum;
			
			// System.out.println(list.toString());

			// 과녁 다 쓰면 그만 (덜 함)
			if (remain == 0) {
				if (idx <= 10) {
					for (int i = idx; i <= 10; i++) {
						list.add(i, 0);
					}
				}
				// System.out.println("후보: " + list.toString());
				// 어피치와의 점수 차이를 계산
				int appeachScore = 0;
				for (int i = 0; i < 11; i++) {
					if (info[i] != 0 && info[i] >= p.list.get(i)) {
						appeachScore += 10 - i;
					}
				}
				// System.out.println("현재 점수: " + p.sum + ", 어피치 점수: " + appeachScore);
				if (p.sum - appeachScore > maxDifference) {
					maxDifference = p.sum - appeachScore;
					// System.out.println("후보에 넣음: " + p.list.toString());
					candidate = p;
				} else if (p.sum - appeachScore == maxDifference && candidate != null) {
					for (int i = 10; i >= 0; i--) {
						// 낮은 점수를 더 많이 맞춘걸 결과로 넣어주기
						if (p.list.get(i) > candidate.list.get(i)) {
							candidate = p;
							System.out.println("후보에 넣음: " + p.list.toString());
							break;
						} else if (p.list.get(i) < candidate.list.get(i)) { 
							break;
						} 
					}
				}
				continue;
			}
			
			if (idx > 10) continue;

			if (info[idx] + 1 <= remain) {
				List<Integer> list2 = new ArrayList<>(list);
				
				// 이 점수를 안 따는 법
				list.add(idx, 0);
				q.offer(new Pos(list, remain, sum));
				
				// 이 점수를 따는 법
				list2.add(idx, info[idx] + 1);
				q.offer(new Pos(list2, remain - info[idx] - 1, sum + 10 - idx));
			} else if (idx < 10 && info[idx] + 1 > remain) {
				// 현재 얻을 수 있는 점수가 없을 때
				list.add(0);
				q.offer(new Pos(list, remain, sum));
			} else if(idx == 10 && info[idx] + 1 > remain) {
				// 마지막까지 왔지만 딸 점수가 없을 때
				list.add(remain);
				q.offer(new Pos(list, 0, sum));
			}
		}
		
		// 일단 점수가 같을 때 말고 다르다고 생각하고 뽑아보기
		// System.out.println("결과");
		
		if(maxDifference == 0 || candidate == null) return new int[] {-1};
		else return candidate.list.stream().mapToInt(Integer::intValue).toArray();
	}

	// public static void main(String[] args) {
	// 	System.out.println("이후 무시");
	// 	System.out.println(Arrays.toString(solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })));
	// 	System.out.println(Arrays.toString(solution(1, new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 })));
	// 	System.out.println(Arrays.toString(solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 })));
	// 	System.out.println(Arrays.toString(solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 })));
	// }

	static class Pos {
		List<Integer> list = new ArrayList<>();
		int remain;
		int sum;

		Pos(List<Integer> l, int r, int s) {
			list = l;
			remain = r;
			sum = s;
		}
	}

}
