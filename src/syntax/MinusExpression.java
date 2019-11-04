package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * This class add "-" sign. For example a=5 => a=-5
 */
public class MinusExpression extends Expression {
  private final Expression expr;

  /*
   * create a new minus expression
   * 
   * @param left for left location
   * 
   * @param right for right location
   * 
   * @param expr for expression
   */
  public MinusExpression(Location left, Location right, Expression expr) {
    super(left, right);
    this.expr = expr;
  }

  public Expression getExpr() {
    return expr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
