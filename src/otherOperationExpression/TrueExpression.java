package otherOperationExpression;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntax.Expression;
import visitor.Visitor;

public class TrueExpression extends Expression {

  public TrueExpression(Location left, Location right) {
    super(left, right);
    // TODO Auto-generated constructor stub
  }
  public boolean getTrue() {
    return true;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
