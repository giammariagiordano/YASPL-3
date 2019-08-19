package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ListParams extends YasplNode {
  private final ParType parType;
  private final TypeNode type;
  private final IdentifierExpression id;
  public ListParams(Location left, Location right,ParType parType, TypeNode type, IdentifierExpression id) {
    super(left, right);
    this.parType =  parType;
    this.type = type;
    this.id = id;
  }

  public ParType getParType() {
    return parType;
  }

  public TypeNode getType() {
    return type;
  }

  public IdentifierExpression getId() {
    return id;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
