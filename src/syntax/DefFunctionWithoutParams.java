package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class DefFunctionWithoutParams extends DefDecl {
  private final IdentifierExpression id;
  private final BodyNode body;
  public DefFunctionWithoutParams(Location left, Location right,IdentifierExpression id,BodyNode body) {
    super(left, right);
    this.id = id;
    this.body = body;
  }
  public DefFunctionWithoutParams(Location left, Location right,String id,BodyNode body) {
    super(left, right);
    this.id = new IdentifierExpression(left, right, id);
    this.body = body;
  }
  
  public BodyNode getBody() {
    return body;
  }
  
  public IdentifierExpression getId() {
    return id;
  }
  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
