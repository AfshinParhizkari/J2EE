package com.afshin;

import org.junit.Test;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Project order
 * @Author Afshin Parhizkari
 * @Date 2020 - 11 - 28
 * @Time 6:51 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description:
 */
public class PaymentDaoTest {
    PaymentDao dao=new PaymentDao();
    //ExecuteQuery
    @Test
    public void findallTest() {
        List<Payment> List =dao.findall();
        for(Payment tmp:List)
        {System.out.println(tmp);}
    }
    @Test
    public void findbyidTest() {
        Payment payment= dao.findbyid(103,"HQ336336");
        System.out.println(payment);
    }
    @Test
    public void parameterizedTest() {
        List<?> list = dao.parameterized(103,"HQ336336");
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            System.out.println(row[0] + ", " + row[1]+" -> " + row[2]);
        }
    }

    //ExecuteUpdate
    @Test
    public void insertTest(){
        Payment p=new Payment();
        p.setCustomerNumber(496);
        p.setCheckNumber("FN155234");
        p.setPaymentDate(new Date());
        p.setAmount(new BigDecimal(10000.50));
        int tmp =dao.insert(p);
        if(tmp>0) System.out.println("1 row affected"); else System.out.println("Error!");
    }
    @Test
    public void updateTest(){
        Payment p=dao.findbyid(496,"FN155234");
        p.setPaymentDate(new Date(2020-1900,00,30));
        p.setAmount(new BigDecimal(15000));
        int tmp =dao.update(p);
        if(tmp>0) System.out.println("1 row affected"); else System.out.println("Error!");
    }
    @Test
    public void deleteTest(){
        Payment p=dao.findbyid(496,"FN155234");
        int tmp =dao.update(p);
        if(tmp>0) System.out.println("1 row affected"); else System.out.println("Error!");
    }
}