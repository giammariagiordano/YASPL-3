package syntax;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class WriteOperation extends StatementsNode {
  private final Args args;
  public WriteOperation(Location left, Location right,Args args) {
    super(left, right);
    this.args = args;
  }

  
  public WriteOperation(Location argsxleft, Location argsxright, ArrayList<Expression> args) {
    super(argsxleft,argsxright);
    this.args = new Args(argsxleft, argsxright, args);
  }


  public Args getArgs() {
    return args;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
