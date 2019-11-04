package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * It represents a node for while operation, for example: while(condition) { }
 */
public class WhileOperation extends StatementNode {
  private final Expression condition;
  private final CompStat whileCompStat;

  public WhileOperation(Location left, Location right, Expression condition,
      CompStat whileCompStat) {
    super(left, right);
    this.condition = condition;
    this.whileCompStat = whileCompStat;
  }

  public Expression getCondition() {
    return condition;
  }

  public CompStat getWhileCompStat() {
    return whileCompStat;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
