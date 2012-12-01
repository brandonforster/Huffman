//Brandon Forster, Robert Millward,
//William Corlett and Phillip Rajala
//COP 3503 Assignment 4
//Huffman Encoding and Decoding
//21 November 2012
//Node Object

public class Node implements Comparable<Node> {

	private int 	token;
	private int 	frequency;
	private Node 	left;
	private Node 	right;

	Node(int ch, int freq, Node l, Node r) {
		this.token = ch;
		this.frequency = freq;
		this.left = l;
		this.right = r;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int id) {
		this.token = id;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	// is the node a leaf node?
	public boolean isLeaf() {
		return (getLeft() == null && getRight() == null);
	}

	/**
	 * returns a positive value when this node has a larger value than arg,
	 * negative when this node's frequency is smaller
	 */
	public int compareTo(Node other) {

		return (this.getFrequency() - other.getFrequency());
	}


}
