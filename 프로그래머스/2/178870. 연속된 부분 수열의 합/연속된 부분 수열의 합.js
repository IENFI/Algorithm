function solution(sequence, k) {
    var answer = [];
    const n = sequence.length;
    
    let minLength = n;
    let start = 0;
    let end = n - 1;
    
    let sum = 0;
    let left = 0;
    for (let right = 0; right < n; right++) {
        sum += sequence[right];
        
        // 같으면 빼면 안 됨
        while (sum > k) {
            sum -= sequence[left++];
        }
        
        if (sum == k) {
            const length = right - left + 1;
            if (length < minLength) {
                minLength = length;
                start = left;
                end = right;
            }
        }
    }
    
    answer = [start, end];
    
    return answer;
}