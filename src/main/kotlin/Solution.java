import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;

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

            maxTime = max(node.time, maxTime);

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

    public static String timeConversion(String s) {
        String type = s.substring(8);

        int hour = Integer.parseInt(s.substring(0,2));
        int newHour = 0;

        if(type.equals("PM") && hour < 12){
            newHour = hour + 12;
        }else if(type.equals("AM") && hour == 12){
            newHour = 0;
        }else{
            return s.substring(0,8);
        }

        return String.format("%02d" + s.substring(2,8), newHour);
    }

    public int lonelyinteger(List<Integer> nums) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int a : nums){
            if (countMap.containsKey(a)){
                countMap.put(a, countMap.get(a) + 1);
            }else{
                countMap.put(a, 1);
            }
        }

        AtomicInteger result = new AtomicInteger();
        countMap.forEach((key, value) -> {
            if(value == 1)
                result.set(key);
        });

        return result.get();
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0, j = arr.size() - 1; i<arr.size(); i++, j--){
            sum1 += arr.get(i).get(i);
            sum2 += arr.get(i).get(j);
        }

        return Math.abs(sum1 - sum2);
    }

    public static List<Integer> countingSort(List<Integer> arr) {

        int mx = arr.stream()
                .max(Integer::compare)
                .orElse(0);

        int[] counting = new int[mx + 1];

        for (int a : arr){
            counting[a] = counting[a] + 1;
        }

        return Arrays.stream(counting)
                .boxed()
                .collect(Collectors.toList());

    }
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> colMaxMap = new HashMap<>();
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < m; i++){
            int colMax = Integer.MIN_VALUE;
            for(int j = 0; j < n; j++){
                if (matrix[j][i] > colMax){
                    colMax = matrix[j][i];
                }
            }
            colMaxMap.put(i, colMax);
        }

        for (int i = 0; i < n; i++){
            int rowMin = Integer.MAX_VALUE;
            int rowIdx = 0;
            for(int j = 0; j < m; j++){
                if(matrix[i][j] < rowMin){
                    rowMin = matrix[i][j];
                    rowIdx = j;
                }
            }

            if(colMaxMap.containsKey(rowIdx) && colMaxMap.get(rowIdx) == rowMin){
                result.add(rowMin);
            }
        }

        return result;
    }

    public int countNumbersWithUniqueDigits(int n) {
       int[] res = new int[9];
       res[0] = 1;
       res[1] = 10;

       for(int i = 2; i < 10; i++){
           int mult = 9;

           for(int j = 9; j > 9 - i + 1; j--){
               mult *= j;
           }

           res[i] = mult + res[i-1];
       }

       return res[n];
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right){
            int mid = (left + right)/2;

            if (nums[mid] == target)
                return mid;

            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public int flippingMatrix(List<List<Integer>> matrix) {
       int sum = Integer.MIN_VALUE;
       int n = matrix.size();

       for (int i = 0; i < n/2; i++){
           for (int j = 0; j< n/2; j++){
               sum += Math.max(
                       Math.max(matrix.get(i).get(j), matrix.get(i).get((2 * n - 1) - j)),
                       Math.max(matrix.get((2 * n - 1) - i).get(j), matrix.get((2 * n - 1) - i).get((2 * n - 1) - j))
               );
           }
       }

       return sum;
    }

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int count = 1;
        int idx = 0;

        for(int i = 1; i < intervals.length; i++){
            if(intervals[idx][1] < intervals[i][1]){
                count++;
                idx = i;
            }
        }
        return count;
    }

    public int diagonalPrime(int[][] nums) {
        int maxPrime = 0;

        for(int i = 0; i<nums.length; i++){
            if(isPrime(nums[i][i])){
                maxPrime = Math.max(maxPrime, nums[i][i]);
            }

            if(isPrime(nums[i][nums.length - 1 - i])){
                maxPrime = Math.max(maxPrime, nums[i][nums.length - 1 - i]);
            }
        }

        return maxPrime;
    }

    private boolean isPrime(int num){
        if(num <= 1)
            return false;

        for(int i = 2; i <= sqrt(num); i++){
            if(num * i == 0){
                return false;
            }
        }

        return true;
    }

    public static String caesarCipher(String s, int k) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i<s.length(); i++){
            int value = 0;

            if(s.charAt(i) >= 'a' && s.charAt(i)<= 'z'){
                value = s.charAt(i) - 97;
                result.insert(i,(char) ((value + k ) % 26 + 97));
            }else if(s.charAt(i) >= 'A' && s.charAt(i)<= 'Z'){
                value = s.charAt(i) - 65;
                result.insert(i,(char) ((value + k ) % 26 + 65));
            }else{
                result.insert(i,s.charAt(i));
            }

        }

        return result.toString();
    }

    public static String gridChallenge(List<String> grid) {
        List<String> sortedGrid = new ArrayList<>();

        for (String row: grid){
            char[] rowCharArr = row.toCharArray();
            Arrays.sort(rowCharArr);

            sortedGrid.add(new String(rowCharArr));
        }

        for (int i = 0; i < sortedGrid.get(0).length(); i++){
            for (int j = 0; j< sortedGrid.size() - 1; j++){
                if(sortedGrid.get(j).charAt(i) > sortedGrid.get(j + 1).charAt(i)){
                    return "NO";
                }
            }
        }

        return "YES";
    }

    public static void minimumBribes(List<Integer> q) {
        int totalBribes = 0;

        for(int i = 0; i < q.size() ; i++){
            int distance = q.get(i) - i - 1;
            int bribe = 0;

            if(distance > 0){
                int j = i;
                while(j < q.size() - 1 && q.get(j) > q.get(j + 1)){
                    Collections.swap(q, j,j + 1);
                    bribe++;
                    j++;
                }
            }else if(distance < 0){
                int j = i;

                while (j > 0 && q.get(j) < q.get(j - 1)){
                    Collections.swap(q, j, j -1);
                    bribe++;
                    j++;
                }
            }

            if(bribe > 2){
                System.out.println("Too chaotic");
                return;
            }
            totalBribes += bribe;
        }

        System.out.println(totalBribes);

    }

    public static int truckTour(List<List<Integer>> petrolpumps) {

        for(int i = 0; i< petrolpumps.size(); i++){
            int fuel = petrolpumps.get(i).get(0) - petrolpumps.get(i).get(1);

            if(fuel < 0){
                continue;
            }
            int start = i;

            while(fuel >= 0){
                start = (start + 1) % petrolpumps.size();
                fuel += (petrolpumps.get(start).get(0) - petrolpumps.get(start).get(1));
                if(start == i){
                    return i;
                }
            }
        }

        return -1;
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        int size = 0;

        for(int i = 0; i < nums.size(); i++){
            for(int j = 0; j < nums.get(i).size(); j++){
                List<Integer> list = map.getOrDefault(i + j, new ArrayList<Integer>());
                list.add(0, nums.get(i).get(j));
                map.put(i + j, list);
                size++;
            }
        }

        int[] arr = new int[size];

        int idx = 0;
        for (int i = 0; i < map.size(); i++){
            for (int n : map.get(i)){
                arr[idx++] = n;
            }
        }

        return arr;
    }

    public long makeIntegerBeautiful(long n, int target) {

        if(sumOfDigit(n) <= target)
            return 0;
        int x = 1;
        while(sumOfDigit(n + x) > target){
            x++;
            System.out.println(x);
        }

        return x;
    }

    private int sumOfDigit(long n){
        int sum = 0;
        while(n != 0){
            sum += n%10;
            n /= 10;
        }

        return sum;
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] result = new ListNode[k];

        int length = 0;

        ListNode current = head;

        while (current != null){
            length++;
            current = current.getNext();
        }

        int partSize = length / k;
        int reminder = length % k;
        current = head;

        for(int i = 0; i < k; i++){
            int partLength = partSize + (i < reminder ? 1 : 0);
            result[i] = current;

            for (int j = 0; j < partLength - 1; j++) {
                if (current != null) {
                    current = current.getNext();
                }
            }

            if (current != null) {
                ListNode temp = current.getNext();
                current.setNext(null);
                current = temp;
            }
        }

        return result;
    }

    /**
     * 2125. Number of Laser Beams in a Bank
     * @param bank
     * @return
     */
    public int numberOfBeams(String[] bank) {
        List<Integer> rows = new ArrayList<>();

        int beam = 0;
        for(String row: bank){
            int count = 0;
            for(int i = 0; i < row.length(); i++){
                if(row.charAt(i) == '1'){
                    count++;
                }
            }
            if(count != 0){
                rows.add(count);
            }

            count = 0;
        }

        for(int i = 1; i < rows.size(); i++){
            int a = rows.get(i) * rows.get(i - 1);

            beam += a;
        }
        return beam;
    }

    /**
     * 1769. Minimum Number of Operations to Move All Balls to Each Box
     * @param boxes
     * @return
     */
    public int[] minOperations(String boxes) {
        List<Integer> balls = new ArrayList<>();

        int[] res = new int[boxes.length()];

        for(int i = 0; i < boxes.length(); i++){
            if(boxes.charAt(i) == '1'){
                balls.add(i);
            }
        }

        for(int i = 0; i < boxes.length(); i++){
            int move = 0;

            for(int j = 0; j < balls.size(); j++){
                move += Math.abs(i - balls.get(j));
            }

            res[i] = move;
        }

        return res;

    }

    /**
     * 496. Next Greater Element I
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[nums1.length];
        Arrays.fill(ans, -1);

        for(int i = 0; i < nums2.length; i++){
            map.put(nums2[i], i);
        }

        for(int i = 0; i < nums1.length; i++){
            int idx = map.get(nums1[i]);

            while (idx < nums2.length){
                if(nums1[i] < nums2[idx]){
                    ans[i] = nums2[idx];
                    break;
                }
                idx++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.makeIntegerBeautiful(467, 6));
    }
}
