package visitor;

import java.util.List;
import java.util.StringJoiner;
import semantic.ReturnType;
import semantic.Scope;
import semantic.SemanticSymbol;
import semantic.SymbolTable;
import semantic.Variable;
import semantic.VariableType;
import syntax.*;

public class CodeVisitor implements Visitor<String, Scope> {
  private static final String C_HEADER =
      "#include <stdio.h>\n" + "#include <stdbool.h>\n#include <string.h>\n"
          + "#define btoa(x) ((x)?\"true\":\"false\")\n";

  private static final String COMMENT_HEADER = "/*This file was generated by yasplcc*/";
  private static final String DECL_HEADER =
      "/***********Declarations***********/\n " + "char str1[256], str2[256];";
  private static final String MAIN_HEADER = "/***********Main*******************/";
  private SymbolTable symbolTable;

  public CodeVisitor(SymbolTable table) {
    super();
    this.symbolTable = table;
  }

  @Override
  public String visit(ArithOperation arithOperation, Scope param) {
    StringBuilder builder = new StringBuilder();

    // if (arithOperation.getLeftOperand().getNodeType() != ReturnType.STRING
    // && arithOperation.getRightOperand().getNodeType() != ReturnType.STRING)
    builder.append("(");
    builder.append(arithOperation.getLeftOperand().accept(this, param));
    builder.append(mapOperand(arithOperation.getOperation()));
    builder.append(arithOperation.getRightOperand().accept(this, param));

    // if (arithOperation.getLeftOperand().getNodeType() != ReturnType.STRING
    // && arithOperation.getRightOperand().getNodeType() != ReturnType.STRING)
    builder.append(")");

    return builder.toString();
  }

  @Override
  public String visit(BooleanOperation booleanOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    builder.append(booleanOperation.getLeftOperand().accept(this, param)).append(" ");
    builder.append(mapOperand(booleanOperation.getOperation())).append(" ");
    builder.append(booleanOperation.getRightOperand().accept(this, param));
    builder.append(")");
    return builder.toString();
  }

  @Override
  public String visit(RelopOperation relopOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    builder.append(relopOperation.getLeftOperand().accept(this, param)).append(" ");
    builder.append(mapOperand(relopOperation.getOperation())).append(" ");
    builder.append(relopOperation.getRightOperand().accept(this, param));
    builder.append(")");
    return builder.toString();
  }

  @Override
  public String visit(MinusExpression minus, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("-");
    builder.append(minus.getExpr().accept(this, param));
    return builder.toString();
  }

  @Override
  public String visit(NotExpression notExpression, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("(");
    builder.append("!");
    builder.append(notExpression.getExpr().accept(this, param));
    builder.append(")");
    return builder.toString();
  }

  @Override
  public String visit(TrueExpression trueExpression, Scope param) {
    return String.valueOf(trueExpression.getValue());
  }

  @Override
  public String visit(FalseExpression falseExpression, Scope param) {
    return String.valueOf(falseExpression.getValue());
  }

  @Override
  public String visit(IdentifierExpression identifierExpression, Scope param) {
    int addr = symbolTable.findAddr(identifierExpression.getName());
    SemanticSymbol ss = param.get(addr);
    if (ss instanceof Variable) {
      Variable var = (Variable) ss;
      if (var.getVarType() == VariableType.OUTPUT) {
        return "*" + identifierExpression.getName();
      }
    }
    return identifierExpression.getName();
  }

  @Override
  public String visit(IntConst intConst, Scope param) {
    return String.valueOf(intConst.getValue());
  }

  @Override
  public String visit(DoubleConst doubleConst, Scope param) {
    return String.valueOf(doubleConst.getValue());

  }

  @Override
  public String visit(CharConst charConst, Scope param) {
    return "'" + String.valueOf(charConst.getValue()) + "'";
  }

  @Override
  public String visit(StringConst stringConst, Scope param) {
    return "\"" + String.valueOf(stringConst.getValue()) + "\"";
  }

  @Override
  public String visit(WhileOperation whileOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("while(");
    String condiction = whileOperation.getCondition().accept(this, param);
    String whileCompStat = whileOperation.getWhileCompStat().accept(this, param);
    builder.append(condiction).append(") {\n");
    builder.append(whileCompStat).append("\n}\n");
    return builder.toString();
  }

  @Override
  public String visit(IfThenOperation ifThenOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("if(");
    String condiction = ifThenOperation.getCondition().accept(this, param);
    String thenCompStat = ifThenOperation.getThenCompStat().accept(this, param);
    builder.append(condiction).append(") {\n");
    builder.append(thenCompStat).append("\n}\n");
    return builder.toString();
  }

