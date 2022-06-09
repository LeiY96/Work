import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class UserTest {
    public static void main(String[] args){
        List<User> user= Arrays.asList(new User(21,"li",18,"1881234568787",0,null),
                                           new User(38,"bo",22,"13456782389",1,null),
                                           new User(44,"bo",25,"13456782382",1,null),
                                           new User(92,"zhang",66,"17264521678",1,null),
                                            new User(57,"qi",66,"13256391647",0,null),
                                            new User(53,"zhou",22,"15620763481",1,null));

//      找到任意一个年龄最大的人的姓名
//        Optional<User> older=user.stream().max(Comparator.comparing(User::getAge));
//        older.ifPresent(o->System.out.println(o.getName()));
        user.stream().max(Comparator.comparing(User::getAge)).ifPresent(o->System.out.println(o.getName()));

//        对List<User> 使用流操作，按照性别、年龄区间(11-20,21-30)，进行分组，得到Map<Integer, Map<Integer, List<User>>>
        Map<Integer, Map<String,List<User>>> findUser=user.stream().collect(
                                                                    Collectors.groupingBy(User::getGender,
                                                                            Collectors.groupingBy(u->{
                                                                                if(u.getAge()>=11&&u.getAge()<=20){
                                                                                    return "11岁-20岁";
                                                                                } else if (u.getAge()>=21&&u.getAge()<=30) {
                                                                                    return "21岁-30岁";
                                                                                }
                                                                                return "超过30岁";
                                                                            })));
        System.out.println(findUser);
//        对List<User> 使用流操作，求不同性别的年龄之和，结果形式为 Map<Integer, Integer>
        Map<Integer, Integer> sumAgeByGender=user.stream().collect(Collectors.groupingBy(User::getGender,Collectors.summingInt(User::getAge)));
        System.out.println(sumAgeByGender);

//        对List<User> 使用流操作，求不同性别的人的个数，结果形式为 Map<Integer, Integer>
        Map<Integer, Long> countByGender=user.stream().collect(Collectors.groupingBy(User::getGender,Collectors.counting()));
        System.out.println(countByGender);

//        List<User> sortUser=user.stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getAge,Comparator.reverseOrder())).collect(Collectors.toList());
//        AtomicInteger i=new AtomicInteger(1);
//        sortUser.stream().forEach(u->{System.out.println("ID:"+u.getID()+" name:"+u.getName());System.out.println(i.get());});
//
//        Predicate<User> isMale=new Predicate<User>() {
//            @Override
//            public boolean test(User user) {
//                return user.getGender()==0?true:false;
//            }
//        };
//
//        Consumer<User> zhuan=new Consumer<User>() {
//            @Override
//            public void accept(User user) {
//                if(user.getGender()==0) {
//                    user.setGenderCode("male");
//                } else {
//                    user.setGenderCode("female");
//                }
//            }
//        };
//
//
//
//        Function<String, String> pn=(String s)-> String.valueOf(s.charAt(s.length()-1));
//        User user1=new User(21,"li",18,"188123456877",0,null);
//        System.out.println();
//        System.out.println(pn.apply(user1.getPhoneNumber()));
//
//        System.out.println(isMale.test(user1));
//        zhuan.accept(user1);
//        System.out.println(user1.getGenderCode());










    }



}
