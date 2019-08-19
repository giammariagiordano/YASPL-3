package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class CallWithoutParam extends StatementsNode {
  private final IdentifierExpression id;
  public CallWithoutParam(Location left, Location right, IdentifierExpression id) {
    super(left, right);
    this.id = id;
  }
  public CallWithoutParam(Location left, Location right, String id) {
    super(left, right);
    this.id = new IdentifierExpression(left, right, id);
  }

  public IdentifierExpression getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
