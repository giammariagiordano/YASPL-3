package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class VarInitValueId extends YasplNode {
  private final IdentifierExpression varName;
  private final VarInitValue initialValue;

  public VarInitValueId(Location left, Location right, IdentifierExpression varName,
      VarInitValue initialValue) {
    super(left, right);
    this.varName = varName;
    this.initialValue = initialValue;
  }


  public VarInitValueId(Location varNamexleft, Location varNamexright, String varName,
      VarInitValue initialValue) {
    super(varNamexleft, varNamexright);
    this.varName = new IdentifierExpression(varNamexleft, varNamexright, varName);
    this.initialValue = initialValue;
  }

  public IdentifierExpression getVarName() {
    return varName;
  }

  public VarInitValue getInitialValue() {
    return initialValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
