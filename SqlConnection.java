import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {


    public Connection getConnection(){
        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jdk1.8.0_181\\bin\\cacerts");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
        Connection conn= null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

             conn =
                    DriverManager.getConnection("jdbc:mysql://fall2018dbatlani.cpca6yvg9gox.us-east-2.rds.amazonaws.com:3306/Electricity_Billing_System_incr_1??" +
                                    "verifyServerCertificate=true&useSSL=true&requireSSL=true",
                            "aatlani","Anchal1567");


            System.out.print(conn);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    public static void main(String args[]){

       /* System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jdk1.8.0_181\\bin\\cacerts");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://Fall2018dbatlani.cpca6yvg9gox.us-east-2.rds.amazonaws.com:3306/PROJ1??verifyServerCertificate=true&useSSL=true&requireSSL=true","aatlani","Anchal1789");


            System.out.print(conn);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
