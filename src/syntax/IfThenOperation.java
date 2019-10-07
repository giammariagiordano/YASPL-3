package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

/*
 * It represents a node for if then, for example: if(condition) then { }
 */
public class IfThenOperation extends StatementNode implements Scopeable{
  private final Expression condition;
  private final BodyNode bodyThen;
  private Scope scope;

  public IfThenOperation(Location left, Location right, Expression condition,
      BodyNode bodyThen) {
    super(left, right);
    this.condition = condition;
    this.bodyThen=bodyThen;
  }


  public Expression getCondition() {
    return condition;
  }

  public BodyNode getBody() {
    return bodyThen;
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
