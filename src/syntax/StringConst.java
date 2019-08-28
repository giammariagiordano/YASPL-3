package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class StringConst extends Expression {
  private final String stringConst;

  public StringConst(Location left, Location right, String stringConst) {
    super(left, right);
    this.stringConst = stringConst;
  }

  public String getStringConst() {
    return stringConst;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
