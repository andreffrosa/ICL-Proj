/* Generated By:JavaCC: Do not edit this line. Parser.java */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ast.ASTAnd;
import ast.ASTApply;
import ast.ASTAssign;
import ast.ASTBool;
import ast.ASTDiv;
import ast.ASTEq;
import ast.ASTMult;
import ast.ASTNode;
import ast.ASTNot;
import ast.ASTNum;
import ast.ASTOr;
import ast.ASTPlus;
import ast.ASTSeq;
import ast.ASTSub;
import ast.ASTNeg;
import ast.ASTNeq;
import ast.ASTId;
import ast.ASTIf;
import ast.ASTLet;
import ast.ASTLess;
import ast.ASTLessEq;
import ast.ASTFun;
import ast.ASTGreater;
import ast.ASTGreaterEq;
import ast.ASTWhile;
import ast.ASTFor;
import ast.ASTDerref;
import ast.ASTNew;
import ivalues.IValue;
import environment.Environment;
import environment.EnvironmentClass;

/** ID lister. */
public class Parser implements ParserConstants {

  /** Main entry point. */
  public static void main(String args[]) {
    Parser parser = new Parser(System.in);
    ASTNode exp;

    while (true) {
    try {
    Environment<IValue> globalEnv = new EnvironmentClass<IValue>();
    exp = parser.Start();
    System.out.println( exp.eval(globalEnv) );
    } catch (Exception e) {
      System.out.println ("Syntax Error!");
      parser.ReInit(System.in);
    }
    }
  }

