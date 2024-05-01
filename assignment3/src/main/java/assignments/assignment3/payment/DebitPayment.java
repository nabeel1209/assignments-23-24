package assignments.assignment3.payment;

public class DebitPayment implements DepeFoodPaymentSystem{
    // Konstanta berupa total transaksi minimum
    public final double MINIMUM_TOTAL_PRICE = 50000;
    
    // Method untuk memproses payment
    public long processPayment(long amount){
        return amount; 
    }
}
