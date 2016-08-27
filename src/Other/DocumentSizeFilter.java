/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Abdullah
 */
class DocumentSizeFilter extends DocumentFilter {
  /*
    This class was obtained from : http://www.java2s.com/Tutorials/Java/Swing_How_to/JTextPane/Limit_the_amount_of_characters_in_JTextPane_as_the_user_types.htm
    Author : java2s.com
    Date : 2012
  */  
    
  int len;
  public DocumentSizeFilter(int max_Chars) {
    len = max_Chars;
  }
  public void insertString(FilterBypass fb, int offset, String str,
      AttributeSet a) throws BadLocationException {
    System.out.println("In DocumentSizeFilter's insertString method");
    if ((fb.getDocument().getLength() + str.length()) <= len){
      super.insertString(fb, offset, str, a);
    }
  }
  public void replace(FilterBypass fb, int offset, int length, String str,
      AttributeSet a) throws BadLocationException {
    System.out.println("In DocumentSizeFilter's replace method");
    if ((fb.getDocument().getLength() + str.length() - length) <= len){
      super.replace(fb, offset, length, str, a);
    }
  }
}
