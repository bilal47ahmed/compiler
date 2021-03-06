
package javaapplication5;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Token {
    String class_Part;
    String value_Part;
    int line_Number;

    Token(String classPart, String valuePart, int lineNumber) {
  this.class_Part = classPart;
        this.value_Part = valuePart;
        this.line_Number = lineNumber;
    }
}

class LexicalAnalyzer {
     static  ArrayList<Token> TokenList = new ArrayList<>(Arrays.asList());
    ArrayList<String> word_BreakedArray;
    ArrayList<String> key_WordArray = new ArrayList<String>(Arrays.asList("return", "void", "class","if","elseif","do","while","else","for","array","ArrayList","this","super","extends"));
    ArrayList<String> datatype = new ArrayList<String>(Arrays.asList("double", "string","boolean"));
    ArrayList<String> punctuator_Array = new ArrayList<String>(Arrays.asList(";", ",", "{", "}", "(", ")", "[", "]"));
    ArrayList<String> boolean_Array = new ArrayList<String>(Arrays.asList("true", "false"));
    ArrayList<String> AccessModifier = new ArrayList<String>(Arrays.asList("public", "private"));
    ArrayList<String> linechange_Array = new ArrayList<String>(Arrays.asList("\n","\r"));
    //ArrayList<String> Refernce_Array = new ArrayList<String>(Arrays.asList("this","super"));
    ArrayList<String> incdec_Array = new ArrayList<String>(Arrays.asList("++", "--")); 


    LexicalAnalyzer(ArrayList<String> file) {
        this.word_BreakedArray = file;
    }

