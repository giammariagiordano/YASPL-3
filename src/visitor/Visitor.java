package visitor;

import syntax.Args;
import syntax.ArithOperation;
import syntax.AssignOperation;
import syntax.BodyNode;
import syntax.BooleanOperation;
import syntax.CallWithParamsOperation;
import syntax.CallWithoutParamsOperation;
import syntax.CharConst;
import syntax.CompStat;
import syntax.DefFunctionWithParamsOperation;
import syntax.DefFunctionWithoutParamsOperation;
import syntax.DoubleConst;
import syntax.FalseExpression;
import syntax.IdentifierExpression;
import syntax.IfThenElseOperation;
import syntax.IfThenOperation;
import syntax.IntConst;
import syntax.MinusExpression;
import syntax.NotExpression;
import syntax.ParDeclsNode;
import syntax.ParType;
import syntax.Program;
import syntax.ReadOperation;
import syntax.RelopOperation;
import syntax.StringConst;
import syntax.TrueExpression;
import syntax.TypeNode;
import syntax.VarDeclaration;
import syntax.VarDecls;
import syntax.VarInitValue;
import syntax.VarInitValueId;
import syntax.Vars;
import syntax.WhileOperation;
import syntax.WriteOperation;

public interface Visitor <T,P> {

  T visit(ArithOperation arithOperation, P param);

  T visit(BooleanOperation booleanOperation, P param);

  T visit(RelopOperation relopOperation, P param);

  T visit(MinusExpression minus, P param);

  T visit(NotExpression notExpression, P param);

  T visit(TrueExpression trueExpression, P param);

  T visit(FalseExpression falseExpression, P param);

  T visit(IdentifierExpression identifierExpression, P param);

  T visit(IntConst intConst, P param);

  T visit(DoubleConst doubleConst, P param);

  T visit(CharConst charConst, P param);

  T visit(StringConst stringConst, P param);

  T visit(WhileOperation whileOperation, P param);

  T visit(IfThenOperation ifThenOperation, P param);

  T visit(IfThenElseOperation ifThenElseOperation, P param);

  T visit(ReadOperation readOperation, P param);

  T visit(Vars vars, P param);

  T visit(Args args, P param);

  T visit(WriteOperation writeOperation, P param);

  T visit(AssignOperation assignOperation, P param);

  T visit(CallWithParamsOperation callWithParamsOperation, P param);

  T visit(CallWithoutParamsOperation callWithoutParamsOperation, P param);

  T visit(Program program, P param);

  T visit(TypeNode type, P param);

  T visit(VarInitValue varInitValue, P param);

  T visit(VarInitValueId varInitValueId, P param);

  T visit(VarDeclaration varDeclaration, P param);

  T visit(ParDeclsNode parDeclsNode, P param);

  T visit(ParType parType, P param);

  T visit(VarDecls varDecls, P param);

  T visit(DefFunctionWithParamsOperation defFunctionWithParamsOperation , P param);

  T visit(BodyNode body, P param);

  T visit(DefFunctionWithoutParamsOperation defFunctionWithoutParamsOperation, P param);

  T visit(CompStat compStat, P param);
  
}
