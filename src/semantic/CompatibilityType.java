package semantic;

public class CompatibilityType {
  
  public static  ReturnType EQRELOP[][] = {
      { ReturnType.BOOLEAN, ReturnType.BOOLEAN, ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.BOOLEAN, ReturnType.BOOLEAN, ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.BOOLEAN, ReturnType.UNDEFINED, ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.BOOLEAN,ReturnType.UNDEFINED },
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.BOOLEAN}
  };
  //relop without ==
  public static ReturnType RELOP[][] = {
      { ReturnType.BOOLEAN, ReturnType.BOOLEAN, ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.BOOLEAN, ReturnType.BOOLEAN, ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.BOOLEAN, ReturnType.UNDEFINED, ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.BOOLEAN,ReturnType.UNDEFINED },
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED}
  };
  
  //add operation
  public static ReturnType ADDOP [][] = {
      {ReturnType.INTEGER, ReturnType.DOUBLE, ReturnType.STRING, ReturnType.UNDEFINED, ReturnType.UNDEFINED},
      {ReturnType.DOUBLE, ReturnType.DOUBLE, ReturnType.STRING, ReturnType.UNDEFINED, ReturnType.UNDEFINED},
      {ReturnType.STRING, ReturnType.STRING, ReturnType.STRING, ReturnType.STRING, ReturnType.STRING},
      {ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.STRING, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      {ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.STRING, ReturnType.UNDEFINED,ReturnType.UNDEFINED}
  };
  
  //Arithop without +
  public static ReturnType ARITHOP [][] = {
      {ReturnType.INTEGER, ReturnType.DOUBLE, ReturnType.UNDEFINED, ReturnType.UNDEFINED, ReturnType.UNDEFINED},
      {ReturnType.DOUBLE, ReturnType.DOUBLE, ReturnType.UNDEFINED, ReturnType.UNDEFINED, ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED}
  };
  //Uminus Operation
  public static ReturnType UMINUS [] = {
      ReturnType.INTEGER, ReturnType.DOUBLE, ReturnType.UNDEFINED, ReturnType.UNDEFINED, ReturnType.UNDEFINED
  };
  
  //NotOperation
  public static ReturnType NOTOP [] = {
      ReturnType.UNDEFINED, ReturnType.UNDEFINED, ReturnType.UNDEFINED, ReturnType.UNDEFINED, ReturnType.BOOLEAN
  };
  
  //ANDOP and OROP
  public static ReturnType BOOLOP [][] = {
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.BOOLEAN}
  };
  
  //ANDOP and OROP
  public static ReturnType ASSIGNOOP [][] = {
      { ReturnType.INTEGER,ReturnType.INTEGER,ReturnType.UNDEFINED, ReturnType.INTEGER,ReturnType.UNDEFINED},
      { ReturnType.DOUBLE,ReturnType.DOUBLE,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.STRING, ReturnType.UNDEFINED,ReturnType.UNDEFINED},
      { ReturnType.INTEGER,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.CHAR,ReturnType.UNDEFINED},
      { ReturnType.UNDEFINED,ReturnType.UNDEFINED,ReturnType.UNDEFINED, ReturnType.UNDEFINED,ReturnType.BOOLEAN}
  };
  
  
  public static int getIndexFor(ReturnType r) {
    int toReturn = 0;
    if(r == ReturnType.INTEGER)
      toReturn = 0;
    else if(r == ReturnType.DOUBLE)
      toReturn = 1;
    else if(r == ReturnType.STRING)
      toReturn = 2;
    else if(r == ReturnType.CHAR)
      toReturn = 3;
    else if (r == ReturnType.BOOLEAN)
      toReturn = 4;
    return toReturn;
  }
}
 
