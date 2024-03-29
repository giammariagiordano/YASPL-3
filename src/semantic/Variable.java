package semantic;

public class Variable extends SemanticSymbol {

  private VariableType varType;

  /**
   * Create a new variable symbol
   * @param returnType the type of variable
   */
  public Variable(ReturnType returnType) {
      super(returnType);
      this.varType = VariableType.GLOBAL;
  }

  /**
   * Get the variable kind of this symbol
   * @return the variable kind
   */
  public VariableType getVarType() {
      return varType;
  }

  /**
   * Set a new variable kind for this symbol
   * @param varType the new variable kind
   */
  public void setVarType(VariableType varType) {
      this.varType = varType;
  }

  @Override
  public String toString() {
      return String.format("Type:%s", this.getReturnType().getValue());
  }

}
