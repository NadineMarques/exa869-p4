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
	private String type;
	private String value; 
	private String name;
	
	public SymbolConstant(Token token) {
		super(token);
		name = token.getValue();
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}

}
