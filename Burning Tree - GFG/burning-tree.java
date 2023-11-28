//{ Driver Code Starts
//Initial Template for Java


import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

class Node {
	int data;
	Node left;
	Node right;

	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

class GfG {

	static Node buildTree(String str) {

		if (str.length() == 0 || str.charAt(0) == 'N') {
			return null;
		}

		String ip[] = str.split(" ");
		// Create the root of the tree
		Node root = new Node(Integer.parseInt(ip[0]));
		// Push the root to the queue

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);
		// Starting from the second element

		int i = 1;
		while (queue.size() > 0 && i < ip.length) {

			// Get and remove the front of the queue
			Node currNode = queue.peek();
			queue.remove();

			// Get the current node's value from the string
			String currVal = ip[i];

			// If the left child is not null
			if (!currVal.equals("N")) {

				// Create the left child for the current node
				currNode.left = new Node(Integer.parseInt(currVal));
				// Push it to the queue
				queue.add(currNode.left);
			}

			// For the right child
			i++;
			if (i >= ip.length)
				break;

			currVal = ip[i];

			// If the right child is not null
			if (!currVal.equals("N")) {

				// Create the right child for the current node
				currNode.right = new Node(Integer.parseInt(currVal));

				// Push it to the queue
				queue.add(currNode.right);
			}
			i++;
		}

		return root;
	}

	static void printInorder(Node root) {
		if (root == null)
			return;

		printInorder(root.left);
		System.out.print(root.data + " ");

		printInorder(root.right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		while (t > 0) {
			String s = br.readLine();
			int target = Integer.parseInt(br.readLine());
			Node root = buildTree(s);

			Solution g = new Solution();
			System.out.println(g.minTime(root, target));
			t--;

		}
	}
}



// } Driver Code Ends


//User function Template for Java

class Solution
{
    /*class Node {
    	int data;
    	Node left;
    	Node right;
    
    	Node(int data) {
    		this.data = data;
    		left = null;
    		right = null;
    	}
    }*/
    
    public static int minTime(Node root, int target) 
    {
        // Your code goes here
        Map<Node,Node> parent_map = new HashMap<>();
        mapParent(root,parent_map);
        Node node = findTarget(root,target);
        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        q.offer(node);
        visited.add(node);
        int burnTime = -1;
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i = 0; i < levelSize; i++){
                Node curr = q.poll();
                if(curr.left != null && (!visited.contains(curr.left))){
                  q.offer(curr.left);
                  visited.add(curr.left);
                } 
                if(curr.right != null && (!visited.contains(curr.right))){
                    q.offer(curr.right);
                    visited.add(curr.right);
                }
                Node parent = parent_map.get(curr);
                if(parent != null && (!visited.contains(parent))){
                    q.offer(parent);
                    visited.add(parent);
                }
            }
            burnTime++;
        }
        return burnTime;
    }
    public static Node findTarget(Node root,int target){
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            if(q.peek().data == target) break;
            Node curr = q.poll();
            if(curr.data == target) return curr;
            if(curr.left != null) q.offer(curr.left);
            if(curr.right != null) q.offer(curr.right);
        }
        return q.peek();
    }
    public static void mapParent(Node root,Map<Node,Node> map){
        if(root == null) return;
        
        if(root.left != null){
            map.put(root.left,root);
            mapParent(root.left,map);
        }
        if(root.right != null){
            map.put(root.right,root);
            mapParent(root.right,map);
        }
    }
}