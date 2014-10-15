/************************  IntBST.java  **************************
 *                 binary search tree of integers
 */

import java.util.*;

public class IntBST {
    protected IntBSTNode root;
    protected void visit(IntBSTNode p) {
        System.out.print(p.key + " ");
    }
    public IntBST() {
        root = null;
    }

    public IntBST(IntBSTNode node) {
    	root = node;
    }

    public IntBSTNode search(int el) {
        return search(root,el);
    }
    protected IntBSTNode search(IntBSTNode p, int el) {
        while (p != null)
            if (el == p.key)
                 return p;
            else if (el < p.key)
                 p = p.left;
            else p = p.right;
        return null;
    }
    public void breadthFirst() {
        IntBSTNode p = root;
        Queue queue = new Queue();
        if (p != null) {
             queue.enqueue(p);
             while (!queue.isEmpty()) {
                 p = (IntBSTNode) queue.dequeue();
                 visit(p);
                 if (p.left != null)
                      queue.enqueue(p.left);
                 if (p.right != null)
                      queue.enqueue(p.right);
             }
        }
    }
    public void preorder() {
        preorder(root);
    }
    protected void preorder(IntBSTNode p) {
        if (p != null) {
             visit(p);
             preorder(p.left);
             preorder(p.right);
        }
    }
    public void inorder() {
        inorder(root);
    }
    protected void inorder(IntBSTNode p) {
        if (p != null) {
             inorder(p.left);
             visit(p);
             inorder(p.right);
        }
    }
    public void postorder() {
        postorder(root);
    }
    protected void postorder(IntBSTNode p) {
        if (p != null) {
             postorder(p.left);
             postorder(p.right);
             visit(p);
        }
    }

	public IntBSTNode prev(IntBSTNode data) {
		IntBSTNode prev = null;
		IntBSTNode p = root;
		while(p != null) {
			if(p.key == data.key)
				return prev;
			else if(p.key - data.key < 0) {
				prev = p;
				p = p.right;
			}
			else {
				prev = p;
				p = p.left;
			}
		}
		return null;
	}

	public void rightRotation(IntBSTNode p) {
		if(p == null && p.left == null)
			throw new NullPointerException();

		IntBSTNode prev = prev(p);
		IntBSTNode tmp;
		tmp = p.left;
		p.left = tmp.right;
		tmp.right = p;

		if(p == root)
			root = tmp;
		else if(prev.right == p)
			prev.right = tmp;
		else
			prev.left = tmp;
	}
	public void leftRotation(IntBSTNode p ) {
		if(p == null || p.right == null)
			throw new NullPointerException();
		IntBSTNode prev = prev(p);
		IntBSTNode tmp = p.right;
		p.right = tmp.left;
		tmp.left = p;
		if(p == root)
			root = tmp;
		else if(prev.right == p)
			prev.right = tmp;
		else
			prev.left = tmp;

	}

	public IntBSTNode rightMost(IntBSTNode p) {
		if(p == null)
			return null;
		while(p.right != null)
			p = p.right;
		return p;
	}
	public IntBSTNode leftMost(IntBSTNode p) {
		if(p == null)
			return null;
		while(p.left != null)
			p = p.left;
		return p;
	}

    public void insert(int el) {
    	root = insert(root, el);
    }
    public IntBSTNode insert(IntBSTNode p,int el) {
        if (p != null) {  // find a place for inserting new node;
            if (el < p.key)
                 p.left = insert(p.left, el);
            else if (el > p.key)
                 p.right = insert(p.right, el);
        }
         else
              p = new IntBSTNode(el);
        return p;
    }

    public void deleteByMerge(int data) {
    	IntBSTNode p = search(data);
    	IntBSTNode prev = prev(p);

    	if(p.left == null && p.right == null) {
			if(p == root)
				clear();
			else if(prev.right == p)
				prev.right = null;
			else
				prev.left = null;
    	}

    	else if(p.left != null && p.right != null) {
    		rightMost(p.left).right = p.right;

    		if(p == root)
    			root = p.left;
    		else if(prev.right == p)
    			prev.right = p.left;
    		else
    			prev.left = p.left;
    	}

    	else {
    		if(p == root) {
    			if(p.left != null)
    				root = p.left;
    			else
    				root = p.right;
    		}


    		else if(prev.right == p) {
    			if(p.left != null)
    				prev.right = p.left;
    			else
    				prev.right = p.right;
    		}
    		else {
    			if(p.left != null)
    				prev.left = p.left;
    			else
    				prev.left = p.right;
    		}
    	}

    }
    public void deleteByCopying(int data) {
        IntBSTNode p = search(data);
        IntBSTNode prev = prev(p);

        if(p.left != null) {
        	int tmp = rightMost(p.left).key;
        	rightMost(p.left).key = p.key;
        	p.key = tmp;
        	prev(rightMost(p.left)).right = null;
        }
        else if(p.right != null){
        	int tmp = leftMost(p.right).key;
        	leftMost(p.right).key = p.key;
        	p.key = tmp;
        	prev(leftMost(p.right)).left = null;
        }

        else {
        	prev = prev(p);
        	if(prev.right == p)
        		prev.right = null;
        	else
        		prev.left = null;
        }

    }

    /*public void balance() {
        backBone(root);
        int n = count(root);
        int m = (int) Math.pow(2, Math.log(count(root) + 1)/Math.log(2)) - 1;

        IntBSTNode p = root;
        IntBSTNode prev = null;

        for(int i = 0; i < n - m; i++) {
                if(p.left != null) {
                    prev = p.right;
                    leftRotation(p);
                    p = prev;
                }
        }
        prev = null;
        p = root;

        while(m > 1) {
            m = m / 2;
            for(int i = 0; i < n - m; i++) {
                if(p.left != null) {
                    prev = p.right;
                    leftRotation(p);
                    p = prev;
                }
            }
        }

    }

    private void backBone(IntBSTNode p) {
        IntBSTNode prev;
        while(p != null) {
            if(p.left != null) {
                prev = p.left;
                rightRotation(p);
                p = prev;
            }
            else
                p = p.right;
        }
    }*/

    public void balanceByArray() {
		int arr[] = toArray();
		Arrays.sort(arr);
		clear();
        balanceByArray(arr, 0, arr.length - 1);
    }
    public void balanceByArray(int[] arr, int i, int j) {
    	if(i <= j) {
    		int m = (i + j) / 2;
    		insert(arr[m]);
            balanceByArray(arr, i, m - 1);
            balanceByArray(arr, m + 1, j);
    	}
    }

	public int count(IntBSTNode p) {
		if(p == null)
			return 0;
		else
			return 1 + count(p.left) + count(p.right);
	}


    public int[] toArray() {
    	Queue q = new Queue();
    	int arr[] = new int[count(root)];
    	IntBSTNode p = root;
    	q.enqueue(p);
    	int i = 0;
    	while(!q.isEmpty()) {
    		p = (IntBSTNode) q.dequeue();
    		arr[i] = p.key;
    		if(p.left != null)
    			q.enqueue(p.left);
    		if(p.right != null)
    			q.enqueue(p.right);
    		i++;
    	}
    	return arr;
    }
    public void clear() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public boolean isInTree(int el) {
        return search(root,el) != null;
    }
}
