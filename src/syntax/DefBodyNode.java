package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class DefBodyNode extends StatementNode {
  private final BodyNode body;

  public DefBodyNode(Location left, Location right, BodyNode body) {
    super(left, right);
    this.body = body;
  }

  public DefBodyNode(Location left, Location right) {
    super(left,right);
    this.body=null;
  }

  public BodyNode getBody() {
    return body;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
