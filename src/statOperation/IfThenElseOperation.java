package statOperation;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntax.Expression;
import syntax.StatementsNode;
import visitor.Visitor;

public class IfThenElseOperation extends StatementsNode {
  private final Expression expr;
  private final CompStat thenCompState,elseCompStat;
  public IfThenElseOperation(Location left, Location right,Expression expr,CompStat csThen,CompStat  csElse) {
    super(left, right);
    this.expr = expr;
    this.thenCompState = csThen;
    this.elseCompStat =  csElse;
  }

  public Expression getExpr() {
    return expr;
  }

  public CompStat getThenCompState() {
    return thenCompState;
  }

  public CompStat getElseCompStat() {
    return elseCompStat;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
