package ex;

import java.util.ArrayList;

import analyseLexicale.AnalyseLexicale;
import analyseLexicale.SourceReader;
import analyseLexicale.Token;
import analyseLexicale.TokenClass;
import analyseSyntaxique.Parser;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Christian Mada-Mbari
 *
 */
public class Interpreter {

	final static double INIT_X = 100;
	final static double INIT_Y = 100;
	final static double INIT_DIRECTION = 180;

	double x;
	double y;
	double direction;

	GraphicsContext gc;
	private ArrayList<Token> tokens;

	/**
	 * @param src
	 * @param gc
	 */
	void interpreter(String src, GraphicsContext gc) {

		SourceReader sr = new SourceReader(src);
		tokens = new AnalyseLexicale().analyseLexicale(sr);

		try {
			new Parser().parser(tokens);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------");

		this.gc = gc;

		x = INIT_X;
		y = INIT_Y;
		direction = INIT_DIRECTION;

		Tortue t = new Tortue(direction, x, y, gc);

		int loop = 0;

		if (findRepeat()) {
			getRepeat();
			
		}

		while (loop != tokens.size()) {

			analyse(tokens, t, loop);

			loop++;

		}

	}

	/**
	 * Permet d'analyser les tokens afin de mettre à jour les coordonnées de l'objet
	 * tortue
	 * 
	 * @param token
	 * @param t
	 * @param loop
	 */
	private void analyse(ArrayList<Token> token, Tortue t, int loop) {
		int k;

		if (token.get(loop).getCl() == TokenClass.forward) {
			k = loop;
			double l = Double.parseDouble(token.get(k + 1).getValue());
			t.avance(l);

		} else if (token.get(loop).getCl() == TokenClass.right) {
			k = loop;
			double l = Double.parseDouble(token.get(k + 1).getValue());
			t.right(l);

		} else if (token.get(loop).getCl() == TokenClass.left) {
			k = loop;
			double l = Double.parseDouble(token.get(k + 1).getValue());
			t.left(l);

		}

	}

	/**
	 * Permet de retouner true si le token repeat existe dans la liste
	 * 
	 * @return boolean
	 */
	private boolean findRepeat() {
		for (Token t : tokens) {
			if (t.getCl() == TokenClass.repeat)
				return true;
		}
		return false;
	}

	/**
	 * permet de trier la liste pour traiter les repeat, en outre on recupère
	 * 
	 * un repeat on sauvegarde sa valeur et on enregistre autant de fois ses valeurs
	 * entre les crochets
	 * 
	 */
	private void getRepeat() {
		Token token_tampon;
		int position_debut = 0;
		int position_fin = tokens.size();
		int valeur_repeat = 0;
		int compteur_leftHook = 0;
		
		/**
		 * la premiere contient les tokens avant le repeat, 
		 * la deuxieme  contient les tokens entre les crochets du repeat autant de 
		 * fois que la valeur du repeat et,
		 *  la troisieme contient les tokens 
		 * apres le crochet fermant du repeat puis je rassemble la liste
            a la fin on a une liste sans repeat sans chrochets
		 */

		ArrayList<Token> liste1 = new ArrayList();
		ArrayList<Token> liste2 = new ArrayList();
		ArrayList<Token> liste3 = new ArrayList();

		token_tampon = tokens.get(position_debut);

		while (token_tampon.getCl() != TokenClass.leftHook) {
			if (token_tampon.getCl() == TokenClass.repeat) {
				position_debut++;
			}
			position_debut++;
			token_tampon = tokens.get(position_debut);
		}
		position_debut++;

		position_fin = position_debut;

		token_tampon = tokens.get(position_fin);

		while (token_tampon.getCl() != TokenClass.rightHook || compteur_leftHook != 0) {
			if (token_tampon.getCl() == TokenClass.leftHook) {
				compteur_leftHook++;
			}
			if (token_tampon.getCl() == TokenClass.rightHook) {
				compteur_leftHook--;
			}
			position_fin++;
			token_tampon = tokens.get(position_fin);
		}
		position_fin++;

		addToken(position_debut, position_fin, liste1, liste2, liste3);

		fusion(liste1, liste2, liste3);

		if (findRepeat())
			getRepeat();
		;
	}

	/**
	 * Permet d'ajouter les tokens dans les listes au moment du trie
	 * 
	 * @param position_debut
	 * @param position_fin
	 * @param liste1
	 * @param liste2
	 * @param liste3
	 */
	private void addToken(int position_debut, int position_fin, ArrayList<Token> liste1, ArrayList<Token> liste2,
			ArrayList<Token> liste3) {
		int valeur_repeat;
		for (int i = 0; i < (position_debut - 3); i++) {
			liste1.add(tokens.get(i));
		}

		for (int i = (position_fin); i < tokens.size(); i++) {
			liste3.add(tokens.get(i));
		}

		valeur_repeat = Integer.parseInt(tokens.get(position_debut - 2).getValue());
		for (int i = 1; i <= valeur_repeat; i++) {
			for (int j = (position_debut); j < (position_fin - 1); j++) {
				liste2.add(tokens.get(j));
			}
		}
	}

	/**
	 * Permet de fusionner les trois listes triers pour mettre à jour la liste
	 * principale des tokens
	 * 
	 * @param liste1
	 * @param liste2
	 * @param liste3
	 */
	private void fusion(ArrayList<Token> liste1, ArrayList<Token> liste2, ArrayList<Token> liste3) {
		tokens = new ArrayList();
		tokens.addAll(liste1);
		tokens.addAll(liste2);
		tokens.addAll(liste3);
	}

}
