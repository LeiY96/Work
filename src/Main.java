import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author 11236
 */
public class Main {
    public enum CaloricLevel {DIET,NORMAL,FAT}

    //判断质数的方法
    public boolean isPrime(int candidate){
        int candidateRoot= (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(i->candidate%i==0);
    }

    public Map<Boolean,List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n).boxed()
                        .collect(partitioningBy(candidate->isPrime(candidate)));
    }

    public static void main(String[] args) throws IOException {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );


        //数值流求卡路里
        int calories=menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(calories);
        //设置默认值
        OptionalInt maxCalories=menu.stream().mapToInt(Dish::getCalories).max();
        int maxC=maxCalories.orElse(1);
        //数值范围range和rangeClosed
        IntStream evenNumbers=IntStream.range(1,100).filter(n->n%2==0);
        System.out.println(evenNumbers.count());

        //生成三元数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                                                    .boxed().flatMap(a->IntStream.rangeClosed(a,100)
                                                                                 .filter(b->Math.sqrt(a*a+b*b)%1==0)
                                                                                 .mapToObj(b->new int[]{a,b, (int) Math.sqrt(a*a+b*b)}));

        pythagoreanTriples.limit(5).forEach(t->System.out.println(t[0]+","+t[1]+","+t[2]));
        //更好的写法
        Stream<double[]> pythagoreanTriples2=IntStream.rangeClosed(1,100).boxed()
                                                      .flatMap(a->IntStream.rangeClosed(a,100)
                                                                           .mapToObj(b->new double[]{a,b,Math.sqrt(a*a+b*b)}))
                                                      .filter(t->t[2]%1==0);
        pythagoreanTriples2.limit(3).forEach(t->System.out.println(t[0]+","+t[1]+","+t[2]));


        //由值创建流
        Stream<String> stream=Stream.of("Java 8 ","Lambdas","In ","Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        //通过empty得到一个空流
        Stream<String> emptyStream=Stream.empty();

        //由数组创建流
        int[] numbersT={2,3,5,7,11,13};
        System.out.println(Arrays.stream(numbersT).sum());

        //由文件生成流
        long uniqueWords=0;
        try(Stream<String> lines= Files.lines(Paths.get("C:\\Lei\\untitled\\src\\data.txt"), Charset.defaultCharset())){
            uniqueWords=lines.flatMap(line->Arrays.stream(line.split(" ")))
                             .distinct()
                             .count();
        }catch (IOException e){

        }

        //由函数创建流--迭代
        Stream.iterate(0,n->n+2)
              .limit(10)
              .forEach(System.out::println);
        //斐波那契元组序列
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
              .limit(5)
              .forEach(t->System.out.println("("+t[0]+","+t[1]+")"));
        //由斐波那契元组序列生成斐波那契数列
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
              .limit(5)
              .map(t->t[0])
              .forEach(System.out::println);

        //由函数创建流--生成
        Stream.generate(Math::random)
              .limit(5)
              .forEach(System.out::println);

        //由generate生成斐波那契数列
        IntSupplier fib=new IntSupplier() {
            private int previous=0;
            private int current=1;
            public int getAsInt() {
                int oldPrevious=this.previous;
                int nextValue=this.previous+this.current;
                this.previous=this.current;
                this.current=nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(6).forEach(System.out::println);


        //计算流中有多少个菜
        int counter=menu.stream().map(dish -> 1).reduce(0,Integer::sum);
        long counter2=menu.stream().count();
        System.out.println(counter+" "+counter2);

        List<String> threeHighCaloriesDishNames=menu.stream()
                                                    .filter(d->d.getCalories()>300)
                                                    .map(Dish::getName)
                                                    .limit(3)
                                                    .collect(Collectors.toList());


        List<Dish> vegetarianMenu=menu.stream()
                                      .filter(Dish::isVegetarian)
                                      .collect(Collectors.toList());


//
//        List<String> threeHighCaloriesDishNames=menu.stream()
//                                                    .filter(d->{
//                                                                  System.out.println("filtering "+d.getName());
//                                                                  return d.getCalories()>300;})
//                                                    .map(d->{
//                                                        System.out.println("mapping "+d.getName());
//                                                        return d.getName();})
//                                                    .limit(3)
//                                                    .collect(Collectors.toList());

        System.out.println(threeHighCaloriesDishNames);

        List<String> names = menu.stream()
                                 .map(Dish::getName)
                                 .collect(Collectors.toList());

//        List<Integer> numbers=Arrays.asList(1,2,1,3,3,2,4);
//        numbers.stream().filter(i->i%2==0).distinct().forEach(System.out::println);

//        List<String> title = Arrays.asList("Java8", "In", "Action");
//        Stream<String> s = title.stream();
//        s.forEach(System.out::println);
////        s.forEach(System.out::println);



        List<Integer> numbers1=Arrays.asList(1,2,3);
        List<Integer> numbers2=Arrays.asList(3,4);
        List<int []> pairs=numbers1.stream()
                                   .flatMap(i->numbers2.stream()
                                                       .filter(j->(i+j)%3==0)
                                                       .map(j->new int[]{i,j}))
                                   .collect(Collectors.toList());


        //检查谓词是否至少匹配一个元素
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("Have");
        }

        //检查谓词是否匹配所有元素-都有
        if(menu.stream().allMatch(dish -> dish.getCalories()<1000)){
            System.out.println("allMatch");
        }else{
            System.out.println("Not_allMatch");
        }

        //检查谓词是否匹配所有元素-都没有
        if(menu.stream().noneMatch(dish -> dish.getCalories()>=1000)){
            System.out.println("NoneMatch");
        }else{
            System.out.println("Not_NoneMatch");
        }

        //查找当前流中的任意元素
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));

        //查找第一个元素
        List<Integer> someNumbers=Arrays.asList(1,2,3,4,5);
        someNumbers.stream().map(x->x*x).filter(x->x%3==0).findFirst().ifPresent(x->System.out.println(x));

        List<Integer> numbers=Arrays.asList(2,3,5,7,9);
        //for-each求和
        int sum=0;
        for (int x:numbers
             ) {
            sum+=x;
        }
        System.out.println(sum);

        //reduce方法求和
        int sum_reduce=numbers.stream().reduce(0,(a,b)->(a+b));
        System.out.println(sum_reduce);
        //reduce方法求积
        int product_reduce=numbers.stream().reduce(1,(a,b)->(a*b));
        System.out.println(product_reduce);
        //reduce方法求和简化后
        int sum_reduce_simple=numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum_reduce_simple);
        //无初始值的reduce重载变体
        Optional<Integer> sum_null=numbers.stream().reduce((a,b)->(a+b));

        //最大最小值
        Optional<Integer> max=numbers.stream().reduce(Integer::max);
        Optional<Integer> min=numbers.stream().reduce(Integer::min);
        max.ifPresent(x->System.out.println(x));
        min.ifPresent(x->System.out.println(x));



        //查找流中的最大和最小值
        Comparator<Dish> dishCaloriesComparator= comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish= menu.stream().max(dishCaloriesComparator);
        mostCalorieDish.ifPresent(System.out::println);
        //查找流的和
        int totalCalories=menu.stream().collect(summingInt(Dish::getCalories));

        //summarizingInt工厂
        IntSummaryStatistics menuStatistics=menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);


        //连接字符串
        String shortMenu=menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu);


        //简单分组
        Map<Dish.Type,List<Dish>> dishesByType=menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        //条件分组
        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel=menu.stream().collect(
                groupingBy(dish -> {
                    if(dish.getCalories()<=400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    }else{
                        return CaloricLevel.FAT;
                    }
                }));
        System.out.println(dishesByCaloricLevel);


        //多级分组
        Map<Dish.Type,Map<CaloricLevel,List<Dish>>> dishesByTypeCaloricLevel=menu.stream().collect(
                groupingBy(Dish::getType,groupingBy(dish->{
                    if(dish.getCalories()<=400) {
                        return CaloricLevel.DIET;
                    } else if(dish.getCalories()<=700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })));
        System.out.println(dishesByTypeCaloricLevel);

        //按子组收集数据
        Map<Dish.Type,Long> typesCount=menu.stream().collect(groupingBy(Dish::getType,counting()));
        System.out.println(typesCount);

        //查找每个子组中热量最高的Dish
        Map<Dish.Type,Dish> mostCaloricByType=menu.stream()
                                                  .collect(groupingBy(Dish::getType,
                                                          collectingAndThen(
                                                                  maxBy(comparingInt(Dish::getCalories)),
                                                          Optional::get)));
        System.out.println(mostCaloricByType);

        //分区
        Map<Boolean,Map<Dish.Type,List<Dish>>> vegetarianDishesByType=menu.stream()
                                                                          .collect(
                                                                                  partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType)));
        System.out.println(vegetarianDishesByType);

        //将数字分为质数和非质数

    }
}
