import java.io.*;
import java.util.*;


class Node
{
    public int freq;
    public char text;
    public Node lNode;
    public Node rNode;

    public Node(int freq, char text)
    {
        this.freq = freq;
        this.text = text;
        lNode = rNode = null;
    }
}

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
public class HuffmanCoding
{
    public static HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
    public static Node huffmanCoding=null;

    public static void countText(String src)
    {
        try {
            BufferedReader in = new BufferedReader(new FileReader(src));
            String s;

            while ((s = in.readLine()) != null)
            {
                for(int i=0;i<s.length();i++)
                {
                    char c = s.charAt(i);
                    if(freq.containsKey(c)) freq.put(c, freq.get(c)+1);
                    else freq.put(c, 1);
                }
            }
            in.close();
        }
        catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
    public static void makeHuffmanTree()
    {
        Heap mh = new Heap();

        if(freq.isEmpty())
            return;

        for(char key : freq.keySet())
            mh.insert(new Node(freq.get(key), key));

        while(true)
        {
            Node lChild = mh.MinNode();
            Node rChild = mh.MinNode();

            huffmanCoding = new Node(lChild.freq+ rChild.freq, '.');
            huffmanCoding.lNode = lChild;
            huffmanCoding.rNode = rChild;

            if(mh.isEmpty())
                return;

            mh.insert(huffmanCoding);
        }
    }

    public static void printTextCode(Node htRoot, int []trace, int top)
    {
        if(htRoot.lNode != null)
        {
            trace[top] = 0;
            printTextCode(htRoot.lNode, trace, top+1);
        }
        if(htRoot.rNode != null)
        {
            trace[top] = 1;
            printTextCode(htRoot.rNode, trace, top+1);
        }

        if(htRoot.lNode == null && htRoot.rNode == null)
        {
            System.out.print(htRoot.text + "(빈도 수: " + htRoot.freq +"): ");
            printArr(trace, top);
        }
    }
    public static void printArr(int[] arr, int top)
    {
        for(int i=0;i<top;i++)
            System.out.print(arr[i]);
        System.out.println("");
    }

    public static void printFreq()
    {
        for(char key : freq.keySet())
            System.out.println("key: " + key + ", freq: " + freq.get(key));
    }
    public static void main(String[] args)
    {
        countText("code/alphabets.txt");
        makeHuffmanTree();

        int []arr = new int[freq.size()-1];
        System.out.println("각 문자의 빈도수 ------ ");
        printFreq();

        System.out.println("각 문자에 할당된 코드 -------- ");
        printTextCode(huffmanCoding, arr, 0);
    }
}