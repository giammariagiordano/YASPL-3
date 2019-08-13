package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import otherOperationExpression.IdentiferExpression;
import visitor.Visitor;

public class VarInitValueId extends YasplNode {
  private final IdentiferExpression ids;
  private final VarInitValue varsInitValue;
  public VarInitValueId(Location left, Location right,IdentiferExpression ids,VarInitValue value) {
    super(left, right);
    this.ids = ids;
    this.varsInitValue = value;
  }

  
  public VarInitValueId(Location idxleft, Location idxright, String id, VarInitValue viv) {
    super(idxleft,idxright);
    this.ids = new IdentiferExpression(idxleft, idxright, id);
    this.varsInitValue = viv;
  }


  public IdentiferExpression getIds() {
    return ids;
  }


  public VarInitValue getVarsInitValue() {
    return varsInitValue;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
