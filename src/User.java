import java.lang.reflect.Type;

public class User {
    private final int ID;
    private final String name;
    private final int age;
    private final String phoneNumber;
    private final int gender;

    private  String genderCode;

    public User(int ID,String name,int age,String phoneNumber,int gender,String genderCode){
        this.ID=ID;
        this.age=age;
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
        this.genderCode=genderCode;
    }



    public String getGenderCode(){
        return genderCode;
    }
    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public int getGender(){
        return gender;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }


    public void setGenderCode(String s) {
        this.genderCode=s;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
