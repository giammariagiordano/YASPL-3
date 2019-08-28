package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class IfThenOperation extends StatementNode {
  private final Expression condition;
  private final CompStat thenCompStat;

  public IfThenOperation(Location left, Location right, Expression condition,
      CompStat thenCompStat) {
    super(left, right);
    this.condition = condition;
    this.thenCompStat = thenCompStat;
  }


  public Expression getCondition() {
    return condition;
  }

  public CompStat getThenCompStat() {
    return thenCompStat;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
