package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class VarDeclaration extends Declarations {
  private final  TypeNode type;
  private final List<VarInitValueId> variables;
  public VarDeclaration(Location left, Location right,TypeNode type, List<VarInitValueId> variables) {
    super(left, right);
    this.type = type;
    Collections.reverse(variables);
    this.variables = variables;
  }

 

  public List<VarInitValueId> getVariables() {
    return variables;
  }


  public TypeNode getType() {
    return type;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
