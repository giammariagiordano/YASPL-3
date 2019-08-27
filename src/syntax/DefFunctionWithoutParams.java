package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.ReturnType;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

public class DefFunctionWithoutParams extends DefDecl implements Scopeable {
  private final IdentifierExpression id;
  private final BodyNode body;
  private Scope scope;
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
  @Override
  public void attachScope(Scope sc) {
    this.scope=sc;
    
  }
  @Override
  public Scope getAttachScope() {
    return this.scope;
  }
  public String getDomain() {
    return ReturnType.VOID.getValue();
  }

}
