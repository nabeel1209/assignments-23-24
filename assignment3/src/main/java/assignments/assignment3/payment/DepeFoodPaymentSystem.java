package assignments.assignment3.payment;

public interface DepeFoodPaymentSystem {
    // Atribut mengikuti soal
    public long saldo = 0;
    
    // Abstracty method untuk memproses payment
    public long processPayment(long amount);
}
