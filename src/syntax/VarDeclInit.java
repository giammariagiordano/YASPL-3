package syntax;

import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import otherOperationExpression.IdentiferExpression;
import visitor.Visitor;

public class VarDeclInit extends YasplNode {
  /*private final List<IdentiferExpression> ids;
  private final List<VarInitValueId> varInitValueIds;*/
  /*private final IdentiferExpression id;
  private final VarInitValue varInitValue;*/
  private final VarInitValueId valueId;
  /*public VarDeclInit(Location left, Location right,List<IdentiferExpression> ids, List<VarInitValueId> valueIds) {
    super(left, right);
    this.ids =ids;
    this.varInitValueIds = valueIds;
  }*/
  public VarDeclInit(Location left, Location right,IdentiferExpression id, VarInitValue valueId) {
    super(left, right);
    this.valueId = new VarInitValueId(left, right, id, valueId);
  }
  public VarDeclInit(Location left, Location right,String id, VarInitValue valueId) {
    super(left, right);
    this.valueId = new VarInitValueId(left, right, id, valueId);
  }
  

 /* public List<IdentiferExpression> getIds() {
    return ids;
  }


  public List<VarInitValueId> getVarInitValueIds() {
    return varInitValueIds;
  }
*/

  
  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }


  public VarInitValueId getValueId() {
    return valueId;
  }


 /*   return id;
  }


  public VarInitValue getVarInitValue() {
    return varInitValue;
  }*/

}
