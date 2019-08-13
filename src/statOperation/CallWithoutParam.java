package statOperation;

import java_cup.runtime.ComplexSymbolFactory.Location;
import otherOperationExpression.IdentiferExpression;
import syntax.StatementsNode;
import visitor.Visitor;

public class CallWithoutParam extends StatementsNode {
  private final IdentiferExpression id;
  public CallWithoutParam(Location left, Location right, IdentiferExpression id) {
    super(left, right);
    this.id = id;
  }
  public CallWithoutParam(Location left, Location right, String id) {
    super(left, right);
    this.id = new IdentiferExpression(left, right, id);
  }

  public IdentiferExpression getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
