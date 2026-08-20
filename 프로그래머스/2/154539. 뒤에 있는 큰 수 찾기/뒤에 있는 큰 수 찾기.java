import java.util.*;

class Solution {
    static PriorityQueue<Integer> untrackedQ;
    public static int[] solution(int[] numbers) {
        untrackedQ = new PriorityQueue<>((x,y) -> numbers[x] - numbers[y]);
        int[] answer = new int[numbers.length];
        
        int[] maxList = new int[numbers.length];
        int maxNum = 0;
        
        for (int i = numbers.length - 1; i >= 0; i--) {
            maxNum = Math.max(maxNum, numbers[i]);
            maxList[i] = maxNum;
        }
        
        for (int i = 0; i < numbers.length - 1; i++) {
            // 아예 없는 애들은 큐에 넣지도 않음
            
            if (numbers[i] < numbers[i+1]) {
                answer[i] = numbers[i+1];
                
                int size = untrackedQ.size();
                if (size != 0 && numbers[untrackedQ.peek()] >= numbers[i+1])
                	continue;
                while (size > 0) {
                    int idx = untrackedQ.poll();
                    if (numbers[idx] < numbers[i+1]) {
                        answer[idx] = numbers[i+1];
                    } else {
                        untrackedQ.offer(idx);
                    }
                    size--;
                }
                
            } else {
                if (numbers[i] >= maxList[i+1]) {
                    answer[i] = -1;
                    continue;
                }
                
                untrackedQ.offer(i);
            }
            
        }
        answer[numbers.length - 1] = -1;
        
        return answer;
    }
}