    void GenerateToken() {

int i, j;
        int k=0;
        String n = "\n";
        int lineNumber = 1;
        boolean isTokenGenrated = false;
        for (i = 0; i < word_BreakedArray.size(); i++) {
            if (word_BreakedArray.get(i).equals(n)) {
                lineNumber++;

            }


            if (word_BreakedArray.get(i).equals("+") || word_BreakedArray.get(i).equals("-")) {
                Token PmToken = new Token("PM", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + PmToken.class_Part + "," + PmToken.value_Part + "," + PmToken.line_Number + "}");
                TokenList.add(PmToken);
               // System.out.println(TokenList.get(0).value_Part.toString());
            } else if (word_BreakedArray.get(i).equals("*") || word_BreakedArray.get(i).equals("/") || word_BreakedArray.get(i).equals("%")) {
                Token AmToken = new Token("AM", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + AmToken.class_Part + "," + AmToken.value_Part + "," + AmToken.line_Number + "}");
                 TokenList.add(AmToken);
               // System.out.println(TokenList.get(0).value_Part.toString());

            } else if (word_BreakedArray.get(i).equals("||") || word_BreakedArray.get(i).equals("&&")) {
                Token LogicalOperatorToken = new Token("Logicaloperator", word_BreakedArray.get(i), lineNumber);
           isTokenGenrated = true;
               System.out.println("{" + LogicalOperatorToken.class_Part + "," + LogicalOperatorToken.value_Part + "," + LogicalOperatorToken.line_Number + "}");
                TokenList.add(LogicalOperatorToken);
          //  System.out.println(TokenList.get(0).value_Part.toString());
            } else if (word_BreakedArray.get(i).equals("!")) {
                Token NotOperatorToken = new Token("Notoperator", word_BreakedArray.get(i), lineNumber);
     isTokenGenrated = true;
                System.out.println("{" + NotOperatorToken.class_Part + "," + NotOperatorToken.value_Part + "," + NotOperatorToken.line_Number + "}");
                TokenList.add(NotOperatorToken);
                //System.out.println(TokenList.get(0).value_Part.toString());
            } else if (word_BreakedArray.get(i).equals("=")) {
                Token AssignmentoperatorToken = new Token("Assignmentoperator", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + AssignmentoperatorToken.class_Part + "," + AssignmentoperatorToken.value_Part + "," + AssignmentoperatorToken.line_Number + "}");
                TokenList.add(AssignmentoperatorToken);

            } else if (word_BreakedArray.get(i).equals("<") || word_BreakedArray.get(i).equals(">")||word_BreakedArray.get(i).equals("!=")||word_BreakedArray.get(i).equals("==")) {
                Token RationaloperatorToken = new Token("Rationaloperator", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + RationaloperatorToken.class_Part + "," + RationaloperatorToken.value_Part + "," + RationaloperatorToken.line_Number + "}");
                TokenList.add(RationaloperatorToken);
            }
              else if (word_BreakedArray.get(i).equals("++") || word_BreakedArray.get(i).equals("--")) {
                Token incdecToken = new Token("incdec", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + incdecToken.class_Part + "," + incdecToken.value_Part + "," + incdecToken.line_Number + "}");
                 TokenList.add(incdecToken);
        }
              else if (word_BreakedArray.get(i).equals("\n") ||word_BreakedArray.get(i).equals("\r") ) {
               // Token RationaloperatorToken = new Token("Rationaloperator", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                //System.out.println("{" + RationaloperatorToken.class_Part + "," + RationaloperatorToken.value_Part + "," + RationaloperatorToken.line_Number + "}");

            } else if (word_BreakedArray.get(i).equals("#")) {
                Token RefToken = new Token("reference", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + RefToken.class_Part + "," + RefToken.value_Part + "," + RefToken.line_Number + "}");
                 TokenList.add(RefToken);
            }
              else {

            }
            for (j = 0; j < punctuator_Array.size(); j++) {

                if (word_BreakedArray.get(i).equals(punctuator_Array.get(j))) {
                    Token PunctuatorToken = new Token(punctuator_Array.get(j), word_BreakedArray.get(i), lineNumber);
                    isTokenGenrated = true;
                    System.out.println("{" + PunctuatorToken.class_Part + "," + PunctuatorToken.value_Part + "," + PunctuatorToken.line_Number + "}");
                    TokenList.add(PunctuatorToken);
                }
            }

            Pattern identifier = Pattern.compile("([A-Za-z][_]?|_)([A-Za-z0-9]+[_]?)*");
  Matcher idmatch = identifier.matcher(word_BreakedArray.get(i));
            boolean id = idmatch.matches();
            boolean IsIdentifier;
            if (id == true) {
                IsIdentifier = false;
                if (IsIdentifier == false) {
                    for (j = 0; j < key_WordArray.size(); j++) {
                        if (word_BreakedArray.get(i).equals(key_WordArray.get(j))) {
                            Token KeywordToken = new Token(key_WordArray.get(j), word_BreakedArray.get(i), lineNumber);
                            isTokenGenrated = true;
                            System.out.println("{" + KeywordToken.class_Part + "," + KeywordToken.value_Part + "," + KeywordToken.line_Number + "}");
                           TokenList.add(KeywordToken);
                            IsIdentifier = true;
                        }
                    }
                }
                if (IsIdentifier == false) {
                    for (j = 0; j < AccessModifier.size(); j++) {
                        if (word_BreakedArray.get(i).equals(AccessModifier.get(j))) {
                            Token AccessModifierToken = new Token("AccessModifier", word_BreakedArray.get(i), lineNumber);
                            isTokenGenrated = true;
                            System.out.println("{" + AccessModifierToken.class_Part + "," + AccessModifierToken.value_Part + "," + AccessModifierToken.line_Number + "}");
                           TokenList.add(AccessModifierToken);
                     IsIdentifier = true;
                        }
                    }
                }
                if (IsIdentifier == false) {
                    for (j = 0; j < datatype.size(); j++) {

                        if (word_BreakedArray.get(i).equals(datatype.get(j))) {
                            Token DataTypeToken = new Token("Datatype", word_BreakedArray.get(i), lineNumber);
                            isTokenGenrated = true;
                            System.out.println("{" + DataTypeToken.class_Part + "," + DataTypeToken.value_Part + "," + DataTypeToken.line_Number + "}");
                           TokenList.add(DataTypeToken);
                            IsIdentifier = true;
                        }
        }
                }
                if (IsIdentifier == false) {
                    for (j = 0; j < boolean_Array.size(); j++) {
                        if (word_BreakedArray.get(i).equals(boolean_Array.get(j))) {
                   Token BoolToken = new Token("boolean", word_BreakedArray.get(i), lineNumber);
                            isTokenGenrated = true;
                            System.out.println("{" + BoolToken.class_Part + "," + BoolToken.value_Part + "," + BoolToken.line_Number + "}");
                            TokenList.add(BoolToken);
                            IsIdentifier = true;
                        }
                    }
                }
                if (IsIdentifier == false) {

    Token IdentifierToken = new Token("Identifier", word_BreakedArray.get(i), lineNumber);
                    isTokenGenrated = true;
                    System.out.println("{" + IdentifierToken.class_Part + "," + IdentifierToken.value_Part + "," + IdentifierToken.line_Number + "}");
                    TokenList.add(IdentifierToken);
                }
            }

            Pattern doubleMatch = Pattern.compile("[+-]?[0-9]*[.]?[0-9]+");
            Matcher dbMatch = doubleMatch.matcher(word_BreakedArray.get(i));
            boolean dB = dbMatch.matches();
            if (dB == true) {
                Token DoubleToken = new Token("double", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
          System.out.println("{" + DoubleToken.class_Part + "," + DoubleToken.value_Part + "," + DoubleToken.line_Number + "}");
                TokenList.add(DoubleToken);
            }
            Pattern stringMatch = Pattern.compile("\".*?\"");
         Matcher stMatch = stringMatch.matcher(word_BreakedArray.get(i));
            boolean sT = stMatch.matches();
            if (sT == true) {
                String f = word_BreakedArray.get(i).replaceAll("^\"|\"$", "");
                Token StringToken = new Token("string", f, lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + StringToken.class_Part + "," + StringToken.value_Part + "," + StringToken.line_Number + "}");
            TokenList.add(StringToken );
            }

            if (isTokenGenrated == false) {
                Token InvalidToken = new Token("invalid", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + InvalidToken.class_Part + " " + "Valuepart:" + InvalidToken.value_Part + "," + InvalidToken.line_Number + "}");

            }
            isTokenGenrated = false;
//            for(k=0; k<TokenList.size(); k++){
//            System.out.println(TokenList.get(k).value_Part.toString());
//            
           //}
    }
                Token EndMarkerToken = new Token("EndMarker", "$", lineNumber);
        TokenList.add(EndMarkerToken );
        System.out.println("{" + EndMarkerToken.class_Part + " " + "Valuepart:" + EndMarkerToken.value_Part + "," + EndMarkerToken.line_Number + "}");
        for(k=0; k<TokenList.size(); k++){
          System.out.println(TokenList.get(k).value_Part.toString());

           }
    }
}
class SymbolTable {

    String Name;
    String Type;
    String Category;
    String Parent_list;
    SymbolTable Link;

    SymbolTable(String name, String type, String category, String parent_list) {
        this.Name = name;
        this.Type = type;
        this.Category = category;
        this.Parent_list = parent_list;
        

    }

}

class ClassTable {

    String Name;
    String Type;
    String Acess_Modifier;
  
ClassTable(String name, String type, String acess_Modifier) {
        this.Name = name;
        this.Type = type;
        this.Acess_Modifier = acess_Modifier;
  
    }
}

class FuntionTable {

