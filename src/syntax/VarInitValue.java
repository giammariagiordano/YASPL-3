package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * Represent an initial value of variable. For example int a=1;
 */

public class VarInitValue extends YasplNode {
  private final Expression expr;

  /*
   * Create a new variable
   * 
   * @param left for left location
   * 
   * @param right for right location
   * 
   * @param expr for initial value of a variable
   */
  public VarInitValue(Location left, Location right, Expression expr) {
    super(left, right);
    this.expr = expr;
  }

  public VarInitValue(Location left, Location right) {
    super(left, right);
    this.expr = null;
  }

  /*
   * Get expression for a variable
   * 
   * @return initial value of a variable
   */

  public Expression getExpr() {
    return expr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
