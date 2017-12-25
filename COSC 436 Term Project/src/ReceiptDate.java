
import java.util.Calendar;
import java.util.Date;

public class ReceiptDate {
    
    private Date date;
    private int dayOfWeek;
    private int dayOfMonth;
    private int month;
    
    public ReceiptDate(Calendar calendar){
        date = calendar.getTime();                     
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 1 for Sun, 7 for Sat
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 8 on Jan 8
        month = calendar.get(Calendar.MONTH) + 1; // 1 for Jan
    }
    
    public static Calendar toCalendar(Date date){ 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    // Boolean methods used by Tax Computation objects to check if tax holiday.
    
    public boolean isWeekend() {
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isFirstWeek(){
        if (dayOfWeek >= 1 && dayOfWeek <=8) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isAugust() {
        if (month == 8) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
