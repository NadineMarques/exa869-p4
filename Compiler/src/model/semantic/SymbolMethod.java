/**
 * 
 */
package model.semantic;

import java.util.LinkedList;
import java.util.List;

import model.Token;

/**
 *
 *Classe que representa os símbolos dos métodos na tabela de símbolos
 *
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class SymbolMethod extends Symbol{
	private String type;
	private String name;
	private LinkedList<SymbolVariable> parameters = new LinkedList<SymbolVariable>();
	private LinkedList<SymbolVariable> variables = new LinkedList<SymbolVariable>();
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
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
	
}
