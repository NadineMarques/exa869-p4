/**
 * 
 */
package model.semantic;

import java.util.LinkedList;
import java.util.List;

import model.Token;
import model.TokensFlow;

/**
 *
 *Classe que representa os símbolos dos métodos na tabela de símbolos
 *
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class SymbolMethod extends Symbol{
	private Token type;
	private Token name;
	private Token returnToken;
	private LinkedList<SymbolVariable> parameters = new LinkedList<SymbolVariable>();
	private LinkedList<SymbolVariable> variables = new LinkedList<SymbolVariable>();
	/**
	 * @return the type
	 */
	public Token getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Token type) {
		this.type = type;
	}
	

	public void setType() {
		this.type = TokensFlow.getToken();
	}
	
	public void setReturn() {
		this.returnToken = TokensFlow.getToken();
	}
	
	/**
	 * @return the name
	 */
	public Token getName() {
		return name;
	}
	

	public void setName() {
		this.name = TokensFlow.getToken();
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(Token name) {
		this.name = name;
	}
	/**
	 * @return the parameters
	 */
	public LinkedList<SymbolVariable> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(LinkedList<SymbolVariable> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * @return the variables
	 */
	public LinkedList<SymbolVariable> getVariables() {
		return variables;
	}
	/**
	 * @param variables the variables to set
	 */
	public void setVariables(LinkedList<SymbolVariable> variables) {
		this.variables = variables;
	}
	
	public void newParameter() {
		parameters.add(new SymbolVariable());
		parameters.getLast().setType();
	}
	
	public void newVariable() {
		variables.add(new SymbolVariable());
		variables.getLast().setType();
	}
	
	public String toString() {
		return type.getValue() + " " + name.getValue() + "\nParâmetros:\n" + parameters.toString() + "\nVariaveis:" + variables.toString(); 
	}
	
}
