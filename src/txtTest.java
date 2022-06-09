import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class txtTest {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> s1=CompletableFuture.supplyAsync(()->{
            try {
                return readTxt("C:\\Lei\\1.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<String> s2=CompletableFuture.supplyAsync(()->{
            try {
                return readTxt("C:\\Lei\\2.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> s_add=CompletableFuture.allOf(s1,s2);
        s_add.thenAccept((result)->{
            try {
                System.out.println(s1.get()+s2.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(200);
        
    }

    private static String readTxt(String s) throws IOException {
        String t1= new BufferedReader(new FileReader(s)).readLine();
//        System.out.println(t1);
        try{
            Thread.sleep((long) (Math.random() * 100));
        }catch (InterruptedException e){

        }
        return t1;
    }
}
