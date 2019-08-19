package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class VarInitValueId extends YasplNode {
  private final IdentifierExpression ids;
  private final VarInitValue varsInitValue;
  public VarInitValueId(Location left, Location right,IdentifierExpression ids,VarInitValue value) {
    super(left, right);
    this.ids = ids;
    this.varsInitValue = value;
  }

  
  public VarInitValueId(Location idxleft, Location idxright, String id, VarInitValue viv) {
    super(idxleft,idxright);
    this.ids = new IdentifierExpression(idxleft, idxright, id);
    this.varsInitValue = viv;
  }


  public IdentifierExpression getIds() {
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
