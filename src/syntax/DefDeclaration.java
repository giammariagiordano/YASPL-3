package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
/*
 * This is an abstract class for DefDeclarations.
 * 
 * It's extended by DefFunctionWithoutParamsOperation and DefFunctionWithParamOperation
 */


public abstract class DefDeclaration extends DeclarationNode {

  public DefDeclaration(Location left, Location right) {
    super(left, right);
  }
}
