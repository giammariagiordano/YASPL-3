package statOperation;

import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import syntax.Expression;
import syntax.YasplNode;
import visitor.Visitor;

public class Args extends YasplNode {
  private final List<Expression> args;
  public Args(Location left, Location right,List<Expression> args) {
    super(left, right);
    this.args = args;
  }

  public List<Expression> getArgs() {
    return args;
  }



  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
