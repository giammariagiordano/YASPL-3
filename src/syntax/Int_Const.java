package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class Int_Const extends Expression {
  private final int intConst;
  public Int_Const(Location left, Location right,int intConst) {
    super(left, right);
    this.intConst = intConst;
  }

  
  public int getIntConst() {
    return intConst;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
