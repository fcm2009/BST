/**
 * Created by Mohammed Alshehry on 4/16/14.
 */
public class Avl extends IntBST {


    public Avl() {
        super();
    }

    public int height(IntBSTNode p) {
        if(p == null)
            return 0;
        else
            return Math.max(1 + height(p.left), 1 + height(p.right));
    }

    public int getBalanceFactor() {
        return getBalanceFactor(root);
    }

    public int getBalanceFactor(IntBSTNode p) {
        return height(p.right) - height(p.left);
    }

    public void insert(int data) {
        super.insert(data);
        balance();
    }

    public void deleteByMerege(int data) {
        super.deleteByMerge(data);
        balance();
    }

    public void deleteByCopying(int data) {
        super.deleteByCopying(data);
        balance();
    }

    public void balance() {
        int bf = getBalanceFactor();

        if(bf == 2) {
            if(getBalanceFactor(root.right) > 0)
                leftRotation(root);
            else
                doubleRightLeft(root);
        }

        else if(bf == -2) {
            if(getBalanceFactor(root.left) < 0)
                rightRotation(root);
            else
                doubleLeftRight(root);
        }
    }

    public void doubleRightLeft(IntBSTNode p) {
        super.rightRotation(p.right);
        super.leftRotation(p);
    }

    public void doubleLeftRight(IntBSTNode p) {
        super.leftRotation(p.left);
        super.rightRotation(p);

    }
}
