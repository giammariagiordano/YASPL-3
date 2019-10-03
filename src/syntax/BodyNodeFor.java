package syntax;

import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class BodyNodeFor extends BodyNode {


  public BodyNodeFor(Location left, Location right, List<VarDeclaration> vd,
      List<StatementNode> statements) {
    super(left, right, vd, statements);

  }

  public BodyNodeFor(Location left, Location right, List<StatementNode> statementsNode) {
    super(left, right, statementsNode);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
