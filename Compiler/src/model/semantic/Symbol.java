/**
 * 
 */
package model.semantic;

import model.Token;

/**
 *
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class Symbol {
	Token token;
	
	public Symbol(Token token) {
		this.token = token;
	}
	
	public Symbol() {
	}
	
	
	public void addToken(Token token) {
		this.token = token;
	}
	
	public Token getToken() {
		return token;
	}
	
}
