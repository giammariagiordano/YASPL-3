package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/**
 * Represent a variable declaration node. For example:
 * 
 * <pre>
 *     {@code
 *     int a,b; or
 *     
 *     int a; or
 *     
 *     int a=0;
 *     }
 * </pre>
 */

public class VarDecls {
 /* private final List<VarDeclaration> varsDeclarations;

  /*
   * Create a new varDeclaration node
   * 
   * @param left for left location
   * 
   * @param right for right location
   *
   * @param varsDeclarations for list of variables
   */
  /*public VarDecls(Location left, Location right, List<VarDeclaration> varsDeclarations) {
    super(left, right);
    Collections.reverse(varsDeclarations);
    this.varsDeclarations = varsDeclarations;
  }

  /*
   * Get the list of variable Declarations
   * 
   * @return Variable Declaration
   */
  /*public List<VarDeclaration> getVarsDeclarations() {
    return varsDeclarations;
  }
/*
  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }*/
}
