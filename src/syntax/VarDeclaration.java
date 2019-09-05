package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/**
 * Represent a single variable for declaration node. For example:
 * 
 * <pre>
 *     {@code
 *     int a; 
 *     }
 * </pre>
 */

public class VarDeclaration extends DeclarationNode {
  private final TypeNode typeNode;
  private final List<VarInitValueId> variables;

  /*
   * Create a new varDeclaration node
   * 
   * @param left for left location
   * 
   * @param rightfor right location
   * 
   * @param typeNode for type of variable (int, double, etc.)
   * 
   * @param variables for the list of varDeclarations
   */
  public VarDeclaration(Location left, Location right, TypeNode typeNode,
      List<VarInitValueId> variables) {
    super(left, right);
    this.typeNode = typeNode;
    Collections.reverse(variables);
    this.variables = variables;
  }
  /*
   * Get the list of variables
   * 
   * @return the list of variable
   */

  public List<VarInitValueId> getVariables() {
    return variables;
  }
  /*
   * Get the type of variables
   * 
   * @return the type of variables
   */

  public TypeNode getTypeNode() {
    return typeNode;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
