package com.afshin.Dao;
/**
 /home/afshin/myprojects/github/afshin/Hibernate/order/src/main/java/com/afshin/Dao* @Project order
 * @Author Afshin Parhizkari
 * @Date 2020 - 11 - 27
 * @Time 5:59 AM
 * Created by   IntelliJ IDEA
 * Email:       Afshin.Parhizkari@gmail.com
 * Description: Hibernate - SQL
 */
import com.afshin.Entity.Payment;
import com.afshin.General.GeneralFunc;
import com.afshin.General.Mysession;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

public class PaymentDao {
    Session neshast = Mysession.getsession();
    //ExecuteQuery
    public PaymentDao() {}
    public List<Payment> findall(){
        try(Session localneshast=Mysession.getsession();) { //try with Resources
            SQLQuery query=neshast.createSQLQuery("select * from payments");
            query.addEntity(Payment.class);
            List<Payment> payments=query.list();
            GeneralFunc.logger.info("{}.{}|Try: All are Fetched",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return payments;
        }catch (Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public Payment findbyid(Integer cif,String checkNum){
        try(Session localneshast=Mysession.getsession();) { //try with Resources
            SQLQuery query=neshast.createSQLQuery(
                    "select * from payments p where customerNumber=:cif and checkNumber=:checkNum");
            query.setParameter("cif",cif);
            query.setParameter("checkNum",checkNum);
            query.addEntity(Payment.class);
            Payment payment=(Payment) query.uniqueResult();
            GeneralFunc.logger.info("{}.{}|Try: ID {} , {} is Fetched",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),payment.getCheckNumber(),payment.getCustomerNumber());
            return payment;
        }catch (Exception e){
            GeneralFunc.logger.error("{}.{}|Exception: {}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<?> parameterized(Integer cif,String checkNum){
        try(Session localneshast=Mysession.getsession();) { //try with Resources
            SQLQuery query=neshast.createSQLQuery(
                    "select p.customerNumber,p.checkNumber,p.amount from payments p where customerNumber=:cif and checkNumber=:checkNum");
            query.setParameter("cif",cif);
            query.setParameter("checkNum",checkNum);
            return query.list();
        }
    }

    //ExecuteUpdate
    public int insert(Payment payment){
        try(Session localneshast=Mysession.getsession();) { //try with Resources
            localneshast.beginTransaction();
            SQLQuery query = localneshast.createSQLQuery(
                    "INSERT INTO payments (customerNumber,checkNumber,paymentDate,amount) VALUES" +
                            " (:cif,:checknum,:paymenttime,:price )");
            query.setParameter("cif", payment.getCustomerNumber());
            query.setParameter("checknum", payment.getCheckNumber());
            query.setParameter("paymenttime", payment.getPaymentDate());
            query.setParameter("price", payment.getAmount());
            int rowaffect=query.executeUpdate();
            localneshast.getTransaction().commit();
            GeneralFunc.logger.info("{}.{}|Try: Inserted",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return rowaffect;
        }catch(Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    public int update(Payment payment) {
        try (Session localneshast = Mysession.getsession();) { //try with Resources
            localneshast.beginTransaction();
            SQLQuery query = localneshast.createSQLQuery(
                    "update payments set paymentDate = :paymenttime,amount =:price " +
                            " where (customerNumber=:cif and checkNumber=:checknum)");
            query.setParameter("cif", payment.getCustomerNumber());
            query.setParameter("checknum", payment.getCheckNumber());
            query.setParameter("paymenttime", payment.getPaymentDate());
            query.setParameter("price", payment.getAmount());
            int rowaffect = query.executeUpdate();
            localneshast.getTransaction().commit();
            GeneralFunc.logger.info("{}.{}|Try: Updated",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return rowaffect;
        }catch(Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    public int delete(Payment payment) {
        try (Session localneshast = Mysession.getsession();) { //try with Resources
            localneshast.beginTransaction();
            SQLQuery query = localneshast.createSQLQuery(
                    "delete from payments  where (customerNumber=:cif and checkNumber=:checknum)");
            query.setParameter("cif", payment.getCustomerNumber());
            query.setParameter("checknum", payment.getCheckNumber());
            int rowaffect = query.executeUpdate();
            localneshast.getTransaction().commit();
            GeneralFunc.logger.info("{}.{}|Try: Deleted",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
            return rowaffect;
        }catch(Exception e){
            GeneralFunc.logger.error("{}.{}|Exception:{}",this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}