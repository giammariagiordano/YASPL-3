package statOperation;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntax.Expression;
import syntax.StatementsNode;
import visitor.Visitor;
import otherOperationExpression.*;
public class AssignOperation extends StatementsNode {
  private final IdentiferExpression id;
  private final Expression expr;
  public AssignOperation(Location left, Location right,IdentiferExpression id,Expression expr) {
    super(left, right);
    this.id = id;
    this.expr = expr;
  }


  public Expression getExpr() {
    return expr;
  }


  public IdentiferExpression getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
