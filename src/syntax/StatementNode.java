package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;

/*
 * This is an abstract class that implements statements as readOp, writeOp, assignOp, callOp,
 * 
 * if and while
 */
public abstract class StatementNode extends YasplNode {

  public StatementNode(Location left, Location right) {
    super(left, right);
  }
}
