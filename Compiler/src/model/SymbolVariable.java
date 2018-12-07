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
	private String type; 
	private String name;
	private String value = "0";
	
	public SymbolVariable(Token token) {
		super(token);
		name = token.getValue();
	}
	
	public SymbolVariable() {

	}
	
	public void setName(String name) {
		this.name = name;
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
