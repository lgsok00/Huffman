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