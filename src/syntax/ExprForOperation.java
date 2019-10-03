package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ExprForOperation extends Expression {
  final Expression expr;

  public ExprForOperation(Location left, Location right, Expression expr) {
    super(left, right);
    this.expr = expr;
  }

  public Expression getExpr() {
    return expr;
  }

  public ExprForOperation(Location left, Location right) {
    super(left, right);
    this.expr = null;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
