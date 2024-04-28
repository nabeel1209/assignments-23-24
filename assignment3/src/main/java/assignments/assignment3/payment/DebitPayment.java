package assignments.assignment3.payment;

public class DebitPayment implements DepeFoodPaymentSystem{
    //TODO implementasikan class di sini
    public double MINIMUM_TOTAL_PRICE = 50000;
    
    // Anda dibebaskan untuk membuat method yang diperlukan
    public long processPayment(long amount){
        if(amount < MINIMUM_TOTAL_PRICE){
            return -1;
        }else{
            return amount; // belum implement
        }
    }
}
