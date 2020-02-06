package analyseLexicale;

import java.util.Scanner;

public class SourceReader {

	private String entree;
	private int k = -1;

	/**
	 * Constructeur
	 * @param entree
	 */
	public SourceReader(String entree) {
		super();
		this.entree = entree;
	}

	/**
	 *  parcours la chaine de caractères pour lire chaque caractère
	 * @return Char
	 */
	public Character lectureSymbole() {
		k++;
		if (entree.length() == k)
			return null;
		else
			return this.entree.charAt(k);
	}

	/**
	 *  retour en arrière
	 */
	public void goBack() {
		k--;
	}

}