  @Override
  public String visit(IfThenElseOperation ifThenElseOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("if(");
    String condiction = ifThenElseOperation.getCondition().accept(this, param);
    String thenCompStat = ifThenElseOperation.getThenCompStat().accept(this, param);
    String elseCompStat = ifThenElseOperation.getElseCompStat().accept(this, param);
    builder.append(condiction).append(") {\n");
    builder.append(thenCompStat).append("\n} else {\n");
    builder.append(elseCompStat).append("\n}\n");
    return builder.toString();
  }


  @Override
  public String visit(ReadOperation readOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("scanf(\"");
    StringJoiner sj = new StringJoiner(" ");
    String varsList = readOperation.getVars().accept(this, param);
    // readOperation.getVars().getVarsNames().forEach(v -> v.accept(this, param));
    readOperation.getVars().getVarsNames()
        .forEach(v -> sj.add(mapType(v.getNodeType().toString())));
    builder.append(sj.toString());
    builder.append("\",");
    builder.append(varsList);
    builder.append(");\n");
    return builder.toString();
  }

  @Override
  public String visit(Vars vars, Scope param) {
    StringJoiner inputs = new StringJoiner(", ");
    vars.getVarsNames().forEach(v -> inputs.add("&".concat(v.accept(this, param))));
    return inputs.toString();
  }

  @Override
  public String visit(Args args, Scope param) {
    StringBuilder builder = new StringBuilder();
    StringJoiner sj = new StringJoiner(", ");
    args.getExprArgs().forEach(e -> {
      sj.add(e.accept(this, param));
    });
    builder.append(sj).toString();
    System.out.println(builder.toString());
    return builder.toString();
  }

  @Override
  public String visit(WriteOperation writeOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    writeOperation.getArgs().getExprArgs().forEach(e -> {
      if (e instanceof RelopOperation) {
        builder.append(relopPrint(e, param));

      } else if (e instanceof ArithOperation) {
        builder.append(mathPrint(e, param));
      } else if (e instanceof IdentifierExpression) {
        builder.append(printfId(e, param));
      } else if (e instanceof iConst) {
        builder.append(printConst(e, param));
      }
    });

    System.out.println(builder.toString());
    return builder.toString();
  }

  private String printConst(Expression e, Scope param) {
    iConst<?> c = (iConst<?>) e;
    return "printf(\"" + c.getValue().toString() + "\");\n";
  }

  private String relopPrint(Expression e, Scope param) {
    String toReturn;
    RelopOperation ro = (RelopOperation) e;
    toReturn = "printf(\"%d\"," + ro.getLeftOperand().accept(this, param) + " "
        + mapOperand(ro.getOperation()) + " " + ro.getRightOperand().accept(this, param) + ");\n";
    return toReturn;
  }

  private String printfId(Expression expr, Scope param) {
    IdentifierExpression id = (IdentifierExpression) expr;
    return "printf(" + "\"" + mapType(id.getNodeType().getValue()) + "\"," + id.getName() + ");\n";
  }

