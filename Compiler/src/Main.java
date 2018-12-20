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
import model.semantic.Expression;
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
			TokensFlow.setTokensSet(lexer.getTokens());
			Expression expression = new Expression();
			Analyzer.analiseGlobal(expression);
			FileController.saveSyntacticResults(fileName);
		}
		
		
		
		
		
		
		System.out.println("Análise Sintatica Concluída!");
		
	}
	
}