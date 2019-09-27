package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class SwitchBodyNode extends StatementNode {
  private final Expression expr;
  private final BodyNode body;

  public SwitchBodyNode(Location left, Location right, Expression expr, BodyNode body ) {
    super(left, right);
    this.expr=expr;
    this.body=body;
  }

  public Expression getExpr() {
    return expr;
  }



  public BodyNode getBody() {
    return body;
  }



  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
