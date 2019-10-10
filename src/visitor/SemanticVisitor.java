package visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import exception.StringError;
import semantic.*;
import syntax.*;

public class SemanticVisitor implements Visitor<ReturnType, Logger> {
  private final SymbolTable symbolTable;

  public SemanticVisitor(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  @Override
  public ReturnType visit(ArithOperation arithOperation, Logger param) {
    arithOperation.getLeftOperand().accept(this, param);
    arithOperation.getRightOperand().accept(this, param);
    if (isOutVar(arithOperation.getLeftOperand())) {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("ArithOperation: ", StringError.outError), arithOperation));
    }
    if (isOutVar(arithOperation.getRightOperand())) {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("ArithOperation: ", StringError.outError), arithOperation));
    }
    if (this.isUndefined(arithOperation.getLeftOperand())
        && this.isUndefined(arithOperation.getRightOperand())) {
      ReturnType left = arithOperation.getLeftOperand().getNodeType();
      ReturnType right = arithOperation.getRightOperand().getNodeType();
      int row = CompatibilityType.getIndexFor(left);
      int col = CompatibilityType.getIndexFor(right);
      if (arithOperation.getOperation().equals("PLUS")) {
        arithOperation.setNodeType(CompatibilityType.ADDOP[row][col]);
      } // end addop
      else {
        arithOperation.setNodeType(CompatibilityType.ARITHOP[row][col]);
      }
      if (arithOperation.getNodeType() == ReturnType.UNDEFINED) {
        param.severe(GenerateError.ErrorGenerate(StringError.setMes("ArithOperation: ",
            StringError.typeMismatch, left.toString(), "'right:'", right.toString(), "' for ",
            arithOperation.getOperation(), " expression"), arithOperation));

      }
    } else {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("ArithOperation ", StringError.notAllowed), arithOperation));
      arithOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return arithOperation.getNodeType();
  }

  @Override
  public ReturnType visit(BooleanOperation booleanOperation, Logger param) {
    booleanOperation.getLeftOperand().accept(this, param);
    booleanOperation.getRightOperand().accept(this, param);
    if (isOutVar(booleanOperation.getLeftOperand())) {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("BooleanOpertion: ", StringError.outError), booleanOperation));
    }
    if (isOutVar(booleanOperation.getRightOperand())) {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("BooleanOpertion: ", StringError.outError), booleanOperation));
    }

    if (this.isUndefined(booleanOperation.getLeftOperand())
        && this.isUndefined(booleanOperation.getRightOperand())) {
      ReturnType left = booleanOperation.getLeftOperand().getNodeType();
      ReturnType right = booleanOperation.getRightOperand().getNodeType();
      int row = CompatibilityType.getIndexFor(left);
      int col = CompatibilityType.getIndexFor(right);
      booleanOperation.setNodeType(CompatibilityType.BOOLOP[row][col]);
      if (booleanOperation.getNodeType() == ReturnType.UNDEFINED) {
        param.severe(GenerateError.ErrorGenerate(StringError.setMes("BooleanOperation: ",
            StringError.typeMismatch, left.toString(), "'right:'", right.toString(), "' for ",
            booleanOperation.getOperation().toString(), " expression"), booleanOperation));
      }
    } else {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("BooleanOperation ", StringError.notAllowed), booleanOperation));
      booleanOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return booleanOperation.getNodeType();
  }

  @Override
  public ReturnType visit(RelopOperation relopOperation, Logger param) {
    relopOperation.getLeftOperand().accept(this, param);
    relopOperation.getRightOperand().accept(this, param);
    if (isOutVar(relopOperation.getRightOperand())) {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("RelopOpertion: ", StringError.outError), relopOperation));
    }
    if (this.isUndefined(relopOperation.getLeftOperand())
        && this.isUndefined(relopOperation.getRightOperand())) {
      ReturnType left = relopOperation.getLeftOperand().getNodeType();
      ReturnType right = relopOperation.getRightOperand().getNodeType();
      int row = CompatibilityType.getIndexFor(left);
      int col = CompatibilityType.getIndexFor(right);
      if (relopOperation.getOperation().equals("EQ")) {
        relopOperation.setNodeType(CompatibilityType.EQRELOP[row][col]);
      } // end eq
      else {
        relopOperation.setNodeType(CompatibilityType.RELOP[row][col]);
      }
      if (relopOperation.getNodeType() == ReturnType.UNDEFINED) {
        param.severe(GenerateError.ErrorGenerate(StringError.setMes("RelopOperation: ",
            StringError.typeMismatch, left.toString(), "'right:'", right.toString(), "' for ",
            relopOperation.getOperation().toString(), " expression"), relopOperation));
      }
    } else {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("RelopOperation ", StringError.notAllowed), relopOperation));
      relopOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return relopOperation.getNodeType();
  }

  @Override
  public ReturnType visit(MinusExpression minus, Logger param) {
    minus.getExpr().accept(this, param);
    if (isOutVar(minus.getExpr())) {
      param.severe(GenerateError
          .ErrorGenerate(StringError.setMes("MinusExpression: ", StringError.outError), minus));
    }
    if (this.isUndefined(minus.getExpr())) {
      ReturnType type = minus.getExpr().getNodeType();
      int row = CompatibilityType.getIndexFor(type);
      minus.setNodeType(CompatibilityType.UMINUS[row]);
      if (minus.getNodeType() == ReturnType.UNDEFINED) {
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("MinusExpression: Expected Integer or Double"), minus));
        minus.setNodeType(ReturnType.UNDEFINED);
      }
    } // else isUndefined
    else {
      param.severe(GenerateError
          .ErrorGenerate(StringError.setMes("MinusExpression ", StringError.notAllowed), minus));
      minus.setNodeType(ReturnType.UNDEFINED);

    }
    return minus.getNodeType();
  }

  @Override
  public ReturnType visit(NotExpression notExpression, Logger param) {
    notExpression.getExpr().accept(this, param);
    if (isOutVar(notExpression.getExpr())) {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("NotExpression: ", StringError.outError), notExpression));
    }
    if (this.isUndefined(notExpression.getExpr())) {
      ReturnType type = notExpression.getExpr().getNodeType();
      int row = CompatibilityType.getIndexFor(type);
      notExpression.setNodeType(CompatibilityType.NOTOP[row]);
      if (notExpression.getNodeType() == ReturnType.UNDEFINED) {
        param.severe(GenerateError
            .ErrorGenerate(StringError.setMes("NotExpression: Expected Boolean"), notExpression));
        notExpression.setNodeType(ReturnType.UNDEFINED);
      }
    } // else isUndefined
    else {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("NotExpression ", StringError.notAllowed), notExpression));
      notExpression.setNodeType(ReturnType.UNDEFINED);

    }
    return notExpression.getNodeType();
  }

  @Override
  public ReturnType visit(TrueExpression trueExpression, Logger param) {
    trueExpression.setNodeType(ReturnType.BOOLEAN);
    return trueExpression.getNodeType();
  }

  @Override
  public ReturnType visit(FalseExpression falseExpression, Logger param) {
    falseExpression.setNodeType(ReturnType.BOOLEAN);
    return falseExpression.getNodeType();
  }

  @Override
  public ReturnType visit(IdentifierExpression identiferExpression, Logger param) {
    int address = this.symbolTable.findAddr(identiferExpression.getName());
    Scope scope = this.symbolTable.lookup(address);
    ReturnType type = (scope != null) ? scope.get(address).getReturnType() : ReturnType.UNDEFINED;
    identiferExpression.setNodeType(type);
    return identiferExpression.getNodeType();
  }

  @Override
  public ReturnType visit(IntConst intConst, Logger param) {
    intConst.setNodeType(ReturnType.INTEGER);
    return intConst.getNodeType();
  }

  @Override
  public ReturnType visit(DoubleConst doubleConst, Logger param) {
    doubleConst.setNodeType(ReturnType.DOUBLE);
    return doubleConst.getNodeType();
  }

  @Override
  public ReturnType visit(CharConst charConst, Logger param) {
    charConst.setNodeType(ReturnType.CHAR);
    return charConst.getNodeType();
  }

  @Override
  public ReturnType visit(StringConst stringConst, Logger param) {
    stringConst.setNodeType(ReturnType.STRING);
    return stringConst.getNodeType();
  }

  @Override
  public ReturnType visit(WhileOperation whileOperation, Logger param) {
    whileOperation.getCondition().accept(this, param);
    whileOperation.getWhileCompStat().accept(this, param);
    if (this.isUndefined(whileOperation.getCondition())
        && this.isUndefined(whileOperation.getWhileCompStat())) {
      if (whileOperation.getCondition().getNodeType() == ReturnType.BOOLEAN) {
        whileOperation.setNodeType(ReturnType.VOID);
      } else {
        param
            .severe(
                GenerateError.ErrorGenerate(
                    StringError.setMes("WhileOperation: ", StringError.expectedBoolButFound,
                        whileOperation.getCondition().getNodeType().toString(), "'"),
                    whileOperation));
        whileOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(
          GenerateError.ErrorGenerate(StringError.setMes("Error WhileOperation"), whileOperation));
      whileOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return whileOperation.getNodeType();
  }

  @Override
  public ReturnType visit(IfThenOperation ifThenOperation, Logger param) {
    this.symbolTable.enterScope();
    ifThenOperation.getCondition().accept(this, param);
    if (isOutVar(ifThenOperation.getCondition())) {
      param.severe(GenerateError.ErrorGenerate(StringError.outError, ifThenOperation));
    }
    ifThenOperation.getBody().accept(this, param);
    if (this.isUndefined(ifThenOperation.getCondition())
        && this.isUndefined(ifThenOperation.getBody())) {
      if (ifThenOperation.getCondition().getNodeType() == ReturnType.BOOLEAN) {
        ifThenOperation.setNodeType(ReturnType.VOID);
      } else {
        param
            .severe(GenerateError.ErrorGenerate(
                StringError.setMes("IfThenOperation: ", StringError.expectedBoolButFound,
                    ifThenOperation.getCondition().getNodeType().toString(), "'"),
                ifThenOperation));
        ifThenOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(GenerateError.ErrorGenerate("Error IfThenOperation", ifThenOperation));
      ifThenOperation.setNodeType(ReturnType.UNDEFINED);
    }
    ifThenOperation.attachScope(this.symbolTable.getCurrentScope());
    this.symbolTable.exitScope();
    return ifThenOperation.getNodeType();
  }

  @Override
  public ReturnType visit(IfThenElseOperation ifThenElseOperation, Logger param) {
    this.symbolTable.enterScope();
    ifThenElseOperation.getCondition().accept(this, param);
    ifThenElseOperation.getBody().accept(this, param);
    ifThenElseOperation.getBodyElse().accept(this, param);
    ifThenElseOperation.getCondition().accept(this, param);
    if (isOutVar(ifThenElseOperation.getCondition())) {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("IfThenElseOperation: ", StringError.outError), ifThenElseOperation));
    }
    if (this.isUndefined(ifThenElseOperation.getCondition())
        && this.isUndefined(ifThenElseOperation.getBody())
        && this.isUndefined(ifThenElseOperation.getBodyElse())) {
      if (ifThenElseOperation.getCondition().getNodeType() == ReturnType.BOOLEAN) {
        ifThenElseOperation.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("IfThenElseOperation: expected 'BOOLEAN' but I found: '",
                ifThenElseOperation.getCondition().getNodeType().toString(), "'"),
            ifThenElseOperation));
        ifThenElseOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("IfThenElseOperation: ", StringError.expectedBoolButFound,
              ifThenElseOperation.getCondition().getNodeType().toString(), "'"),
          ifThenElseOperation));
      ifThenElseOperation.setNodeType(ReturnType.UNDEFINED);
    }
    ifThenElseOperation.attachScope(this.symbolTable.getCurrentScope());
    this.symbolTable.exitScope();
    return ifThenElseOperation.getNodeType();
  }

  @Override
  public ReturnType visit(ReadOperation readOperation, Logger param) {
    readOperation.getVars().accept(this, param);
    if (readOperation.getVars().getVarsNames().stream().anyMatch(v -> isInVar(v))) {
      param.severe(GenerateError.ErrorGenerate(StringError.inError, readOperation));
    }
    if (this.checkAll(readOperation.getVars().getVarsNames())) {
      readOperation.setNodeType(readOperation.getVars().getNodeType());
    } else {
      param.severe(
          GenerateError.ErrorGenerate(StringError.setMes("Error Read Operation"), readOperation));
      readOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return readOperation.getNodeType();
  }

  @Override
  public ReturnType visit(Vars vars, Logger param) {
    vars.getVarsNames().forEach(i -> i.accept(this, param));
    if (checkAll(vars.getVarsNames())) {
      vars.setNodeType(ReturnType.VOID);
    } else {
      vars.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("Error Vars: ", StringError.variableNotDeclared), vars));
    }
    return vars.getNodeType();
  }

  @Override
  public ReturnType visit(Args args, Logger param) {
    args.getExprArgs().forEach(e -> e.accept(this, param));
    if (checkAll(args.getExprArgs())) {
      args.setNodeType(ReturnType.VOID);
    } else {
      args.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("Error Args"), args));
    }
    return args.getNodeType();
  }

  @Override
  public ReturnType visit(WriteOperation writeOperation, Logger param) {
    writeOperation.getArgs().accept(this, param);
    if ((writeOperation.getArgs().getExprArgs().stream().anyMatch(e -> isOutVar(e)))) {
      param.severe(GenerateError.ErrorGenerate(StringError.outError, writeOperation));
    }
    if (this.checkAll(writeOperation.getArgs().getExprArgs())) {
      writeOperation.setNodeType(writeOperation.getArgs().getNodeType());
    } else {
      param.severe(
          GenerateError.ErrorGenerate(StringError.setMes("Error WriteOperation"), writeOperation));
      writeOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return writeOperation.getNodeType();
  }

  @Override
  public ReturnType visit(AssignOperation assignOperation, Logger param) {
    assignOperation.getVarName().accept(this, param);
    assignOperation.getExpr().accept(this, param);
    if (this.isUndefined(assignOperation.getVarName())
        && (this.isUndefined(assignOperation.getExpr()))) {
      ReturnType left = assignOperation.getVarName().getNodeType();
      ReturnType right = assignOperation.getExpr().getNodeType();
      int row = CompatibilityType.getIndexFor(left);
      int col = CompatibilityType.getIndexFor(right);
      assignOperation.setNodeType(CompatibilityType.ASSIGNOOP[row][col]);
      int addrLeft = this.symbolTable.findAddr(assignOperation.getVarName().getName());
      Variable varLeft = (Variable) this.symbolTable.lookup(addrLeft).get(addrLeft);
      // se ho una expr del tipo a = b dove a è in
      if (varLeft.getVarType() == VariableType.IN) {
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("AssignOperation: ", StringError.inError), assignOperation));
      }
      // se ho una expr del tipo a = b (dove b è out)
      if (isOutVar(assignOperation.getExpr())) {
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("AssignOperation: ", StringError.outError), assignOperation));
      }
      if (assignOperation.getNodeType() == ReturnType.UNDEFINED) {
        ReturnType identifier = assignOperation.getVarName().getNodeType();
        ReturnType expression = assignOperation.getExpr().getNodeType();
        param.severe(GenerateError.ErrorGenerate(StringError.setMes("AssignOperation: expected ",
            identifier.toString(), " for variable '", assignOperation.getVarName().getName(),
            "' but I found ", expression.toString()), assignOperation));
      }
    } else {
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("Error AssignOperation"),
          assignOperation));
      assignOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return assignOperation.getNodeType();
  }

  @Override
  public ReturnType visit(CallWithParamsOperation callWithParamsOperation, Logger param) {
    callWithParamsOperation.getFunctionName().accept(this, param);
    callWithParamsOperation.getArgs().forEach(a -> a.accept(this, param));
    if (this.isUndefined(callWithParamsOperation.getFunctionName())) {
      int addr = this.symbolTable.findAddr(callWithParamsOperation.getFunctionName().getName());
      FunctionSymbol fs = (FunctionSymbol) this.symbolTable.lookup(addr).get(addr);
      if (fs.getInputDom().equals(callWithParamsOperation.getDomain())) {
        callWithParamsOperation.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("CallWithParamsOperation: invalid parameters expected: '",
                fs.getInputDom().replaceAll("x", ", "), "' but I found: '",
                callWithParamsOperation.getDomain().replaceAll("x", ", "), "", "'"),
            callWithParamsOperation));
        callWithParamsOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      callWithParamsOperation.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("Error callWithParamsOperation"),
          callWithParamsOperation));
    }
    return callWithParamsOperation.getNodeType();
  }

  @Override
  public ReturnType visit(CallWithoutParamsOperation callWithoutParamsOperation, Logger param) {
    callWithoutParamsOperation.getFunctionName().accept(this, param);
    if (this.isUndefined(callWithoutParamsOperation.getFunctionName())) {
      int addr = this.symbolTable.findAddr(callWithoutParamsOperation.getFunctionName().getName());
      FunctionSymbol fs = (FunctionSymbol) this.symbolTable.lookup(addr).get(addr);
      if (fs.getInputDom().equals(callWithoutParamsOperation.getDomain())) {
        callWithoutParamsOperation.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("CallWithoutParamsOperation: invalid parameters expected: ",
                fs.getInputDom().replaceAll("x", ", "), "but I found ",
                callWithoutParamsOperation.getDomain().replaceAll("x", ", ")),
            callWithoutParamsOperation));
        callWithoutParamsOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      callWithoutParamsOperation.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("Error CallWithoutParamsOperation"), callWithoutParamsOperation));
    }
    return callWithoutParamsOperation.getNodeType();
  }

  @Override
  public ReturnType visit(Program program, Logger param) {
    // this.symbolTable.enterScope();
    program.getDeclsNode().forEach(d -> d.accept(this, param));
    program.getStatementsNode().forEach(s -> s.accept(this, param));
    if (checkAll(program.getDeclsNode()) && (checkAll(program.getStatementsNode()))) {
      program.setNodeType(ReturnType.VOID);
    } else {
      program.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("Error Program"), program));
    }
    program.attachScope(this.symbolTable.getCurrentScope());
    return program.getNodeType();
  }

  @Override
  public ReturnType visit(TypeNode type, Logger param) {
    if (type.getTypeName().equals("int") || type.getTypeName().equals("double")
        || type.getTypeName().equals("string") || type.getTypeName().equals("bool")
        || type.getTypeName().equals("char")) {
      type.setNodeType(ReturnType.getEnumFor(type.getTypeName()));
    } else {
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("TypeNode: invalid type"), type));
      type.setNodeType(ReturnType.UNDEFINED);
    }
    return type.getNodeType();
  }

  @Override
  public ReturnType visit(VarInitValue varInitValue, Logger param) {
    if (varInitValue.getExpr() != null) {
      varInitValue.getExpr().accept(this, param);
      if (isUndefined(varInitValue.getExpr())) {
        varInitValue.setNodeType(ReturnType.VOID);
      } else {
        varInitValue.setNodeType(ReturnType.UNDEFINED);
        param.severe(
            GenerateError.ErrorGenerate(StringError.setMes("Error VarInitValue"), varInitValue));
      }
      return varInitValue.getNodeType();
    }
    varInitValue.setNodeType(ReturnType.VOID);
    return varInitValue.getNodeType();
  }

  @Override
  public ReturnType visit(VarInitValueId varInitValueId, Logger param) {
    varInitValueId.getVarName().accept(this, param);
    if (varInitValueId.getInitialValue().getExpr() != null) {
      varInitValueId.getInitialValue().accept(this, param);
      if (isUndefined(varInitValueId.getVarName())
          && isUndefined(varInitValueId.getInitialValue())) {
        if (varInitValueId.getVarName().getNodeType()
            .equals(varInitValueId.getInitialValue().getExpr().getNodeType())) {
          varInitValueId.setNodeType(ReturnType.VOID);
        } else {
          param.severe(GenerateError.ErrorGenerate(
              StringError.setMes("VarInitValueId: expected '",
                  varInitValueId.getVarName().getNodeType().toString(), "' for variable '",
                  varInitValueId.getVarName().getName(), "' but I found ",
                  varInitValueId.getInitialValue().getExpr().getNodeType().toString()),
              varInitValueId));
          varInitValueId.setNodeType(ReturnType.UNDEFINED);
        }
      } else {
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes(
                StringError.setMes("Error in VarInitValueId: ", StringError.undefinedParameters)),
            varInitValueId));
      }
    }
    return varInitValueId.getNodeType();
  }

  @Override
  public ReturnType visit(VarDeclaration varDeclaration, Logger param) {

    varDeclaration.getTypeNode().accept(this, param);
    varDeclaration.getVariables().forEach(v -> {
      int variableAddress = this.symbolTable.findAddr(v.getVarName().getName());
      if (this.symbolTable.prob(variableAddress))
        v.setNodeType(this.symbolTable.getCurrentScope().get(variableAddress).getReturnType());
      else
        v.setNodeType(ReturnType.UNDEFINED);
    });
    if (this.allUndefined(varDeclaration.getVariables())
        && this.isUndefined(varDeclaration.getTypeNode())) {
      ReturnType varType = varDeclaration.getTypeNode().getNodeType();
      varDeclaration.setNodeType(varType);
      varDeclaration.getVariables().forEach(v -> {
        int tmpAddress = this.symbolTable.findAddr(v.getVarName().getName());
        if (!symbolTable.prob(tmpAddress))
          this.symbolTable.add(tmpAddress, new Variable(varType));
        else {
          param
              .severe(GenerateError.ErrorGenerate(
                  StringError.setMes("Error in VariableDeclaration: ",
                      StringError.variableAlreadyDeclared, v.getVarName().getName()),
                  varDeclaration));
          varDeclaration.setNodeType(ReturnType.UNDEFINED);
        }
      });
    } else {
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("Error in VariableDeclaration: ",
          StringError.variableAlreadyDeclared, getAllNameVarUndefined(varDeclaration).toString()),
          varDeclaration));
      varDeclaration.setNodeType(ReturnType.UNDEFINED);
    }
    varDeclaration.getVariables().forEach(v -> v.accept(this, param));
    return varDeclaration.getNodeType();
  }

  @Override
  public ReturnType visit(ParDeclsNode parDeclsNode, Logger param) {
    parDeclsNode.getParType().accept(this, param);
    parDeclsNode.getType().accept(this, param);
    parDeclsNode.getVarName().accept(this, param);
    if (isUndefined(parDeclsNode.getParType()) && isUndefined(parDeclsNode.getType())) {
      parDeclsNode.setNodeType(parDeclsNode.getType().getNodeType());
    } else {
      parDeclsNode.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("Error ParDeclsNode", parDeclsNode));
    }
    return parDeclsNode.getNodeType();
  }

  @Override
  public ReturnType visit(ParType parType, Logger param) {
    if (parType.getType().equals("in") || parType.getType().equals("out")
        || parType.getType().equals("inout") || parType.getType().equals("global")) {
      parType.setNodeType(ReturnType.getEnumFor(parType.getType()));
    } else {
      param.severe(
          GenerateError.ErrorGenerate(StringError.setMes("ParType: invalid parType"), parType));
      parType.setNodeType(ReturnType.UNDEFINED);
    }
    return parType.getNodeType();
  }

  @Override
  public ReturnType visit(DefFunctionWithParamsOperation defFunctionWithParamsOperation,
      Logger param) {
    defFunctionWithParamsOperation.getFunctionName().accept(this, param);
    if (!isUndefined(defFunctionWithParamsOperation.getFunctionName())) {
      int addrFunction =
          this.symbolTable.findAddr(defFunctionWithParamsOperation.getFunctionName().getName());
      FunctionSymbol fs = new FunctionSymbol(ReturnType.VOID, "undefined", "undefined");
      this.symbolTable.add(addrFunction, fs);
      this.symbolTable.enterScope();
      defFunctionWithParamsOperation.getdefListParams().forEach(p -> p.accept(this, param));
      defFunctionWithParamsOperation.getdefListParams().forEach(p -> {
        int addr = this.symbolTable.findAddr(p.getVarName().getName());
        Variable var = new Variable(p.getReturnType());
        var.setVarType(VariableType.getEnumFor(p.getParType().getType()));
        this.symbolTable.add(addr, var);
      });
      defFunctionWithParamsOperation.getBody().accept(this, param);
      if (checkAll(defFunctionWithParamsOperation.getdefListParams())
          && isUndefined(defFunctionWithParamsOperation.getBody())) {
        defFunctionWithParamsOperation.setNodeType(ReturnType.VOID);
        StringBuilder app = new StringBuilder();
        defFunctionWithParamsOperation.getdefListParams().forEach(p -> {
          // separo i tipi partype (in, out,inout) con una x per poterli
          // usare nella generazione del codice
          app.append(p.getParType().accept(this, param).getValue() + "x");

        });
        fs.setOutputDom(app.toString());
        fs.setInputDom(defFunctionWithParamsOperation.getDomain());
      } else {
        defFunctionWithParamsOperation.setNodeType(ReturnType.UNDEFINED);
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("DefFunctionWithParamsOperation: invalid parameters"),
            defFunctionWithParamsOperation));
      }
      defFunctionWithParamsOperation.attachScope(this.symbolTable.getCurrentScope());
      this.symbolTable.exitScope();
    } else {
      defFunctionWithParamsOperation.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("DefFunctionWithParamsOperation: ",
              StringError.functionAlreadyDeclared,
              defFunctionWithParamsOperation.getFunctionName().getName()),
          defFunctionWithParamsOperation));
    }
    return defFunctionWithParamsOperation.getNodeType();
  }

  @Override
  public ReturnType visit(BodyNode body, Logger param) {
    body.getVarDecls().forEach(v -> v.accept(this, param));
    body.getStatementsNode().forEach(s -> s.accept(this, param));
    if (this.checkAll(body.getVarDecls()) && this.checkAll(body.getStatementsNode())) {
      body.setNodeType(ReturnType.VOID);
    } else {
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("BodyNode: invalid body"), body));
      body.setNodeType(ReturnType.UNDEFINED);
    }
    return body.getNodeType();
  }

  @Override
  public ReturnType visit(DefFunctionWithoutParamsOperation defFunctionWithoutParamsOperation,
      Logger param) {
    defFunctionWithoutParamsOperation.getFunctionName().accept(this, param);
    if (!isUndefined(defFunctionWithoutParamsOperation.getFunctionName())) {
      int functionAddress =
          this.symbolTable.findAddr(defFunctionWithoutParamsOperation.getFunctionName().getName());
      FunctionSymbol fs = new FunctionSymbol(ReturnType.VOID, "undefined", "undefined");
      this.symbolTable.add(functionAddress, fs);
      this.symbolTable.enterScope();
      defFunctionWithoutParamsOperation.getBody().accept(this, param);
      if (isUndefined(defFunctionWithoutParamsOperation.getBody())) {
        fs.setInputDom(defFunctionWithoutParamsOperation.getDomain());
        defFunctionWithoutParamsOperation.setNodeType(ReturnType.VOID);
      } else {
        defFunctionWithoutParamsOperation.setNodeType(ReturnType.UNDEFINED);
        param.severe(GenerateError.ErrorGenerate(
            StringError.setMes("DefFunctionWithoutParamsOperation: invalid parameters"),
            defFunctionWithoutParamsOperation));
      }
      defFunctionWithoutParamsOperation.attachScope(this.symbolTable.getCurrentScope());
      this.symbolTable.exitScope();
    } else {
      defFunctionWithoutParamsOperation.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(
          StringError.setMes("DefFunctionWithoutParamsOperation: ",
              StringError.functionAlreadyDeclared,
              defFunctionWithoutParamsOperation.getFunctionName().getName()),
          defFunctionWithoutParamsOperation));
    }
    return defFunctionWithoutParamsOperation.getNodeType();
  }

  @Override
  public ReturnType visit(CompStat compStat, Logger param) {
    compStat.getStatementsNode().forEach(s -> s.accept(this, param));
    if (checkAll(compStat.getStatementsNode())) {
      compStat.setNodeType(ReturnType.VOID);
    } else {
      compStat.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(StringError.setMes("Error CompStat"), compStat));
    }
    return compStat.getNodeType();
  }

  private boolean checkAll(List<? extends YasplNode> list) {
    return list.stream().allMatch(node -> node.getNodeType() != ReturnType.UNDEFINED);
  }

  private boolean allUndefined(List<? extends YasplNode> list) {
    return list.stream().allMatch(node -> node.getNodeType() == ReturnType.UNDEFINED);
  }

  private boolean isUndefined(YasplNode node) {
    return node.getNodeType() != ReturnType.UNDEFINED;
  }

  private String getAllNameVarUndefined(VarDeclaration varDecl) {
    List<String> listVarUndefined = new ArrayList<String>();
    for (int i = 0; i < varDecl.getVariables().size(); i++) {
      if (varDecl.getVariables().get(i).getVarName().getReturnType() == ReturnType.UNDEFINED) {
        listVarUndefined.add(varDecl.getVariables().get(i).getVarName().getName());
      }
    }
    String toReturn = "'";
    for (int i = 0; i < listVarUndefined.size(); i++) {
      toReturn += listVarUndefined.get(i) + "";
    }
    toReturn += "'";
    return toReturn;
  }


  private boolean isInVar(Expression expr) {
    try {
      if (expr instanceof IdentifierExpression) {
        IdentifierExpression leftIdentifier = (IdentifierExpression) expr;
        int leftAddr = this.symbolTable.findAddr(leftIdentifier.getName());
        Variable varLeft = (Variable) this.symbolTable.lookup(leftAddr).get(leftAddr);
        if (varLeft != null)
          return varLeft.getVarType() == VariableType.IN;
      }
      return false;
    } catch (NullPointerException e) {
      return false;
    }
  }


  private boolean isOutVar(Expression expr) {
    try {
      if (expr instanceof IdentifierExpression) {
        IdentifierExpression rightIdentifier = (IdentifierExpression) expr;
        int rightAddr = this.symbolTable.findAddr(rightIdentifier.getName());
        Variable varRight = (Variable) this.symbolTable.lookup(rightAddr).get(rightAddr);
        if (varRight != null)
          return varRight.getVarType() == VariableType.OUT;
      }
      return false;
    } catch (NullPointerException e) {
      return false;
    }
  }


}
