package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class TrueExpression extends Expression {

  public TrueExpression(Location left, Location right) {
    super(left, right);
  }

  public boolean getTrue() {
    return true;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
