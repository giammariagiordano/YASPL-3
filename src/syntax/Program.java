package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;
/*
 * This node is the root of Yaspl Program
 */

public class Program extends YasplNode implements Scopeable {
  private final List<DeclarationNode> declsNode;
  private final List<StatementNode> statementsNode;
  private Scope scope;

  /**
   * Create a new Program node (The root program)
   * 
   * @param left for left location
   * @param right for right location
   * @param declsNode for list of declarations
   * @param statementsNode for list of statements
   */

  public Program(Location left, Location right, List<DeclarationNode> declsNode,
      List<StatementNode> statementsNode) {
    super(left, right);
    Collections.reverse(declsNode);
    Collections.reverse(statementsNode);
    this.declsNode = declsNode;
    this.statementsNode = statementsNode;
  }

  /*
   * Get the list of all declarations
   * 
   * @return the list of declarations
   */

  public List<DeclarationNode> getDeclsNode() {
    return declsNode;
  }

  /*
   * Get the list of all statements
   * 
   * @return the list of statements
   */

  public List<StatementNode> getStatementsNode() {
    return statementsNode;
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
    return this.scope;
  }
}