    String Name;
    String Type;
    Stack<Integer> Stk;
FuntionTable(String name, String type,Stack<Integer> stk) {
        this.Name = name;
        this.Type = type;
        this.Stk=stk;
    }
}

class Semantic {
     static  ArrayList<SymbolTable> SymbolTableList = new ArrayList<>(Arrays.asList());
  public static boolean InsertSymbolTable(String name, String type, String category,String parent_list) {
        SymbolTable S1 = new SymbolTable(name,type,category,parent_list);
        S1.Link=S1;
        if(LookupSymbolTable(S1.Name,S1.Type)){
       System.out.println("redeclartion");
        return false;
        }
      else
        {SymbolTableList.add(S1);

System.out.println(" added");
        return true;
        }
        
          }

  public static boolean LookupSymbolTable(String name,String type) {
for (int i=0;i<SymbolTableList.size();i++ )
{
    if(SymbolTableList.get(i).Name.equals(name) && SymbolTableList.get(i).Type.equals(type))
        
return true;
        }

   System.out.println("Undeclared");
       
        return false;
    }
  static  ArrayList<ClassTable> ClassTableList = new ArrayList<>(Arrays.asList());
  public static  boolean InsertClassTable(String name, String type, String acess_Modifier) {
     ClassTable CTD1= new ClassTable(name,type,acess_Modifier); 
  
           if(LookupClassTable(CTD1.Name,CTD1.Type)){
       System.out.println("redeclartion");
        return false;
        
        }
      else
        {ClassTableList.add(CTD1);
        
System.out.println(" added");

        return true;
        
        }
           }

   public static boolean LookupClassTable(String name,String type) {
for (int i=0;i<ClassTableList.size();i++ )
{
    if(ClassTableList.get(i).Name.equals(name)&&ClassTableList.get(i).Type.equals(type))
return true;
        }
   System.out.println("Undeclared");
        return false;
    }

   
static  ArrayList<FuntionTable> FuntionTableList = new ArrayList<>(Arrays.asList());
   public static boolean InsertFuntionTable(String name, String type,Stack<Integer> stk) {
     FuntionTable FT1= new FuntionTable(name,type,stk); 
   if(LookupFuntionTable(FT1.Name,FT1.Stk)){
       System.out.println("redeclartion");
        return false;
        
        }
      else
        {
System.out.println(" added");

            FuntionTableList.add(FT1); 
        return true;
        } 
   }

    public static boolean LookupFuntionTable(String name,Stack<Integer> stk ) {
for (int i=0;i<FuntionTableList.size();i++ )
{
    if(FuntionTableList.get(i).Name.equals(name) && stk.peek()==FuntionTableList.get(i).Stk.peek())
return true;
        }
   System.out.println("Undeclared");
       
        return false;

    }
  public static  boolean CompatibilityCheck(String lt,String rt,String operator){
        return false;
    }
}


 class Syntax {
   int s=1;
    int k=0;
    String TempNameCT,Tempam="",TempTypeCT,TempParent="",TempNameST,TempTypeST,Cat="",TempNameFT,TempTypeFT; 
     Stack<Integer> stack = new Stack<Integer>();
    
    public boolean start(){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("class")){
    if(def()){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")){
        k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("class")){
     TempTypeST=LexicalAnalyzer.TokenList.get(k).value_Part;
        k++;
    if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
     TempNameST=LexicalAnalyzer.TokenList.get(k).value_Part;    
        k++;
        
    if(inh()){
          Semantic.InsertSymbolTable(TempNameST,TempTypeST,Cat,TempParent); 
          //check is this insertion is valid if yes follow it as examples 
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
        k++;
        if(CB()){
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")){
        k++;
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("static")){
            k++;
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("void")){
        k++;
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("main")){
           k++;
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
            k++;
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
            k++;
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
        k++;
        if(MST()){
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
        k++;
        if(CB()){
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}"))
        {k++;
        if(def()){
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("$"))    
        return true;
        }}}}}}}}}}}}}}}}}}}}

