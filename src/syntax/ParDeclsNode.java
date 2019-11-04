package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * Create a list of parameters for function declaration. For example: (in int a, out char c)
 */
public class ParDeclsNode extends YasplNode {
  private final ParType parType;
  private final TypeNode type;
  private final IdentifierExpression varName;

  /*
   * Create a list of parameters
   * 
   * @param left for left location
   * 
   * @param right for right location
   * 
   * @param parType for parType of variable (in, out, inout)
   * 
   * @param type for type of variabel (int, double, etc.)
   * 
   * @param varName for name of variable
   */
  public ParDeclsNode(Location left, Location right, ParType parType, TypeNode type,
      IdentifierExpression varName) {
    super(left, right);
    this.parType = parType;
    this.type = type;
    this.varName = varName;
  }

  public ParType getParType() {
    return parType;
  }

  public TypeNode getType() {
    return type;
  }

  public IdentifierExpression getVarName() {
    return varName;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
