package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class TypeNode extends YasplNode {
  final String typeName;

  public TypeNode(Location left, Location right, String typeName) {
    super(left, right);
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
