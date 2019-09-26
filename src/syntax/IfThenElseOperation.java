package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * It represents a node for if then else, for example: 
 * if(condition)
 *  then {
 * } else {
 }
 */
public class IfThenElseOperation extends StatementNode {
  private final Expression condition;
  private final CompStat thenCompStat, elseCompStat;

  public IfThenElseOperation(Location left, Location right, Expression condition,
      CompStat thenCompStat, CompStat elseCompStat) {
    super(left, right);
    this.condition = condition;
    this.thenCompStat = thenCompStat;
    this.elseCompStat = elseCompStat;
  }

  public Expression getCondition() {
    return condition;
  }

  public CompStat getThenCompStat() {
    return thenCompStat;
  }

  public CompStat getElseCompStat() {
    return elseCompStat;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
