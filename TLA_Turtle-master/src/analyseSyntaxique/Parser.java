package analyseSyntaxique;

import java.util.ArrayList;

import analyseLexicale.Token;
import analyseLexicale.TokenClass;

/**
 * @author Christian Mada-Mbari
 *
 */
public class Parser {

	private int pos;
	private int profondeur;
	private ArrayList<Token> tokens;

	/**
	 * @param tokens
	 * @throws Exception
	 */
	public void parser(ArrayList<Token> tokens) throws Exception {
		this.tokens = tokens;
		pos = 0;
		S();

		System.out.println("Fin atteinte = " + (pos == tokens.size()));

	}

	/*
	 * 
	 * méthodes des symboles non terminaux
	 * 
	 */

	/**
	 * @throws Exception
	 */
	private void S() throws Exception {

		if (getTokenClass() == TokenClass.repeat) {

			// production S -> repeat S"S

			Token ident = getToken();

			printNode(ident.getValue()); // affiche la valeur int
			S_second();
			profondeur--;
			S();

			return;
		}

		if (getTokenClass() == TokenClass.right || getTokenClass() == TokenClass.left
				|| getTokenClass() == TokenClass.forward) {

			// production S -> AS

			profondeur++;
			A();
			profondeur--;
			S();

			return;
		}

	}

	private void S_second() throws Exception {

		if (getTokenClass() == TokenClass.intVal) {

			// production S" -> intVal S'

			Token tokIntVal = getToken();

			printNode(tokIntVal.getValue()); // affiche la valeur int
			S_prime();
			return;
		}

		throw new Exception("intVal ou [ attendu");

	}

	private void S_prime() throws Exception {

		if (getTokenClass() == TokenClass.leftHook) {

			// production S' -> [SS]

			getToken();
			printNode("[");

			profondeur++;
			S();

			profondeur--;
			S();

			if (getTokenClass() == TokenClass.rightHook) {
				getToken();
				printNode("]");
				S();
				return;
			}

			throw new Exception("[ attendu");

		}

		throw new Exception(" ] attendu");
	}

	private void A() throws Exception {

		if (getTokenClass() == TokenClass.right || getTokenClass() == TokenClass.left
				|| getTokenClass() == TokenClass.forward) {

			// production A -> left intval ou right intval ou forward intval

			printNode(getToken().getValue());

			if (getTokenClass() == TokenClass.intVal) {

				

				Token tokIntVal = getToken();
				printNode(tokIntVal.getValue()); // affiche la valeur int

				return;
			}
			throw new Exception("int attendu");

		}
		throw new Exception("right, left or forward attendu");
	}

	/*
	 * 
	 * autres méthodes
	 * 
	 */

	private boolean isEOF() {
		return pos >= tokens.size();
	}

	/*
	 * Retourne la classe du prochain token à lire SANS AVANCER au token suivant
	 */
	private TokenClass getTokenClass() {
		if (pos >= tokens.size()) {
			return null;
		} else {
			return tokens.get(pos).getCl();
		}
	}

	/*
	 * Retourne le prochain token à lire ET AVANCE au token suivant
	 */
	private Token getToken() {
		if (pos >= tokens.size()) {
			return null;
		} else {
			Token current = tokens.get(pos);
			pos++;
			return current;
		}
	}

	/**
	 * @param s
	 */
	private void printNode(String s) {
		for (int i = 0; i < profondeur; i++) {
			System.out.print("    ");
		}
		System.out.println(s);
	}
}
