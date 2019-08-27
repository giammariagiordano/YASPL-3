package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class CompStat extends StatementsNode {
  private final List<StatementsNode> statements;
  public CompStat(Location left, Location right,List<StatementsNode> statements) {
    super(left, right);
   this.statements = statements;
  }

  
  public List<StatementsNode> getStatements() {
    return statements;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
