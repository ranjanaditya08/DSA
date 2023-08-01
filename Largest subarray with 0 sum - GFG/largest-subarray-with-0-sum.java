//{ Driver Code Starts
import java.util.*;

class MaxLenZeroSumSub
{

    // Returns length of the maximum length subarray with 0 sum

    // Drive method
    public static void main(String arg[])
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T > 0)
        {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            GfG g = new GfG();
            System.out.println(g.maxLen(arr, n));
            T--;
        }
    }
}
// } Driver Code Ends


class GfG
{
    int maxLen(int arr[], int n)
    {
        // Your code here
        HashMap<Integer,Integer> map = new HashMap<>();
        int max_length = 0;
        int sum = 0;
        map.put(0,-1);
        for(int i=0; i<n; i++){
            sum += arr[i];
            if(map.containsKey(sum)){
                int length = i - map.get(sum);
                max_length = Math.max(length,max_length);
            }else{
                 map.put(sum,i);
            }
           
        }
        return max_length;
    }
}