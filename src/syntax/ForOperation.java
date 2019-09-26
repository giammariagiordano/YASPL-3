package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ForOperation extends StatementNode {
 //  InitFor:initfor SEMI ExprFor: expr SEMI IncrFor: incrFor RPAR Body:body
   final InitForOperation initFor;
   final ExprForOperation exprFor;
   final IncrForOperation incrFor;
   final BodyNode body;
   

  public ForOperation(Location left, Location right,InitForOperation initForOperation, ExprForOperation exprFor, IncrForOperation incrFor, BodyNode body) {
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


  public BodyNode getBody() {
    return body;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
