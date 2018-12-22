/**
 * 
 */
package model.semantic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import controller.SemanticAnalyzer;
import model.Token;
import model.TokensFlow;

/**
*
* Classe que representa os símbolos das classes na tabela de símbolos
*
* @author Nadine Cerqueira
* @author Valmir Vinicius
*
*/
public class SymbolClass {
	private Token name;
	private Token heritage;
	private LinkedList<SymbolVariable> attributes = new LinkedList<SymbolVariable>();
	private LinkedList<SymbolMethod> methods = new LinkedList<SymbolMethod>();
	
	/**
	 * @return the attributes
	 */
	public SymbolVariable getAttribute() {
		return attributes.getLast();
	}
	
	/**
	 * @return the attributes
	 */
	public LinkedList<SymbolVariable>  getAttributes() {
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
	public Token getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName() {
		this.name = TokensFlow.getToken();
	}

	/**
	 * @return the heritage
	 */
	public Token getHeritage() {
		return heritage;
	}

	/**
	 * @param heritage the heritage to set
	 */
	public void setHeritage() {
		this.heritage = TokensFlow.getToken();
	}
	
	public void newAttribute() {
		attributes.add(new SymbolVariable());
		attributes.getLast().setType();
	}
	
	public void newMethod() {
		methods.add(new SymbolMethod());
		methods.getLast().setType();
	}
	
	public String toString() {
		return "class " + name.getValue() + " extends "  +
				"\nAtributos:\n" +
				attributes.toString() + 
				"\nMetodos:\n" +
				methods.toString();
	}



}
