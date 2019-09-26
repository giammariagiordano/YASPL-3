package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class DoWhileOperation extends StatementNode {
  
  private final BodyNode body;
  private final Expression condition;

  public DoWhileOperation(Location left, Location right, BodyNode body, Expression condition) {
    super(left, right);
    this.body = body;
    this.condition = condition;
  }

  public BodyNode getBody() {
    return body;
  }

  public Expression getCondition() {
    return condition;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
