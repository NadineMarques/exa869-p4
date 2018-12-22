/**
 * 
 */
package model.semantic;

import java.util.LinkedList;
import java.util.List;
import model.TokensFlow;

/**
 *
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class Expressions {
	
	public static List<String> list = new LinkedList<String>();
	
	
	public static void reset() {
		 list.clear();
	}
	
	public static void addNumber() {
		if(TokensFlow.getToken().getValue().contains(".")) {
			if(TokensFlow.getToken().getValue().contains("-")) {
				list.add("OPERADOR_ARITMETICO");
				list.add("float");
			} else {
				list.add("float");
			}
		} else {
			if(TokensFlow.getToken().getValue().contains("-")) {
				list.add("OPERADOR_ARITMETICO");
				list.add("int");
			} else {
				list.add("int");
			}
		}
	}
	
	public static void addIdentifier() {
		//verifica na tabela de símbolos
		list.add("type");
	}
	
	public static void addBolean() {
		list.add("boolean");
	}
	
	public static void addOperator() {
		list.add(TokensFlow.getToken().getTokenClass());
	}
	
	public static void addParenthesis() {
		list.add(TokensFlow.getToken().getValue());
	}

}
