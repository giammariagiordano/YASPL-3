package syntax;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ReadOperation extends StatementsNode {
private final Vars vars;
  public ReadOperation(Location left, Location right, Vars vars) {
    super(left, right);
    this.vars = vars;
  }

  public Vars getVars() {
    return vars;
  }

  public ReadOperation(Location varsxleft, Location varsxright,
      ArrayList<IdentifierExpression> vars2) {
    super(varsxleft,varsxright);
    this.vars = new Vars(varsxleft,varsxright,vars2);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
