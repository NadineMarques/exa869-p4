/**
 * 
 */
package model.semantic;

import model.Token;
import model.TokensFlow;

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
	
	public SymbolVariable() {
		
	}
	
	
	public void setName() {
		this.name = TokensFlow.getToken();
	}
	
	public void setName(Token name) {
		this.name = name;
	}
	
	public void setType() {
		this.type = TokensFlow.getToken();
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

	public void setValue() {
		if(type.getValue().equals("int")) {
			this.value = new Token("NUMERO", "0", type.getRow());
		} else if(type.getValue().equals("float")) {
			this.value = new Token("NUMERO", "0.0", type.getRow());
		} else if(type.getValue().equalsIgnoreCase("bool")) {
			this.value = new Token("PALAVRA_RESERVADA", "false", type.getRow());
		} else if(type.getValue().equals("string")) {
			this.value = new Token("CADEIA_DE_CARACTERES", "false", type.getRow());
		}
	}
	
	public void setValue(Token value) {
		this.value = value;
	}
	
	public Token getValue() {
		return value;
	}
	
	public String toString() {
		return type.getValue() + " " + name.getValue() + "\n";
	}
	
	public boolean equals(Object symbol) {
		if(symbol instanceof SymbolVariable) {
			SymbolVariable sv = (SymbolVariable) symbol;
			
			if(sv.getName().getValue().equals(name.getValue())) {
				if(sv.getType().getValue().equals(type.getValue())) {
					return true;
				}
			}
		}
		
		return false;
	}
	

}
