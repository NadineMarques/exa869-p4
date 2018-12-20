/**
 * 
 */
package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Token;
import model.TokensFlow;
import model.semantic.Expressions;
import model.semantic.SymbolClass;
import model.semantic.SymbolConstant;
import model.semantic.SymbolVariable;

/**
 *
 * Classe que realiza a análise semântica
 *
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class SemanticAnalyzer {
	public static List<String> semanticErrors = new LinkedList<String>();
	public static List<SymbolConstant> constTable = new LinkedList<SymbolConstant>();
	public static List<SymbolClass> table = new LinkedList<SymbolClass>();
	
	
	public static void arrayIndexVerifier(Token token) {
		if(token.getTokenClass().equals("NUMERO")) {
			if(token.getValue().contains("-")) {
				addSemanticError("número negativo em índice de array");				
				System.out.println("número negativo em índice de array");
			}
		}
	}
	
	public static void addSemanticError(String erro) {
		semanticErrors.add("Erro semântico " + erro + " na linha " + TokensFlow.getToken().getRow());
	}
	
	public static void atribuitionChecker(SymbolConstant constante) {
		if(constante.getType().getValue().equals("int")) {
			if(constante.getValue().getTokenClass().equals("NUMERO") && (!constante.getValue().getValue().contains(".")) ) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(constante.getType().getValue().equals("float")) {
			if(constante.getValue().getTokenClass().equals("NUMERO")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(constante.getType().getValue().equals("bool")) {
			if(constante.getValue().getValue().equals("true") || constante.getValue().getValue().equals("false")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(constante.getType().getValue().equals("string")) {
			if(constante.getValue().getTokenClass().equals("CADEIA_DE_CARACTERES")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
		}
	}
	
	
	public static void atribuitionChecker(SymbolVariable variavel) {
		if(variavel.getType().getValue().equals("int")) {
			if(variavel.getValue().getTokenClass().equals("NUMERO") && (!variavel.getValue().getValue().contains(".")) ) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(variavel.getType().getValue().equals("float")) {
			if(variavel.getValue().getTokenClass().equals("NUMERO")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(variavel.getType().getValue().equals("bool")) {
			if(variavel.getValue().getValue().equals("true") || variavel.getValue().getValue().equals("true")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(variavel.getType().getValue().equals("string")) {
			if(variavel.getValue().getTokenClass().equals("CADEIA_DE_CARACTERES")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos");				
				System.out.println("Incompatibilidade de Tipos");
			}
		}
	}
	
	
	public static void checkConstantOrVariable(Token token) {
		boolean isDeclared = false;
		
		for(int i=0;i<Analyzer.symbolTable.size();i++) {
			
			
			if((Analyzer.symbolTable.get(i).getToken().getValue().equals(token.getValue()))) {
				System.out.println("Tentando atribuir valor a uma constante");
				addSemanticError("Atribuição de valor em constante");
				isDeclared = true;
			}
			
			if((Analyzer.symbolTable.get(i)).getToken().getValue().equals(token.getValue())) {
				
				isDeclared = true;
			}
		
		}
		
		if(!isDeclared) {
			System.out.println("Tentando atribuir a uma variável não declarada");
			addSemanticError("Atribuição em variável não declarada");
		}
	}
	

	
	
	
	
	
}
