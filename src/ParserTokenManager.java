/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
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
import ast.ASTPrintln;
import ast.ASTSeq;
import ast.ASTString;
import ast.ASTSub;
import ast.ASTNeg;
import ast.ASTNeq;
import ast.ASTId;
import ast.ASTIf;
import ast.ASTLet;
import ast.ASTMod;
import ast.ASTLess;
import ast.ASTLessEq;
import ast.ASTFun;
import ast.ASTGreater;
import ast.ASTGreaterEq;
import ast.ASTWhile;
import ast.ASTFor;
import ast.ASTDerref;
import ast.ASTNew;
import ast.ASTStruct;
import ivalues.IValue;
import itypes.*;
import environment.Environment;
import environment.EnvironmentClass;
import compiler.Compiler;

/** Token Manager. */
public class ParserTokenManager implements ParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x8000000c000L) != 0L)
            return 0;
         if ((active0 & 0x80000800L) != 0L)
         {
            jjmatchedKind = 40;
            return 20;
         }
         if ((active0 & 0x70020580L) != 0L)
         {
            jjmatchedKind = 40;
            return 22;
         }
         if ((active0 & 0x4000200L) != 0L)
         {
            jjmatchedKind = 40;
            return 9;
         }
         if ((active0 & 0x200000000000L) != 0L)
            return 35;
         if ((active0 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 40;
            return 16;
         }
         return -1;
      case 1:
         if ((active0 & 0x44000000L) != 0L)
            return 22;
         if ((active0 & 0xb8020d80L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 1;
            return 22;
         }
         if ((active0 & 0x200L) != 0L)
            return 8;
         return -1;
      case 2:
         if ((active0 & 0x80020c80L) != 0L)
            return 22;
         if ((active0 & 0x38000100L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 2;
            return 22;
         }
         return -1;
      case 3:
         if ((active0 & 0x18000000L) != 0L)
            return 22;
         if ((active0 & 0x20000100L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 3;
            return 22;
         }
         return -1;
      case 4:
         if ((active0 & 0x20000000L) != 0L)
            return 22;
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 4;
            return 22;
         }
         return -1;
      case 5:
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 5;
            return 22;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 18;
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 37:
         return jjStopAtPos(0, 46);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 40:
         return jjStopAtPos(0, 47);
      case 41:
         return jjStopAtPos(0, 48);
      case 42:
         return jjStopAtPos(0, 44);
      case 43:
         return jjStopAtPos(0, 42);
      case 44:
         return jjStopAtPos(0, 13);
      case 45:
         return jjStartNfaWithStates_0(0, 43, 0);
      case 47:
         return jjStartNfaWithStates_0(0, 45, 35);
      case 58:
         jjmatchedKind = 39;
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 59:
         jjmatchedKind = 49;
         return jjMoveStringLiteralDfa1_0(0x4000000000000L);
      case 60:
         jjmatchedKind = 37;
         return jjMoveStringLiteralDfa1_0(0x1000000000L);
      case 61:
         jjmatchedKind = 15;
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 62:
         jjmatchedKind = 38;
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 91:
         return jjStopAtPos(0, 20);
      case 93:
         return jjStopAtPos(0, 21);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x10000400L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x80000800L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x4000200L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x8000000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x20000000L);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 126:
         return jjStopAtPos(0, 32);
      default :
         return jjMoveNfa_0(1, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(1, 33);
         break;
      case 59:
         if ((active0 & 0x4000000000000L) != 0L)
            return jjStopAtPos(1, 50);
         break;
      case 61:
         if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(1, 14);
         else if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(1, 16);
         else if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(1, 19);
         else if ((active0 & 0x800000000L) != 0L)
            return jjStopAtPos(1, 35);
         else if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(1, 36);
         break;
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x20080L);
      case 102:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(1, 26, 22);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x28000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000L);
      case 110:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(1, 9, 8);
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      case 111:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(1, 30, 22);
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x100L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 124:
         if ((active0 & 0x400000000L) != 0L)
            return jjStopAtPos(1, 34);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(2, 10, 22);
         break;
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000100L);
      case 110:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(2, 11, 22);
         break;
      case 114:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(2, 31, 22);
         break;
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000L);
      case 116:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(2, 7, 22);
         break;
      case 119:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(2, 17, 22);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(3, 28, 22);
         break;
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000L);
      case 110:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(3, 27, 22);
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(4, 29, 22);
         break;
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(6, 8, 22);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 41;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 9:
               case 22:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(22);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(22);
                  break;
               case 16:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(22);
                  break;
               case 20:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(22);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 41)
                        kind = 41;
                     jjCheckNAdd(23);
                  }
                  else if (curChar == 47)
                     jjAddStates(0, 1);
                  else if (curChar == 39)
                     jjCheckNAddStates(2, 4);
                  else if (curChar == 34)
                     jjCheckNAddStates(5, 7);
                  else if (curChar == 61)
                     jjCheckNAdd(0);
                  else if (curChar == 45)
                     jjCheckNAdd(0);
                  break;
               case 35:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(38, 40);
                  else if (curChar == 47)
                  {
                     if (kind > 1)
                        kind = 1;
                     jjCheckNAdd(36);
                  }
                  break;
               case 0:
                  if (curChar == 62 && kind > 12)
                     kind = 12;
                  break;
               case 2:
                  if (curChar == 61)
                     jjCheckNAdd(0);
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(23);
                  break;
               case 24:
                  if (curChar == 34)
                     jjCheckNAddStates(5, 7);
                  break;
               case 26:
                  jjCheckNAddStates(5, 7);
                  break;
               case 27:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddStates(5, 7);
                  break;
               case 28:
                  if (curChar == 34 && kind > 51)
                     kind = 51;
                  break;
               case 29:
                  if (curChar == 39)
                     jjCheckNAddStates(2, 4);
                  break;
               case 31:
                  jjCheckNAddStates(2, 4);
                  break;
               case 32:
                  if ((0xffffff7fffffffffL & l) != 0L)
                     jjCheckNAddStates(2, 4);
                  break;
               case 33:
                  if (curChar == 39 && kind > 51)
                     kind = 51;
                  break;
               case 34:
                  if (curChar == 47)
                     jjAddStates(0, 1);
                  break;
               case 36:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 1)
                     kind = 1;
                  jjCheckNAdd(36);
                  break;
               case 37:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(38, 40);
                  break;
               case 38:
                  if ((0x3ff000100002600L & l) != 0L)
                     jjCheckNAddTwoStates(38, 40);
                  break;
               case 39:
                  if (curChar == 47 && kind > 6)
                     kind = 6;
                  break;
               case 40:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 39;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 9:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 40)
                        kind = 40;
                     jjCheckNAdd(22);
                  }
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 8:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 40)
                        kind = 40;
                     jjCheckNAdd(22);
                  }
                  if (curChar == 116)
                  {
                     if (kind > 23)
                        kind = 23;
                  }
                  break;
               case 16:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 40)
                        kind = 40;
                     jjCheckNAdd(22);
                  }
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 20:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 40)
                        kind = 40;
                     jjCheckNAdd(22);
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 1:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 40)
                        kind = 40;
                     jjCheckNAdd(22);
                  }
                  if ((0x4000000040L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 20;
                  else if ((0x10000000100000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 16;
                  else if ((0x4000000040000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 12;
                  else if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 9;
                  else if ((0x400000004L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 3:
                  if ((0x400000004L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 4:
                  if (curChar == 108 && kind > 22)
                     kind = 22;
                  break;
               case 5:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 10:
                  if ((0x4000000040000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 11:
                  if (curChar == 102 && kind > 24)
                     kind = 24;
                  break;
               case 12:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 13:
                  if ((0x10000000100000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 14:
                  if (curChar == 101 && kind > 25)
                     kind = 25;
                  break;
               case 15:
                  if (curChar == 117)
                     jjCheckNAdd(14);
                  break;
               case 17:
                  if ((0x4000000040L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 18:
                  if (curChar == 115)
                     jjCheckNAdd(14);
                  break;
               case 19:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 21:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(22);
                  break;
               case 22:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(22);
                  break;
               case 25:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 26:
                  jjCheckNAddStates(5, 7);
                  break;
               case 27:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(5, 7);
                  break;
               case 30:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 31:
                  jjCheckNAddStates(2, 4);
                  break;
               case 32:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(2, 4);
                  break;
               case 36:
                  if (kind > 1)
                     kind = 1;
                  jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 38:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     jjAddStates(8, 9);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 26:
               case 27:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddStates(5, 7);
                  break;
               case 31:
               case 32:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddStates(2, 4);
                  break;
               case 36:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 1)
                     kind = 1;
                  jjstateSet[jjnewStateCnt++] = 36;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 41 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   35, 37, 30, 32, 33, 25, 27, 28, 38, 40, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, "\154\145\164", 
"\160\162\151\156\164\154\156", "\151\156", "\145\156\144", "\146\165\156", null, "\54", "\75\75", "\75", 
"\72\75", "\156\145\167", "\41", "\41\75", "\133", "\135", null, null, null, null, 
"\151\146", "\164\150\145\156", "\145\154\163\145", "\167\150\151\154\145", "\144\157", 
"\146\157\162", "\176", "\46\46", "\174\174", "\76\75", "\74\75", "\74", "\76", "\72", null, 
null, "\53", "\55", "\52", "\57", "\45", "\50", "\51", "\73", "\73\73", null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xfffffffffff81L, 
};
static final long[] jjtoSkip = {
   0x7eL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[41];
static private final int[] jjstateSet = new int[82];
static protected char curChar;
/** Constructor. */
public ParserTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public ParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 41; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
