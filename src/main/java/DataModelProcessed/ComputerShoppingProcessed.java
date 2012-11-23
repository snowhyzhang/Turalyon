package DataModelProcessed;

import DataModel.ComputerShopping;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-9
 * Time: 上午3:39
 * To change this template use File | Settings | File Templates.
 */
public class ComputerShoppingProcessed {
    private Age age;
    private Gender gender;
    private Income income;
    private String buyOrNot;

    public void processData(ComputerShopping computerShopping){
        int csAge = computerShopping.getAge();
        String csGender = computerShopping.getGender();
        int csIncome = computerShopping.getIncome();

        age = getAgeGroup(csAge);
        gender = getGenderGroup(csGender);
        income = getIncomeGroup(csIncome);
        buyOrNot = computerShopping.getBuyOrNot();
    }

    private Income getIncomeGroup(int csIncome) {
        if (csIncome < 3000){
            return Income.LOW;
        } else if (csIncome < 8000){
            return Income.MEDIAN;
        } else {
            return Income.HIGH;
        }
    }

    private Gender getGenderGroup(String csGender) {
        if (csGender.equalsIgnoreCase("MALE"))
            return Gender.MALE;
        return Gender.FEMALE;
    }

    private Age getAgeGroup(int csAge) {
        if (csAge <= 18){
            return Age.CHILDHOOD;
        } else if (csAge <= 40){
            return Age.JUNIOR;
        } else {
            return Age.SENIOR;
        }
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public String getBuyOrNot() {
        return buyOrNot;
    }

    public void setBuyOrNot(String buyOrNot) {
        this.buyOrNot = buyOrNot;
    }
}
