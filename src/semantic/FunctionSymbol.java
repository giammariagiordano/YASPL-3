package semantic;

/*traccia quando la una funzione è aggiunta alla tabella dei simboli*/
public class FunctionSymbol extends SemanticSymbol {
  private  String inputDom, outputDom;

  public FunctionSymbol(ReturnType returnType, String inputDom, String outputDom) {
    super(returnType);
    this.inputDom = inputDom;
    this.outputDom = outputDom;
  }

  public String getInputDom() {
    return inputDom;
  }

  public String getOutputDom() {
    return outputDom;
  }

  public void setInputDom(String inputDom) {
    this.inputDom = inputDom;
  }

  public void setOutputDom(String outputDom) {
    this.outputDom = outputDom;
  }

  @Override
  public String toString() {
    return "Type:".concat(this.getReturnType().getValue()).concat("\n").concat("Domain:").concat(this.inputDom)
        .concat(" Codomain:").concat(this.outputDom);
    }
  

}
