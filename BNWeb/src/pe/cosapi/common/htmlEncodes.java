package pe.cosapi.common;

import java.text.*;
import java.util.*;

public class htmlEncodes {
	
	 private static final char c[] = { '<', '>', '&', '\"'};
	 private static final String expansion[] = {"&lt;", "&gt;", "&amp;",
	                                     "&quot;"};
	 public static String HTMLEncode(String s) {
	      StringBuffer st = new StringBuffer();
	      for (int i = 0; i < s.length(); i++) {
	          boolean copy = true;
	          char ch = s.charAt(i);
	          for (int j = 0; j < c.length ; j++) {
	            if (c[j]==ch) {
	                st.append(expansion[j]);
	                copy = false;
	                break;
	            }
	          }
	          if (copy) st.append(ch);
	      }
	      return st.toString();
	    }
}
