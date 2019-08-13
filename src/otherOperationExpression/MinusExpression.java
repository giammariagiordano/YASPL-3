package otherOperationExpression;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntax.Expression;
import visitor.Visitor;

public class MinusExpression extends Expression {
  private final Expression expr;
  public MinusExpression(Location left, Location right,Expression expr) {
    super(left, right);
    this.expr = expr;
  }

  public Expression getExpr() {
    return expr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this,param);
  }

}
