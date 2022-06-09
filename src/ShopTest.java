import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

public class ShopTest {
    public static void main(String[] args){

        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        Shop shop=new Shop("Best Shop");
        long start=System.nanoTime();
        Future<Double> futurePrice=shop.getPriceAsync("my favorite product");
        long invocationTime=((System.nanoTime()-start)/1_000_000);
        System.out.println("Invocation returned after "+invocationTime+" msecs");
        doSomethingElse();
        try{
            double price=futurePrice.get();
            System.out.printf("Price is %.2f%n",price);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        long retrievalTime=((System.nanoTime()-start)/1_000_000);
        System.out.println("Price return after "+retrievalTime+" msecs");
        
    }

    private static void doSomethingElse() {
    }

}
