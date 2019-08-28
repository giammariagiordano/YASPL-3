package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ParType extends YasplNode {
  final String parType;

  public ParType(Location left, Location right, String parType) {
    super(left, right);
    this.parType = parType;
  }

  public String getParType() {
    return parType;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