  private String mathPrint(Expression writeOperation, Scope param) {
    String toReturn = "";

    if (writeOperation instanceof ArithOperation) {
      ArithOperation ao = (ArithOperation) writeOperation;
      ReturnType left = ao.getLeftOperand().getNodeType();
      ReturnType right = ao.getRightOperand().getNodeType();
      if (left == ReturnType.INTEGER && right == ReturnType.INTEGER) {
        return "printf(\"%d\"," + ao.getLeftOperand().accept(this, param)
            + mapOperand(ao.getOperation()) + ao.getRightOperand().accept(this, param) + ");\n";
      }
      if ((left == ReturnType.DOUBLE && right == ReturnType.DOUBLE)
          || (left == ReturnType.INTEGER && right == ReturnType.DOUBLE)
          || (left == ReturnType.DOUBLE && right == ReturnType.INTEGER)) {
        return "printf(\"%lf\"," + ao.getLeftOperand().accept(this, param)
            + mapOperand(ao.getOperation()) + ao.getRightOperand().accept(this, param) + ");\n";
      }
      if ((left == ReturnType.CHAR && right == ReturnType.CHAR)
          || (left == ReturnType.CHAR && right == ReturnType.INTEGER)
          || (left == ReturnType.INTEGER && right == ReturnType.CHAR)) {
        return "printf(\"%d\"," + ao.getLeftOperand().accept(this, param)
            + mapOperand(ao.getOperation()) + ao.getRightOperand().accept(this, param) + ");\n";
      }
      if (left == ReturnType.STRING && right == ReturnType.STRING) {
        toReturn = "";
        // System.out.println(ao.accept(this, param));

        toReturn += "strcpy(str1," + ao.getLeftOperand().accept(this, param) + ");\n";
        toReturn += "strcpy(str2," + ao.getRightOperand().accept(this, param) + ");\n";
        toReturn += "strcat(str1, str2);\n";
        toReturn += "printf(\"%s\",str1);\n";
        return toReturn;
      } else if (left == ReturnType.STRING || right == ReturnType.STRING) {
        toReturn = "";
        if (left == ReturnType.BOOLEAN) {
          toReturn +=
              "sprintf(str1," + "\"%s\",btoa(" + ao.getLeftOperand().accept(this, param) + "));\n";
        } else {
          toReturn += "sprintf(str1," + "\"" + mapType(ao.getLeftOperand().getNodeType().getValue())
              + "\"," + ao.getLeftOperand().accept(this, param) + ");\n";
        }
        if (right == ReturnType.BOOLEAN) {
          toReturn +=
              "sprintf(str2," + "\"%s\",btoa(" + ao.getRightOperand().accept(this, param) + "));\n";
        } else {
          toReturn +=
              "sprintf(str2," + "\"" + mapType(ao.getRightOperand().getNodeType().getValue())
                  + "\"," + ao.getRightOperand().accept(this, param) + ");\n";
        }
        toReturn += "strcat(str1, str2);\n";
        toReturn += "printf(\"%s\",str1);\n   ";
        return toReturn;
      }
    }

    return "error";
  }



  @Override
  public String visit(AssignOperation assignOperation, Scope param) {
    StringBuilder builder = new StringBuilder();
    assignOperation.getVarName().accept(this, param);
    assignOperation.getExpr().accept(this, param);
    /*
     * Variable x =(Variable) scope.get(addr); if(x.getVarType() == VariableType.OUTPUT) {
     * builder.append("*"+assignOperation.getVarName().getName()); } else {
     * builder.append(assignOperation.getVarName().getName()); }
     */
    builder.append(assignOperation.getVarName().accept(this, param));
    builder.append("=");
    builder.append(assignOperation.getExpr().accept(this, param)).append(";\n");
    return builder.toString();
  }

  @Override
  public String visit(CallWithParamsOperation callWithParamsOperation, Scope param) {
    String NameFunction = callWithParamsOperation.getFunctionName().accept(this, param);
    StringBuilder builder = new StringBuilder();
    StringJoiner paramsCall = new StringJoiner(", ");
    callWithParamsOperation.getArgs().forEach(a -> paramsCall.add(a.accept(this, param)));
    builder.append(NameFunction).append("(");
    builder.append(paramsCall);
    builder.append(");\n");
    System.out.println(paramsCall.toString());
    return builder.toString();
  }

  @Override
  public String visit(CallWithoutParamsOperation callWithoutParamsOperation, Scope param) {
    callWithoutParamsOperation.getFunctionName().accept(this, param);
    StringBuilder builder = new StringBuilder();
    builder.append(callWithoutParamsOperation.getFunctionName().getName()).append("();\n");
    return builder.toString();
  }

  @Override
  public String visit(Program program, Scope param) {
    // this.symbolTable.enterScope();
    String declarations = this.compactCode(program.getDeclsNode(), program.getAttachScope());
    String statements = this.compactCode(program.getStatementsNode(), program.getAttachScope());
    StringBuilder programBuilder = new StringBuilder();
    programBuilder.append(COMMENT_HEADER).append('\n').append(C_HEADER).append('\n')
        .append(DECL_HEADER).append('\n').append(declarations).append('\n').append(MAIN_HEADER)
        .append('\n').append("int main(void){\n").append(statements).append('\n')
        .append(" return 0;\n}\n");
    return programBuilder.toString();
  }

  @Override
  public String visit(TypeNode type, Scope param) {
    return type.getTypeName();
  }

  @Override
  public String visit(VarInitValue varInitValue, Scope param) {
    StringBuilder builder = new StringBuilder();
    if (varInitValue.getExpr() != null) {
      builder.append(" = ");
      builder.append(varInitValue.getExpr().accept(this, param));
    }
    return builder.toString();
  }

  @Override
  public String visit(VarInitValueId varInitValueId, Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append(varInitValueId.getVarName().accept(this, param));
    builder.append(varInitValueId.getInitialValue().accept(this, param));
    return builder.toString();
  }

