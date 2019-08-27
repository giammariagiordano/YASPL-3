package visitor;

import syntax.Args;
import syntax.ArithOperation;
import syntax.AssignOperation;
import syntax.BodyNode;
import syntax.BooleanExpression;
import syntax.CallOpParamOperation;
import syntax.CallWithoutParam;
import syntax.Char_Const;
import syntax.CompStat;
import syntax.DefDecl;
import syntax.DefFunctionWithParam;
import syntax.DefFunctionWithoutParams;
import syntax.Double_Const;
import syntax.FalseExpression;
import syntax.IdentifierExpression;
import syntax.IfThenElseOperation;
import syntax.IfThenOperation;
import syntax.Int_Const;
import syntax.MinusExpression;
import syntax.NotExpression;
import syntax.ParDeclsNode;
import syntax.ParType;
import syntax.Program;
import syntax.ReadOperation;
import syntax.RelopExpression;
import syntax.String_Const;
import syntax.TrueExpression;
import syntax.TypeNode;
import syntax.VarDeclaration;
import syntax.VarDecls;
import syntax.VarInitValue;
import syntax.VarInitValueId;
import syntax.Vars;
import syntax.WhileNode;
import syntax.WriteOperation;

public interface Visitor <T,P> {

  T visit(ArithOperation arithOperation, P param);

  T visit(BooleanExpression booleanExpession, P param);

  T visit(RelopExpression relopExpression, P param);

  T visit(MinusExpression minus, P param);

  T visit(NotExpression notOp, P param);

  T visit(TrueExpression trueExpression, P param);

  T visit(FalseExpression falseExpression, P param);

  T visit(IdentifierExpression identiferExpression, P param);

  T visit(Int_Const intConst, P param);

  T visit(Double_Const double_Const, P param);

  T visit(Char_Const char_Const, P param);

  T visit(String_Const string_Const, P param);

  T visit(WhileNode whileNode, P param);

  T visit(IfThenOperation ifThenOperation, P param);

  T visit(IfThenElseOperation ifThenElseOperation, P param);

  T visit(ReadOperation readOperation, P param);

  T visit(Vars vars, P param);

  T visit(Args args, P param);

  T visit(WriteOperation writeOperation, P param);

  T visit(AssignOperation assignOperation, P param);

  T visit(CallOpParamOperation callOpParamOperation, P param);

  T visit(CallWithoutParam callWithoutParam, P param);

  T visit(Program program, P param);

  T visit(TypeNode type, P param);

  T visit(VarInitValue varInitValue, P param);


  T visit(VarInitValueId varInitValueId, P param);

  T visit(VarDeclaration varDeclaration, P param);

 // T visit(ListParams listParams, P param);

  T visit(ParDeclsNode parDecls, P param);

  T visit(ParType parType, P param);

  T visit(VarDecls varDecls, P param);

  T visit(DefFunctionWithParam defFunctionWithParam, P param);

  T visit(BodyNode body, P param);

  T visit(DefFunctionWithoutParams defFunctionWithoutParams, P param);

  T visit(CompStat compStat, P param);


  
}
