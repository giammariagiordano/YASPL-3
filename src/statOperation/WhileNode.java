package statOperation;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntax.Expression;
import syntax.StatementsNode;
import visitor.Visitor;

public class WhileNode extends StatementsNode {
  private final Expression expr;
  private final CompStat cs;
  public WhileNode(Location left, Location right,Expression expr, CompStat cs) {
    super(left, right);
    this.expr =  expr;
    this.cs = cs;
  }

  
  public Expression getExpr() {
    return expr;
  }


  public CompStat getCs() {
    return cs;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
