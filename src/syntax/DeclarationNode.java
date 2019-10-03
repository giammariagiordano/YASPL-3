package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
/*
 * This is an abstract class for declarations.
 * 
 * It's extended by VarDecls and DefDeclarations
 */

public abstract class DeclarationNode extends YasplNode {

  public DeclarationNode(Location left, Location right) {
    super(left, right);

  }

}