return false;


 }   
    public boolean def(){

        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("class")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("$"))
        {
            if(class_def()){
if(def()){
                   return true;
               }
            }
        }

        return true;
    }
    public boolean inh(){
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("extends")){
            k++;
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")) {
          if(Semantic.LookupSymbolTable(LexicalAnalyzer.TokenList.get(k).value_Part, TempTypeST))//follow this
          TempParent=LexicalAnalyzer.TokenList.get(k).value_Part;    
          k++;
               return true;
           }
        }
        return true;
    }
   
    public boolean Dec(){
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")){
        TempTypeFT=LexicalAnalyzer.TokenList.get(k).value_Part;
            if(AM()){
            if(DT()){
                if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
     TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
    stack.push(s);
                    Semantic.InsertFuntionTable(TempNameFT,TempTypeFT,stack);
                   //follow this example
                    k++;
                
                if(init()){
                    if(list()){
              return true;
                    }
                }
                }
            }
        }
        }
        return false;
    }
    public boolean list(){ 

    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")){
       if(((LexicalAnalyzer.TokenList.get(k).value_Part).equals(","))){
       k++;
       if(((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier"))){
           
           stack.push(s);
                    Semantic.InsertFuntionTable(LexicalAnalyzer.TokenList.get(k).value_Part,LexicalAnalyzer.TokenList.get(k).class_Part,stack);
        k++;
        if(init()){   
        if(list()){
        return true;
    }
        }
       }
        }
    }
    return true;
 }
  public boolean init(){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=") ){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")){
      k++;
      if(OE()){
          return true;
      }
      }
      }
      else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")){
          return true;
      }
      return false;
  }
  public boolean For(){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("for") ){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("for")){
      k++;
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
         k++;
         if(C1()){
             if(C2()){
              if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")){
                  k++;
                 if(C3()){ 
                 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
                     k++;
             if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){        
             k++;
             if(MST()){
                 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
                 k++;
                 return true;
         }
             }
             }
                 }
                 }
              }
             }
         }
      }
      }
      }
      return true;
  }
  public boolean C1(){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("bool")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("string"))
      {
          if(Dec()){
          if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")){    
          k++;
          return true;
          }
          }
      }
      else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      if(Assign()){
         if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")) {
           k++;
             return true;
         }
      }
      }
      return false;
    }
  public boolean C2(){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
  if(OE()){
  return true;
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")){
  return true;
  }
   return false; 
  }
  public boolean C3(){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier"))
  {
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
          k++;
          if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
              k++;
              return true;
          }

  }
   }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  if(Assign()){
  return true;
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  return true;
  }
  return false;
      }
 public boolean IF(){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("if"))
     {
         if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("if")){
        k++;
        if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("("))
        {
            k++;
            if(OE()){
                if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
                k++;
           if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){     
  k++;
          if(MST()){
         if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
         k++;
         return true;
 }
   }
   }
  }
 }
  }
   }
   }
     return false;
     }
 public boolean ElseIf(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("elseif")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("elseif")){
 k++;
         if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("("))
        {
            k++;
            if(OE()){
             if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
                k++;
           if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){     
          k++;
          if(MST()){
         if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
         k++;
         return true;
 }
   }
   }
  }
 }
  }
 }
 }
 return false;
     }
 public boolean Else(){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("else")){
       if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("else")){
        k++;
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
         k++;
if(MST()){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
    k++;
    return true;
    }
}
              }
          }

  }
      return false;
 }
 public boolean DoWhile(){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("do")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("do")){
     k++;
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
     k++;
     if(MST()){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
     k++;
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("while")){
  k++;
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
     k++;
     if(OE()){
       if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
      k++;
      return true;
       }
     }
     }
     }
     }
     }
     }
     }
     }
     return false;
 }
 public boolean While(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("while")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("while")){
 k++;
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
 k++;
 if(OE()){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
     k++;
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
     k++;
     if(MST()){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
     k++;
     return true;
     }
     }
     }
 }
 }
 }
 }
 }
 return false;
 }
 public boolean Ts(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")){
 k++;
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
 k++;
     return true;
 }
 }}
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")){
     k++;
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
 k++;
   return true;
     }
 }}
 else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier"))
 {return true;
 }
 return false;
 }
 public boolean Assign(){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
     if(Ts()){
     if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
    TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
         Semantic.LookupFuntionTable(TempNameFT, stack);
         k++;
     
     if(ref()){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")){
     k++;
     if(OE()){
         return true;
     }
     }
     }
     }
     }
     }
     return false;
 }
 public boolean Return(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("return")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("return")){
 k++;
 if(R1()){
 return true;
 }
 }
 }
 return false;
 }
 public boolean R1(){
 if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")){
 if(OE()){
 return true;
 }
 }
 else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals(";")){
 return true;
 }
 return false;
 }
 public boolean AM(){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("bool")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")){
         k++;
     return true;
     }
     }
     else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")){
     k++;
         return true;
     }
     return true;
     }
 public boolean DT(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")){
 k++;
 return true;
 }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")){
     k++;
     return true;
 }}
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")){
     k++;
 return true;
 }
 }
 return false;
 }
 public boolean PL(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")){
     if(DT()){
     if(PL2()){
     return true;
     }
     }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
 return true;
 }
 return false;
 }
 public boolean PL1(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")){
     k++;
     if(PL()){
     return true;
     }
     }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
 return true;
 }
 return false;
 }
 public boolean PL2(){
 if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
 if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
  Semantic.LookupFuntionTable(TempNameFT, stack);
     k++;
 if(PL3()){
 return true;
 }
 }
 }
 return false;
 }
 public boolean PL3(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")){
  if(PL1()){
     return true;
     }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
       k++;
       if(OE()){
       if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
      k++;
      if(PL1()){
          return true;
      }
       }
       }
   }  
   }else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
 return true;
   }
 return false;
 }
 public boolean class_def(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("class")){
 if(AM1()){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("class")){
    TempTypeST=LexicalAnalyzer.TokenList.get(k).value_Part;
         k++;
     if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
        TempNameST=LexicalAnalyzer.TokenList.get(k).value_Part;
         k++;
     
     if(inh()){
          Semantic.InsertSymbolTable(TempNameST,TempTypeST,Cat,TempParent); 
      
         if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("{")){
     k++;
     if(CB()){
     if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("}")){
     k++;
     return true;
     }
     }
     }
     }
     }
     }
 }
 }
 return false;
 }
 public boolean ref(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
 k++;
 if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
     TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
  Semantic.LookupFuntionTable(TempNameFT, stack);
     k++;
 if(ref()){
 return true;
 }
 }
 }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
     k++;
     if(args()){
         if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
         k++;
         if(ref()){
         return true;
         }
         }
     }
 }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
         k++;
  if(args()){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
        k++;
      
  if(x()){
  return true;
  }
  }
 }}}
 else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("=")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("!=")){
 return true;
 } 
 return true;
 }
 public boolean AM1(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("private"))
 {
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")){
     k++;
     return true;
     }
 }
 return true;
 }
 public boolean x(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
     k++;
     if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
         TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
      Semantic.LookupFuntionTable(TempNameFT, stack);
         k++;
     if(ref()){
         return true;
     }
     }
     }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
 if(args()){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
 k++;
 if(ref()){
 return true;
 }
 }
 }
 }
 else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("=")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("!=")){
return true;     
 }
 return true;
 }

