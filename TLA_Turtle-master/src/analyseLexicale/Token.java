package analyseLexicale;

/**
 * @author Christian Mada-Mbari
 *
 */
public class Token {

	private TokenClass cl;
	private String value;

	/**
	 * Constructeur 1
	 * @param cl
	 */
	public Token(TokenClass cl) {
		this.cl = cl;
		this.value = null;
	}

	/**
	 * Constructeur 2
	 * @param cl
	 * @param value
	 */
	public Token(TokenClass cl, String value) {
		this.cl = cl;
		this.value = value;
	}

	/**
	 * @return TokenClass
	 */
	public TokenClass getCl() {
		return cl;
	}

	/**
	 * @return string
	 */
	public String getValue() {
		return value;
	}

	/**
	 * affichage
	 */
	public String toString() {
		String res = cl.toString();
		if (value != null)
			res = res + "(" + value + ")";
		return res;
	}

}