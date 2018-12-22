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
import model.semantic.SymbolMethod;
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
	public static LinkedList<String> semanticErrors = new LinkedList<String>();
	public static LinkedList<SymbolConstant> constTable = new LinkedList<SymbolConstant>();
	public static LinkedList<SymbolClass> table = new LinkedList<SymbolClass>();
	public static boolean processingAttributes = false;
	
	
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
	
	
	public static List<String> reduceExpression(int index) {
		Iterator<String> i = Expressions.list.subList(index, Expressions.list.size()).iterator();
		List<String> expression = new LinkedList<String>();
		List<String> temp;
		String actual;
		int open = 0, close = 0;

		while(i.hasNext()) {
			actual = i.next();

			if(actual.equals("(")) {
				open = 0;
				close = 0;
				expression.addAll(reduceExpression(index+1));
				
				while(i.hasNext()) {
					actual = i.next();
					
					if(actual.equals("(")) {
						open++;
					} else if(actual.equals(")")) {
						close++;
					}
					
					index++;
					
					if(close > open) {
						break;
					}
				}

			} else if(actual.equals(")")) {
				return reduceMinimalArithmetic(expression);
			} else {
				expression.add(actual);
			}
			
			index++;
		}
		
		
		return expression;
	}
	
	public static List<String> reduceMinimalArithmetic(List<String> expression) {
		if(expression.size() <= 1) {
			return expression;
		}
		
		System.out.println(expression + "kkkkk");
				
		Iterator<String> i = expression.iterator();
		LinkedList<String> result = new LinkedList<String>();
		String first = i.next(), actual;
		
		if(!(first.equals("int") || first.equals("float"))) {
			result.add(first);
		}

		while(i.hasNext()) {
			actual = i.next();
			
			if(first.equals("int") || first.equals("float")) {
				if(actual.equals("OPERADOR_ARITMETICO")) {
					actual = i.next();
					if(!actual.equals(first)) {
						System.out.println("TIpos incompativeis ari");
					}
					
					if(!i.hasNext()) {
						result.add(actual);						
					}
					first = actual; //????
				} else if(actual.equals("OPERADOR_RELACIONAL") || actual.equals("OPERADOR_LOGICO")) {
					result.add(first);
					result.add(actual);
					actual = i.next();
					if(!i.hasNext() || actual.equals("boolean")) {
						result.add(actual);
					}
					
					first = actual; //?????
				}
			} else {
				if(!(actual.equals("int") || actual.equals("float"))) {
					result.add(actual);		
				}
				first = actual;
			}
		}
		
		return reduceMinimalRelational(result);
	}
	
	public static List<String> reduceMinimalRelational(List<String> expression) {
		if(expression.size() == 1) {
			return expression;
		}
		System.out.println(expression + "dddd");
		Iterator<String> i = expression.iterator();
		LinkedList<String> result = new LinkedList<String>();
		String first = i.next(), actual = first;
		boolean flag = false;
		
		if(!(first.equals("int") || first.equals("float"))) {
			result.add(first);
		}
		
		while(i.hasNext()) {
			actual = i.next();
			
			if(first.equals("int") || first.equals("float")) {
				if(actual.equals("OPERADOR_RELACIONAL")) {
					actual = i.next();

					System.out.println("oi " + first + "  " + actual);
					if(!actual.equals(first)) {
						System.out.println("TIpos incompativeis rela");
					}

					if(!i.hasNext()) {
						result.add("boolean");						
					} 
					
					first = actual;
					flag = true;
				} else if(actual.equals("OPERADOR_LOGICO")) {
					if(flag) {
						result.add("boolean");
					} else {
						result.add(first);
					}
					
					result.add(actual);
					actual = i.next();
					if(!i.hasNext() || actual.equals("boolean")) {
						result.add(actual);
					} 
					
					first = actual;
					flag = false;
				}
			} else {
				if(!(actual.equals("int") || actual.equals("float"))) {
					result.add(actual);		
				}
				first = actual;
			}
		}
		System.out.println(result.toString() + "cccc");

		return reduceMinimalLogical(result);
	}
	
	public static List<String> reduceMinimalLogical(List<String> expression) {
		if(expression.size() == 1) {
			return expression;
		}
				
		Iterator<String> i = expression.iterator();
		LinkedList<String> result = new LinkedList<String>();
		String first = i.next(), actual = first;
		
		while(i.hasNext()) {
			actual = i.next();
			
			if(first.equals("boolean")) {
				if(actual.equals("OPERADOR_LOGICO")) {
					actual = i.next();
					if(!actual.equals(first)) {
						System.out.println("TIpos incompativeis logic");
					}
					
					if(!i.hasNext()) {
						result.add(actual);						
					}
				}
			}
		}
		
		return result;
	}

	/**
	 * 
	 * Verifica retorno do método com tipo da declaração
	 * 
	 * @param type
	 * @param forth
	 */
	public static void returnChecker(Token type, Token forth) {
		System.out.println("Tipo declarado " + type.getValue());
		System.out.println("Tipo retornado " + forth.getTokenClass());
		if( !(type.getValue().equals(forth.getTokenClass())) ) {
			System.out.println("Retorno incompatível com declaração");
			addSemanticError("Retorno incompatível com declaração");
		}
		
	}
	
	public static void firstPassing() {
		Iterator<SymbolClass> i = SemanticAnalyzer.table.iterator();
		SymbolClass symbolClass;
		int contClass = 0;
		
		while(i.hasNext()) {
			symbolClass = i.next();
			
			if(symbolClass.getHeritage() != null) {
				if(contClass == 0) {
					System.out.println("Classe utilizada em herança não foi declarada. Linha: " + symbolClass.getHeritage().getRow());
					addSemanticError("Classe utilizada em herança não foi declarada. Linha: " + symbolClass.getHeritage().getRow());
				} else {
					List<SymbolClass> subListClasses = table.subList(0, contClass);
					Iterator<SymbolClass> i2 = subListClasses.iterator();
					SymbolClass fatherSymbol;
					boolean found = false;
					
					while(i2.hasNext()) {
						fatherSymbol = i2.next();
						
						if(symbolClass.getHeritage().getValue().equals(fatherSymbol.getName().getValue())) {
							found = true;
							symbolClass.getAttributes().addAll(variablesHeritage(fatherSymbol.getAttributes(), symbolClass.getAttributes()));
							symbolClass.getMethods().addAll(methodsHeritage(fatherSymbol.getMethods(), symbolClass.getMethods()));
						}
					}
					
					if(!found) {
						addSemanticError("Classe utilizada em herança não foi declarada. Linha: " + symbolClass.getHeritage().getRow());
					}
					
				}
			}
			
			contClass++;
			
		}
	}
	
	private static List<SymbolVariable> variablesHeritage(List<SymbolVariable> father, List<SymbolVariable> son) {
		Iterator<SymbolVariable> i = son.iterator();
		List<SymbolVariable> result = new LinkedList<SymbolVariable>();
		List<String> fatherNames = getVariableNames(father);
		SymbolVariable symbol;
		
		while(i.hasNext()) {
			symbol = i.next();
			
			if(fatherNames.contains(symbol.getName().getValue())) {
				addSemanticError("Atributo já foi definido na classe pai. Linha: " + symbol.getName().getRow());
			} else {
				result.add(symbol);
			}
		}
		
		return result;
	}
	
	private static List<String> getVariableNames(List<SymbolVariable> list) {
		Iterator<SymbolVariable> i = list.iterator();
		List<String> result = new LinkedList<String>();
		
		while(i.hasNext()) {
			result.add(i.next().getName().getValue());
		}
		
		return result;
	}
	
	private static List<SymbolMethod> methodsHeritage(List<SymbolMethod> father, List<SymbolMethod> son) {
		Iterator<SymbolMethod> iS;
		Iterator<SymbolMethod> iF = father.iterator();
		List<SymbolMethod> result = new LinkedList<SymbolMethod>();
		SymbolMethod symbolF, symbolS;
		
		while(iF.hasNext()) {
			symbolF = iF.next();
			
			iS = son.iterator();
			
			while(iS.hasNext()) {
				symbolS = iS.next();
				
				if(symbolF.getName().getValue().equals(symbolS.getName().getValue())) {
					if(symbolF.getType().getValue().equals(symbolS.getType().getValue())) {
						if(symbolF.getParameters().equals(symbolS.getParameters())) {
							addSemanticError("Método já definido na classe pai. Linha: " + symbolS.getName().getRow());
							continue;
						}
					}
				}
				
				result.add(symbolF);
			}
		}
		
		return result;
	}
	

	
	
}
