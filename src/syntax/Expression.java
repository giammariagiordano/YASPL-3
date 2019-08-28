package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class Expression extends YasplNode {

  public Expression(Location left, Location right) {
    super(left, right);
  }
}
