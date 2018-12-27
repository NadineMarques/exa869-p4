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
	
	
	public static void arrayIndexVerifier(int row) {
		List<String> reduced = Expressions.reduce(row);

		if(reduced.size()  == 1) {
			if(!reduced.get(0).equals("ERROR")) {
				if(!(reduced.get(0).equals("int"))) {
					SemanticAnalyzer.addSemanticError("Expressão com resultado inesperado em índice de array. Esperado: int. Obtido: " + reduced.get(0), row);
				}
			}
		} else {
			SemanticAnalyzer.addSemanticError("Expressão com resultado inesperado em índice de array.", row);
		}
	}
	
	public static void conditionVerifier(int row, String command) {
		List<String> reduced = Expressions.reduce(row);

		if(reduced.size()  == 1) {
			if(!reduced.get(0).equals("ERROR")) {
				if(!(reduced.get(0).equals("boolean"))) {
					SemanticAnalyzer.addSemanticError("Expressão com resultado inesperado em condição de comando " + command +". Esperado: boolean. Obtido: " + reduced.get(0), row);
				}
			}
		} else {
			SemanticAnalyzer.addSemanticError("Expressão com resultado inesperado em condição de comando" + command + ".", row);
		}
	}
	
	public static void methodVerifier() {
		SymbolMethod method = SemanticAnalyzer.table.getLast().getMethods().removeLast();
		Iterator<SymbolMethod> i = SemanticAnalyzer.table.getLast().getMethods().iterator();
		SymbolMethod temp;
		boolean conflict = false;
		
		while(i.hasNext()) {
			temp = i.next();
			
			if(method.getName().getValue().equals(temp.getName().getValue())) {
				if(method.getType().getValue().equals(temp.getType().getValue())) {
					if(method.getParameters().equals(temp.getParameters())) {
						conflict = true;
						System.out.println("O método " + method.getName().getValue() + " já foi declarado anteriormente." + method.getName().getRow());
						SemanticAnalyzer.addSemanticError("O método " + method.getName().getValue() + " já foi declarado anteriormente.", method.getName().getRow());
					}
					
				}
			}
		}
		
		if(!conflict) {
			SemanticAnalyzer.table.getLast().getMethods().add(method);
		}
		
	}
	
	public static void addSemanticError(String erro, int linha) {
		semanticErrors.add("Erro semântico " + erro + " na linha " + linha);
		System.out.println("Erro semântico " + erro + " na linha " + linha);
	}
	
	public static void atribuitionChecker(SymbolConstant constante) {
		if(constante.getType().getValue().equals("int")) {
			if(constante.getValue().getTokenClass().equals("NUMERO") && (!constante.getValue().getValue().contains(".")) ) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", constante.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(constante.getType().getValue().equals("float")) {
			if(constante.getValue().getTokenClass().equals("NUMERO")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", constante.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(constante.getType().getValue().equals("bool")) {
			if(constante.getValue().getValue().equals("true") || constante.getValue().getValue().equals("false")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", constante.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(constante.getType().getValue().equals("string")) {
			if(constante.getValue().getTokenClass().equals("CADEIA_DE_CARACTERES")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", constante.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
		}
	}
	
	
	public static void atribuitionChecker(SymbolVariable variavel) {
		if(variavel.getType().getValue().equals("int")) {
			if(variavel.getValue().getTokenClass().equals("NUMERO") && (!variavel.getValue().getValue().contains(".")) ) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", TokensFlow.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(variavel.getType().getValue().equals("float")) {
			if(variavel.getValue().getTokenClass().equals("NUMERO")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", TokensFlow.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(variavel.getType().getValue().equals("bool")) {
			if(variavel.getValue().getValue().equals("true") || variavel.getValue().getValue().equals("true")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", TokensFlow.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
			
		} else if(variavel.getType().getValue().equals("string")) {
			if(variavel.getValue().getTokenClass().equals("CADEIA_DE_CARACTERES")) {
				return;
			} else {
				addSemanticError("Incompatibilidade de Tipos", TokensFlow.getToken().getRow());				
				System.out.println("Incompatibilidade de Tipos");
			}
		}
	}
	
	
	public static void checkConstantOrVariable(Token token) {
		boolean isDeclared = false;
		
		for(int i=0;i<Analyzer.symbolTable.size();i++) {
			
			
			if((Analyzer.symbolTable.get(i).getToken().getValue().equals(token.getValue()))) {
				System.out.println("Tentando atribuir valor a uma constante");
				addSemanticError("Atribuição de valor em constante", TokensFlow.getToken().getRow());
				isDeclared = true;
			}
			
			if((Analyzer.symbolTable.get(i)).getToken().getValue().equals(token.getValue())) {				
				isDeclared = true;
			}
		
		}
		
		if(!isDeclared) {
			System.out.println("Tentando atribuir a uma variável não declarada");
			addSemanticError("Atribuição em variável não declarada", TokensFlow.getToken().getRow());
		}
	}
	
	
	public static List<String> reduceExpression(int index, int row) {
		Iterator<String> i = Expressions.list.subList(index, Expressions.list.size()).iterator();
		List<String> expression = new LinkedList<String>();
		String actual;
		int open = 0, close = 0;

		while(i.hasNext()) {
			actual = i.next();

			if(actual.equals("(")) {
				open = 0;
				close = 0;
				expression.addAll(reduceExpression(index+1, row));
				
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
				return reduceMinimalArithmetic(expression, row);
			} else {
				expression.add(actual);
			}
			
			index++;
		}
		
		
		return expression;
	}
	
	public static List<String> reduceMinimalArithmetic(List<String> expression, int row) {
		if(expression.size() <= 1) {
			return expression;
		}
				
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
						SemanticAnalyzer.addSemanticError("Tipos incompatíveis em Operação Aritmética.", row);
						List<String> list = new LinkedList<String>();
						list.add("ERROR");
						return list;
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
		
		return reduceMinimalRelational(result, row);
	}
	
	public static List<String> reduceMinimalRelational(List<String> expression, int row) {
		if(expression.size() == 1) {
			return expression;
		}
		
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
					if(!actual.equals(first)) {
						SemanticAnalyzer.addSemanticError("Tipos incompatíveis em Operação Relacional.", row);
						List<String> list = new LinkedList<String>();
						list.add("ERROR");
						return list;
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

		return reduceMinimalLogical(result, row);
	}
	
	public static List<String> reduceMinimalLogical(List<String> expression, int row) {
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
						SemanticAnalyzer.addSemanticError("Tipos incompatíveis em Operação Lógica.", row);
						List<String> list = new LinkedList<String>();
						list.add("ERROR");
						return list;
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
	public static void methodReturnChecker(Token type, Token forth, String className) {
		
		System.out.println("Tipo declarado " + type.getValue());
		System.out.println("Valor retornado " + forth.getValue());
		
		if(forth.getTokenClass().equals("NUMERO")) {	
			if(forth.getValue().contains(".")) {
				if(type.getValue().equals("float")) {
					return;
				} else {
					System.out.println("Retorno incompatível com declaração");
					addSemanticError("Retorno incompatível com declaração", forth.getRow());
					return;
				}
			} else {
				if(type.getValue().equals("int")) {
					return;
				} else {
					System.out.println("Retorno incompatível com declaração");
					addSemanticError("Retorno incompatível com declaração", forth.getRow());
					return;
				}
			}
		}
		
		if(forth.getTokenClass().equals("CADEIA_DE_CARACTERES")) {	
			if(type.getValue().equals("string")) {
					return;
			
			} else {
					System.out.println("Retorno incompatível com declaração");
					addSemanticError("Retorno incompatível com declaração", forth.getRow());
					return;
			}
			
		}

		if(forth.getValue().equals("true") || forth.getValue().equals("false")) {	
			if(type.getValue().equals("bool")) {
				return;
			
			} else {
				System.out.println("Retorno incompatível com declaração");
				addSemanticError("Retorno incompatível com declaração", forth.getRow());
				return;
			}
			
		}
		
		
		if(forth.getTokenClass().equals("IDENTIFICADOR")) {
			//verificar os tipos na tabela
			System.out.println("CLASSNAME " + className);
			return;
		}
		
		
	}
	
	public static void passingClass() {
		SymbolClass symbolClass = SemanticAnalyzer.table.getLast();
		
		if(symbolClass.getHeritage() != null) {
			if(SemanticAnalyzer.table.size() == 1) {
				addSemanticError("Classe utilizada em herança não foi declarada.", symbolClass.getHeritage().getRow());
			} else {
				Iterator<SymbolClass> i = SemanticAnalyzer.table.iterator();
				SymbolClass fatherSymbol;
				boolean found = false;
				
				while(i.hasNext()) {
					fatherSymbol = i.next();
					
					if(symbolClass.getHeritage().getValue().equals(fatherSymbol.getName().getValue())) {
						found = true;
						symbolClass.getAttributes().addAll(variablesHeritage(fatherSymbol.getAttributes(), symbolClass.getAttributes()));
						symbolClass.getMethods().addAll(methodsHeritage(fatherSymbol.getMethods(), symbolClass.getMethods()));
					}
				}
				
				if(!found) {
					addSemanticError("Classe utilizada em herança não foi declarada.", symbolClass.getHeritage().getRow());
				}
			}
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
				addSemanticError("Atributo já foi definido na classe pai.", symbol.getName().getRow());
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
							addSemanticError("Método já definido na classe pai.", symbolS.getName().getRow());
							continue;
						}
					}
				}
				
				result.add(symbolF);
			}
		}
		
		return result;
	}

	/**
	 * 
	 * Erro se não há retorno no método
	 * 
	 * @param type
	 */
	public static void returnRequiredChecker(Token type) {
		System.out.println("Tipo do return required checker " + type.getValue());
		System.out.println("IsThereReturn " + Analyzer.thereIsReturn);
		if(!(type.getValue().equals("void")) && (!(Analyzer.thereIsReturn)) ) {
			System.out.println("Retorno esperado do tipo " + type.getValue());
			addSemanticError("Retorno esperado do tipo " + type.getValue(), TokensFlow.back().getRow());
			return;
		}
		
		Analyzer.thereIsReturn = false;
	}
	

	
	
}
