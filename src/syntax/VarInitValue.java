package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class VarInitValue extends YasplNode {
  private final Expression expr;

  public VarInitValue(Location left, Location right, Expression expr) {
    super(left, right);
    this.expr = expr;
  }


  public VarInitValue(Location left, Location right) {
   super(left,right);
   this.expr = null;
   }


  public Expression getExpr() {
    return expr;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }


}
