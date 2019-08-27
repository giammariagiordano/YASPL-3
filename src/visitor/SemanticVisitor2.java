package visitor;

import java.util.List;
import java.util.logging.Logger;
import semantic.FunctionSymbol;
import semantic.ReturnType;
import semantic.Scope;
import semantic.SemanticSymbol;
import semantic.SymbolTable;
import semantic.Variable;
import syntax.*;

public class SemanticVisitor2 implements Visitor<ReturnType, Logger> {
  private final SymbolTable symbolTable;

  public SemanticVisitor2(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  @Override
  public ReturnType visit(ArithOperation arithOperation, Logger param) {
    arithOperation.getLeftOperation().accept(this, param);
    arithOperation.getRightOperation().accept(this, param);
    if (this.isUndefined(arithOperation.getLeftOperation())
        && this.isUndefined(arithOperation.getRightOperation())) {
      ReturnType left = arithOperation.getLeftOperation().getReturnType();
      ReturnType right = arithOperation.getRightOperation().getReturnType();
      if (left == ReturnType.STRING || left == ReturnType.CHAR || right == ReturnType.STRING
          || right == ReturnType.CHAR) {
        arithOperation.setNodeType(ReturnType.STRING);
      } else if ((left == ReturnType.INTEGER || left == ReturnType.DOUBLE)
          && (right == ReturnType.INTEGER || right == ReturnType.DOUBLE)) {
        if (left == ReturnType.DOUBLE || right == ReturnType.DOUBLE) {
          arithOperation.setNodeType(ReturnType.DOUBLE);
        } else {
          arithOperation.setNodeType(ReturnType.INTEGER);
        }
      } else {
        param.severe(
            GenerateError.ErrorGenerate("Mi aspettavo un Integer o un Double", arithOperation));
        arithOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } // end if isUndefined
    else {
      param.severe(
          GenerateError.ErrorGenerate("Espressione aritmetica non consentita", arithOperation));
      arithOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return arithOperation.getNodeType();
  }

  @Override
  public ReturnType visit(BooleanExpression booleanExpession, Logger param) {
    booleanExpession.getLeftOperation().accept(this, param);
    booleanExpession.getRightOperation().accept(this, param);
    if (this.isUndefined(booleanExpession.getLeftOperation())
        && (this.isUndefined(booleanExpession.getRightOperation()))) {
      ReturnType left = booleanExpession.getLeftOperation().getNodeType();
      ReturnType right = booleanExpession.getRightOperation().getNodeType();
      if (left == ReturnType.BOOLEAN && right == ReturnType.BOOLEAN) {
        booleanExpession.setNodeType(ReturnType.BOOLEAN);
      } else {
        param.severe(GenerateError.ErrorGenerate("Mi aspettavo un Boolean", booleanExpession));
        booleanExpession.setNodeType(ReturnType.UNDEFINED);
      }
    } // else isUndefined
    else {
      param.severe(
          GenerateError.ErrorGenerate("Espressione boolean non consentita", booleanExpession));
      booleanExpession.setNodeType(ReturnType.UNDEFINED);
    }
    return booleanExpession.getNodeType();
    
  }

  @Override
  public ReturnType visit(RelopExpression relopExpression, Logger param) {
    relopExpression.getLeftOperation().accept(this, param);
    relopExpression.getRightOperation().accept(this, param);
    if (this.isUndefined(relopExpression.getLeftOperation())
        && this.isUndefined(relopExpression.getRightOperation())) {
      ReturnType left = relopExpression.getLeftOperation().getNodeType();
      ReturnType right = relopExpression.getRightOperation().getNodeType();
      if ((left == ReturnType.INTEGER || left == ReturnType.DOUBLE)
          && (right == ReturnType.INTEGER || right == ReturnType.DOUBLE)) {
        relopExpression.setNodeType(ReturnType.BOOLEAN);
      } else if (left == ReturnType.CHAR && right == ReturnType.CHAR
          && relopExpression.getOperation().equals("EQ")) {
        relopExpression.setNodeType(ReturnType.BOOLEAN);
      } else if (left == ReturnType.STRING && right == ReturnType.STRING
          && relopExpression.getOperation().equals("EQ")) {
        relopExpression.setNodeType(ReturnType.BOOLEAN);
      } else if (left == ReturnType.BOOLEAN && right == ReturnType.BOOLEAN) {
        if (relopExpression.getOperation().equals("EQ") || relopExpression.equals("NOT")) {
          relopExpression.setNodeType(ReturnType.BOOLEAN);
        }
      } else {
        param.severe(
            GenerateError.ErrorGenerate("Mi aspettavo un intero o un double", relopExpression));
        relopExpression.setNodeType(ReturnType.UNDEFINED);
      }
    } // else is Undefined
    else {
      param
          .severe(GenerateError.ErrorGenerate("Espressione relop non consentita", relopExpression));
      relopExpression.setNodeType(ReturnType.UNDEFINED);
    }
    return relopExpression.getNodeType();
  }

  @Override
  public ReturnType visit(MinusExpression minus, Logger param) {
    minus.getExpr().accept(this, param);
    if (this.isUndefined(minus.getExpr())) {
      ReturnType type = minus.getExpr().getNodeType();
      if (type == ReturnType.INTEGER) {
        minus.setNodeType(ReturnType.INTEGER);
      } else if (type == ReturnType.DOUBLE) {
        minus.setNodeType(ReturnType.DOUBLE);
      } else {
        param.severe(GenerateError.ErrorGenerate("Mi aspettavo un intero o un double", minus));
        minus.setNodeType(ReturnType.UNDEFINED);
      }
    } // else isUndefined
    else {
      param.severe(GenerateError.ErrorGenerate("Espressione minus non consentita", minus));
      minus.setNodeType(ReturnType.UNDEFINED);
    }
    return minus.getNodeType();
  }

  @Override
  public ReturnType visit(NotExpression notOp, Logger param) {
    notOp.getExpr().accept(this, param);
    if (this.isUndefined(notOp.getExpr())) {
      ReturnType type = notOp.getExpr().getNodeType();
      if (type == ReturnType.BOOLEAN) {
        notOp.setNodeType(ReturnType.BOOLEAN);
      } else {
        param.severe(GenerateError.ErrorGenerate("Mi aspettavo un boolean in NotExpression", notOp));
        notOp.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(GenerateError.ErrorGenerate("Espressione NotOp non consentita", notOp));
      notOp.setNodeType(ReturnType.UNDEFINED);
    }
    return notOp.getNodeType();
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
    // Scope scope = this.symbolTable.getCurrentScope();
    int address = this.symbolTable.findAddr(identiferExpression.getName());
    Scope scope = this.symbolTable.lookup(address);
    ReturnType type = (scope != null) ? scope.get(address).getReturnType() : ReturnType.UNDEFINED;
    identiferExpression.setNodeType(type);
    return identiferExpression.getNodeType();
  }

  @Override
  public ReturnType visit(Int_Const intConst, Logger param) {
    intConst.setNodeType(ReturnType.INTEGER);
    return intConst.getNodeType();
  }

  @Override
  public ReturnType visit(Double_Const double_Const, Logger param) {
    double_Const.setNodeType(ReturnType.DOUBLE);
    return double_Const.getNodeType();
  }

  @Override
  public ReturnType visit(Char_Const char_Const, Logger param) {
    char_Const.setNodeType(ReturnType.CHAR);
    return char_Const.getNodeType();
  }

  @Override
  public ReturnType visit(String_Const string_Const, Logger param) {
    string_Const.setNodeType(ReturnType.STRING);
    return string_Const.getNodeType();
  }

  @Override
  public ReturnType visit(WhileNode whileNode, Logger param) {
    whileNode.getExpr().accept(this, param);
    whileNode.getCs().accept(this, param);
    if (this.isUndefined(whileNode.getExpr()) && this.isUndefined(whileNode.getCs())) {
      if (whileNode.getExpr().getNodeType() == ReturnType.BOOLEAN) {
        whileNode.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate("Condizione while non valida", whileNode));
        whileNode.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(GenerateError.ErrorGenerate("Errore nel while", whileNode));
      whileNode.setNodeType(ReturnType.UNDEFINED);
    }

    return whileNode.getNodeType();
  }

  @Override
  public ReturnType visit(IfThenOperation ifThenOperation, Logger param) {
    ifThenOperation.getExpr().accept(this, param);
    ifThenOperation.getCs().accept(this, param);
    if (this.isUndefined(ifThenOperation.getExpr()) && this.isUndefined(ifThenOperation.getCs())) {
      if (ifThenOperation.getExpr().getNodeType() == ReturnType.BOOLEAN) {
        ifThenOperation.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate("Condizione if non valida", ifThenOperation));
        ifThenOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(GenerateError.ErrorGenerate("Errore nell'if", ifThenOperation));
      ifThenOperation.setNodeType(ReturnType.UNDEFINED);
    }

    return ifThenOperation.getNodeType();
  }

  @Override
  public ReturnType visit(IfThenElseOperation ifThenElseOperation, Logger param) {
    ifThenElseOperation.getExpr().accept(this, param);
    ifThenElseOperation.getThenCompState().accept(this, param);
    ifThenElseOperation.getElseCompStat().accept(this, param);
    if (this.isUndefined(ifThenElseOperation.getExpr())
        && this.isUndefined(ifThenElseOperation.getThenCompState())
        && this.isUndefined(ifThenElseOperation.getElseCompStat())) {
      if (ifThenElseOperation.getExpr().getNodeType() == ReturnType.BOOLEAN) {
        ifThenElseOperation.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate("Condizione if non valida", ifThenElseOperation));
        ifThenElseOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(GenerateError.ErrorGenerate("Errore nell'if", ifThenElseOperation));
      ifThenElseOperation.setNodeType(ReturnType.UNDEFINED);
    }

    return ifThenElseOperation.getNodeType();
  }

  @Override
  public ReturnType visit(ReadOperation readOperation, Logger param) {
    readOperation.getVars().accept(this, param);
    if (this.checkAll(readOperation.getVars().getIds())) {
      readOperation.setNodeType(ReturnType.VOID);
    } else {
      param.severe(GenerateError.ErrorGenerate("Errore nella read", readOperation));
      readOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return readOperation.getNodeType();
  }

  @Override
  public ReturnType visit(Vars vars, Logger param) {
    vars.getIds().forEach(i -> i.accept(this, param));
    if (checkAll(vars.getIds())) {
      vars.setNodeType(ReturnType.VOID);
    } else {
      vars.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("Errore in vars", vars));
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
      param.severe(GenerateError.ErrorGenerate("Errore in args", args));
    }
    return args.getNodeType();
  }

  @Override
  public ReturnType visit(WriteOperation writeOperation, Logger param) {
    writeOperation.getArgs().accept(this, param);
    if (this.checkAll(writeOperation.getArgs().getExprArgs())) {
      writeOperation.setNodeType(ReturnType.VOID);
    } else {
      param.severe(GenerateError.ErrorGenerate("Errore nella scrittura", writeOperation));
      writeOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return writeOperation.getNodeType();
  }

  @Override
  public ReturnType visit(AssignOperation assignOperation, Logger param) {
    assignOperation.getId().accept(this, param);
    assignOperation.getExpr().accept(this, param);
    if (this.isUndefined(assignOperation.getId())
        && (this.isUndefined(assignOperation.getExpr()))) {
      ReturnType identifier = assignOperation.getId().getNodeType();
      ReturnType expression = assignOperation.getExpr().getNodeType();
      if ((identifier == ReturnType.INTEGER || identifier == ReturnType.DOUBLE)
          && (expression == ReturnType.INTEGER || expression == ReturnType.DOUBLE)) { // controllare
                                                                                      // bene
        assignOperation.setNodeType(ReturnType.VOID);
      }
      else if ((identifier == ReturnType.CHAR && expression == ReturnType.CHAR)
          || (identifier == ReturnType.STRING && expression == ReturnType.STRING)) {
        assignOperation.setNodeType(ReturnType.VOID);
      }
      else if(identifier == ReturnType.BOOLEAN && expression == ReturnType.BOOLEAN) {
        assignOperation.setNodeType(ReturnType.VOID);

      }
      else {
        param.severe(GenerateError.ErrorGenerate(
            "Mi aspettavo un int o un double o una string o un char", assignOperation));
        assignOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      param.severe(GenerateError.ErrorGenerate("Errore nella assegnazione", assignOperation));
      assignOperation.setNodeType(ReturnType.UNDEFINED);
    }
    return assignOperation.getNodeType();
  }

  @Override
  public ReturnType visit(CallOpParamOperation callOpParamOperation, Logger param) {
    // this.symbolTable.attachOldScope();
    callOpParamOperation.getFunctionName().accept(this, param);
    callOpParamOperation.getArgs().forEach(a -> a.accept(this, param));
    if (this.isUndefined(callOpParamOperation.getFunctionName())) {
      int addr = this.symbolTable.findAddr(callOpParamOperation.getFunctionName().getName());
      FunctionSymbol fs = (FunctionSymbol) this.symbolTable.getCurrentScope().get(addr);
      if (fs.getInputDom().equals(callOpParamOperation.getDomain())) {
        callOpParamOperation.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate("Parametri sbagliati in CallOpParamOperation",
            callOpParamOperation));
        callOpParamOperation.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      callOpParamOperation.setNodeType(ReturnType.UNDEFINED);
      param.severe(
          GenerateError.ErrorGenerate("Errore in CallOpParamOperation", callOpParamOperation));

    }
    // this.symbolTable.exitScope();
    return callOpParamOperation.getNodeType();
  }

  @Override
  public ReturnType visit(CallWithoutParam callWithoutParam, Logger param) {
    callWithoutParam.getFunctionName().accept(this, param);
    if (this.isUndefined(callWithoutParam.getFunctionName())) {
      int addr = this.symbolTable.findAddr(callWithoutParam.getFunctionName().getName());
      FunctionSymbol fs = (FunctionSymbol) this.symbolTable.getCurrentScope().get(addr);
      if (fs.getInputDom().equals(callWithoutParam.getDomain())) {
        callWithoutParam.setNodeType(ReturnType.VOID);
      } else {
        param.severe(GenerateError.ErrorGenerate("Parametri sbagliati in callWithoutParam",
            callWithoutParam));
        callWithoutParam.setNodeType(ReturnType.UNDEFINED);
      }
    } else {
      callWithoutParam.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("Errore in callWithoutParam", callWithoutParam));

    }
    return callWithoutParam.getNodeType();
  }

  @Override
  public ReturnType visit(Program program, Logger param) {
    this.symbolTable.enterScope();
    program.getDecls().forEach(d -> d.accept(this, param));
    program.getStatements().forEach(s -> s.accept(this, param));
    if (checkAll(program.getDecls()) && (checkAll(program.getStatements()))) {
      program.setNodeType(ReturnType.VOID);
    } else {
      program.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("Errore in program", program));
    }
    program.attachScope(this.symbolTable.getCurrentScope());
    this.symbolTable.exitScope();
    return program.getNodeType();
  }

  @Override
  public ReturnType visit(TypeNode type, Logger param) {
    if (type.getTypeName().equals("int") || type.getTypeName().equals("double")
        || type.getTypeName().equals("string") || type.getTypeName().equals("bool")
        || type.getTypeName().equals("char")) {
      type.setNodeType(ReturnType.getEnumFor(type.getTypeName()));
    } else {
      param.severe(GenerateError.ErrorGenerate("tipo non riconosciuto", type));
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
        param.severe(GenerateError.ErrorGenerate("errore in varInitValue", varInitValue));

      }
      return varInitValue.getNodeType();
    }
    varInitValue.setNodeType(ReturnType.VOID);
    return varInitValue.getNodeType();
  }

  @Override
  public ReturnType visit(VarInitValueId varInitValueId, Logger param) {
    varInitValueId.getId().accept(this, param);
    varInitValueId.getVarInitValue().accept(this, param);
    varInitValueId.getId().accept(this, param);

    if (isUndefined(varInitValueId.getId()) && isUndefined(varInitValueId.getVarInitValue())) {
      varInitValueId.setNodeType(ReturnType.VOID);
    } else {
      varInitValueId.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("VarInitValue non valido", varInitValueId));
    }
    return varInitValueId.getNodeType();
  }

  @Override
  public ReturnType visit(VarDeclaration varDeclaration, Logger param) {
    /*
     * varDeclaration.getType().accept(this, param); varDeclaration.getVariables().forEach(v ->
     * v.accept(this, param)); varDeclaration.getVariables().forEach(v -> { int variableAddress =
     * this.symbolTable.findAddr(v.getId().getName()); if(this.symbolTable.prob(variableAddress))
     * v.setNodeType(this.symbolTable.getCurrentScope().get(variableAddress).getReturnType()); else
     * v.setNodeType(ReturnType.UNDEFINED); }); if(this.allUndefined(varDeclaration.getVariables())
     * && this.isUndefined(varDeclaration.getType())) { ReturnType varType =
     * varDeclaration.getType().getNodeType(); varDeclaration.setNodeType(varType);
     * varDeclaration.getVariables().forEach(v -> { int tmpAddress =
     * this.symbolTable.findAddr(v.getId().getName()); this.symbolTable.add(tmpAddress, new
     * Variable(varType)); }); }else{
     * param.severe(GenerateError.ErrorGenerate("Variable declaration error", varDeclaration));
     * varDeclaration.setNodeType(ReturnType.UNDEFINED); } return varDeclaration.getNodeType();
     */
    varDeclaration.getType().accept(this, param);
    varDeclaration.getVariables().forEach(v -> {
      int variableAddress = this.symbolTable.findAddr(v.getId().getName());
      if (this.symbolTable.prob(variableAddress))
        v.setNodeType(this.symbolTable.getCurrentScope().get(variableAddress).getReturnType());
      else
        v.setNodeType(ReturnType.UNDEFINED);
    });
    if (this.allUndefined(varDeclaration.getVariables())
        && this.isUndefined(varDeclaration.getType())) {
      ReturnType varType = varDeclaration.getType().getNodeType();
      varDeclaration.setNodeType(varType);
      varDeclaration.getVariables().forEach(v -> {
        int tmpAddress = this.symbolTable.findAddr(v.getId().getName());
        this.symbolTable.add(tmpAddress, new Variable(varType));
      });
    } else {
      param.severe(GenerateError.ErrorGenerate("Variable declaration error", varDeclaration));
      varDeclaration.setNodeType(ReturnType.UNDEFINED);
    }
    return varDeclaration.getNodeType();
  }

  /*
   * @Override public ReturnType visit(ListParams listParams, Logger param) {
   * listParams.getParType().accept(this, param); listParams.getType().accept(this, param);
   * listParams.getId().accept(this, param); if(isUndefined(listParams.getParType()) &&
   * isUndefined(listParams.getType())) {
   * listParams.setNodeType(listParams.getType().getNodeType()); } else {
   * listParams.setNodeType(ReturnType.UNDEFINED);
   * param.severe(GenerateError.ErrorGenerate("Errore in ListParam", listParams)); } return
   * listParams.getNodeType(); }
   */

  @Override
  public ReturnType visit(ParDeclsNode parDecls, Logger param) {
    /*
     * parDecls.getListParam().forEach(p -> p.accept(this, param)); if
     * (checkAll(parDecls.getListParam())) { parDecls.setNodeType(ReturnType.VOID); } else {
     * param.severe(GenerateError.ErrorGenerate("errore in parDecls", parDecls));
     * parDecls.setNodeType(ReturnType.UNDEFINED); } return parDecls.getNodeType();
     */
    parDecls.getParType().accept(this, param);
    parDecls.getType().accept(this, param);
    parDecls.getId().accept(this, param);
    if (isUndefined(parDecls.getParType()) && isUndefined(parDecls.getType())) {
      parDecls.setNodeType(parDecls.getType().getNodeType());
    } else {
      parDecls.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("Errore in parDecls", parDecls));
    }
    return parDecls.getNodeType();
  }

  @Override
  public ReturnType visit(ParType parType, Logger param) {
    if (parType.getParType().equals("in") || parType.getParType().equals("out")
        || parType.getParType().equals("inout")) {
      parType.setNodeType(ReturnType.getEnumFor(parType.getParType()));
    } else {
      param.severe(GenerateError.ErrorGenerate("partype non valido", parType));
      parType.setNodeType(ReturnType.UNDEFINED);
    }
    return parType.getNodeType();
  }

  @Override
  public ReturnType visit(VarDecls varDecls, Logger param) {
    varDecls.getVarsDeclarations().forEach(v -> v.accept(this, param));
    if (checkAll(varDecls.getVarsDeclarations())) {
      varDecls.getVarsDeclarations().forEach(v -> {
        int varAddr = this.symbolTable.findAddr(v.getVariables().get(0).getId().getName());
        SemanticSymbol s = new Variable(v.getVariables().get(0).getId().getReturnType());
        this.symbolTable.add(varAddr, s);
      });
      varDecls.setNodeType(ReturnType.VOID);
    } else {
      param.severe(GenerateError.ErrorGenerate("Errore in varDecls", varDecls));
      varDecls.setNodeType(ReturnType.UNDEFINED);
    }
    return varDecls.getNodeType();
  }

  @Override
  public ReturnType visit(DefFunctionWithParam defFunctionWithParam, Logger param) {

    /*
     * defFunctionWithParam.getId().accept(this, param);
     * if(!isUndefined(defFunctionWithParam.getId())) { int functionAddress =
     * this.symbolTable.findAddr(defFunctionWithParam.getId().getName()); FunctionSymbol fs = new
     * FunctionSymbol(ReturnType.VOID, "undefined", "undefined");
     * this.symbolTable.add(functionAddress,fs); this.symbolTable.lookup(functionAddress);
     * defFunctionWithParam.getParDecls().forEach(v -> {
     * 
     * SemanticSymbol var = new Variable(v.getReturnType()); this.symbolTable.add(functionAddress,
     * var); }); defFunctionWithParam.attachScope(this.symbolTable.getCurrentScope());
     * 
     * defFunctionWithParam.getParDecls().forEach(p -> p.accept(this, param));
     * defFunctionWithParam.getBody().accept(this, param);
     * if(checkAll(defFunctionWithParam.getParDecls()) &&
     * (isUndefined(defFunctionWithParam.getBody()))) {
     * fs.setInputDom(defFunctionWithParam.getDomain());
     * defFunctionWithParam.setNodeType(ReturnType.VOID); }else {
     * defFunctionWithParam.setNodeType(ReturnType.UNDEFINED);
     * param.severe(GenerateError.ErrorGenerate("parametri non corretti", defFunctionWithParam)); }
     * } else { defFunctionWithParam.setNodeType(ReturnType.UNDEFINED);
     * param.severe(GenerateError.ErrorGenerate("Funzione già dichiarata", defFunctionWithParam)); }
     * this.symbolTable.exitScope(); return defFunctionWithParam.getNodeType();
     */
    defFunctionWithParam.getId().accept(this, param);
    if (!isUndefined(defFunctionWithParam.getId())) {
      int addrFunction = this.symbolTable.findAddr(defFunctionWithParam.getId().getName());
      FunctionSymbol fs = new FunctionSymbol(ReturnType.VOID, "undefined", "undefined");
      this.symbolTable.add(addrFunction, fs);
      this.symbolTable.enterScope();
      defFunctionWithParam.getParDecls().forEach(p -> p.accept(this, param));
      defFunctionWithParam.getParDecls().forEach(p -> {
        int addr = this.symbolTable.findAddr(p.getId().getName());
        Variable var = new Variable(p.getReturnType());
        this.symbolTable.add(addr, var);
      });
      defFunctionWithParam.getBody().accept(this, param);
      if (checkAll(defFunctionWithParam.getParDecls())
          && isUndefined(defFunctionWithParam.getBody())) {
        defFunctionWithParam.setNodeType(ReturnType.VOID);
        fs.setInputDom(defFunctionWithParam.getDomain());
      } else {
        defFunctionWithParam.setNodeType(ReturnType.UNDEFINED);
        param.severe(GenerateError.ErrorGenerate("parametri non corretti", defFunctionWithParam));
      }
      defFunctionWithParam.attachScope(this.symbolTable.getCurrentScope());
      this.symbolTable.exitScope();
    } else {
      defFunctionWithParam.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("Funzione già dichiarata", defFunctionWithParam));
    }
    return defFunctionWithParam.getNodeType();
  }

