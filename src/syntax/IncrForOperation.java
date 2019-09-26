package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class IncrForOperation extends YasplNode {
  final IdentifierExpression id;
  final Expression expr;
  public IncrForOperation(Location left, Location right,IdentifierExpression id, Expression expr) {
    super(left, right);
    this.id = id;
    this.expr = expr;
  }

  public IncrForOperation(Location left, Location right) {
    super(left, right);
    this.id = null;
    this.expr = null;
  }

  public IdentifierExpression getId() {
    return id;
  }


  public Expression getExpr() {
    return expr;
  }


  @Override
   public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return  visitor.visit(this, param);
  }

}
