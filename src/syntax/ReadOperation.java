package syntax;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.*;

public class ReadOperation extends StatementNode {
  private final Vars vars;

  public ReadOperation(Location left, Location right, Vars vars) {
    super(left, right);
    this.vars = vars;
  }

  public Vars getVars() {
    return vars;
  }

  public ReadOperation(Location varsxleft, Location varsxright,
      ArrayList<IdentifierExpression> vars) {
    super(varsxleft, varsxright);
    this.vars = new Vars(varsxleft, varsxright, vars);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  public String variableDomain() {
    return this.vars.getVarsNames().stream().map(v -> v.getNodeType().getValue())
        .collect(Collectors.joining("X"));
  }
}
