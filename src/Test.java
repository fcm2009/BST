

public class Test {

    public static void main(String[] args) {
        Avl t = new Avl();
        t.insert(10);
        t.insert(5);
        t.insert(15);
        t.insert(4);
        t.insert(6);
        t.insert(20);
        t.insert(1);
        t.insert(16);
        t.breadthFirst();

    }
}
