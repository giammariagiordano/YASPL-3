package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class DefDeclaration extends DeclarationNode {

  public DefDeclaration(Location left, Location right) {
    super(left, right);
  }
}
