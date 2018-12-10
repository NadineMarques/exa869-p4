/**
 * 
 */
package model;

/**
 *
 *Classe que representa os símbolos das constantes na tabela de símbolos
 *
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class SymbolConstant extends Symbol{
	private Token type;
	private Token value; 
	private Token name;
	
	public SymbolConstant(Token token) {
		super(token);
		name = token;
		
	}
	
	public void setType(Token type) {
		this.type = type;
	}
	public Token getType() {
		return type;
	}

	public Token getName() {
		return name;
	}
	public void setName(Token name) {
		this.value = name;
	}

	public void setValue(Token value) {
		this.value = value;
	}
	public Token getValue() {
		return value;
	}

}
