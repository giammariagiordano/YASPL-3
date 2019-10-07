package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

/*
 * It represents a node for if then else, for example: if(condition) then { } else { }
 */
public class IfThenElseOperation extends StatementNode implements Scopeable{
  private final Expression condition;
  private final BodyNode bodyThen, bodyElse;
  private Scope scope;
  
  public IfThenElseOperation(Location left, Location right, Expression condition,
      BodyNode bodyThen, BodyNode bodyElse) {
    super(left, right);
    this.condition = condition;
    this.bodyThen = bodyThen;
    this.bodyElse = bodyElse;
  }

  public Expression getCondition() {
    return condition;
  }

  public BodyNode getBody() {
    return bodyThen;
  }

  public BodyNode getBodyElse() {
    return bodyElse;
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
}
