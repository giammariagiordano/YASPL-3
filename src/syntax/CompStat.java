package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * It represents a list of statements for while node or ifThen node or ifThenElse node
 */
public class CompStat extends StatementNode {
  private final List<StatementNode> statementsNode;

  public CompStat(Location left, Location right, List<StatementNode> statementsNode) {
    super(left, right);
    Collections.reverse(statementsNode);
    this.statementsNode = statementsNode;
  }

  public List<StatementNode> getStatementsNode() {
    return statementsNode;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
