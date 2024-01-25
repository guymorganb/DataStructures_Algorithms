package Time_SpaceComplexity;
//You have been given a random integer array/list(ARR) and a number X. 
//Find and return the triplet(s) in the array/list which sum to X.
//Note :
//Given array/list can contain duplicate elements.
public class findTriplets {

	public static int findTriplets(int[] arr, int X) {
		 int n = arr.length;
	        int count = 0;

	        for (int i = 0; i < n - 2; i++) {
	            for (int j = i + 1; j < n - 1; j++) {
	                for (int k = j + 1; k < n; k++) {
	                    if (arr[i] + arr[j] + arr[k] == X) {
	                        count++;
	                    }
	                }
	            }
	        }

	        return count;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		int solution = findTriplets(arr, 7);
		System.out.println(solution);
	}

}
