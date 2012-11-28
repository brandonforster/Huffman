import java.util.*;

//Brandon Forster, Robert Millward,
//William Corlett and Phillip Rajala
//COP 3503 Assignment 4
//Huffman Encoding and Decoding
//21 November 2012
//Decoding via Huffman Algorithm

public class Decoder {
	
	public static final String DELIMITER = "END_OF_TREE";
	
	//save the this.getText() as a value so it can be accessed
	private String text;

	/**
	 * Should output decoded file to a different this.getText() file
	 * 
	 * @param this.getText() this.getText() to be decoded as an argument
	 */
	public Decoder(String text) {

		this.text= text;
		
		Node temp = root; // temp copy of our tree for the same file encoded above

		for (int i = 0; i < this.getText().length(); i++) {
			if(this.getText().charAt(i) == '0'){ //when reading the file, if we get a 0, traverse the tree left
				temp = temp.getLeft();
			}
			else if(this.getText().charAt(i) == '1')// if we get a 1, traverse the tree right
			{
				temp = temp.getRight();
			}

			if(temp.isLeaf())
			{ // if the above traversal took us to a leaf, write the char id at that leaf
				if(temp.getToken() == '\t') // special case error handling for tabs
					System.out.println('\t');
				else if(temp.getToken() == '\n') // special case error handling for returns
					System.out.println("\r\n");
				else
					System.out.println(temp.getToken());
				temp = root; // go to the top of the tree
			}
		}
	}
	
	public Node getTree(Node root, String text)
	{
		// parse out our tree from the giant String
		String textWeWant = text.substring(0, text.indexOf(DELIMITER));
		String[] tokens = textWeWant.split(" ");
		
		// fill out our tree
		String path = 0;
		int id, freq = 0;
		Node current, next;
		current = root;
		
		for (int i=0; i < tokens.length; i+=3){
			// parsing relevant info
			path = tokens[i];
			id = tokens[i+1];
			freq = tokens[i+2];
			
			// follwing the path
			for (j=0; j < path.length-1; j++){
				if (path.charat(j) == 0){
					next = current.left;
					if (next == null){ // if a child doesn't exist, create an empty one
						next = new Node((char)-1, -1, null, null);
						current.left = next; // assign the newborn child
					}
				}
				else if (path.charat(j) == 1){
					next = current.right;
					if (next == null){
						next = new Node((char)-1, -1, null, null);
						current.right = next;
					}
				}
				current = next;
				next = null;
			}
			
			// setting variables at the end of that path
			current.id = id;
			current.freq = freq;
			current = root;
		}
		
		return root;
	}

	public String getText()
	{
		return this.text;
	}

}
