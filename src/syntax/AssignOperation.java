package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class AssignOperation extends StatementsNode {
  private final IdentifierExpression id;
  private final Expression expr;
  public AssignOperation(Location left, Location right,IdentifierExpression id,Expression expr) {
    super(left, right);
    this.id = id;
    this.expr = expr;
  }


  public Expression getExpr() {
    return expr;
  }


  public IdentifierExpression getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
