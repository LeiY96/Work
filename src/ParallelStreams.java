import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author 11236
 */
public class ParallelStreams {
     public void ParallelStreams(){

    }
    //顺序求和
    public static long sequentialSum(long n){
        return Stream.iterate(1L,i->i+1).limit(n).reduce(0L,Long::sum);
    }

    //并行求和
    public static long parallelSum(long n){
        return Stream.iterate(1L,i->i+1).limit(n).parallel().reduce(0L,Long::sum);
    }


    public static long parallelRangedSum(long n){
         return LongStream.rangeClosed(1,n).parallel().reduce(0L,Long::sum);
    }
    //测量对前n个自然数求和的函数性能
    public long measureSumPerf(Function<Long,Long> adder,long n){
        long faster=Long.MAX_VALUE;
        for(int i=0;i<10;i++){
            long start=System.nanoTime();
            long sum=adder.apply(n);
            long duration=(System.nanoTime()-start)/1_000_000;
            System.out.println("Result: "+sum);
            if(duration<faster){
                faster=duration;
            }

        }
        return faster;
    }


}
