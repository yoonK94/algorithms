package quickSelect;

//Finding the kth biggest element from an unsorted list.
//Average complexity is O(n)

public class quickSelect {
	
	public static int select(int[] arr, int k) {
		return findingPivot(arr, 0, arr.length - 1, k - 1);
	}
	
	private static int findingPivot(int[] arr, int left, int right, int k) {
		if(left == right) {
			return arr[left];
		}
		
		for(;;) {
			int pivot = left;
			int pivotVal = arr[pivot];
			
			swap(arr, pivot, right);
			int current = left;
			
			for(int i = left; i < right; i++) {
				//greater than and not equal to, in order to restore pivot at the end
				
				if(arr[i] > pivotVal) {
					//no need to sort
					swap(arr, current++, i);
				}
			}
			swap(arr, current, right);
			
			if (current == k) {
				return arr[current];
			}
			//exclude current index and..
			
			//the kth biggest element is in the right bound 
			else if(current < k) {
				left = current + 1;
			}
			
			//the kth biggest element is in the left bound
			else if(current > k) {
				right = current - 1;
			}
				
		}
	}
	
	private static void swap(int[] arr, int ind1, int ind2) {
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
		return;
	}
	
	public static void main(String[] args) {
		int[] a = {2,3,4,5,7,8,9,10,11,12};
		System.out.println(select(a, 5));
	}
}
