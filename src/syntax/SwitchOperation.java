package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class SwitchOperation extends StatementNode {
  private final Expression expr;
  private final List<SwitchBodyNode> switchBody;
  private final DefBodyNode defBody;

  public SwitchOperation(Location left, Location right, Expression expr, List<SwitchBodyNode> switchBody, DefBodyNode defBody) {
    super(left, right);
    this.expr = expr;
    Collections.reverse(switchBody);
    this.switchBody = switchBody;
    this.defBody = defBody;
  }
  

  public Expression getExpr() {
    return expr;
  }

  public List<SwitchBodyNode> getSwitchBody() {
    return switchBody;
  }

  public DefBodyNode getDefBody() {
    return defBody;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
