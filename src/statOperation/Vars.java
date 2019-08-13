package statOperation;

import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import otherOperationExpression.IdentiferExpression;
import syntax.YasplNode;
import visitor.Visitor;

public class Vars extends YasplNode {
 private List<IdentiferExpression> ids;
  public Vars(Location left, Location right, List<IdentiferExpression> ids) {
    super(left, right);
    this.ids = ids;
   }

  public List<IdentiferExpression> getIds() {
    return ids;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
