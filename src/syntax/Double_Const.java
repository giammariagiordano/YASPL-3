package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class Double_Const extends Expression {
  final double doubleNumber;
  public Double_Const(Location left, Location right,double dn) {
    super(left, right);
    this.doubleNumber = dn;
  }

  
  public double getDoubleNumber() {
    return doubleNumber;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
