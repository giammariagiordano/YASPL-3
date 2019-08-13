package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import otherOperationExpression.IdentiferExpression;
import visitor.Visitor;

public class DefFunctionWithoutParams extends DefDecl {
  private final IdentiferExpression id;
  private final BodyNode body;
  public DefFunctionWithoutParams(Location left, Location right,IdentiferExpression id,BodyNode body) {
    super(left, right);
    this.id = id;
    this.body = body;
  }
  public DefFunctionWithoutParams(Location left, Location right,String id,BodyNode body) {
    super(left, right);
    this.id = new IdentiferExpression(left, right, id);
    this.body = body;
  }
  
  public BodyNode getBody() {
    return body;
  }
  
  public IdentiferExpression getId() {
    return id;
  }
  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
