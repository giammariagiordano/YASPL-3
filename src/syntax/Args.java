package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * It represents a list of expression that used in write operation and callOperationWithParams
 */
public class Args extends YasplNode {
  private final List<Expression> args;

  public Args(Location left, Location right, List<Expression> args) {
    super(left, right);
    Collections.reverse(args);
    this.args = args;
  }

  public List<Expression> getExprArgs() {
    return args;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
