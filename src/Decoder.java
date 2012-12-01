import java.util.*;

//Brandon Forster, Robert Millward,
//William Corlett and Phillip Rajala
//COP 3503 Assignment 4
//Huffman Encoding and Decoding
//21 November 2012
//Decoding via Huffman Algorithm

public class Decoder {

	public static final String DELIMITER = "END_OF_TREE";
	private static final int ASCII_ALPHABET_SIZE = 256;

	//save the this.getText() as a value so it can be accessed
	private String text;

	/**
	 * Should output decoded file to a different this.getText() file
	 * 
	 * @param this.getText() this.getText() to be decoded as an argument
	 */
	public Decoder(String text) {

		this.text= text;

		Node root = getTree();
		
		//index of, start from end of delimiter + eating new line to EOF
		String workingText= text.substring(text.indexOf(DELIMITER)+DELIMITER.length());

		Node temp = root; // temp copy of our tree for the same file encoded above

		for (int i = 0; i < workingText.length(); i++) {
			if(workingText.charAt(i) == '0'){ //when reading the file, if we get a 0, traverse the tree left
				temp = temp.getLeft();
			}
			else if(workingText.charAt(i) == '1')// if we get a 1, traverse the tree right
			{
				temp = temp.getRight();
			}

			if(temp.isLeaf())
			{ // if the above traversal took us to a leaf, write the char id at that leaf
				if(temp.getToken() == '\t') // special case error handling for tabs
					System.out.print('\t');
				else if(temp.getToken() == '\n') // special case error handling for returns
					System.out.print("\r\n");
				else
					System.out.print((char)temp.getToken());
				temp = root; // go to the top of the tree
			}
		}
	}

	public Node getTree()
	{
		// parse out our tree from the giant String
		String textWeWant= this.getText().substring(0, this.getText().indexOf(DELIMITER));

		Scanner treeScanner = new Scanner(textWeWant);

		// fill out our tree
		int[] freqTable = new int[256];
		int id, freq = 0;
		Node current, next = null;
		Node root = new Node(-1, 0, null, null);

		while(treeScanner.hasNext()){
			id = treeScanner.nextInt();
			freq = treeScanner.nextInt();
			
			freqTable[id] = freq;
		}
		
		// priority queue of tree nodes that will build our tree
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		// add the frequencies
		for (int i = 0; i < ASCII_ALPHABET_SIZE; i++) {
			if (freqTable[i] > 0)
			{
				//cast the incrementor to the char so the node is well formed
				//add the freqency to the node object
				pq.add(new Node(i, freqTable[i], null, null));
			}
		}

		// make trees out of the frequencies
		while (pq.size() > 1) {
			Node left  = pq.remove();
			Node right = pq.remove();
			Node parent = new Node(-1, left.getFrequency() + right.getFrequency(), left, right);
			pq.add(parent);
		}

		// our root node for the final merged tree
		root = pq.remove();

		treeScanner.close();

		return root;
	}

	public String getText()
	{
		return this.text;
	}

}
