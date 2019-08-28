package syntax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

public class DefFunctionWithParamsOperation extends DefDeclaration implements Scopeable {
  private final IdentifierExpression functionName;
  private final List<ParDeclsNode> defListParams;
  private final BodyNode body;
  private Scope scope;

  public DefFunctionWithParamsOperation(Location left, Location right,
      IdentifierExpression functionName, ParDeclsNode defParam, BodyNode body) {
    super(left, right);
    this.functionName = functionName;
    this.defListParams = new ArrayList<ParDeclsNode>();
    this.defListParams.add(defParam);
    this.body = body;
  }

  public DefFunctionWithParamsOperation(Location left, Location right, String functionName,
      ParDeclsNode defParam, BodyNode body) {
    super(left, right);
    this.functionName = new IdentifierExpression(left, right, functionName);
    this.defListParams = new ArrayList<ParDeclsNode>();
    this.defListParams.add(defParam);
    this.body = body;
  }

  public DefFunctionWithParamsOperation(Location functionNamexleft, Location functionNamexright,
      String functionName, List<ParDeclsNode> defListParams, BodyNode body) {
    super(functionNamexleft, functionNamexright);
    this.functionName =
        new IdentifierExpression(functionNamexleft, functionNamexright, functionName);
    Collections.reverse(defListParams);
    this.defListParams = defListParams;
    this.body = body;
  }

  public IdentifierExpression getFunctionName() {
    return functionName;
  }

  public List<ParDeclsNode> getdefListParams() {
    return defListParams;
  }

  public BodyNode getBody() {
    return body;
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
    StringJoiner sj = new StringJoiner(",");
    defListParams.forEach(e -> sj.add(e.getNodeType().getValue()));
    return sj.toString();
  }
}
