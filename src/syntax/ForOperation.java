package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

public class ForOperation extends StatementNode implements Scopeable {
  // InitFor:initfor SEMI ExprFor: expr SEMI IncrFor: incrFor RPAR Body:body
  final InitForOperation initFor;
  final ExprForOperation exprFor;
  final IncrForOperation incrFor;
  final BodyNodeFor body;
  Scope scope;


  public ForOperation(Location left, Location right, InitForOperation initForOperation,
      ExprForOperation exprFor, IncrForOperation incrFor, BodyNodeFor body) {
    super(left, right);
    this.initFor = initForOperation;
    this.exprFor = exprFor;
    this.incrFor = incrFor;
    this.body = body;
  }

  public InitForOperation getInitFor() {
    return initFor;
  }


  public ExprForOperation getExprFor() {
    return exprFor;
  }


  public IncrForOperation getIncrFor() {
    return incrFor;
  }


  public BodyNodeFor getBodyFor() {
    return body;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }


  @Override
  public void attachScope(Scope sc) {
    this.scope = sc;

  }


  @Override
  public Scope getAttachScope() {
    return scope;
  }

}
