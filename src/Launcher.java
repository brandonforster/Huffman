//Brandon Forster, Robert Millward,
//William Corlett and Phillip Rajala
//COP 3503 Assignment 4
//Huffman Encoding and Decoding
//21 November 2012
//Launcher and Helper Object

import java.io.*;
import java.util.*;

public class Launcher {

	private String inputFileText;
	private String treeText;

	public Launcher(String inputFilename)
	{
		try{
			Scanner inputReader= new Scanner(new File(inputFilename));

			//variable to fix a problem where null was being inserted due to += logic
			boolean firstRun= true;

			//while there is input to eat
			while (inputReader.hasNextLine())
			{
				//if it's the first run, we need to not string concatenate
				if (firstRun == true)
				{
					inputFileText= inputReader.nextLine() + "\n";
					firstRun= false;
				}
				//otherwise we need to concatenate
				else
					inputFileText= inputFileText + inputReader.nextLine() + "\n";
			}
			
			//remove extra newline at end of file
			inputFileText = inputFileText.substring(0, inputFileText.length() - 1);

			//close the input scanner
			inputReader.close();
		}
		//catch error in scripting call
		catch (IOException e)
		{
			System.out.println("File not found. Please relaunch.");
			System.exit(1);
		}
	}

	//getter for the big string
	public String getText()
	{
		return this.inputFileText;
	}

	/**
	 * The main function for @Launcher.
	 * 
	 * Functions as a helper function to launch the rest of the program. 
	 * 
	 * @param args 0 must be "encode" or "decode", 1 is the filename 
	 */
	public static void main(String[] args)
	{
		//perform an encode
		if (args[0].compareTo("encode") == 0)
		{
			//create a new Launcher object, reading in the filename
			Launcher encoderScanner= new Launcher(args[1]);

			//pass the string from the read in to the encoder
			Encoder encoder= new Encoder(encoderScanner.getText());
		}

		//perform a decode
		else if (args[0].compareTo("decode") == 0)
		{
			//create a new Launcher object, reading in the filename
			Launcher decoderScanner= new Launcher(args[1]);

			//pass the string from the read in to the decoder
			Decoder decoder= new Decoder(decoderScanner.getText());
		}

		//if there is an error in the scripting file
		else
		{
			System.out.println("Invalid argument to function call.\n" +
					"Please edit your argument.");
			System.exit(1);

		}
	}

}
