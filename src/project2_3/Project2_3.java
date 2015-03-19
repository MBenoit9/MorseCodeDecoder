package project2_3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Project2_3 {

static int number=0;//counter used to display the number of valid translations found
static Node treeDot;//tree for string starting with a dot
static Node treeDash;//tree for string starting with a dash

	public static void main(String[] args) throws FileNotFoundException {
		String dot_dash; 
		System.out.println("Please enter some Morse code. Use 'O' for a dot and 'A' for a dash.");
		System.out.println("Morse Code: ");
		
		Scanner input= new Scanner(System.in);	//User enters morse code using O's and A's
		dot_dash= input.nextLine();	//dot_dash holds the input from the use
		input.close();
		System.out.println();
		
		treeDot=buildTree('O');//make treeDot
		treeDash=buildTree('A');//make treeDash
		
		int morseLength= dot_dash.length();
		char[] words=new char[morseLength];//create an array that will be used to store the translated characters
		
		PrintWriter output= new PrintWriter(new FileOutputStream(dot_dash+".txt"));	//create a file named after the string given
		
		long funcTime=System.nanoTime();//used to calculate time it takes for function Translate to finish
		
		char[][]reference= new char[morseLength][morseLength+1];
		
		for(int i=0; i<morseLength; i++)
			for(int j=0; j<(morseLength+1); j++)
			{
				reference[i][j]='*';
			}
		
		Translate(dot_dash, words, morseLength, 0, 0, output, reference);//the two zeros on the right are the starting index and the index counter for words[]
		
		funcTime=System.nanoTime()-funcTime;
		
		output.println("The total number number of possible translations is: "+number);
		output.println("This program took "+funcTime+" nanoseconds.");
		
		output.close();
	}
	
	static void Translate(String dot_dash, char[] words, int n, int start, int counter, PrintWriter output, char[][] reference) throws FileNotFoundException
	{//Tries to find every valid translation of a string of morse code
		if (start==n)
		{
			print(words,counter, output);
			number++;
		}
		
		else
		{
			for (int i=1; i<=5; i++)//cycles through taking pieces of 1,2,3,4, and 5 length
			{
				if (i<=(n-start))//do if start index + i does not go past the last character in the string
				{
					char translation;
					if(reference[start][start+i]!='*')// if a substring has been translated already and the result has been stored in the table, then grab the translation from the table.
					{
						translation= reference[start][start+i];
					}
					else
					{
						translation=MorseToChar(dot_dash.substring(start,start+i),i);//otherwise, check to see if substring correlates to a letter or a number
						reference[start][start+i]=translation;// store the translation into the table
					}
					
					if (translation!='~')//If substring yields a valid translation place that character at the current index(counter) in the array
					{
						words[counter]=translation;
						Translate(dot_dash, words, n, start+i, counter+1, output, reference);//look at the remaining symbols in dot_dash
					}
				}
			}
		}
	}
	
	static char MorseToChar(String  morseCode, int length)
	{	//Translate Morse code to a letter or number
		Node current;
		char answer;
		if(morseCode.substring(0,1).equals("A"))//check to see if the morseCode starts with a dash or a dot in order to pick the right tree
			current=treeDash;
		else
		{
			current=treeDot;
		}
		
		for(int i=1; i<length; i++)//traverse the tree to find the correct translation
		{
			if(morseCode.substring(i,i+1).equals("O"))
			{
				
				current=current.leftChild;
			}
			else
			{
				current=current.rightChild;
			}
			
			if(current==null)//if you reach a null then the translation is invalid
				break;
		}
		
		if(current==null)//send back ~ to signify an invalid series of symbols
			answer='~';
		else
			answer=current.info;
		
		return answer;//return the correct translation
	}
	
	static void print(char[] words, int count, PrintWriter output) throws FileNotFoundException
	{//print an interpretation of dot_dash to a file named with dot_dash
		
		for(int i=0; i<count; i++)
		{
			output.print(words[i]);
		}
		output.println();
	}
	
	public static Node buildTree(char choose)
	{// build trees
		Node root;
		if(choose=='O')//build treeDot
		{
			root= new Node('E');
			Node nodeA= new Node('A');
			Node nodeF= new Node('F');
			Node nodeH= new Node('H');
			Node nodeI= new Node('I');
			Node nodeJ= new Node('J');
			Node nodeL= new Node('L');
			Node nodeP= new Node('P');
			Node nodeR= new Node('R');
			Node nodeS= new Node('S');
			Node nodeU= new Node('U');
			Node nodeV= new Node('V');
			Node nodeW= new Node('W');
			Node node1= new Node('1');
			Node node2= new Node('2');
			Node node3= new Node('3');
			Node node4= new Node('4');
			Node node5= new Node('5');
			Node nodeFake1= new Node('~');
			
			root.leftChild=nodeI;
			root.rightChild=nodeA;
			nodeI.leftChild=nodeS;
			nodeI.rightChild=nodeU;
			nodeA.leftChild=nodeR;
			nodeA.rightChild=nodeW;
			nodeS.leftChild=nodeH;
			nodeS.rightChild=nodeV;
			nodeU.leftChild=nodeF;
			nodeU.rightChild=nodeFake1;
			nodeR.leftChild=nodeL;
			nodeW.leftChild=nodeP;
			nodeW.rightChild=nodeJ;
			nodeH.leftChild=node5;
			nodeH.rightChild=node4;
			nodeV.rightChild=node3;
			nodeFake1.rightChild=node2;
			nodeJ.rightChild=node1;			
		}
		else//build treeDash
		{
			root= new Node('T');
			Node nodeB= new Node('B');
			Node nodeC= new Node('C');
			Node nodeD= new Node('D');
			Node nodeG= new Node('G');
			Node nodeK= new Node('K');
			Node nodeM= new Node('M');
			Node nodeN= new Node('N');
			Node nodeO= new Node('O');
			Node nodeQ= new Node('Q');
			Node nodeX= new Node('X');
			Node nodeY= new Node('Y');
			Node nodeZ= new Node('Z');
			Node node6= new Node('6');
			Node node7= new Node('7');
			Node node8= new Node('8');
			Node node9= new Node('9');
			Node node0= new Node('0');
			Node nodeFake3= new Node('~');
			Node nodeFake4= new Node('~');
			
			root.leftChild=nodeN;
			root.rightChild=nodeM;
			nodeN.leftChild=nodeD;
			nodeN.rightChild=nodeK;
			nodeM.leftChild=nodeG;
			nodeM.rightChild=nodeO;
			nodeD.leftChild=nodeB;
			nodeD.rightChild=nodeX;
			nodeK.leftChild=nodeC;
			nodeK.rightChild=nodeY;
			nodeG.leftChild=nodeZ;
			nodeG.rightChild=nodeQ;
			nodeO.leftChild=nodeFake3;
			nodeO.rightChild=nodeFake4;
			nodeB.leftChild=node6;
			nodeZ.leftChild=node7;
			nodeFake3.leftChild=node8;
			nodeFake4.leftChild=node9;
			nodeFake4.rightChild=node0;
		}
		return root;
	}
	
	public static class Node
	{//define a node
		Node rightChild;
		Node leftChild;
		char info;
		
		public Node(char rep)
		{
			rightChild=null;
			leftChild=null;
			info=rep;			
		}
	}
	
	

}
