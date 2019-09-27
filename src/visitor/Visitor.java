package visitor;

import syntax.*;

public interface Visitor<T, P> {

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

  //T visit(VarDecls varDecls, P param);

  T visit(DefFunctionWithParamsOperation defFunctionWithParamsOperation, P param);

  T visit(BodyNode body, P param);

  T visit(DefFunctionWithoutParamsOperation defFunctionWithoutParamsOperation, P param);

  T visit(CompStat compStat, P param);

  T visit(SwitchBodyNode switchBodyNode, P param);

  T visit(SwitchOperation switchOperation, P param);

  T visit(DefBodyNode defBodyNode, P param);

}