  @Override
  public ReturnType visit(BodyNode body, Logger param) {
    body.getVarDecls().forEach(v -> v.accept(this, param));
    body.getStatementsNode().forEach(s -> s.accept(this, param));
    if (this.checkAll(body.getVarDecls()) && this.checkAll(body.getStatementsNode())) {
      body.setNodeType(ReturnType.VOID);
    } else {
      param.severe(GenerateError.ErrorGenerate("body non valido", body));
      body.setNodeType(ReturnType.UNDEFINED);
    }
    return body.getNodeType();
  }

  @Override
  public ReturnType visit(DefFunctionWithoutParams defFunctionWithoutParams, Logger param) {
    defFunctionWithoutParams.getId().accept(this, param);
    if (!isUndefined(defFunctionWithoutParams.getId())) {
      int functionAddress = this.symbolTable.findAddr(defFunctionWithoutParams.getId().getName());
      FunctionSymbol fs = new FunctionSymbol(ReturnType.VOID, "undefined", "undefined");
      this.symbolTable.add(functionAddress, fs);
      this.symbolTable.enterScope();
      defFunctionWithoutParams.getBody().accept(this, param);
      if (isUndefined(defFunctionWithoutParams.getBody())) {
        fs.setInputDom(defFunctionWithoutParams.getDomain());
        defFunctionWithoutParams.setNodeType(ReturnType.VOID);
      } else {
        defFunctionWithoutParams.setNodeType(ReturnType.UNDEFINED);
        param.severe(GenerateError.ErrorGenerate(
            "parametri non corretti in defFunctionWithoutParams", defFunctionWithoutParams));
      }
      defFunctionWithoutParams.attachScope(this.symbolTable.getCurrentScope());
      this.symbolTable.exitScope();
    } else {
      defFunctionWithoutParams.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate(
          "Funzione già dichiarata in defFunctionWithoutParams", defFunctionWithoutParams));
    }
    return defFunctionWithoutParams.getNodeType();
  }

  @Override
  public ReturnType visit(CompStat compStat, Logger param) {
    compStat.getStatements().forEach(s -> s.accept(this, param));
    if (checkAll(compStat.getStatements())) {
      compStat.setNodeType(ReturnType.VOID);
    } else {
      compStat.setNodeType(ReturnType.UNDEFINED);
      param.severe(GenerateError.ErrorGenerate("Errore in CompStat", compStat));
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

  private boolean checkReadType(List<? extends YasplNode> node) {
    return node.stream().allMatch(
        n -> n.getNodeType() != ReturnType.STRING && n.getNodeType() != ReturnType.BOOLEAN);
  }

}
