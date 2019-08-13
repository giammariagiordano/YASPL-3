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
import binaryOperationExpression.ArithOperation;
import binaryOperationExpression.BooleanExpession;
import binaryOperationExpression.RelopExpression;
import otherOperationExpression.Char_Const;
import otherOperationExpression.Double_Const;
import otherOperationExpression.FalseExpression;
import otherOperationExpression.IdentiferExpression;
import otherOperationExpression.Int_Const;
import otherOperationExpression.MinusExpression;
import otherOperationExpression.NotExpression;
import otherOperationExpression.String_Const;
import otherOperationExpression.TrueExpression;
import statOperation.*;
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
      StreamResult result =
          new StreamResult(new File(System.getProperty("user.home").concat("/Scrivania/".concat(fileName))));
      System.out.println(System.getProperty("user.home"));
      transformer.transform(source, result);
      System.out.println("File saved!");
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }
  
  public Element visit(ArithOperation arithOperation, Void param) throws RuntimeException {
    Element el = this.xmlDocument.createElement("ArithOp");
    el.setAttribute("operator", arithOperation.getSimbolOperation());
    el.appendChild(arithOperation.getLeftOperation().accept(this, param));
    el.appendChild(arithOperation.getRightOperation().accept(this, param));
    return el;
  }


  public Element visit(BooleanExpession booleanExpession, Void param) throws RuntimeException {
    Element el = this.xmlDocument.createElement("booleanExpession");
    el.setAttribute("operator", booleanExpession.getOperation());
    el.appendChild(booleanExpession.getLeftOperation().accept(this, param));
    el.appendChild(booleanExpession.getRightOperation().accept(this, param));
    return el;
  }

  public Element visit(RelopExpression relopExpression, Void param) {
    Element el = this.xmlDocument.createElement("relopExpression");
    el.setAttribute("operator", relopExpression.getOperation());
    el.appendChild(relopExpression.getLeftOperation().accept(this, param));
    el.appendChild(relopExpression.getRightOperation().accept(this, param));
    return el;
  }


  public Element visit(MinusExpression minus, Void param) {
    Element el = this.xmlDocument.createElement("Uminus");
    el.appendChild(minus.getExpr().accept(this, param));
    return el;
  }


  public Element visit(NotExpression notOp, Void param) {
    Element el = this.xmlDocument.createElement("not");
    el.appendChild(notOp.getExpr().accept(this, param));
    return el;
  }


  public Element visit(TrueExpression trueExpression, Void param) {
   Element el = this.xmlDocument.createElement("true");
   el.setAttribute("value",String.valueOf(trueExpression.getTrue()));
   return el;
  }


  public Element visit(FalseExpression falseExpression, Void param) {
    Element el = this.xmlDocument.createElement("false");
    el.setAttribute("value",String.valueOf(falseExpression.getFalse()));
    return el;
  }


  public Element visit(IdentiferExpression identiferExpression, Void param) {
    Element el = this.xmlDocument.createElement("IdentifierOp");
    el.setAttribute("lexem", identiferExpression.getId());
    return el;
  }

  
  public Element visit(Int_Const intConst, Void param) {
    Element el = this.xmlDocument.createElement("INT_CONST");
    el.setAttribute("NUMBER",String.valueOf(intConst.getIntConst()));
    return el;
  }


  public Element visit(Double_Const double_Const, Void param) {
    Element el = this.xmlDocument.createElement("DOUBLE_CONST");
    el.setAttribute("Double_Number", String.valueOf(double_Const.getDoubleNumber()));
    return el;
  }


  public Element visit(Char_Const char_Const, Void param) {
    Element el = this.xmlDocument.createElement("char_Const");
    el.setAttribute("char_Const", String.valueOf(char_Const.getChar_const()));
    return el;
  }


  public Element visit(String_Const string_Const, Void param) {
    Element el = this.xmlDocument.createElement("StringConst");
    el.setAttribute("value", String.valueOf(string_Const.getString_const()));
    return el;
  }


  public Element visit(WhileNode whileNode, Void param) {
   Element el = this.xmlDocument.createElement("WhileNode");
   el.appendChild(whileNode.getExpr().accept(this, param));
   el.appendChild(whileNode.getCs().accept(this, param));
   return el;
  }


  public Element visit(IfThenOperation ifThenOperation, Void param) {
    Element el = this.xmlDocument.createElement("IfThen");
    el.appendChild(ifThenOperation.getExpr().accept(this, param));
    el.appendChild(ifThenOperation.getCs().accept(this, param));
    return el;
  }


  public Element visit(IfThenElseOperation ifThenElseOperation, Void param) {
    Element el = this.xmlDocument.createElement("IfThenElse");
    el.appendChild(ifThenElseOperation.getExpr().accept(this, param));
    el.appendChild(ifThenElseOperation.getThenCompState().accept(this, param));
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
    vars.getIds().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
    
  }


  @Override
  public Element visit(Args args, Void param) {
    Element el = this.xmlDocument.createElement("Args");
    args.getArgs().forEach(i ->  el.appendChild(i.accept(this, param)));
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
    el.appendChild(assignOperation.getId().accept(this, param));
    el.appendChild(assignOperation.getExpr().accept(this, param));
    return el;
  }


  @Override
  public Element visit(CallOpParamOperation callOpParamOperation, Void param) {
    Element el = this.xmlDocument.createElement("CallWithParam");
    el.appendChild(callOpParamOperation.getId().accept(this, param));
   callOpParamOperation.getArgs().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }


  @Override
  public Element visit(CallWithoutParam callWithoutParam, Void param) {
    Element el = this.xmlDocument.createElement("CallWithoutParam");
    el.appendChild(callWithoutParam.getId().accept(this, param));
    return el;
  }


  @Override
  public Element visit(Program program, Void param) {
    Element el = this.xmlDocument.createElement("Program");
    program.getDecls().forEach(i -> el.appendChild(i.accept(this, param)));
    program.getStatements().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }


  @Override
  public Element visit(TypeNode type, Void param) {
    Element el = this.xmlDocument.createElement("Type");
    el.setAttribute("value",type.getTypeName());
    
    return el;
  }


  @Override
  public Element visit(VarInitValue varInitValue, Void param) {
    Element el = this.xmlDocument.createElement("VarInitValue");
    if(varInitValue.getExpr()!=null)
    el.appendChild(varInitValue.getExpr().accept(this, param));
    return el;
  }


  @Override
  public Element visit(VarDeclInit varDeclInit, Void param) {
    Element el = this.xmlDocument.createElement("VarDeclInit");
    el.appendChild(varDeclInit.getValueId().getIds().accept(this, param));
    el.appendChild(varDeclInit.getValueId().getVarsInitValue().accept(this, param));
    return el;
  }


  @Override
  public Element visit(VarInitValueId varInitValueId, Void param) {
    Element el = this.xmlDocument.createElement("VarInitValueID");
    el.appendChild(varInitValueId.getIds().accept(this, param));
    el.appendChild(varInitValueId.getVarsInitValue().accept(this, param));
    return el;
  }


  @Override
  public Element visit(VarDeclaration varDeclaration, Void param) {
    Element el = this.xmlDocument.createElement("VarDeclarations");
    el.appendChild(varDeclaration.getType().accept(this, param));
    varDeclaration.getVariables().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }


  @Override
  public Element visit(ListParams listParams, Void param) {
    Element el = this.xmlDocument.createElement("ListParam");
    el.appendChild(listParams.getParType().accept(this, param));
    el.appendChild(listParams.getType().accept(this, param));
    el.appendChild(listParams.getId().accept(this, param));
    return el;
  }


  @Override
  public Element visit(ParDeclsNode parDecls, Void param) {
    Element el = this.xmlDocument.createElement("ParDecls");
    parDecls.getListParam().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }


  @Override
  public Element visit(ParType parType, Void param) {
    Element el = this.xmlDocument.createElement("ParType");
    el.setAttribute("value", parType.getParType());
    return el;
  }


  @Override
  public Element visit(VarDecls varDecls, Void param) {
    Element el = this.xmlDocument.createElement("VarDecls");
    varDecls.getVarsDeclarasions().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }


  @Override
  public Element visit(DefFunctionWithParam defFunctionWithParam, Void param) {
    Element el = this.xmlDocument.createElement("defFunctionWithParam");
    el.appendChild(defFunctionWithParam.getId().accept(this, param));
    defFunctionWithParam.getParDecls().forEach(i -> el.appendChild(i.accept(this, param)));
    el.appendChild(defFunctionWithParam.getBody().accept(this, param));
    return el;
  }


  @Override
  public Element visit(BodyNode body, Void param) {
    Element el = this.xmlDocument.createElement("body");
    body.getVarDecls().forEach(i -> el.appendChild(i.accept(this, param)));
    body.getStatementsNode().forEach(i-> el.appendChild(i.accept(this, param)));;
    return el;
  }


  @Override
  public Element visit(DefFunctionWithoutParams defFunctionWithoutParams, Void param) {
    Element el = this.xmlDocument.createElement("defFunctionWithoutParams");
    el.appendChild(defFunctionWithoutParams.getId().accept(this, param));
    el.appendChild(defFunctionWithoutParams.getBody().accept(this, param));
    return el;
  }


  @Override
  public Element visit(CompStat compStat, Void param) {
    Element el = this.xmlDocument.createElement("CompStat");
    compStat.getStatements().forEach(i -> el.appendChild(i.accept(this, param)));
    return el;
  }


}
