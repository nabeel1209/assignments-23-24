package assignments.assignment3.payment;

public class CreditCardPayment implements DepeFoodPaymentSystem{
    //TODO: implementasikan class yang implement interface di sini
    public double TRANSACTION_FEE_PERCENTAGE = 2/100;
    // Anda dibebaskan untuk membuat method yang diperlukan
    public long processPayment(long amount){
        return amount;
    }

    public long countTransaction(long amount){
        return (long)TRANSACTION_FEE_PERCENTAGE*amount;
    }
}
