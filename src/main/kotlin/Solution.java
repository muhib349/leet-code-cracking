import java.util.*;
import java.util.stream.IntStream;

class Pair {
    int row;
    int col;
    int time;

    Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        var count = 1;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        var end = intervals[0][1];

        for(int i = 1; i<intervals.length; i++){
            if(intervals[i][0] >= end){
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
    public String findNonAdjacentCombination(String str) {
        List<Character> letters = new ArrayList<>();
        List<Character> numbers = new ArrayList<>();

        // Separate letters and numbers
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                letters.add(c);
            } else if (Character.isDigit(c)) {
                numbers.add(c);
            }
        }

        // Sort letters and numbers in ascending order
        Collections.sort(letters);
        Collections.sort(numbers);

        // Interleave the sorted letters and numbers to form the output string
        StringBuilder result = new StringBuilder();
        boolean lettersFirst = letters.size() >= numbers.size();

        while (!letters.isEmpty() || !numbers.isEmpty()) {
            if (lettersFirst) {
                if (!letters.isEmpty()) {
                    result.append(letters.remove(0));
                }
                if (!numbers.isEmpty()) {
                    result.append(numbers.remove(0));
                }
            } else {
                if (!numbers.isEmpty()) {
                    result.append(numbers.remove(0));
                }
                if (!letters.isEmpty()) {
                    result.append(letters.remove(0));
                }
            }
        }

        // Check if the generated string satisfies the condition
        if (isAdjacent(result.toString())) {
            return "";
        }

        return result.toString();
    }
    private boolean isAdjacent(String str) {
        for (int i = 1; i < str.length(); i++) {
            char prev = str.charAt(i - 1);
            char current = str.charAt(i);
            if (Character.isLetter(prev) && Character.isLetter(current)) {
                return true;
            }
        }
        return false;
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        var queue = new ArrayDeque<Integer>();
        queue.push(0);

        while (!queue.isEmpty()){
            int key = queue.pop();
            visited[key] = true;

            List<Integer> keysInARoom = rooms.get(key);
            keysInARoom.forEach(a -> {
                if(!visited[a]){
                    queue.push(a);
                }
            });
        }
        return IntStream.range(0, visited.length)
                .allMatch(i -> visited[i]);
    }

    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        var queue = new ArrayDeque<Integer>();
        var provience = 0;

        for (int i = 0; i < isConnected.length; i++){
            queue.push(i);
            if(!visited[i]){
                while (!queue.isEmpty()){
                    int city = queue.pop();
                    visited[city] = true;
                    for (int j = 0; j<isConnected[city].length; j++){
                        if(isConnected[city][j] == 1 && !visited[j]){
                            queue.push(j);
                        }
                    }
                }
                provience++;
            }
        }

        return provience;
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        var step = 0;
        int m = maze.length;
        int n = maze[0].length;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);

        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] position = queue.poll();
                maze[position[0]][position[1]] = '+';

                for (int[] dir: directions){
                    int newRow = position[0] + dir[0];
                    int newCol = position[1] + dir[1];

                    if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || maze[newRow][newCol] == '+')
                        continue;
                    if(newRow == 0 || newRow == m - 1 || newCol == 0 || newCol == n - 1)
                        return step+1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
            step++;
        }

        return -1;
    }

    public int orangesRotting(int[][] grid) {
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        Queue<Pair> queue = new LinkedList<>();

        int m = grid.length;
        int n = grid[0].length;

        int[][] visited = new int[m][n];
        int countFresh = 0;

        for (int i = 0; i<m; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    queue.offer(new Pair(i,j,0));
                    visited[i][j] = 2;
                }else{
                    visited[i][j] = 0;
                }

                if(grid[i][j] == 1){
                    countFresh++;
                }
            }
        }

        int maxTime = 0;
        int countRotten = 0;

        while (!queue.isEmpty()){
            var node = queue.poll();

            maxTime = Math.max(node.time, maxTime);

            for (int[] dir : directions){
                int x = node.row + dir[0];
                int y = node.col + dir[1];
                int time = node.time;

                if(x >= 0 && x < m && y >= 0 && y < n && visited[x][y] == 0 && grid[x][y] == 1){
                    queue.offer(new Pair(x,y,time + 1));
                    visited[x][y] = 2;
                    countRotten++;
                }
            }
        }

        if(countRotten != countFresh)
            return -1;

        return maxTime;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.reverseOrder());
        int result = 0;

        for (int a : nums){
            queue.add(a);
        }

        while (k-- > 0 && !queue.isEmpty()){
            result = queue.poll();
        }

        return result;
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new LinkedList<>();
        if(k == 0){
            ans.add(new LinkedList<>());
            return ans;
        }
//        backtracking(1, new LinkedList<Integer>(),n,k,ans);

        return ans;
    }

    public void backtrack(int num, String current, List<String> result, String[] keypadMap){
        int digit = num%10;
        String letters = keypadMap[digit];
        if(num == 0){
            result.add(current);
            return;
        }

        for (int i = 0; i < letters.length(); i++){
            backtrack(num/10,letters.charAt(i) + current, result, keypadMap);
        }

    }
    public List<String> letterCombinations(String digits) {
        String[] keypadMap = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result = new ArrayList<>();

        if(digits.isEmpty()){
            return result;
        }

        int num = Integer.parseInt(digits);
        backtrack(num, "", result, keypadMap);
        return result;
    }

    public void backtrackCombinationSum3(int start, int k, int n, int sum, LinkedList<Integer> current, List<List<Integer>> result){
        if(current.size() == k && sum == n){
            result.add(new LinkedList<>(current));
            return;
        }

        if(sum > n){
            return;
        }

        for (int i = start; i <= 9; i++){
            if(current.contains(i)){
                continue;
            }

            if(i > n)
                break;

            current.add(i);
            backtrackCombinationSum3(i + 1, k,n,sum + i, current, result);
            current.removeLast();
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        backtrackCombinationSum3(1, k,n,0,new LinkedList<>(), result);

        return result;
    }

    public void backTrackingCombinationSum(int i, int currentSum, int[] candidates, int target, LinkedList<Integer> current, List<List<Integer>> result) {

        if(currentSum > target){
            return;
        }

        if(i == candidates.length){
            if(currentSum == target)
                result.add(new LinkedList<>(current));
            return;
        }
        currentSum += candidates[i];
        current.add(candidates[i]);
        backTrackingCombinationSum(i, currentSum, candidates, target, current, result);

        current.removeLast();
        currentSum -= candidates[i];

        backTrackingCombinationSum(i + 1, currentSum, candidates, target, current, result);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        backTrackingCombinationSum(0, 0, candidates, target, new LinkedList<>(), result);

        return result;
    }

    public void backTrackingCombinationSum2(int start, int[] candidates, int target, LinkedList<Integer> current, List<List<Integer>> result) {

        if (target == 0){
            result.add(new LinkedList<>(current));
            return;
        }
        if(target < 0)
            return;

        for (int i = start; i < candidates.length; i++){
            if(i>start && candidates[i]==candidates[i-1]) continue;

            current.add(candidates[i]);
            backTrackingCombinationSum2(i + 1, candidates, target - candidates[i], current, result);
            current.removeLast();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrackingCombinationSum2(0, candidates, target, new LinkedList<>(), result);

        return result;
    }

    public static void main(String[] args) {
        var solution = new Solution();

        System.out.println(solution.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
    }
}
