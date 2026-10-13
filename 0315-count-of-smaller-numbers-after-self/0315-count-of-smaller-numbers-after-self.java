import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        
        
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        
        mergeSort(arr, 0, n - 1);
        
        List<Integer> result = new ArrayList<>();
        for (int c : count) {
            result.add(c);
        }
        return result;
    }

    private void mergeSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[][] arr, int left, int mid, int right) {
        int[][] temp = new int[right - left + 1][2];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int rightCount = 0; 
        
        while (i <= mid && j <= right) {
            if (arr[j][0] < arr[i][0]) {
               
                rightCount++;
                temp[k++] = arr[j++];
            } else {
               
                count[arr[i][1]] += rightCount;
                temp[k++] = arr[i++];
            }
        }
        
       
        while (i <= mid) {
            count[arr[i][1]] += rightCount;
            temp[k++] = arr[i++];
        }
        
       
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
      
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }
}