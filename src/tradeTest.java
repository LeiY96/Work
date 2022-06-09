import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @author 11236
 */
public class tradeTest {
    public static void main(String[] args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //找出2011年发生的所有交易，并按照交易额倒序排序
        List<Transaction> trs_2011=transactions.stream().filter(t->t.getYear()==2011).sorted(comparing(Transaction::getValue,reverseOrder())).collect(toList());
        System.out.println(trs_2011);

        //交易员都在哪些不同城市干过
        List<String> city_count=transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(toList());
        System.out.println(city_count);

        //查找所有来自剑桥的交易员，并按照姓名排序
        List<Trader> trader_cambridge=transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge")).sorted(comparing(Trader::getName)).collect(toList());
        System.out.println(trader_cambridge);

        //返回所有交易员的姓名字符串，并按照字母顺序排序
//        List<String> trader_name=transactions.stream().map(Transaction::getTrader).sorted(comparing(Trader::getName)).map(Trader::getName).distinct().collect(toList());
        String trader_name=transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().collect(Collectors.joining());
        System.out.println(trader_name);

        //有没有交易员在米兰工作
        if(transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"))){
            System.out.println("Someone works in Milan");
        }

        //打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).map(Transaction::getValue).forEach(System.out::println);

        //所有交易中最高交易额
        Optional<Integer> value_max=transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        value_max.ifPresent(x->System.out.println(x));

        //找到交易额最小的交易
//        Optional<Transaction> tMinvalue =transactions.stream().reduce((t1,t2)->t1.getValue()<t2.getValue()?t1:t2);
        Optional<Transaction> tMinvalue=transactions.stream().min(comparing(Transaction::getValue));
        tMinvalue.ifPresent(x->System.out.println(x));





    }
}
