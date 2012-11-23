package DataModel;

import com.mongodb.DBObject;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-9
 * Time: 上午3:26
 * To change this template use File | Settings | File Templates.
 */
public class ComputerShopping {
    private int age;
    private String gender;
    private int income;

    private String buyOrNot;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getBuyOrNot() {
        return buyOrNot;
    }

    public void setBuyOrNot(String buyOrNot) {
        this.buyOrNot = buyOrNot;
    }

    public void changeFromMongoDB (DBObject object){
        this.age = Integer.valueOf((String)object.get("age"));
        this.gender = (String) object.get("gender");
        this.income = Integer.valueOf((String)object.get("income"));
        this.buyOrNot = (String) object.get("buyornot");
    }

}
