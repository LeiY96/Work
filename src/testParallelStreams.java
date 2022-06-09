public class testParallelStreams {
    public static void main(String[] args){
        ParallelStreams p = new ParallelStreams();
//        System.out.println("顺序执行时间"+p.measureSumPerf(ParallelStreams::sequentialSum,10_000_000)+"msecs");
//        System.out.println("Parallel sum done in: " +
//                p.measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs" );
        System.out.println("Parallel range sum done in:" +
                p.measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) +
                " msecs");
    }
}
