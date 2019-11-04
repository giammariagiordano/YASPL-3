package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;
/*
 * Represent a declaration of variable with an initial value. For example int a = 1;
 */

public class VarInitValueId extends YasplNode {
  private final IdentifierExpression varName;
  private final VarInitValue initialValue;

  /*
   * Create a new variable
   * 
   * @param left for left location
   * 
   * @param right for right location
   * 
   * @param varName for name of variable
   * 
   * @param initialValue for first value
   */
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

  /*
   * Get variable name
   * 
   * @return name of variable
   */
  public IdentifierExpression getVarName() {
    return varName;
  }

  /*
   * Get initial value
   * 
   * @return initial value of variable
   */
  public VarInitValue getInitialValue() {
    return initialValue;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
