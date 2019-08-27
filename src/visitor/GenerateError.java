package visitor;

import syntax.YasplNode;

public class GenerateError {

  public static String ErrorGenerate(String msg, YasplNode yasplNode) {
    return String.format("%s at: %s %s", msg, yasplNode.getLeftLocation(),
        yasplNode.getRightLocation());
  }

}
