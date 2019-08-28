package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class DeclarationNode extends YasplNode {

  public DeclarationNode(Location left, Location right) {
    super(left, right);

  }

}
