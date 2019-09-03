package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class IntConst extends Expression implements iConst<Integer>{
  private final int intConst;

  public IntConst(Location left, Location right, int intConst) {
    super(left, right);
    this.intConst = intConst;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  @Override
  public Integer getValue() {
    return intConst;
  }
}
