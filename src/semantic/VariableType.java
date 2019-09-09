package semantic;

public enum VariableType {
GLOBAL("global"), IN("in"),OUT("out"),INOUT("inout"),UNDEFINED("undefined");
  private String value;

  VariableType(String value) {
      this.value = value;
  }
  public String getValue() {
    return value;
}
  public void setValue(String value) {
    this.value = value;
}
  
  public static VariableType getEnumFor(String value){
      for(VariableType t : values()){
          if(t.getValue().equals(value)){
              return t;
          }
      }
      return VariableType.UNDEFINED;
  }
  
}
