package codecomm.data;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class CodeData implements Serializable {
    //private GregorianCalendar currentDate = new GregorianCalendar();
    //private int currentYear = currentDate.get(Calendar.YEAR);
    
    private String registered;
    private String currentRole;
    
    public CodeData() {
        registered = "n/a";
        currentRole = "n/a";
    }
    
    public CodeData(String s1, String s2) { 
         registered = s1; currentRole = s2;
    }
    
    public String getRegistered(){
        return registered;
    }
   
    public String getCurrentRole(){
        return currentRole;
    }
   
}
