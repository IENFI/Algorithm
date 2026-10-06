import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]> [] graph = new ArrayList[n + 1];
        int[] intensity = new int[n + 1];
        Set<Integer> summitSet = new HashSet<>();
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            intensity[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < summits.length; i++) {
            summitSet.add(summits[i]);
        }
        
        for (int[] path: paths) {
            int i = path[0];
            int j = path[1];
            int cost = path[2];
            
            graph[i].add(new int[] {j, cost});
            graph[j].add(new int[] {i, cost});
        }
        
        int[] answer = {-1, Integer.MAX_VALUE};
        
        Queue<int[]> pq = new PriorityQueue<>(
            Comparator.comparingInt(a -> a[1])
        );
        
        for (int gate: gates) {
            // 큐에 동시에 추가하기
            pq.offer(new int[] {gate, 0});
        }
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int start = cur[0];
            int cost = cur[1];
            
            if (cost > intensity[start]) continue;
            intensity[start] = cost;
            
            for (int[] next: graph[start]) {
                int nextNode = next[0];
                int edgeCost = next[1];
                
                int curIntensity = Math.max(cost, edgeCost);
                if (curIntensity >= intensity[nextNode]) continue;
                
                intensity[nextNode] = curIntensity;
                
                if (summitSet.contains(nextNode)) {
                    if (answer[0] == -1 || curIntensity < answer[1]
                       || (curIntensity == answer[1] && nextNode < answer[0])) {
                        answer[0] = nextNode;
                        answer[1] = curIntensity;
                    }
                    continue;
                }
                pq.offer(new int[] {nextNode, curIntensity});
            }
        }
        
        return answer;
    }
}