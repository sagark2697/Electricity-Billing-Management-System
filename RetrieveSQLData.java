import java.sql.*;

public class RetrieveSQLData {

    public SqlConnection getSqlConnection() {
        return sqlConnection;
    }

    public void setSqlConnection(SqlConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    private SqlConnection sqlConnection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    public ResultSet getAllCustomerDetails(){
        ResultSet rs=null;
        try {
        sqlConnection= new SqlConnection();

         connection= sqlConnection.getConnection();

        Statement stmt = connection.createStatement();

        // Let us select all the records and display them.
        String sql = "SELECT * FROM Customer";
        rs = stmt.executeQuery(sql);

            System.out.println("Customer Details:"+sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return rs;
    }


    public static void main(String args[]){
        RetrieveSQLData sqlData=new RetrieveSQLData();
        try {
            sqlData.generateBillByAddingMeterReading(1004,100.00,20.00,new String("4"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getElectricityBillingDetail(int customerId){

        ResultSet rs=null;
        try {
            sqlConnection= new SqlConnection();

             connection= sqlConnection.getConnection();

            Statement stmt = connection.createStatement();

            // Let us select all the records and display them.
            String sql = "SELECT * FROM Electricity_Billing_Details where customer_id="+customerId;
            System.out.println("Electricity billing details:"+sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getElectricityBillOfParticularMonth(int customerId, int month, int year){

        ResultSet rs=null;
        try {
            sqlConnection= new SqlConnection();

            connection= sqlConnection.getConnection();

            Statement stmt = connection.createStatement();

            // Let us select all the records and display them.
            String sql = "SELECT * FROM Electricity_Billing_Details where customer_id="+customerId+ " AND"+
                            " MONTH(billing_cycle_start_date)="+month+" AND"+ " YEAR(billing_cycle_start_date)="
                               +year ;
            System.out.println("For a month"+sql);
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet getNumberOfUnitsConsumedofAnyMonth(int customerId, int month, int year){

        ResultSet rs=null;
        try {
            sqlConnection= new SqlConnection();

            connection= sqlConnection.getConnection();

            Statement stmt = connection.createStatement();

            // Let us select all the records and display them.
            String sql = "SELECT Electricity_Billing_Details.customer_id, Electricity_Billing_Details.electricity_bill_id, " +
                    "Electricity_Billing_Details.billing_cycle_start_date, last_meter_reading, " +
                    "Electricity_Billing_Details.present_meter_reading, Electricity_Billing_Details.number_of_billing_days, " +"("+
                    "Electricity_Billing_Details.present_meter_reading"+"-"+
                    "Electricity_Billing_Details.last_meter_reading"+")"+" as Units_Consumed"+

                    " FROM Electricity_Billing_Details  where customer_id="+customerId+ " AND"+
                    " MONTH(billing_cycle_start_date)="+month+" AND"+ " YEAR(billing_cycle_start_date)="
                    +year;
            System.out.println("Units consumed:"+sql);
            rs = stmt.executeQuery(sql);
            //System.out.println(rs.next());
           // System.out.println(rs.getInt(6));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }


    public ResultSet getQueryResult(String queryString) throws SQLException {
        ResultSet rs=null;
        try {
            sqlConnection= new SqlConnection();

            connection= sqlConnection.getConnection();

            Statement stmt = connection.createStatement();

            // Let us select all the records and display them.


            rs = stmt.executeQuery(queryString);
            //System.out.println(rs.next());
            // System.out.println(rs.getInt(6));

        } catch (SQLException e) {
           throw e;
        }
        finally {
            //sqlConnection.getConnection().close();
        }
        return rs;

    }


    public ResultSet executeInsertQuery(String insertQuery) throws SQLException {
        ResultSet rs=null;
        try {
            sqlConnection= new SqlConnection();

            connection= sqlConnection.getConnection();

            Statement stmt = connection.createStatement();


            int resultUpdated = stmt.executeUpdate(insertQuery);
            System.out.println("Result after insertion :"+resultUpdated);

            connection.close();
            stmt.close();
            if(resultUpdated>=1){

                String tableName=getTableName(insertQuery);

                System.out.println(tableName);
                String refreshSql= "Select * from "+tableName;
                System.out.println("Query:"+refreshSql);
                sqlConnection = new SqlConnection();

                connection = sqlConnection.getConnection();
                Statement stmt2=connection.createStatement();
                rs=stmt2.executeQuery(refreshSql);

            }


        } catch (SQLException e) {
            //e.printStackTrace();
            throw e;
        }
        finally {
            //sqlConnection.getConnection().close();
        }
        return rs;
    }

    private String getTableName(String insertQuery) {

        String[] tableNames= insertQuery.split(" ");
        String tableName=null;
        for(int i=0; i<tableNames.length;i++){
            if(tableNames[i].equalsIgnoreCase("INTO")){
                tableName=tableNames[i+1];
                break;
            }
            else if(tableNames[i].equalsIgnoreCase("Delete")){
                tableName=tableNames[i+2];
                break;
            }
            else if(tableNames[i].equalsIgnoreCase("Update")){
                tableName=tableNames[i+1];
                System.out.println(tableName);
                break;
            }
        }


        return tableName;

    }

    public ResultSet generateBillByAddingMeterReading(int meter_number, Double
            peak_hour_reading, Double off_peak_hour_reading,String customer_id) throws SQLException {
        ResultSet rs=null;
        try {
            sqlConnection = new SqlConnection();
            connection = sqlConnection.getConnection();
            String procQuery= "{CALL generate_electricity_bill(?,?,?,?)}";
            CallableStatement stmt = connection.prepareCall(procQuery);
            stmt.setInt(1, meter_number);
            stmt.setDouble(2,peak_hour_reading);
            stmt.setDouble(3,off_peak_hour_reading);
            stmt.setInt(4,Integer.parseInt(customer_id));
            rs = stmt.executeQuery();
            System.out.println(rs);

        }catch (SQLException e){
            throw e;
        }
        return rs;

    }


}