public boolean funcall(){
if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
if(Ts()){
if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
k++;
if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
k++;
if(args()){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
    k++;
    return true;
    }
}
}
}
}
}
return false;
}
public boolean args(){
if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")){
if(OE()){
if(args1()){
return true;
}
}
}
else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
   return true; 
}
return true;
}
public boolean args1(){
if((LexicalAnalyzer.TokenList.get(k).class_Part).equals(",")){
    if((LexicalAnalyzer.TokenList.get(k).class_Part).equals(",")){
    k++;
    if(args()){
    return true;
    }
    }}
    else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
        return true;
    }
return false;
}
public boolean OE(){
    if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
    if(AE()){
    if(OE1()){
  return true;
    }}}
    return false;
    }
    public boolean OE1(){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("||")){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("||")){
        k++;
        if(AE()){
        if(OE1()){
 return true;
        }
        }
    }
    }
    else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
 return true;       
    }
    return false;
    }
    public boolean AE(){
    if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
    if(RE()){
    if(AE1()){
    return true;
    }}}
    return false;
    }
    public boolean AE1(){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("&&"))
    {if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("&&")){
    k++;
    if(RE()){
    if(AE1()){
    return true;
    }
    }
    }

    }
    else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("||")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
    return true;        
    }
    return false;
        }
 public boolean RE(){
 if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
 if(E()){
 if(RE1()){
 return true;
 }
 }
 }
 return false;
 }
   public boolean RE1(){
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("<")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(">")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")){
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("<")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(">")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")){
   k++;
   if(E()){
   if(RE1()){
   return true;
   }
   }
   }
   }
   else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("&&")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("||")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
      return true; 
   }
   return false;
   }
   public boolean E(){
   if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
   if(T()){
   if(E1()){
   return true;
   }
   }
   }
   return false;
   }
  public boolean E1(){
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("+")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("-")){
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("+")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("-")){
   k++;
   if(T()){
   if(E1()){
   return true;
   }
   }
   }
   }
   else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("<")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(">")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("&&")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("||")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
return true;
   }
   
   return false;
   }
  public boolean T(){
   if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
   if(F()){
   if(T1()){
   return true;
   }
   }
   }
   return false;
   }
  public boolean T1(){
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("*")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("%")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("/")){
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("*")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("/")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("%")){
 k++;
   if(F()){
   if(T1()){
   return true;
   }
   }
   }
   }
   else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("+")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("-")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("<")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(">")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("&&")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("||")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
  return true;
   }
   return false;
   }
  public boolean F(){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")){
  k++;
  return true;
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
          k++;
          if(OE()){
          if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
          k++;
          return true;
          }
          }
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
      k++;
      if(F()){
      return true;
      }
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
  if(F1()){
  return true;
  }
  }
  return false;
  }
  public boolean F1(){
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
         if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
             TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
     Semantic.LookupFuntionTable(TempNameFT, stack);
     
             k++;
      if(F2()){
          return true;
      }
      }
      else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")){
          
          k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
              k++;
          if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
              TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
               Semantic.LookupFuntionTable(TempNameFT ,stack);
              k++;
              if(F2()){
              return true;
  }
          }
      }
      }}
      else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")){
          k++;
          if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
          if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
           TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
              Semantic.LookupFuntionTable(TempNameFT, stack);
              k++;
              if(F2()){
              return true;
              }
          }
      }
      }}
      else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
      k++;
  return true;
      }
      }}
      return false;
  }
  public boolean F2(){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
       Semantic.LookupFuntionTable(TempNameFT, stack);
      k++;
      if(F2()){
      return true;
      }
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
      
          k++;
      if(args()){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
      k++;
      if(F3()){
      return true;
      }
      }
      }
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
  k++;
  return true;
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
      k++;
      if(args()){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
      k++;
      if(F3()){
      return true;
      }
      }
      }
  }
  }
  return true;
  }
  public boolean F3(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
      k++;
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
          TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
      Semantic.LookupFuntionTable(TempNameFT, stack);
          k++;
      if(F2()){
      return true;
      }
      }

  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("*")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("%")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("/")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("+")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("-")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("<")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(">")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("&&")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("||")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
  return true;
  }
return false;
  }
  public boolean SST(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")){
      k++;
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
          k++;
      if(SST1()){
      return true;
      }      
      }
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")){
    k++;
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")){
      k++;
      if(SST1()){
      return true;
      }
      }
  }}
  else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
 if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
