/**
 * 
 */
package model;

/**
 * Classe que representa os símbolos das variáveis na tabela de símbolos
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class SymbolVariable extends Symbol{
	private Token type; 
	private Token name;
	private Token value;
	
	public SymbolVariable(Token token) {
		super(token);
	}
	
	public SymbolVariable() {

	}
	
	public void setName(Token name) {
		this.name = name;
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

	public void setValue(Token value) {
		this.value = value;
	}
	public Token getValue() {
		return value;
	}
	

}
