package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class String_Const extends Expression {
  private final String String_const;
  public String_Const(Location left, Location right,String sc) {
    super(left, right);
    this.String_const = sc;
  }

  
  public String getString_const() {
    return String_const;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
