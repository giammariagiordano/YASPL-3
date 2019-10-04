package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ArrayDeclaration extends VarInitValueId {
  public ArrayDeclaration(Location left, Location right, IdentifierExpression varName) {
    super(left, right, varName, null);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
