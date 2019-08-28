package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class DoubleConst extends Expression {
  final double doubleConst;

  public DoubleConst(Location left, Location right, double doubleConst) {
    super(left, right);
    this.doubleConst = doubleConst;
  }

  public double getDoubleConst() {
    return doubleConst;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
