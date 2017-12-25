import java.util.ArrayList;

public abstract class TaxComputation {
    public abstract double computeTax(ArrayList<Product> items, ReceiptDate date);
    public abstract boolean taxHoliday(ReceiptDate date);
}
