function solution(land) {
    var answer = 0;
    const len = land.length;
    
    for (let r = 1; r < len; r++) {
        // 현재 행의 열
        for (let c = 0; c < 4; c++) {
            let max = 0;
            // 이전 행의 열
            for (let k = 0; k < 4; k++) {
                if (c == k) continue;
                max = Math.max(land[r - 1][k], max);
            }
            // 현재 행에 이전 행의 최대값을 찾아서 업데이트 (더하기)
            land[r][c] += max;
        }
    }
    
    for (let c = 0; c < 4; c++) {
        answer = Math.max(land[len - 1][c], answer);
    }

    return answer;
}