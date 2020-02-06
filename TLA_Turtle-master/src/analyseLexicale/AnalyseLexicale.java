package analyseLexicale;




import java.util.ArrayList;

import analyseSyntaxique.Parser;


public class AnalyseLexicale {
	
	private String entree;
	 String buf="";
   	 int etat = 0;
	private ArrayList<Token> tokens = new ArrayList<Token>();
	

	

	/**
	 * Tableau de transition
	 */
	static Integer transition[][] = {
			 //             espace lettre chiffre   [     ]         autre
			/*  0 */    {      0,     1,      2,  101,  102,         null      },
			/*  1 */    {    201,     1,      1,  201,  201,          null      },
			/*  2 */    {    202,   202,      2,  202,  202,         null      },			
			

			// 101 accepte [                        (goBack : non)
			// 102 accepte ]                        (goBack : non)                     
                       
		
			// 201 accepte identifiant ou mot clé   (goBack : oui)
			// 202 accepte entier                   (goBack : oui)			
			
	};
	
			

	
	public AnalyseLexicale() {
		
	}

/**
 * permet d'attribuer des Tokens aux différents éléments lus
 * @param sr
 * @return ArrayList<Token>
 */
public ArrayList<Token> analyseLexicale(SourceReader sr) {		
	
   	 while (true) {
   		 Character c = sr.lectureSymbole();
   		Integer e = transition[etat][indiceSymbole(c)];
   		 if (e == null) {
   			 
   		 }
   		 if (e >= 100) {
   			 if (e == 101) {
   				 System.out.println("Accepte [");
   				  tokens.add(new Token(TokenClass.leftHook,"["));
   			 } else if (e == 102) {
   				 System.out.println("Accepte ]");
   				  tokens.add(new Token(TokenClass.rightHook,"]"));
   			 } 
   			
   			 else if (e == 201) {
   				 System.out.println("Accepte identifiant " + buf);
   				   
   				  if(buf.contains("repeat") == true) tokens.add(new Token(TokenClass.repeat, buf));
   				  else if(buf.contains("forward") == true) tokens.add(new Token(TokenClass.forward, buf));
   				  else if(buf.contains("right") == true) tokens.add(new Token(TokenClass.right, buf));
   				  else if(buf.contains("left") == true) tokens.add(new Token(TokenClass.left, buf));
		
   				 sr.goBack();
   			 } else if (e == 202) {
   				 System.out.println("Accepte intVal " + buf);
   				  tokens.add(new Token(TokenClass.intVal, buf));
   				 sr.goBack();
   			 } 
   			 etat = 0;
   			 buf = "";
   		 } else {
   			 etat = e;
   			 if (etat>0) buf = buf + c;
   		 }
   		 
   		 if (c==null) break;
   	 }
   	 return  tokens;
    }

	
	
	
	/**
	 * Retourne l'indice de chaque symbole en rapport 
	 * avec le tableau de transition
	 * @param c
	 * @return int
	 */
	int indiceSymbole(Character c) {
		
		try {
			if(c==null) return 0;
//			if(cc == null) return 0;
			if(Character.isWhitespace(c)) return 0;
			if(Character.isAlphabetic(c)) return 1;
			if(Character.isDigit(c)) return 2;
			if(c == '[') return 3;
			if(c== ']') return 4;
			
			
			else			
			return 5;
			
		
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("le symbole n'est pas dans l'alphabet");
		}
		
		return -1;
	}
		




	

}
