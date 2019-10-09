
import java.math.BigInteger;
import java.util.BitSet;

public class PowerCalculator {

    public static void main(String[] args) {
        Thread thread = new Thread(new ToThePower(new BigInteger("2000000"), new BigInteger("2000000")));
        thread.start();
        thread.interrupt();
    }
}

class ToThePower implements Runnable {

    BigInteger base;
    BigInteger power;

    protected ToThePower(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }

    @Override
    public void run() {
        System.out.println(base + " to the power of " + power + " = " + pow(base, power));
    }

    protected BigInteger pow(BigInteger base, BigInteger power) {
        BigInteger result = BigInteger.ONE;

        // Purposefully long computation
        for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0 ; i.add(BigInteger.ONE)) {
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("This is taking too long! Stopping thread.");
                return BigInteger.ZERO;
            }
            result = result.multiply(base);
        }
        return result;
    }
}

