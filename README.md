# <허프만 코드(Huffman Coding)>
  : 트리를 이용해 문자열을 2진수로 압축하는 알고리즘
  
## 방법


1. 알파벳과 알파벳의 빈도수를 저장할 Node 클래스 생성

2. 알파벳의 빈도수를 count 한 후, 최소힙에 빈도수와 해당 알파벳을 Node로 만들어 저장

3. 최소 힙에서 Node 두 개를 꺼낸다.

4. 부모 Node(3에서 꺼낸 두 Node의 부모 Node)를 만든 후 최소 힙에 넣는다.
  (이때 부모 Node의 빈도 수는 자식 Node들의 합)

5. 최소 힙이 빌(empty)때까지 1~4를 반복한다.

  
  
## 과정
:*(1,2,3,4,5,6)의 빈도수를 가진 요소들의 허프만 트리 과정*

  
![컴알 (2)](https://user-images.githubusercontent.com/80369805/114546059-b2482780-9c97-11eb-8375-53d47d2b06b2.png)


## 구현

### 노드(Node)
: 허프만 트리(힙 구조)에 쓰이는 노드객체



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

### 힙(Heap)

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

### 허프만 트리

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

## 실행결과

### 입력

![컴알 입력](https://user-images.githubusercontent.com/80369805/114552603-bc6e2400-9c9f-11eb-99ce-3906985e8470.png)

### 결과

![컴알 결과](https://user-images.githubusercontent.com/80369805/114552635-c55ef580-9c9f-11eb-931f-e6021f7a0514.png)


## 허프만 트리 제작의 시간 복잡도
1.최소 값 추출에 걸리는 시간 : log N
2.원소의 개수 : N

*시간복잡도 : log N x N = *O(NlogN)*


![허프만 시간복자도](https://user-images.githubusercontent.com/80369805/114535798-62fbfa00-9c8b-11eb-92bb-e6e41ee39f42.png)


