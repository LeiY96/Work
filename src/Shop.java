import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    String product;
    public  Shop(String product){
        this.product=product;
    }


    public double getPrice(String product){
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice=new CompletableFuture<>();
        new Thread(()->{
            try{
                double price=calculatePrice(product);
                futurePrice.complete(price);
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    public static void delay(){
        try{
            Thread.sleep(1000L);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    private double calculatePrice(String product){
        delay();
        Random r=new Random();
        return r.nextDouble()*product.charAt(0)+product.charAt(1);
    }
}