  static final public ASTNode Start() throws ParseException {
  ASTNode t;
    t = Multiple_Exp();
    jj_consume_token(EL);
     {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Multiple_Exp() throws ParseException {
  ASTNode t, t2;
    t = Exp();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMI_COLON:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMI_COLON);
      t2 = Multiple_Exp();
        t = new ASTSeq(t, t2);
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Exp() throws ParseException {
  ASTNode t1, t2;
  Token op;
    t1 = Clause();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case OR:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        op = jj_consume_token(AND);
        break;
      case OR:
        op = jj_consume_token(OR);
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Exp();
        switch(op.kind) {
          case AND: t1 = new ASTAnd(t1, t2); break;
          case OR: t1 = new ASTOr(t1, t2); break;
          }
    }
   {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Clause() throws ParseException {
  ASTNode t1, t2;
    // t1 = EArithmetic() ( <D_EQ> t2=EArithmetic() { t1 = new ASTEq(t1, t2); } | <N_EQ> t2=EArithmetic() { t1 = new ASTNeq(t1, t2); } )?
     //	t = EArithmetic() | t = Comp()
     t1 = EArithmetic();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case D_EQ:
    case N_EQ:
    case GREATER_EQ:
    case LOWER_EQ:
    case LOWER:
    case GREATER:
      t1 = Comp(t1);
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Comp(ASTNode t1) throws ParseException {
  Token op;
  ASTNode t2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case D_EQ:
      op = jj_consume_token(D_EQ);
      break;
    case N_EQ:
      op = jj_consume_token(N_EQ);
      break;
    case GREATER:
      op = jj_consume_token(GREATER);
      break;
    case LOWER:
      op = jj_consume_token(LOWER);
      break;
    case GREATER_EQ:
      op = jj_consume_token(GREATER_EQ);
      break;
    case LOWER_EQ:
      op = jj_consume_token(LOWER_EQ);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    t2 = Clause();
                        switch(op.kind) {
                          case D_EQ: t1 = new ASTEq(t1, t2); break;
                          case N_EQ: t1 = new ASTNeq(t1, t2); break;
                          case GREATER: t1 = new ASTGreater(t1, t2); break;
                          case LOWER: t1 = new ASTLess(t1, t2); break;
                          case GREATER_EQ: t1 = new ASTGreaterEq(t1, t2); break;
                          case LOWER_EQ: t1 = new ASTLessEq(t1, t2); break;
                          }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode EArithmetic() throws ParseException {
  Token op;
  ASTNode t1, t2;
    t1 = Term();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        op = jj_consume_token(PLUS);
        break;
      case MINUS:
        op = jj_consume_token(MINUS);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = EArithmetic();
                        if (op.kind == PLUS)
                                t1 = new ASTPlus(t1,t2);
                else
                        t1 = new ASTSub(t1,t2);
    }
    {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Term() throws ParseException {
        Token op;
        ASTNode t1, t2;
        List<ASTNode> args;
    t1 = Fact();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAR:
      jj_consume_token(LPAR);
      args = ArgList();
      jj_consume_token(RPAR);
                                      t1 = new ASTApply(t1, args);
      break;
    case ATTRIB:
      jj_consume_token(ATTRIB);
      t2 = Multiple_Exp();
                                         t1 = new ASTAssign(t1, t2);
      break;
    default:
      jj_la1[9] = jj_gen;
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIMES:
        case DIV:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_4;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case TIMES:
          op = jj_consume_token(TIMES);
          break;
        case DIV:
          op = jj_consume_token(DIV);
          break;
        default:
          jj_la1[8] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        t2 = Term();
    if (op.kind == TIMES)
        t1 = new ASTMult(t1, t2);
    else
        t1 = new ASTDiv(t1, t2);
      }
    }
       {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public List<ASTNode> ArgList() throws ParseException {
  ASTNode t;
  List<ASTNode> list = new LinkedList<ASTNode>();
  List<ASTNode> list2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LET:
    case FUN:
    case NEW:
    case DERREF:
    case Bool:
    case IF:
    case WHILE:
    case FOR:
    case NOT:
    case ID:
    case Num:
    case MINUS:
    case LPAR:
      t = Multiple_Exp();
                      list.add(t);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[10] = jj_gen;
          break label_5;
        }
        jj_consume_token(COMMA);
        list2 = ArgList();
                                                              list.addAll(list2);
      }
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
    {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  static final public Map<String, ASTNode> EE() throws ParseException {
  ASTNode t;
  Token id;
  Map<String, ASTNode> decls = new HashMap<String,ASTNode>();
    id = jj_consume_token(ID);
    jj_consume_token(EQ);
    t = Exp();
                           decls.put(id.image, t);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      id = jj_consume_token(ID);
      jj_consume_token(EQ);
      t = Exp();
                                                                                     decls.put(id.image, t);
    }
    {if (true) return decls;}
    throw new Error("Missing return statement in function");
  }

  static final public List<String> PL() throws ParseException {
  Token id;
  List<String> list = new LinkedList<String>();
  List<String> list2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      id = jj_consume_token(ID);
             list.add(id.image);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_7;
        }
        jj_consume_token(COMMA);
        list2 = PL();
                                                       list.addAll(list2);
      }
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    {if (true) return list;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Fact() throws ParseException {
  Token n;
  ASTNode t1, t2, t3;
  List<String> args;
  Map<String,ASTNode> decls;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Num:
      n = jj_consume_token(Num);
                t1 = new ASTNum(Integer.parseInt(n.image));
      break;
    case ID:
      n = jj_consume_token(ID);
               t1 = new ASTId(n.image);
      break;
    case Bool:
      n = jj_consume_token(Bool);
                 t1 = new ASTBool(Boolean.parseBoolean(n.image));
      break;
    case LPAR:
      jj_consume_token(LPAR);
      t1 = Exp();
      jj_consume_token(RPAR);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      t1 = Fact();
                          t1 = new ASTNeg(t1);
      break;
    case NOT:
      jj_consume_token(NOT);
      t1 = Fact();
                        t1 = new ASTNot(t1);
      break;
    case LET:
      jj_consume_token(LET);
      decls = EE();
      jj_consume_token(IN);
      t2 = Multiple_Exp();
      jj_consume_token(END);
                                                        t1 = new ASTLet(decls,t2);
      break;
    case FUN:
      jj_consume_token(FUN);
      args = PL();
      jj_consume_token(R_ARROW);
      t2 = Multiple_Exp();
      jj_consume_token(END);
                                                          t1 = new ASTFun(args, t2);
      break;
    case NEW:
      jj_consume_token(NEW);
      t1 = Exp();
                       t1 = new ASTNew(t1);
      break;
    case DERREF:
      jj_consume_token(DERREF);
      t1 = Fact();
                               t1 = new ASTDerref(t1);
      break;
    case IF:
      jj_consume_token(IF);
      t1 = Multiple_Exp();
      jj_consume_token(THEN);
      t2 = Multiple_Exp();
      jj_consume_token(ELSE);
      t3 = Multiple_Exp();
      jj_consume_token(END);
                                                                                           t1 = new ASTIf(t1, t2, t3);
      break;
    case WHILE:
      jj_consume_token(WHILE);
      t1 = Multiple_Exp();
      jj_consume_token(DO);
      t2 = Multiple_Exp();
      jj_consume_token(END);
                                                                   t1 = new ASTWhile(t1, t2);
      break;
    case FOR:
      jj_consume_token(FOR);
      jj_consume_token(LPAR);
      decls = EE();
      jj_consume_token(SEMI_COLON);
      t1 = Exp();
      jj_consume_token(SEMI_COLON);
      t2 = Exp();
      jj_consume_token(RPAR);
      jj_consume_token(IN);
      t3 = Multiple_Exp();
      jj_consume_token(END);
                                                                                                                    t1= new ASTFor(decls, t1, t2, t3);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x6000000,0x6000000,0x78010800,0x78010800,0x0,0x0,0x0,0x0,0x2000,0x400,0x1a6c120,0x400,0x400,0x0,0x1a6c120,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x100,0x0,0x0,0x0,0x0,0xc,0xc,0x30,0x30,0x40,0x0,0x4b,0x0,0x0,0x1,0x4b,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[42];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 16; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 42; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
