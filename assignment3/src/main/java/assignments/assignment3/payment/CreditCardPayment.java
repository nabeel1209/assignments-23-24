package assignments.assignment3.payment;

public class CreditCardPayment implements DepeFoodPaymentSystem{
    // Konstanta berupa persentase transaction fee
    public final double TRANSACTION_FEE_PERCENTAGE = 0.02;
    
    // Method untuk memproses payment
    public long processPayment(long amount){
        return amount;
    }

    // Method untuk mereturn transaction fee dari setiap transaksi
    public long countTransaction(long amount){
        return (long)(TRANSACTION_FEE_PERCENTAGE*amount);
    }
}
