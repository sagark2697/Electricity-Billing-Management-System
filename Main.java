import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class Main  extends JFrame {
    JPanel parentPanel;
    JPanel dynamicPanel;
    JPanel customerPanel;
    JPanel electricityPanelBillingPanel;
    JPanel numberOfUnitsConsumedPanel;

    private RetrieveSQLData retrieveSQLData;
    public Main(){
        initialize();
    }

    public void initialize(){
        JLabel queryLabel1= new JLabel();
        queryLabel1.setText("Write query");
        queryLabel1.setFont(new Font(Font.SANS_SERIF, Font.BOLD,26));

        JTextField textField= new JTextField();
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        textField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        textField.setMinimumSize(new Dimension(100,10));
        textField.setCaretPosition(0);



        JPanel buttonPanel= new JPanel();

        JPanel buttonPanel2= new JPanel();

        JButton button= new JButton();
        button.setEnabled(true);
        button.setText("VIEW ALL CUSTOMERS");
        button.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 14));



        button.setPreferredSize(new Dimension(320,25));
        buttonPanel.add(button,BorderLayout.LINE_START);

        JButton  viewElectricityBillButton= new JButton();
        viewElectricityBillButton.setEnabled(true);
        viewElectricityBillButton.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 14));
        viewElectricityBillButton.setText("CUSTOMER VIEW ELECTRICITY BILLS");
        viewElectricityBillButton.setPreferredSize(new Dimension(320,25));

        buttonPanel2.setPreferredSize(new Dimension(400,50));
        buttonPanel2.add(viewElectricityBillButton, BorderLayout.LINE_START);


        JButton viewNumOfUnitsConsumed= new JButton();
        viewNumOfUnitsConsumed.setEnabled(true);
        viewNumOfUnitsConsumed.setVisible(true);
        viewNumOfUnitsConsumed.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 14));
        viewNumOfUnitsConsumed.setText("UNITS CONSUMED IN SELECTED MONTH");
        viewNumOfUnitsConsumed.setPreferredSize(new Dimension(320,25));

        JPanel numOfUnitsConsumedPanel= new JPanel();
        buttonPanel2.add(viewNumOfUnitsConsumed, BorderLayout.SOUTH);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveSQLData= new RetrieveSQLData();
                ResultSet customerDetail=retrieveSQLData.getAllCustomerDetails();
                try {
                    showCustomerDataOnScreen(customerDetail);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }finally {
                    try {
                        retrieveSQLData.getConnection().close();
                        customerDetail.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        viewElectricityBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveSQLData= new RetrieveSQLData();
                ResultSet electricityBilling=retrieveSQLData.getElectricityBillingDetail(1);

                try {

                    showElectricityBilligData(electricityBilling);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                finally {
                    try {
                        retrieveSQLData.getConnection().close();
                        electricityBilling.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        viewNumOfUnitsConsumed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveSQLData= new RetrieveSQLData();
                ResultSet electricityBilling=retrieveSQLData.
                        getNumberOfUnitsConsumedofAnyMonth(1,9,2018);

                try {

                    showNumberOfUnitsConsumed(electricityBilling);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                finally {
                    try {
                        retrieveSQLData.getConnection().close();
                        electricityBilling.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });

        createLayout(buttonPanel,buttonPanel2);
        setTitle("Electricity Billing System");
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        setSize(1800,700);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void showNumberOfUnitsConsumed(ResultSet electricityBilling) throws SQLException {

        if (electricityBilling != null) {

            Object[][] resultSetInfo=null;

            Object[] columns={"Customer ID","Electricity Bill Id","Bill  Cycle Start Date","Last Month Meter Reading",
                    "Current Month Meter Reading","Number Of Billing Days","Units Consumed"};
            DefaultTableModel defaultTableModel= new DefaultTableModel(resultSetInfo,columns);

            Object[] row;
            while(electricityBilling.next()){
                System.out.println("Inside loop");
                row= new Object[]{electricityBilling.getInt(1),
                        electricityBilling.getInt(2),electricityBilling.getDate(3),
                        electricityBilling.getDouble(4),
                        electricityBilling.getDouble(5),electricityBilling.getInt(6),
                       electricityBilling.getDouble(7)};
                defaultTableModel.addRow(row);
            }

            JTable jTable= new JTable(defaultTableModel);
            jTable.setRowHeight(jTable.getRowHeight()+5);
            jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

            jTable.setAutoCreateColumnsFromModel(true);

            jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            JScrollPane scrollPane= new JScrollPane(jTable);
            scrollPane.setPreferredSize(new Dimension(1700,500));
            scrollPane.setOpaque(false);
            if(numberOfUnitsConsumedPanel==null){
                numberOfUnitsConsumedPanel= new JPanel();

            }
            numberOfUnitsConsumedPanel.setVisible(true);
            numberOfUnitsConsumedPanel.add(scrollPane,BorderLayout.CENTER);
            numberOfUnitsConsumedPanel.setPreferredSize(new Dimension(1700,510));
            //electricityPanelBillingPanel.setOpaque(false);
            if(customerPanel!=null){
                customerPanel.setVisible(false);
            }
            if(electricityPanelBillingPanel!=null){
                electricityPanelBillingPanel.setVisible(false);
            }

            dynamicPanel.setPreferredSize(new Dimension(1700,600));
            dynamicPanel.add( numberOfUnitsConsumedPanel,BorderLayout.SOUTH);
            getContentPane().validate();
        }

    }

    private void showElectricityBilligData(ResultSet electricityBilling) throws SQLException {

            System.out.println("In showElectricityBilligData "+electricityBilling);
            Object[][] resultSetInfo=null;

            Object[] columns={"Electricity Billing Id","Bill Generated Date","Bill Due Date",
                    "Bill Cycle Start Date","Number Of Billing Days","Payment Amount",
                    "Amount Due"
                    , "Last Meter Reading","Current Meter Reading","Customer Id"};
            DefaultTableModel defaultTableModel= new DefaultTableModel(resultSetInfo,columns);

            Object[] row;
            if(electricityBilling!=null){
                while(electricityBilling.next()){
                    System.out.println("Inside loop");
                    row= new Object[]{electricityBilling.getInt(1),electricityBilling.getDate(2),
                            electricityBilling.getDate(3),
                            electricityBilling.getDate(4),electricityBilling.getInt(5),
                            electricityBilling.getDouble(6),electricityBilling.getDouble(7),
                            electricityBilling.getInt(8),electricityBilling.getInt(9),electricityBilling.getInt(10)};
                    defaultTableModel.addRow(row);
                }
            }

            JTable jTable= new JTable(defaultTableModel);
            jTable.setRowHeight(jTable.getRowHeight()+5);
            jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

            jTable.setAutoCreateColumnsFromModel(true);

            jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            JScrollPane scrollPane= new JScrollPane(jTable);
            scrollPane.setPreferredSize(new Dimension(1700,500));
            scrollPane.setOpaque(false);
            if(electricityPanelBillingPanel==null){
             electricityPanelBillingPanel= new JPanel();

            }
            electricityPanelBillingPanel.setVisible(true);
            electricityPanelBillingPanel.add(scrollPane,BorderLayout.CENTER);
            electricityPanelBillingPanel.setPreferredSize(new Dimension(1700,510));
            //electricityPanelBillingPanel.setOpaque(false);
            if(customerPanel!=null){
                customerPanel.setVisible(false);
            }

            dynamicPanel.setPreferredSize(new Dimension(1700,600));
            dynamicPanel.add( electricityPanelBillingPanel,BorderLayout.SOUTH);
            getContentPane().validate();

    }

    private void createLayout(JComponent...jcomponents) {
        parentPanel=(JPanel) getContentPane();
        parentPanel.setLayout(new BorderLayout());

        dynamicPanel= new JPanel();
        dynamicPanel.setLayout(new BorderLayout());
        dynamicPanel.setPreferredSize(new Dimension(90,90));
        dynamicPanel.add(jcomponents[0], BorderLayout.NORTH);
        dynamicPanel.add(jcomponents[1], BorderLayout.CENTER);
        //dynamicPanel.add(jcomponents[2], BorderLayout.SOUTH);

        parentPanel.add(dynamicPanel,BorderLayout.NORTH);



    }

    private void showCustomerDataOnScreen(ResultSet customerDetail) throws SQLException {

        //ResultSetMetaData data=customerDetail.getMetaData();


        System.out.println("In showCustomer"+customerDetail);
        Object[][] resultSetInfo=null;
        Object[] columns={"Customer ID","Login Email","SSN","First Name","Last Name",
                "Address Line 1"
                            , "Address Line 1","City","State","Zip Code","Work Phone Number","Home Phone Number",
                            "Service Provider","Register Date","Meter Type"};
        DefaultTableModel defaultTableModel= new DefaultTableModel(resultSetInfo,columns);

        Object[] row;
        if(customerDetail!=null){
            while(customerDetail.next()){

                SimpleDateFormat  df = new SimpleDateFormat("yyyy-mm-dd");
                String text = df.format(customerDetail.getDate(15).getTime());

                System.out.println("The date is: " + text);
               java.util.Date date1= new java.util.Date(customerDetail.getDate(15).getTime());
                System.out.println(date1);
                System.out.println(customerDetail.getDate(15));
                   row= new Object[]{customerDetail.getInt(1),customerDetail.getString(2),

                           customerDetail.getString(4),customerDetail.getString(5),
                           customerDetail.getString(6),customerDetail.getString(7),
                           customerDetail.getString(8),customerDetail.getString(9),
                           customerDetail.getString(10),customerDetail.getString(11),
                           customerDetail.getString(12),customerDetail.getString(13),
                            customerDetail.getString(14),customerDetail.getDate(15),customerDetail.getString(16)};
                defaultTableModel.addRow(row);
            }
        }

        defaultTableModel.fireTableDataChanged();
        JTable jTable= new JTable(defaultTableModel);
        jTable.setRowHeight(jTable.getRowHeight()+5);
        jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        jTable.setAutoCreateColumnsFromModel(true);

        jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        JScrollPane scrollPaneCustomer= new JScrollPane(jTable);
        scrollPaneCustomer.setPreferredSize(new Dimension(1700,500));
        if(customerPanel==null){
         customerPanel= new JPanel();
        }
        customerPanel.setVisible(true);
        customerPanel.add(scrollPaneCustomer,BorderLayout.CENTER);
        customerPanel.setPreferredSize(new Dimension(1700,510));
        if(electricityPanelBillingPanel!=null){
            electricityPanelBillingPanel.setVisible(false);
        }
        dynamicPanel.setPreferredSize(new Dimension(1700,600));
        dynamicPanel.add(customerPanel,BorderLayout.SOUTH);
        getContentPane().validate();

    }


    public static void main(String[] args) {

        Main m= new Main();
        m.setVisible(true);
        System.out.println("Hello World!");
    }
}
