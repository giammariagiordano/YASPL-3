package visitor;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import syntax.*;

public class SyntaxVisitor implements Visitor<Element, Void> {
  private Document xmlDocument;

  public SyntaxVisitor() {
    super();
    this.createDocument();
  }


  /**
   * This method append the root child to document
   * 
   * @param el the root child
   */
  public void appendRoot(Element el) {
    this.xmlDocument.appendChild(el);
  }


  private void createDocument() {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      this.xmlDocument = docBuilder.newDocument();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public void toXml(String fileName) {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer;
    try {
      transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(this.xmlDocument);
      StreamResult result = new StreamResult(
          new File(System.getProperty("user.home").concat("/Scrivania/".concat(fileName))));
      String path = System.getProperty("user.home").concat("/Scrivania/".concat(fileName));
      System.out.println("SyntaxVisitor.xml saved in: " + path);
      transformer.transform(source, result);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  public Element visit(ArithOperation arithOperation, Void param) {
    Element el = this.xmlDocument.createElement("ArithOp");
    el.setAttribute("operator", arithOperation.getOperation());
    el.appendChild(arithOperation.getLeftOperand().accept(this, param));
    el.appendChild(arithOperation.getRightOperand().accept(this, param));
    return el;
  }


  public Element visit(BooleanOperation booleanOperation, Void param) {
    Element el = this.xmlDocument.createElement("booleanExpression");
    el.setAttribute("operator", booleanOperation.getOperation());
    el.appendChild(booleanOperation.getLeftOperand().accept(this, param));
    el.appendChild(booleanOperation.getRightOperand().accept(this, param));
    return el;
  }

  public Element visit(RelopOperation relopOperation, Void param) {
    Element el = this.xmlDocument.createElement("relopExpression");
    el.setAttribute("operator", relopOperation.getOperation());
    el.appendChild(relopOperation.getLeftOperand().accept(this, param));
    el.appendChild(relopOperation.getRightOperand().accept(this, param));
    return el;
  }

  public Element visit(MinusExpression minus, Void param) {
    Element el = this.xmlDocument.createElement("Uminus");
    el.appendChild(minus.getExpr().accept(this, param));
    return el;
  }

  public Element visit(NotExpression notExpression, Void param) {
    Element el = this.xmlDocument.createElement("not");
    el.appendChild(notExpression.getExpr().accept(this, param));
    return el;
  }

  public Element visit(TrueExpression trueExpression, Void param) {
    Element el = this.xmlDocument.createElement("true");
    el.setAttribute("value", String.valueOf(trueExpression.getValue()));
    return el;
  }

  public Element visit(FalseExpression falseExpression, Void param) {
    Element el = this.xmlDocument.createElement("false");
    el.setAttribute("value", String.valueOf(falseExpression.getValue()));
    return el;
  }

  public Element visit(IdentifierExpression identifierExpression, Void param) {
    Element el = this.xmlDocument.createElement("IdentifierOp");
    el.setAttribute("lexem", identifierExpression.getName());
    return el;
  }

  public Element visit(IntConst intConst, Void param) {
    Element el = this.xmlDocument.createElement("INT_CONST");
    el.setAttribute("NUMBER", String.valueOf(intConst.getValue()));
    return el;
  }

  public Element visit(DoubleConst doubleConst, Void param) {
    Element el = this.xmlDocument.createElement("DOUBLE_CONST");
    el.setAttribute("Double_Number", String.valueOf(doubleConst.getValue()));
    return el;
  }

  public Element visit(CharConst charConst, Void param) {
    Element el = this.xmlDocument.createElement("CHAR_CONST");
    el.setAttribute("CHAR_CONST", String.valueOf(charConst.getValue()));
    return el;
  }

  public Element visit(StringConst stringConst, Void param) {
    Element el = this.xmlDocument.createElement("STRING_CONST");
    el.setAttribute("STRING_CONST", String.valueOf(stringConst.getValue()));
    return el;
  }

  public Element visit(WhileOperation whileOperation, Void param) {
    Element el = this.xmlDocument.createElement("WhileNode");
    el.appendChild(whileOperation.getCondition().accept(this, param));
    el.appendChild(whileOperation.getWhileCompStat().accept(this, param));
    return el;
  }

  public Element visit(IfThenOperation ifThenOperation, Void param) {
    Element el = this.xmlDocument.createElement("IfThen");
    el.appendChild(ifThenOperation.getCondition().accept(this, param));
    el.appendChild(ifThenOperation.getThenCompStat().accept(this, param));
    return el;
  }

  public Element visit(IfThenElseOperation ifThenElseOperation, Void param) {
    Element el = this.xmlDocument.createElement("IfThenElse");
    el.appendChild(ifThenElseOperation.getCondition().accept(this, param));
    el.appendChild(ifThenElseOperation.getThenCompStat().accept(this, param));
    el.appendChild(ifThenElseOperation.getElseCompStat().accept(this, param));
    return el;
  }

  public Element visit(ReadOperation readOperation, Void param) {
    Element el = this.xmlDocument.createElement("ReadOp");
    el.appendChild(readOperation.getVars().accept(this, param));
    return el;
  }

  public Element visit(Vars vars, Void param) {
    Element el = this.xmlDocument.createElement("Vars");
    vars.getVarsNames().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }

  @Override
  public Element visit(Args args, Void param) {
    Element el = this.xmlDocument.createElement("Args");
    args.getExprArgs().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }

  @Override
  public Element visit(WriteOperation writeOperation, Void param) {
    Element el = this.xmlDocument.createElement("WriteOp");
    el.appendChild(writeOperation.getArgs().accept(this, param));
    return el;
  }

  @Override
  public Element visit(AssignOperation assignOperation, Void param) {
    Element el = this.xmlDocument.createElement("AssignOp");
    el.appendChild(assignOperation.getVarName().accept(this, param));
    el.appendChild(assignOperation.getExpr().accept(this, param));
    return el;
  }

  @Override
  public Element visit(CallWithParamsOperation callWithParamsOperation, Void param) {
    Element el = this.xmlDocument.createElement("CallWithParam");
    el.appendChild(callWithParamsOperation.getFunctionName().accept(this, param));
    callWithParamsOperation.getArgs().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }

  @Override
  public Element visit(CallWithoutParamsOperation callWithoutParamsOperation, Void param) {
    Element el = this.xmlDocument.createElement("CallWithoutParam");
    el.appendChild(callWithoutParamsOperation.getFunctionName().accept(this, param));
    return el;
  }

  @Override
  public Element visit(Program program, Void param) {
    Element el = this.xmlDocument.createElement("Program");
    program.getDeclsNode().forEach(i -> el.appendChild(i.accept(this, param)));
    program.getStatementsNode().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }

  @Override
  public Element visit(TypeNode type, Void param) {
    Element el = this.xmlDocument.createElement("Type");
    el.setAttribute("value", type.getTypeName());
    return el;
  }

  @Override
  public Element visit(VarInitValue varInitValue, Void param) {
    Element el = this.xmlDocument.createElement("VarInitValue");
    if (varInitValue.getExpr() != null)
      el.appendChild(varInitValue.getExpr().accept(this, param));
    return el;
  }

  @Override
  public Element visit(VarInitValueId varInitValueId, Void param) {
    Element el = this.xmlDocument.createElement("VarInitValueID");
    el.appendChild(varInitValueId.getVarName().accept(this, param));
    el.appendChild(varInitValueId.getInitialValue().accept(this, param));
    return el;
  }


  @Override
  public Element visit(VarDeclaration varDeclaration, Void param) {
    Element el = this.xmlDocument.createElement("VarDeclarations");
    el.appendChild(varDeclaration.getTypeNode().accept(this, param));
    varDeclaration.getVariables().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }

  @Override
  public Element visit(ParDeclsNode parDecls, Void param) {
    Element el = this.xmlDocument.createElement("ListParam");
    el.appendChild(parDecls.getParType().accept(this, param));
    el.appendChild(parDecls.getType().accept(this, param));
    el.appendChild(parDecls.getVarName().accept(this, param));
    return el;
  }


  @Override
  public Element visit(ParType parType, Void param) {
    Element el = this.xmlDocument.createElement("ParType");
    el.setAttribute("value", parType.getType());
    return el;
  }


  @Override
  public Element visit(VarDecls varDecls, Void param) {
    Element el = this.xmlDocument.createElement("VarDecls");
    varDecls.getVarsDeclarations().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }

  @Override
  public Element visit(DefFunctionWithParamsOperation defFunctionWithParamsOperation, Void param) {
    Element el = this.xmlDocument.createElement("defFunctionWithParam");
    el.appendChild(defFunctionWithParamsOperation.getFunctionName().accept(this, param));
    defFunctionWithParamsOperation.getdefListParams().forEach(i -> el.appendChild(i.accept(this, param)));
    el.appendChild(defFunctionWithParamsOperation.getBody().accept(this, param));
    return el;
  }

  @Override
  public Element visit(BodyNode body, Void param) {
    Element el = this.xmlDocument.createElement("body");
    body.getVarDecls().forEach(i -> el.appendChild(i.accept(this, param)));
    body.getStatementsNode().forEach(i -> el.appendChild(i.accept(this, param)));;
    return el;
  }

  @Override
  public Element visit(DefFunctionWithoutParamsOperation defFunctionWithoutParamsOperation, Void param) {
    Element el = this.xmlDocument.createElement("defFunctionWithoutParams");
    el.appendChild(defFunctionWithoutParamsOperation.getFunctionName().accept(this, param));
    el.appendChild(defFunctionWithoutParamsOperation.getBody().accept(this, param));
    return el;
  }

  @Override
  public Element visit(CompStat compStat, Void param) {
    Element el = this.xmlDocument.createElement("CompStat");
    compStat.getStatementsNode().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }
}