  @Override
  public String visit(VarDeclaration varDeclaration, Scope param) {
    StringBuilder builder = new StringBuilder();
    StringJoiner varJoiner = new StringJoiner(", ");
    String type = varDeclaration.getTypeNode().accept(this, param);
    if (type.equals("string")) {
      varDeclaration.getVariables().forEach(v -> {
        varJoiner.add("* " + v.accept(this, param));
      });

      builder.append(toCType(type)).append(' ').append(varJoiner.toString()).append(';')
          .append('\n');
    } else {
      varDeclaration.getVariables().forEach(v -> {

        varJoiner.add(v.accept(this, param));
      });
      builder.append(type).append(' ').append(varJoiner.toString()).append(';').append('\n');
    }
    return builder.toString();
  }

  @Override
  public String visit(ParDeclsNode parDeclsNode, Scope param) {
    StringBuilder builder = new StringBuilder();
    String parType = parDeclsNode.getParType().accept(this, param);
    String type = parDeclsNode.getType().accept(this, param);
    String id = parDeclsNode.getVarName().accept(this, param);


    type = toCType(type);
    builder.append(type).append(" ");
    /*
     * if (parType.equals("out") || parType.equals("inout")) { builder.append("*"); }
     */
    builder.append(id);
    return builder.toString();
  }

  @Override
  public String visit(ParType parType, Scope param) {
    return parType.getType();
  }

  @Override
  public String visit(VarDecls varDecls, Scope param) {
    return compactCode(varDecls.getVarsDeclarations(), param);
  }

  // void nomeFunzione(int a,int *b){}
  @Override
  public String visit(DefFunctionWithParamsOperation defFunctionWithParamsOperation, Scope param) {

    StringBuilder builder = new StringBuilder();
    StringJoiner listParams = new StringJoiner(", ");
    String FunctionName = defFunctionWithParamsOperation.getFunctionName().accept(this, param);
    defFunctionWithParamsOperation.getdefListParams().forEach(
        p -> listParams.add(p.accept(this, defFunctionWithParamsOperation.getAttachScope())));
    String body = defFunctionWithParamsOperation.getBody().accept(this,
        defFunctionWithParamsOperation.getAttachScope());
    builder.append("void").append(" ");
    builder.append(FunctionName).append("(");
    builder.append(listParams).append(") {\n");
    builder.append(body).append("\n}\n");
    return builder.toString();
  }

  // in getBody devi passare lo scope della sua funzione
  @Override
  public String visit(DefFunctionWithoutParamsOperation defFunctionWithoutParamsOperation,
      Scope param) {
    StringBuilder builder = new StringBuilder();
    builder.append("void").append(" ");
    builder.append(defFunctionWithoutParamsOperation.getFunctionName().accept(this, param));
    builder.append("() {\n");
    builder.append(defFunctionWithoutParamsOperation.getBody().accept(this,
        defFunctionWithoutParamsOperation.getAttachScope()));
    builder.append("\n}");
    return builder.toString();
  }

  @Override
  public String visit(BodyNode body, Scope param) {
    StringBuilder builder = new StringBuilder();
    /*
     * builder.append(compactCode(body.getVarDecls(), param));
     * builder.append(compactCode(body.getStatementsNode(), param));
     */
    body.getVarDecls().forEach(v -> {
      builder.append(v.accept(this, param));
    });
    body.getStatementsNode().forEach(s -> {
      builder.append(s.accept(this, param));
    });
    builder.toString();
    return builder.toString();
  }

  @Override
  public String visit(CompStat compStat, Scope param) {
    return compactCode(compStat.getStatementsNode(), param);
  }


  private static String mapType(String type) {
    switch (type) {
      case "bool":
      case "int":
        return "%d";
      case "double":
        return "%lf";
      case "string":
        return "%s";
      default:
        return "%d";
    }
  }

  private static String mapOperand(String op) {
    switch (op) {
      case "PLUS":
        return "+";
      case "MINUS":
        return "-";
      case "TIMES":
        return "*";
      case "DIV":
        return "/";
      case "GT":
        return ">";
      case "LT":
        return "<";
      case "GE":
        return ">=";
      case "LE":
        return "<=";
      case "EQ":
        return "==";
      case "AND":
        return "&&";
      case "OR":
        return "||";
      default:
        return "";
    }
  }

  private static String toCType(String type) {
    if (type.equals("string")) {
      type = "char";
    }
    return type;
  }

  private String compactCode(List<? extends YasplNode> list, Scope scope) {
    return list.stream().map(l -> l.accept(this, scope)).reduce("", String::concat);
  }

}
