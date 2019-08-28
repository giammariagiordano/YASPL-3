package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class AssignOperation extends StatementNode {
  private final IdentifierExpression varName;
  private final Expression expr;

  public AssignOperation(Location left, Location right, IdentifierExpression varName,
      Expression expr) {
    super(left, right);
    this.varName = varName;
    this.expr = expr;
  }

  public Expression getExpr() {
    return expr;
  }

  public IdentifierExpression getVarName() {
    return varName;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
