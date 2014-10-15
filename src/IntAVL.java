public class IntAVL extends IntBST {
	protected int height;
	
	public IntAVL() {
		super();
		height = -1;
	}
	
	public IntAVL(IntBSTNode root) {
		super(root);
		balance();
	}
	
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(IntBSTNode node) {
		if(node == null)
			return -1;
		else
			return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}
	
	private IntAVL getLeftAVL() {
		IntAVL leftsubtree = new IntAVL(root.left);
        return (IntAVL) leftsubtree;
    }

    private IntAVL getRightAVL() {
       	IntAVL rightsubtree = new IntAVL(root.right);
        return (IntAVL) rightsubtree;
    }
    
	protected int getBalanceFactor() {
        if(isEmpty())
            return 0;
        else
            return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }
    
    public void insert(int el)
    {
    	super.insert(el);
    	this.balance();
    }
    
    public void delete(int e1) {
    	//QUESTION 2
    }

    protected void balance() {
        adjustHeight();
        
        int balanceFactor = getBalanceFactor();
        
        if(balanceFactor == -2)
		{
			System.out.println("Balancing node with key: "+root);
            if(getLeftAVL().getBalanceFactor() < 0)
				rotateRight();
            else
                rotateLeftRight();
        }
		else if(balanceFactor == 2)
        {
        	System.out.println("Balancing node with key: "+root);
            if(getRightAVL().getBalanceFactor() > 0)
                rotateLeft();
            else
                rotateRightLeft();
        }
    }
    
    protected void adjustHeight()
    {
        if(isEmpty())
            height = -1;
        else
            height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());   
    }
    
    protected void rotateRight() {
        //QUESTION 1
	}
    
    protected void rotateLeft() {
           	System.out.println("LEFT ROTATION");
            IntBSTNode tempNode = root.left;
            root.left = root.right;
            root.right = root.right.right;
            root.left.right = root.left.left;
            root.left.left = tempNode;
            
            int val = root.key;
            root.key = root.left.key;
            root.left.key = val;
            
            getLeftAVL().adjustHeight();
            adjustHeight();
	}
	
	protected void rotateLeftRight()
    {
        getLeftAVL().rotateLeft();
        rotateRight();
    }

    protected void rotateRightLeft()
    {
        getRightAVL().rotateRight();
        rotateLeft();
    }
}