package exception;

public class StringError {

  public static String inError = "Impossible to write a variable of type IN";

  public static String outError = "Impossible to read an OUT type variable";

  public static String variableNotDeclared = "variable is not declared:";

  public static String variableAlreadyDeclared = "variable is already declared:";

  public static String functionAlreadyDeclared = "function is already declared:";

  public static String typeMismatch = "type mismatch beetween: left: '";

  public static String notAllowed = "not allowed";

  public static String expectedBoolButFound = "expected Boolean but I found: '";

  public static String expectedIntButFound = "expected Integer but I found: '";

  public static String undefinedParameters = "undefined parameters";
  
  public static String indexOutOfBound= " index out of bound, max size: ";

  public static String setMes(String... errorMessage) {
    String toReturn = "";
    for (String s : errorMessage)
      toReturn += s + " ";
    return toReturn;
  }
}
