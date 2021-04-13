import java.util.ArrayList;
import java.util.Collections;

class Heap
{
    private ArrayList<Node> tree = new ArrayList<Node>(54);

    public Heap()
    {
        tree.add(null);
    }
    public void insert(Node n)
    {
        tree.add(n);

        int child = tree.size()-1;
        int parent = child /2;

        while(parent >= 1 && tree.get(child).freq < tree.get(parent).freq)
        {
            Collections.swap(tree, child, parent);

            child = parent;
            parent = child /2;
        }
    }

    public boolean isEmpty()
    {
        return (tree.size() <= 1);
    }

    public Node MinNode()
    {
        if(isEmpty()) return null;

        Node min = tree.get(1);

        int top = tree.size()-1;

        tree.set(1, tree.get(top));
        tree.remove(top);

        int parent = 1;
        int lPos = parent *2;
        int rPos = parent *2 + 1;

        while(lPos <= tree.size()-1)
        {
            int target;
            if(rPos > tree.size()-1)
            {
                if(tree.get(lPos).freq >= tree.get(parent).freq)
                    break;
                target = lPos;
            }
            else
            {
                if(tree.get(lPos).freq >= tree.get(parent).freq && tree.get(rPos).freq >= tree.get(parent).freq)
                    break;

                target = (tree.get(lPos).freq < tree.get(rPos).freq) ? lPos : rPos;
            }
            Collections.swap(tree, target, parent);


            parent = target;
            lPos = parent *2;
            rPos = parent *2 + 1;
        }
        return min;
    }
    public void printTree()
    {
        for(Node n : tree)
            if(n != null)
                System.out.print(n.freq+" ");
        System.out.println("");
    }
}