TempNameCT=LexicalAnalyzer.TokenList.get(k).value_Part;

     k++;
 if(SST2()){
 return true;
 }
 }
  }
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")){
TempTypeFT=LexicalAnalyzer.TokenList.get(k).value_Part;
      k++;
 if(SST3()){
 return true;
 }
  }
  
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")){
        k++;
  if(DT()){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  stack.push(s);
TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;  
    Semantic.InsertFuntionTable(TempNameFT,TempTypeFT,stack);
      k++;
  if(init()){
  if(list()){
  return true;
  }
  }
  }
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")){
  
      k++;
  if(DT()){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  stack.push(s);
TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;    
    Semantic.InsertFuntionTable(TempNameFT,TempTypeFT,stack);
      k++;
  if(init()){
  if(list()){
  return true;
  }
  }
  }
  }
  }
  }

  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("while")){
     if(While()){
     return true;
     }

  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("do")){
     if(DoWhile()){
          return true;
     }

  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("if")){
     if(IF()){
          return (true);
     }

  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("elseif")){
     if(ElseIf()){
          return true;
     }

  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("else")){
     if(Else()){
          return true;
     }

  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("return")){
     if(Return()){
          return true;
     }

  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("for")){
     if(For()){
          return true;
     }

  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("ArrayList")){
     if(Arraylist()){
          return true;
     }

  }


  return false;
  }
  public boolean SST1(){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
  Semantic.LookupFuntionTable(TempNameFT, stack);
      k++;
  if(SST4()){
  return true;
  }
  }
  }
  return false;
  }
  public boolean SST2(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")){
  Semantic.LookupFuntionTable(TempNameCT, stack);
      if(ref()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("="))
  {k++;
  if(OE()){
  return true;
  }

  }}
  }
  else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      Semantic.LookupSymbolTable(TempNameST, TempTypeST);
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      k++;
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")){
     k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("new")){
    k++;
    if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
    k++;
    if(args()){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
     k++;
     return true;
     }   }
    }
    }
    } 
     } 
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  Semantic.LookupClassTable(TempNameCT,TempTypeCT);
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  k++;
  if(args()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  return true;
  }
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
  k++;
  return true;
  }
  }
  return false;
  }
  public boolean SST3(){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
   stack.push(s);
   TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;  
   Semantic.InsertFuntionTable(TempNameFT,TempNameFT,stack);
      k++;
  if(init()){
  if(list()){
  return true;
  }
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
  if(ar()){

  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
   stack.push(s);
   TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;  
    Semantic.InsertFuntionTable(TempNameFT,TempTypeFT,stack);
     k++;
  if(ar3()){
  return true;
  }
  }
  }
  }
  return false;
  }
  public boolean SST4(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("#")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("!")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")){
  if(ref()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("!=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("==")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")){
  k++;
  if(OE()){
  return true;
  }
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  k++;
  if(args()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  return true;
  }
  }
  }
  }
  return false;
  }

  public boolean MST(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("this")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("super")||(LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("++")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("--")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("while")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("do")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("if")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("elseif")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("else")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("return")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("for")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("ArrayList")){
  if(SST()){

  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")){
      k++;
  if(MST()){
  return true;
  }
  }
  }
  }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}"))
  {
      return true;
  }
  return false;
  }
  public boolean ar1(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
  k++;
  if(ar2()){
  return true;
  }
  }
  }
  return false;
  }
  public boolean CB(){
//  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("public")){
//Tempam=LexicalAnalyzer.TokenList.get(k).value_Part;
//k++;
//      if(CB1()){
//      return true;
//
//      }
//  }
  if ((LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("private")){
   Tempam=LexicalAnalyzer.TokenList.get(k).value_Part;
          k++;
      if(CB1()){
      return true;
      }
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("static")){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("static")){
      k++;
      TempTypeCT =LexicalAnalyzer.TokenList.get(k).value_Part;
       if(Return()){
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      TempNameCT =LexicalAnalyzer.TokenList.get(k).value_Part;
          stack.push(s);

    Semantic.InsertClassTable(TempNameCT, TempTypeCT,Tempam);
  

          k++;
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
      k++;
      if(PL()){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
          k++;
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
           s++;
      k++;
      if(MST()){
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
      k++;
      if(CB()){
      return true;
      }
      }
      }
      }
      }
      }
      }
      }
      }
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
TempNameCT=LexicalAnalyzer.TokenList.get(k).value_Part;
      k++;
  if(CB2()){
  return true;
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("sting")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")){
  TempTypeCT=LexicalAnalyzer.TokenList.get(k).value_Part;
      if(DT()){
  if(CB3()){
  return true;
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("void")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("void")){
  TempTypeCT=LexicalAnalyzer.TokenList.get(k).value_Part;
      k++;
  
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
 TempNameCT=LexicalAnalyzer.TokenList.get(k).value_Part;   
 Semantic.InsertClassTable(TempNameCT,TempNameCT,Tempam);
      k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  k++;
  if(PL()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
  k++;
  if(MST()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
  k++;
  if(CB()){
  return true;
  }
  }
  }
  }
  }
  }
  }
  }
  }
  }
  return true;
      }
  public boolean CB1(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("sting")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")){
     TempTypeCT=LexicalAnalyzer.TokenList.get(k).value_Part;
      if(DT()){
      if(CB5()){    
      return true;
      }
      }
  }
  else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
       if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
          TempNameCT=LexicalAnalyzer.TokenList.get(k).value_Part;
           Semantic.InsertClassTable(TempNameCT,TempTypeCT , Tempam);
           
  k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  k++;
  if(PL()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
  k++;
  if(MST()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
  k++;
  if(CB()){
  return true;
  }
  }
  }
  }
  }
  }
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("void")){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("void")){
  TempTypeCT=LexicalAnalyzer.TokenList.get(k).value_Part;
      k++;
  
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
          TempNameCT=LexicalAnalyzer.TokenList.get(k).value_Part;
  Semantic.InsertClassTable(TempNameCT,TempTypeCT, Tempam);
          k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  k++;
  if(PL()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
  k++;
  if(MST()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
  k++;
  if(CB()){
  return true;
  }
  }
  }
  }
  }
  }
  }
  }
  }
  }
  return false;

  }
  public boolean CB2(){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  Semantic.LookupClassTable(TempNameCT,TempTypeCT);
         k++;
  if(PL()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
  k++;
  if(MST()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
  k++;
  if(CB()){
  return true;
  }
  }
  }
  }
  }
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
      Semantic.LookupSymbolTable(TempNameST,TempTypeST );
      if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  TempNameFT=LexicalAnalyzer.TokenList.get(k).value_Part;
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("new")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  	 stack.push(s);
  TempTypeFT=LexicalAnalyzer.TokenList.get(k).value_Part;
    Semantic.InsertFuntionTable(TempNameFT,TempTypeFT,stack);


      k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){

  k++;
  if(args()){
  if(CB()){
      return true;
  }
  }
  }
  }
  }
  }
  }
  }
