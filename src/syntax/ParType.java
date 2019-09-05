package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * @return a string for partype of variables (in, out, inout)
 */
public class ParType extends YasplNode {
  final String type;

  public ParType(Location left, Location right, String type) {
    super(left, right);
    this.type = type;
  }

  public String getType() {
    return type;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
