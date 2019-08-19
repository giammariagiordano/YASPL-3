package syntax;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class CallOpParamOperation extends StatementsNode {
  private final IdentifierExpression id;
  private final List<Expression> args;
  
  public CallOpParamOperation(Location left,Location right,IdentifierExpression id, ArrayList<Expression> args) {
    super(left,right);
    this.id = id;
    this.args = args;
  }


  public IdentifierExpression getId() {
    return id;
  }


  public List<Expression> getArgs() {
    return args;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
