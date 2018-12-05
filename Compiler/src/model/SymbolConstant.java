/**
 * 
 */
package model;

/**
 *
 *Classe que representa os símbolos das constantes na tabela de símbolos
 *
 * @author Nadine Cerqueira
 * @author Valmir Vinicius
 *
 */
public class SymbolConstant extends Symbol{
	private String type;
	private float value; //coloca qual tipo para o valor da constante já que pode ser de vários tipos?
	
	public SymbolConstant(Token token) {
		super(token);
	}
	
}
