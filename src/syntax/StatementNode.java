package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class StatementNode extends YasplNode {

  public StatementNode(Location left, Location right) {
    super(left, right);
  }
}
