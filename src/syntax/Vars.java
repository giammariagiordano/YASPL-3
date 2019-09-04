package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class Vars extends YasplNode {
  private List<IdentifierExpression> varsNames;

  public Vars(Location left, Location right, List<IdentifierExpression> varsNames) {
    super(left, right);
    Collections.reverse(varsNames);
    this.varsNames = varsNames;
  }

  public List<IdentifierExpression> getVarsNames() {
    return varsNames;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
