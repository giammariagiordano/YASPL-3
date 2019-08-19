package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class Char_Const extends Expression {
  char char_const;
  public Char_Const(Location left, Location right,char char_const) {
    super(left, right);
    this.char_const = char_const;
  }

  public char getChar_const() {
    return char_const;
  }

  public void setChar_const(char char_const) {
    this.char_const = char_const;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
