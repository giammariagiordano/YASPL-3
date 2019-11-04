package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;
/*
 * this class add "not". For example: not(a==b);
 */

public class NotExpression extends Expression {
  private final Expression expr;

  public NotExpression(Location left, Location right, Expression expr) {
    super(left, right);
    this.expr = expr;
  }

  public Expression getExpr() {
    return expr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
