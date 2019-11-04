package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * This class create a name for identifier
 */
public class IdentifierExpression extends Expression implements iConst<String> {
  private final String name;

  public IdentifierExpression(Location left, Location right, String name) {
    super(left, right);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  @Override
  public String getValue() {
    return name;
  }
}
