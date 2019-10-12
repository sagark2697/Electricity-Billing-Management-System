import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Vector;

public class TextQuery extends JFrame {
    JPanel parentPanel;
    JPanel dynamicPanel;
    JPanel customerPanel;
    JPanel electricityPanelBillingPanel;
    JPanel numberOfUnitsConsumedPanel;

    private RetrieveSQLData retrieveSQLData;
    public TextQuery(){

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


        JTextArea queryArea = new JTextArea();
        queryArea.setLineWrap(true);
        queryArea.setWrapStyleWord(true);
        queryArea.setPreferredSize(new Dimension(800,400));

        JScrollPane pane = new JScrollPane(queryArea);
        pane.setPreferredSize(new Dimension(500, 200));
        pane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel textAreaPanel= new JPanel();
        textAreaPanel.setPreferredSize(new Dimension(600,600));
        textAreaPanel.add(pane, BorderLayout.LINE_START);

        JPanel buttonPanel= new JPanel();
        JButton button= new JButton();
        button.setEnabled(true);
        button.setText("VIEW ALL CUSTOMERS");
        button.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 14));

        button.setPreferredSize(new Dimension(320,25));
        buttonPanel.add(button,BorderLayout.LINE_START);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveSQLData= new RetrieveSQLData();
                String text=textField.getText();
                System.out.println(text);

                try {
                    ResultSet resultOfQuery=retrieveSQLData.getQueryResult(text);
                   ResultSetMetaData metaData= resultOfQuery.getMetaData();
                   int numberOfColumns=metaData.getColumnCount();
                    Vector<String> columnNames = new Vector<String>();
                    Object[][] resultSetInfo=null;
                    Object[] columns=new Object[numberOfColumns+1];
                   for(int i=1; i<=numberOfColumns; i++){

                       columnNames.add(metaData.getColumnName(i));
                      // System.out.println("Coloumn:"+columns[i]);
                   }
                  //int columnType= metaData.getColumn

                    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                    DefaultTableModel defaultTableModel= new DefaultTableModel(resultSetInfo,columns);
                    Object[] row;
                    while(resultOfQuery.next()){
                        int k=0;
                        Vector<Object> vector = new Vector<Object>();

                        for (int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex++) {
                            vector.add(resultOfQuery.getObject(columnIndex));
                        }
                        data.add(vector);

                    }

                    defaultTableModel.setDataVector(data,columnNames);
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

                    dynamicPanel.setPreferredSize(new Dimension(1700,600));
                    dynamicPanel.add(customerPanel,BorderLayout.SOUTH);
                    getContentPane().validate();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        retrieveSQLData.getConnection().close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }});

        createLayout(queryLabel1,textAreaPanel, buttonPanel);
        setTitle("Electricity Billing System");
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        setSize(1800,700);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void createLayout(JComponent...jcomponents) {
        parentPanel=(JPanel) getContentPane();
        parentPanel.setLayout(new BorderLayout());

        dynamicPanel= new JPanel();
        dynamicPanel.setLayout(new BorderLayout());
        dynamicPanel.setPreferredSize(new Dimension(200,200));
        dynamicPanel.add(jcomponents[0], BorderLayout.NORTH);
        dynamicPanel.add(jcomponents[1], BorderLayout.WEST);
        dynamicPanel.add(jcomponents[2], BorderLayout.EAST);
        JPanel conatiner= new JPanel();
        conatiner.setLayout(new BorderLayout());
        conatiner.setPreferredSize(new Dimension(700,700));
        conatiner.add(dynamicPanel,BorderLayout.NORTH);
        parentPanel.add(dynamicPanel,BorderLayout.NORTH);


    }

    public static void main(String args[]){
        TextQuery query= new TextQuery();
        query.setVisible(true);

    }
}
