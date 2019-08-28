package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class VarDeclaration extends DeclarationNode {
  private final TypeNode typeNode;
  private final List<VarInitValueId> variables;

  public VarDeclaration(Location left, Location right, TypeNode typeNode,
      List<VarInitValueId> variables) {
    super(left, right);
    this.typeNode = typeNode;
    Collections.reverse(variables);
    this.variables = variables;
  }

  public List<VarInitValueId> getVariables() {
    return variables;
  }

  public TypeNode getTypeNode() {
    return typeNode;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
