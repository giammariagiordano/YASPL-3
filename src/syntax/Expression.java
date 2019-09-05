package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

/*
 * This is an abstract class for expressions, as ArithOp, BoolOp, RelOp, Uminus, Not, etc
 */
public abstract class Expression extends YasplNode {

  public Expression(Location left, Location right) {
    super(left, right);
  }
}
