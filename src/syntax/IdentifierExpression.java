package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class IdentifierExpression extends Expression {
  private final String name;
  public IdentifierExpression(Location left, Location right,String id) {
    super(left, right);
    this.name = id;
  }

  public String getName() {
    return name;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
