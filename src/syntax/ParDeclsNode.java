package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ParDeclsNode extends YasplNode {
  private final ParType parType;
  private final TypeNode type;
  private final IdentifierExpression varName;

  public ParDeclsNode(Location left, Location right, ParType parType, TypeNode type,
      IdentifierExpression varName) {
    super(left, right);
    this.parType = parType;
    this.type = type;
    this.varName = varName;
  }

  public ParType getParType() {
    return parType;
  }

  public TypeNode getType() {
    return type;
  }

  public IdentifierExpression getVarName() {
    return varName;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
