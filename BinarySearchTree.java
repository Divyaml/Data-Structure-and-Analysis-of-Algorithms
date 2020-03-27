
public class BinarySearchTree {
	class Node{
		int key;
		Node left,right;
		public Node(int item)
		{
			key=item;
			left=right=null;
		}
	}
	
	Node root;
	
	BinarySearchTree()
	{
		root=null;
	}
	
    void insertVal(Node tmp,int key) 
	{
		if (tmp==null) 
		{
			root= new Node(key);
			return;
		}
		if(tmp.key<key)
		{
			if(tmp.right==null) 
			{
				tmp.right=new Node(key);
				return;
			}
			else 
			{
				 insertVal(tmp.right,key);
				 return;
				 
			}
			
		}
		
		if(tmp.key>key) 
		{
			if(tmp.left==null) 
			{
				tmp.left= new Node(key);
				return;
			}
			else
			{
				insertVal(tmp.left,key);
				return;
			}
			
		}
	}
	
    //Function to print the binary search tree in inorder.
    public void inOrderTrav(Node tmp) 
	{
		if(tmp==null) 
		{
			return;
		}
		else 
		{
			inOrderTrav(tmp.left);
			System.out.print(tmp.key+" ");
			inOrderTrav(tmp.right);
		}
	}
    
    //Function to delete a value.
    public void deleteVal(Node tmp, int val)
    {
    	Node predecessor;
    	Node prevnode=tmp;
    	
    	if (tmp==null) 
    	{
    		return;
    	}
    	if (tmp.key==val)
    	{
    		predecessor=getPredecessor(tmp);
    		predecessor.right=tmp.right;
    		predecessor.left=tmp.left;
    		root=predecessor;
    		return;
    	}
    	
    	else if(tmp.key>val)
    	{
    		prevnode=tmp;
    		tmp=tmp.left;
    		while(tmp!=null) 
    		{
    			if(tmp.key==val)
    			{
    				break;    				
    			}
    			else if(tmp.key>val) 
    				{
    					prevnode=tmp;
    					tmp=tmp.left;
    				}
    			else if (tmp.key< val)
    			{
    					prevnode=tmp;
    					tmp=tmp.right;
    			}
    			
    		}
    	} 
        
    	else if(tmp.key<val)
    	{
    		prevnode=tmp;
    		tmp=tmp.right;
    		while(tmp!=null) 
    		{
    			if(tmp.key==val)
    			{
    				break;    				
    			}
    			else if(tmp.key>val) 
    				{
    					prevnode=tmp;
    					tmp=tmp.left;
    				}
    			else if (tmp.key< val)
    			{
    					prevnode=tmp;
    					tmp=tmp.right;
    			}
    			
    		}
    	}
    	
    	if(tmp.right==null && tmp.left==null) 
    	{
    		if (prevnode.left==tmp)
    		{
    			prevnode.left=null;
    		}
    		else
    		{
    			prevnode.right=null;
    		}
    	}
    	else if(tmp.left==null && tmp.right!=null)
    	{
    		prevnode.right=tmp.right;
    	}
    	else 
    	{
    		predecessor=getPredecessor(tmp);
    		predecessor.right=tmp.right;
    		if(tmp.left!=predecessor) 
    		{
    			predecessor.left=tmp.left;
    		}
        	
    		
        	if(prevnode.right==tmp) 
        	{
        		prevnode.right=predecessor;
        	}
        	else if (prevnode.left==tmp)
        	{
        		prevnode.left=predecessor;
        	}
    	}
    	return;
    }
	
    //function to get the predecessor of a given value.
    Node getPredecessor(Node tmp) 
    {
    	Node prevnode=tmp;
    	tmp=tmp.left;
    	while(tmp.right!=null)
    	{
    		prevnode=tmp;
    		tmp=tmp.right;
    	}
    	if (prevnode.left!=tmp)
    	{
    		prevnode.right=tmp.left;
    	}
    	return tmp;
    }
    
	public static void main(String[] args)
	{
		BinarySearchTree tree= new BinarySearchTree();
		tree.insertVal(tree.root, 100);
		tree.insertVal(tree.root, 50);
		tree.insertVal(tree.root, 200);
		tree.insertVal(tree.root, 150);
		tree.insertVal(tree.root, 300);
		tree.insertVal(tree.root, 25);
		tree.insertVal(tree.root, 75);
		tree.insertVal(tree.root, 12);
		tree.insertVal(tree.root, 37);
		tree.insertVal(tree.root, 125);
		tree.insertVal(tree.root, 175);
		tree.insertVal(tree.root, 250);
		tree.insertVal(tree.root, 320);
		tree.insertVal(tree.root, 67);
		tree.insertVal(tree.root, 87);
		tree.insertVal(tree.root, 94);
		tree.insertVal(tree.root, 89);
		tree.insertVal(tree.root, 92);
		tree.insertVal(tree.root, 88);
		
		System.out.print("Binary Search Tree Operation Before delete.\n");
		tree.inOrderTrav(tree.root);
		tree.deleteVal(tree.root,100);
		System.out.print("\nBinary Search Tree after deleting the given value\n");
		tree.inOrderTrav(tree.root);
	}

}