return false;
  }
  public boolean CB3(){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
 TempNameCT=LexicalAnalyzer.TokenList.get(k).value_Part;
      k++;
  if(CB4()){
      return true;
  }
  }
  }
  else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  if(ar()){
  if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
  k++;
  if(ar1()){
  if(CB()){
  return true;
  }
  }
  }
  }
  }
  return false;
  }
 public boolean CB4(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
  Semantic.InsertClassTable(TempNameCT, TempTypeCT,Tempam);
         k++;
  if(PL()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
  k++;
  if(MST()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
  k++;
  if(CB()){
  return true;
  }
  }
  }
  }
  }
  }
  }
 }
 else if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")){
 	 stack.push(s);
    Semantic.InsertFuntionTable(LexicalAnalyzer.TokenList.get(k).value_Part,LexicalAnalyzer.TokenList.get(k).class_Part,stack);

    if(init()){
 if(list()){
   if(CB()){  
   return true;
   }
 }
 }
 }
 return false;
 }
 public boolean CB5(){
 if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
     if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
TempNameCT=LexicalAnalyzer.TokenList.get(k).value_Part;
         k++;
         if(CB6()){
             return true;
         }
     }
 }
 return false;
 }
 public boolean CB6(){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(";")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals(",")){
   	 stack.push(s);
    Semantic.InsertFuntionTable(TempNameCT,LexicalAnalyzer.TokenList.get(k).class_Part,stack);

 if(init()){
 if(list()){
   if(CB()){  
   return true;
   }
 }
 }   
     }
  else if ((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
        Semantic.InsertClassTable(TempNameCT, TempTypeCT, Tempam);
  k++;
  if(PL()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
  k++;
  if(MST()){
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
  k++;
  if(CB()){
  return true;
  }
  }
  }
  }
  }
  }
  }
     } 
     return false;
 }
 public boolean ar(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
 k++;
 if(args()){
 if(ar1()){
 return true;
 }
 }
 }}
 return false;
 }
 public boolean Arraylist(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("ArrayList")){
     if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("ArrayList")){
    k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("<")){
        k++; 
        if(DT()){
            if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(">")){
                k++;
    if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
    k++;
    if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")){
   k++;
   if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("new")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("ArrayList")){
  k++;
  if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("<")){
      k++;
     if(DT()){
         if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(">")){
             k++;

      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("(")){
      k++;
      if((LexicalAnalyzer.TokenList.get(k).value_Part).equals(")")){
      k++;
      return true;
      }
      }
  }
   }
    }
    }
         }

 }
 }
 }
 }
 }
 }
 }
 return false;
 }
 public boolean array_dec(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("string")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("double")||(LexicalAnalyzer.TokenList.get(k).value_Part).equals("boolean")){
 if(DT()){
     if(ar()){
     if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
     k++;
     if(ar3()){
         return true;
     }
     }
     }
 }
 }
 return false;
 }
 public boolean ar2(){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("[")){
 k++;
 if(args()){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("]")){
 k++;
 return true;
 }
 }
 }}
 else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
 return true;
 }
 return true;
 }
 public boolean ar3(){

 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("=")){
 k++;
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("{")){
     k++;
 if(args()){
 if((LexicalAnalyzer.TokenList.get(k).value_Part).equals("}")){
 k++;
 return true;
 }
 }
 }}
 }else if((LexicalAnalyzer.TokenList.get(k).class_Part).equals("Identifier")){
 return true;}
 return true;
 }



   public void  parser(){
 if(start()==true && (LexicalAnalyzer.TokenList.get(k).value_Part).equals("$"))
 {
      System.out.println("success");
 }
 else{
      System.out.println("unsuccess");

 }
     }



}
/**
 *
 * @author Bilal
 */
public class JavaApplication5 {
  static String ReadFile() throws IOException{
           String  data = new String(Files.readAllBytes(Paths.get("C:\\Users\\Bilal\\Desktop\\test.txt")));
       return data;
    }
   static boolean SpaceBreaker(char ch ){
       if(ch ==' '){
        return true ;       
}else return false;
    } 
   static boolean PunctuatorBreaker(char ch){
 String [] punctuators={";",",","{","}","(",")","[","]"};
     for(int i =0 ;i<punctuators.length; i ++ ){
         if(punctuators[i].equals(String.valueOf(ch)))
        return true;
     }
     return false;}

      static boolean OperatorBreaker(char ch){
       String [] operators={"*","/","%","&&","||",">","<"};

     for(int i =0 ;i<operators.length; i ++ ){
         if(operators[i].equals(String.valueOf(ch) ))
             return true;
     }
     return false;
   }
     static boolean linechangebreaker(char ch ){

       if(ch == '\r'){

        return true ;       
}else return false;
    }
      static boolean linechangebreaker1(char ch ){

       if(ch == '\n'){

        return true ;       
}else return false;
    }
            static boolean CommentBreaker(char ch){
       String  comment="//";
       String comment1="/";
if(comment1==(String.valueOf(ch))){
      if(comment==(String.valueOf(ch))){
             return true;

         }    }

     return false;
   }
             static boolean IsDigit(String ch){

       String [] digits={"0","1","2","3","4","5","6","7","8","9"};
 for(int j=0;j<ch.length();j++){

     for(int i =0 ;i<digits.length; i ++ ){
         if(digits[i].equals(String.valueOf(ch) ))
             return true;
     }}
     return false;
   }    
    

