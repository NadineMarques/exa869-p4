/**
 * 
 */
package model.semantic;

import java.util.LinkedList;
import java.util.List;

import model.Token;

/**
*
* Classe que representa os símbolos das classes na tabela de símbolos
*
* @author Nadine Cerqueira
* @author Valmir Vinicius
*
*/
public class SymbolClass {
	private String name;
	private String heritage;
	private LinkedList<Token> attributes = new LinkedList<Token>();
	private LinkedList<SymbolMethod> methods = new LinkedList<SymbolMethod>();
	
	/**
	 * @return the attributes
	 */
	public LinkedList<Token> getAttributes() {
		return attributes;
	}

	/**
	 * @return the methods
	 */
	public LinkedList<SymbolMethod> getMethods() {
		return methods;
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
	 * @return the heritage
	 */
	public String getHeritage() {
		return heritage;
	}

	/**
	 * @param heritage the heritage to set
	 */
	public void setHeritage(String heritage) {
		this.heritage = heritage;
	}


}
