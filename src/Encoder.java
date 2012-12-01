import java.util.PriorityQueue;

//Brandon Forster, Robert Millward,
//William Corlett and Phillip Rajala
//COP 3503 Assignment 4
//Huffman Encoding and Decoding
//21 November 2012
//Encoding via Huffman Algorithm

public class Encoder {

	//size of full ascii table
	private static final int ASCII_ALPHABET_SIZE = 256;

	/**
	 * Should output encoded file to a different text file
	 * 
	 * @param text text to be encoded as an argument
	 */
	public Encoder(String text) {

		//make a table to keep track of how many times each ascii character
		//appears
		int[] frequencyTable= new int[ASCII_ALPHABET_SIZE];

		//for each character in text
		for (int i = 0; i< text.length(); i++)
		{
			//makes an int cast on the character at i, giving us its ascii
			//value. increment that ascii value in the table
			frequencyTable[(int)text.charAt(i)]++;
		}

		// priority queue of tree nodes that will build our tree
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		// add the frequencies
		for (int i = 0; i < ASCII_ALPHABET_SIZE; i++) {
			if (frequencyTable[i] > 0)
			{
				//cast the incrementor to the char so the node is well formed
				//add the freqency to the node object
				pq.add(new Node(i, frequencyTable[i], null, null));
			}
			System.out.println(i + " " + frequencyTable[i]);
		}

		// make trees out of the frequencies
		while (pq.size() > 1) {
			Node left  = pq.remove();
			Node right = pq.remove();
			Node parent = new Node(-1, left.getFrequency() + right.getFrequency(), left, right);
			pq.add(parent);
		}

		// our root node for the final merged tree
		Node root = pq.remove();

		// our lookup table for which int equates to which series of 0s and 1s
		String[] hash = new String[ASCII_ALPHABET_SIZE];

		// fill the lookup table
		fillHash(hash, root, "");

		//print a delimiter so we know where tree ends
		System.out.println("\nEND_OF_TREE\n");
		
		// write the encoded file using the lookup table
		for (int i = 0; i < text.length(); i++) {
			System.out.print(hash[(int)text.charAt(i)]);
		}
	}

	// recursive function that fills the hash table of what bits equate to which chars
	public void fillHash(String[] hashArray, Node current, String code) {
		if (!current.isLeaf()) { // recursive traversal
			fillHash(hashArray, current.getRight(), code + '1');
			fillHash(hashArray, current.getLeft(),  code + '0');
		}
		else { // if we're at a leaf, set the array value
			hashArray[current.getToken()] = code;
		}
	}

}
