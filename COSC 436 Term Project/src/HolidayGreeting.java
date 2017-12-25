import java.util.ArrayList;

public class HolidayGreeting implements AddOn, SecondaryHeading {
    
    @Override
    public boolean applies(ArrayList<Product> items) {
        return true; // SecondaryHeading decorators always applied
    }
    
    @Override
    public String getLines(){
        return "* Summer Sales are Heating Up at Best Buy *";
    }
    
}
