package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class FalseExpression extends Expression {

  public FalseExpression(Location left, Location right) {
    super(left, right);
  }

  public boolean getFalse() {
    return false;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
