package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class CharConst extends Expression {
  char charConst;

  public CharConst(Location left, Location right, char charConst) {
    super(left, right);
    this.charConst = charConst;
  }

  public char getCharConst() {
    return charConst;
  }

  public void setCharConst(char charConst) {
    this.charConst = charConst;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
