package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.ReturnType;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

/*
 * The class represent a Function Declaration without parameters. For example def func()
 */
public class DefFunctionWithoutParamsOperation extends DefDeclaration implements Scopeable {
  private final IdentifierExpression functionName;
  private final BodyNode body;
  private Scope scope;

  /*
   * Create a new Function Declaration With Parameters
   * 
   * @param left for left location
   * 
   * @param right for right location
   * 
   * @param functionName for name function
   * 
   * @param body for body function
   */
  public DefFunctionWithoutParamsOperation(Location left, Location right,
      IdentifierExpression functionName, BodyNode body) {
    super(left, right);
    this.functionName = functionName;
    this.body = body;
  }

  public DefFunctionWithoutParamsOperation(Location left, Location right, String functionName,
      BodyNode body) {
    super(left, right);
    this.functionName = new IdentifierExpression(left, right, functionName);
    this.body = body;
  }

  public BodyNode getBody() {
    return body;
  }

  public IdentifierExpression getFunctionName() {
    return functionName;
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

  public String getDomain() {
    return ReturnType.VOID.getValue();
  }
}
