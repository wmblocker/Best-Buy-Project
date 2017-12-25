
public class PostDecorator extends Decorator {
    
    private AddOn a;
    
    public PostDecorator(AddOn a, Receipt r) {
        super(r);
        this.a = a;
    }
    
    @Override
    public void prtReceipt(){
        callTrailer();
        System.out.println("\n" + a.getLines() + "\n");
    }
    
}
