package com.learning.guava.transform;

import com.google.common.base.Enums;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ListsTransformTest {

    public PersonVo personDbToVo(PersonDb personDb) {
        Preconditions.checkNotNull(personDb, "[PersonDbToVo]personDb为null");
        PersonVo personVo = new PersonVo();
        personVo.setName(personDb.getName() + ",from Db");
        personVo.setAge(personDb.getAge());
        personVo.setMsg(personDb.getMsg());
        return personVo;
    }

    @Test
    public void testListsTransform() {
        List<PersonDb> personDbs = Lists.newArrayList(new PersonDb("zhangsan", 20),
                new PersonDb("lisi", 24), new PersonDb("wangwu", 30));

        List<PersonVo> personVos = Lists.transform(personDbs, new Function<PersonDb, PersonVo>() {
            @Override
            public PersonVo apply(PersonDb personDb) {
                return personDbToVo(personDb);
            }
        });
        for (PersonDb personDb : personDbs) {
            personDb.setMsg("hello world!");
        }
//        Collections.shuffle(personVos);
        //personVos = ImmutableList.copyOf(personVos);
        //personVos = Lists.newArrayList(personVos);
        for (PersonVo personVo : personVos) {
            personVo.setMsg("Merry Christmas!");
        }
//        personVos.add(personDbToVo(new PersonDb("sting", 30)));
        System.out.println(personVos);
    }


    @Test
    public void convert_tdinvestment_etradeinvestment () {

        List<TdAmeritradeInvestment> tdInvestments = Lists.newArrayList();
        tdInvestments.add(new TdAmeritradeInvestment(555, "Facebook Inc", 57.51));
        tdInvestments.add(new TdAmeritradeInvestment(123, "Micron Technology, Inc.", 21.29));
        tdInvestments.add(new TdAmeritradeInvestment(456, "Ford Motor Company", 15.31));
        tdInvestments.add(new TdAmeritradeInvestment(236, "Sirius XM Holdings Inc", 3.60));


        Function<TdAmeritradeInvestment, ETradeInvestment> tdToEtradeFunction = new Function<TdAmeritradeInvestment, ETradeInvestment>() {

            public ETradeInvestment apply(TdAmeritradeInvestment input) {
                ETradeInvestment investment = new ETradeInvestment();
//                investment.setKey(Ints.stringConverter().reverse()
//                        .convert(input.getInvestmentKey()));
                investment.setName(input.getInvestmentName());
                investment.setPrice(new BigDecimal(input.getInvestmentPrice()));
                return investment;
            }
        };

        List<ETradeInvestment> etradeInvestments = Lists.transform(tdInvestments, tdToEtradeFunction);

        System.out.println(etradeInvestments);
    }

    @Test
    public void transform_string_to_enum () {

        List<String> days = Lists.newArrayList(
                "WEDNESDAY",
                "SUNDAY",
                "MONDAY",
                "WEDNESDAY");

        Function<String, Day> stringToDayEnum = Enums.valueOfFunction(Day.class);

        Iterable<Day> daysAsEnum = Iterables.transform(days, stringToDayEnum);

        for (Day day : daysAsEnum) {
            System.out.println(day);
        }
    }


}

enum Day {

    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY;
}
class ETradeInvestment {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private String key;
    private String name;
    private BigDecimal price;

}

class TdAmeritradeInvestment {

    private int investmentKey;
    private String investmentName;
    private double investmentPrice;

    public TdAmeritradeInvestment(int investmentKey, String facebook_inc, double v) {
        this.investmentKey = investmentKey ;
        this.investmentName=facebook_inc;
        this.investmentPrice = v ;
    }

    public int getInvestmentKey() {
        return investmentKey;
    }

    public void setInvestmentKey(int investmentKey) {
        this.investmentKey = investmentKey;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public double getInvestmentPrice() {
        return investmentPrice;
    }

    public void setInvestmentPrice(double investmentPrice) {
        this.investmentPrice = investmentPrice;
    }
}
class PersonDb {
    private String name;
    private int age;
    private String msg;

    public PersonDb(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("msg", msg).toString();
    }
}

class PersonVo {
    private String name;
    private int age;
    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("msg", msg).toString();
    }
}
