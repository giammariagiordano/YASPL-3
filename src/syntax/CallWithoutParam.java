package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.ReturnType;
import visitor.Visitor;

public class CallWithoutParam extends StatementsNode {
  private final IdentifierExpression functionName;
  public CallWithoutParam(Location left, Location right, IdentifierExpression functionName) {
    super(left, right);
    this.functionName = functionName;
  }
  public CallWithoutParam(Location left, Location right, String id) {
    super(left, right);
    this.functionName = new IdentifierExpression(left, right, id);
  }

  public IdentifierExpression getFunctionName() {
    return functionName;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
  public String getDomain() {
    return ReturnType.VOID.getValue();
  }
}