        public static void main(String[] args) throws IOException {
     ArrayList <String> allChars = new ArrayList();
      String fileInput = ReadFile();
      String temp = "";

      for(int i =0;i<fileInput.length();i++){

           if( CommentBreaker(fileInput.charAt(i)) == true)
               {
                while(fileInput.charAt(i)!='\r'){
                    i++;
                }}

           else if( SpaceBreaker(fileInput.charAt(i)) == true){  
                if(temp.length()!=0){
                    allChars.add(temp);
                    temp="";
             }}
           else if(OperatorBreaker(fileInput.charAt(i))==true){
        if(temp.length()==0)
      allChars.add(String.valueOf(fileInput.charAt(i)));
        else{ 
            allChars.add(temp);
                    temp="";
                    allChars.add(String.valueOf(fileInput.charAt(i)));

                  }}

   else if(PunctuatorBreaker(fileInput.charAt(i))==true){
        if(temp.length()==0)
      allChars.add(String.valueOf(fileInput.charAt(i)));
        else{
            allChars.add(temp);
                    temp="";
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                  }}
     else if(linechangebreaker(fileInput.charAt(i))==true)
      {
              if(temp.length()!=0){

                    allChars.add(temp);
                    temp="";
    i++;
                    allChars.add(String.valueOf(fileInput.charAt(i)));

                  }
              else{

                    i++;
                    allChars.add(String.valueOf(fileInput.charAt(i)));
              }
      }
     else if(linechangebreaker1(fileInput.charAt(i))==true)
      {
              if(temp.length()!=0){

                    allChars.add(temp);
                    temp="";

                 allChars.add(String.valueOf(fileInput.charAt(i)));

                  }else{
              allChars.add(String.valueOf(fileInput.charAt(i)));
              }
      }


   else if(fileInput.charAt(i)=='.'){
                   if(temp.length()==0){
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                      }
                    String regex = "[0-9]+";
                  if(temp.matches(regex)){//temphas numbers
                temp+= fileInput.charAt(i);
                    }
                  else{
                   allChars.add(temp);
                    temp="";
                    temp+=(String.valueOf(fileInput.charAt(i)));
                  }}


   else if(fileInput.charAt(i)=='\\'){
       while(fileInput.charAt(i)!='\n'){
         i++;  
   }}
    else if(fileInput.charAt(i)=='#'){
        if(temp=="this"||temp=="super"){
         temp+=(String.valueOf(fileInput.charAt(i)));
 allChars.add(temp);
         temp="";
    }
        else{
                 allChars.add(temp);
               temp="";
                allChars.add(String.valueOf(fileInput.charAt(i)));           
        }


    }
  else if(fileInput.charAt(i)=='"'){
       allChars.add(temp);
       temp="";
       temp+=(String.valueOf(fileInput.charAt(i)));
       i++;
       while(fileInput.charAt(i)!='"'){
           if(fileInput.charAt(i)=='\r')
           {allChars.add(temp);
       temp="";
       //i++;
           }else
           {  temp+=(String.valueOf(fileInput.charAt(i)));
           i++;
           }}
       temp+=(String.valueOf(fileInput.charAt(i)));
       allChars.add(temp);
       temp="";
  }
   else if(fileInput.charAt(i)=='+'){
            allChars.add(temp);
                    temp="";               
     temp+=(String.valueOf(fileInput.charAt(i)));
       i++;
       if(fileInput.charAt(i)=='+'){
       temp+=(String.valueOf(fileInput.charAt(i)));
       allChars.add(temp);
       temp="";
       }else{
    allChars.add(temp);
       temp="";
       i--;     
  }}
    else if(fileInput.charAt(i)=='-'){
            allChars.add(temp);
                    temp="";               
     temp+=(String.valueOf(fileInput.charAt(i)));
       i++;
       if(fileInput.charAt(i)=='-'){
       temp+=(String.valueOf(fileInput.charAt(i)));
       allChars.add(temp);
       temp="";
       }else{
    allChars.add(temp);
       temp="";
       i--;     
  }}
    else if(fileInput.charAt(i)=='!'){
            allChars.add(temp);
                    temp="";               
     temp+=(String.valueOf(fileInput.charAt(i)));
       i++;
       if(fileInput.charAt(i)=='='){
       temp+=(String.valueOf(fileInput.charAt(i)));
       allChars.add(temp);
       temp="";
       }else{
    allChars.add(temp);
       temp="";
       i--;     
  }}
    else if(fileInput.charAt(i)=='='){
            allChars.add(temp);
                    temp="";               
     temp+=(String.valueOf(fileInput.charAt(i)));
       i++;
       if(fileInput.charAt(i)=='='){
       temp+=(String.valueOf(fileInput.charAt(i)));
       allChars.add(temp);
       temp="";
       }else{
    allChars.add(temp);
       temp="";
       i--;     
  }}
   else {

            temp+=fileInput.charAt(i);
            }}
            for(String x : allChars){
//                if(x =='\r')
//                    x++;
      System.out.println(x);
      }
        LexicalAnalyzer input = new LexicalAnalyzer(allChars);    
        input.GenerateToken();
        Syntax a = new Syntax();
        a.parser();


      }
private static boolean isNumeric(String temp) {
    try {  
String someString = temp;
boolean isNumeric = someString.chars().allMatch( Character::isDigit );
        Integer.parseInt(temp);  
    return true;
  }
    catch(NumberFormatException e){  
    return false;  
 }
       
    }}

    