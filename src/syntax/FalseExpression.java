package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * This class create a constant value false
 */
public class FalseExpression extends Expression implements iConst<Boolean> {

  public FalseExpression(Location left, Location right) {
    super(left, right);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  @Override
  public Boolean getValue() {
    return false;
  }
}
