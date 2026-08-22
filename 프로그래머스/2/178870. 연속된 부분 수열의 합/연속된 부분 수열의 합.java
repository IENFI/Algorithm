class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int sum = 0;

        int start = 0;
        int end = sequence.length - 1;
        int minLength = sequence.length;

        for (int right = 0; right < sequence.length; right++) {
            sum += sequence[right];

            // 합이 k보다 크면 왼쪽을 이동
            while (sum > k) {
                sum -= sequence[left++];
            }

            // 합이 k인 구간 발견
            if (sum == k) {
                int length = right - left;

                if (length < minLength) {
                    minLength = length;
                    start = left;
                    end = right;
                }
            }
        }

        return new int[]{start, end};
    }
}