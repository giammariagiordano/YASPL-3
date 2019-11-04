package visitor;

import exception.SemanticException;
import syntax.YasplNode;

public class GenerateError {

  public static String ErrorGenerate(String msg, YasplNode yasplNode) {
    throw new SemanticException(
        String.format("%s at line: %s", msg, yasplNode.getLeftLocation().getLine()));
  }

}
