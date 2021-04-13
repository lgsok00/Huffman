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
