package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class VarInitValueId extends YasplNode {
  private final IdentifierExpression id;
  private final VarInitValue varInitValue;
  public VarInitValueId(Location left, Location right,IdentifierExpression id,VarInitValue value) {
    super(left, right);
    this.id = id;
    this.varInitValue = value;
  }

  
  public VarInitValueId(Location idxleft, Location idxright, String id, VarInitValue viv) {
    super(idxleft,idxright);
    this.id = new IdentifierExpression(idxleft, idxright, id);
    this.varInitValue = viv;
  }


  public IdentifierExpression getId() {
    return id;
  }


  public VarInitValue getVarInitValue() {
    return varInitValue;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
