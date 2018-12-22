import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import controller.Analyzer;
import controller.FileController;
import controller.SemanticAnalyzer;
import model.Token;
import model.TokensFlow;
import model.Util;
import model.semantic.Expressions;
import model.semantic.Symbol;
import model.semantic.SymbolConstant;

public class Main {


	public static void main(String[] args) throws IOException {		
		Map<String, String> sourceFiles = FileController.readFiles();
		Iterator<String> iSource = sourceFiles.keySet().iterator();
		
		while(iSource.hasNext()) {
			Analyzer.symbolTable = new LinkedList<Symbol>();
			Util.errors = new LinkedList<String>();
			String fileName = iSource.next();
			String sourceCode = sourceFiles.get(fileName);
			
			Lexer lexer = new Lexer();
			lexer.initialize(sourceCode);
			
			List<Token> tokens = lexer.getTokens();
			List<Token> tokensUpdated = new LinkedList<Token>();
			tokensUpdated.addAll(tokens);
			
			Iterator<Token> i = tokens.iterator();
			Token token;
			int index = 0;
			
			while(i.hasNext()) {
				token = i.next();
				
				if(token.getTokenClass().equals("NUMERO") && token.getValue().contains("-")) {
					String number = token.getValue().replace("-", "").trim();
					tokensUpdated.remove(index);
					tokensUpdated.add(index, new Token("OPERADOR_ARITMETICO", "-", token.getRow()));
					tokensUpdated.add(index+1, new Token("NUMERO", number, token.getRow()));
				} 
				
				index++;
			}
			
			
			System.out.println(tokensUpdated);
			
			TokensFlow.setTokensSet(tokensUpdated);
			
			Analyzer.analiseGlobal();
			//System.out.println(Expressions.list.toString());
			Expressions.list.add(0, "(");
			Expressions.list.add(")");
			System.out.println(Expressions.list);
			System.out.println(SemanticAnalyzer.reduceExpression(0));
			SemanticAnalyzer.firstPassing();
			System.out.println("\n\n\n\n\n\n\n\n\n");
			System.out.println(SemanticAnalyzer.table);
			System.out.println(SemanticAnalyzer.table.toString());

			FileController.saveSyntacticResults(fileName);
		}
		
		
		
		
		
		System.out.println("Análise Sintatica Concluída!");
		
	}
	
}