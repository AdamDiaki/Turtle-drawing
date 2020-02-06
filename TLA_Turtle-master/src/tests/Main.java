package tests;

import java.util.ArrayList;
import java.util.Iterator;

import analyseLexicale.AnalyseLexicale;
import analyseLexicale.SourceReader;
import analyseLexicale.Token;
import analyseSyntaxique.Parser;



/**
 * @author Christian Mada-Mbari
 *
 */
public class Main {
	
	
	public static void main(String[] args) {
		
		
		//test(" left 10 forward 34 right 34");

		//testParser( "repeat 20 [ right 12 left 23 repeat 13 [ left 19] left 5 left 23]");
//		testParser("repeat 12 [\r\n" + 
//				"forward 20\r\n" + 
//				"right 30\r\n" + 
//				"\r\n" + 
//				"repeat 6[\r\n" + 
//				"forward 2\r\n" + 
//				"left 70\r\n" + 
//				"]\r\n" + 
//				"\r\n" + 
//				"repeat 12[\r\n" + 
//				"forward 4\r\n" + 
//				"right 10\r\n" + 
//				"]\r\n" + 
//				"left 10\r\n" + 
//				"]");
//
//	
//	
//
	}
	
	
	
	private static void test(String entree) {
		System.out.println();
		SourceReader sr = new SourceReader(entree);
		ArrayList<Token> tokens = new AnalyseLexicale().analyseLexicale(sr);
		for(Token t: tokens) {
			System.out.println(t);
		}
		
		System.out.println("--------------------------------------------------------------------");
	}

	public static void testParser(String s) {
		SourceReader sr = new SourceReader(s);
		ArrayList<Token> tokens = new AnalyseLexicale().analyseLexicale(sr);
		try {
			new Parser().parser(tokens);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------");
	}
	
	

	
}
