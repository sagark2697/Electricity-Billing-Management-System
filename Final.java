import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Final {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel customer_view;
    private JPanel admin_view;
    private JLabel customer_label;
    private JButton executeButton;
    private JLabel center_panel_label;
    private JTextArea query_text_area;
    private JPanel options_panel;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JScrollPane scroll_panel;
    private JPanel button_panel;
    private JLabel register_into_system_label;
    private JLabel view_electricity_bills;
    private JLabel look_up_service_provider_text;
    private JLabel view_number_of_units_consumed_label;
    private JLabel login_text;
    private JLabel pay_bill_text;
    private JPanel dynamic_view_panel;
    private JPanel admin_label_panel;
    private JLabel admin_label_text;
    private JPanel admin_functions_panel;
    private JTextArea admin_query_area;
    private JLabel execute_query_label_1;
    private JScrollPane admin_scroll_panel;
    private JLabel admin_function_one;
    private JLabel admin_function_three;
    private JLabel admin_function_four;
    private JLabel add_function_two;
    private JPanel scroll_admin_panel_2;
    private JPanel execute_button_panel_2;
    private JButton admin_view_button;
    private JPanel dynamic_panel_2;
    private JLabel query_result;
    private JPanel querr_result_panel;
    private JLabel login_admin_label;
    private JLabel view_tariff_detail_label_2;
    private JLabel empty_label_2;
    private JPanel admin_query_execute_panel;
    private JLabel query_result_2;
    private JPanel procedure_option_panel;
    private JPanel options_proc_panel_one;
    private JPanel procedure_view_panel;
    private JLabel procedure_label;
    private JLabel first_procedure_label;
    private JTextField meter_num_field;
    private JTextField customer_id_text_field;
    private JTextField peak_hr_reading_text;
    private JTextField off_peak_hr_reading_text;
    private JButton generate_button;
    private RetrieveSQLData retrieveSQLData;
    static JFrame temp;

    public Final() {
        $$$setupUI$$$();
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dynamic_view_panel.getComponentCount() > 0) {
                    dynamic_view_panel.remove(0);
                }
                retrieveSQLData = new RetrieveSQLData();
                String text = query_text_area.getText();
                if (text == null || text.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the query");
                } else {
                    System.out.println(text);
                    ResultSet resultOfQuery = null;
                    try {
                        if (text.startsWith("INSERT") || text.startsWith("insert") || text.startsWith("DELETE") || text.startsWith("delete")
                                || text.startsWith("UPDATE") || text.startsWith("update")) {

                            resultOfQuery = retrieveSQLData.executeInsertQuery(text);
                            ResultSetMetaData metaData = resultOfQuery.getMetaData();
                            int numberOfColumns = metaData.getColumnCount();
                            Vector<String> columnNames = new Vector<String>();
                            Object[][] resultSetInfo = null;
                            Object[] columns = new Object[numberOfColumns + 1];
                            for (int i = 1; i <= numberOfColumns; i++) {

                                columnNames.add(metaData.getColumnName(i));
                                // System.out.println("Coloumn:"+columns[i]);
                            }
                            //int columnType= metaData.getColumn

                            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                            DefaultTableModel defaultTableModel = new DefaultTableModel(resultSetInfo, columns);
                            Object[] row;
                            while (resultOfQuery.next()) {
                                int k = 0;
                                Vector<Object> vector = new Vector<Object>();

                                for (int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex++) {
                                    vector.add(resultOfQuery.getObject(columnIndex));
                                }
                                data.add(vector);

                            }

                            defaultTableModel.setDataVector(data, columnNames);
                            defaultTableModel.fireTableDataChanged();
                            JTable jTable = new JTable(defaultTableModel);
                            jTable.setRowHeight(jTable.getRowHeight() + 5);
                            jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

                            jTable.setAutoCreateColumnsFromModel(true);

                            jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                            JScrollPane scrollPaneCustomer = new JScrollPane(jTable);
                            scrollPaneCustomer.setPreferredSize(new Dimension(1000, 500));
                            System.out.println("Scroll Pane" + scrollPaneCustomer);

                            dynamic_view_panel.add(scrollPaneCustomer, BorderLayout.SOUTH);
                            query_result.setText("Query Executed Successfully");
                            temp.getContentPane().validate();

                        } else {
                            resultOfQuery = retrieveSQLData.getQueryResult(text);
                            ResultSetMetaData metaData = resultOfQuery.getMetaData();
                            int numberOfColumns = metaData.getColumnCount();
                            Vector<String> columnNames = new Vector<String>();
                            Object[][] resultSetInfo = null;
                            Object[] columns = new Object[numberOfColumns + 1];
                            for (int i = 1; i <= numberOfColumns; i++) {

                                columnNames.add(metaData.getColumnName(i));
                                // System.out.println("Coloumn:"+columns[i]);
                            }
                            //int columnType= metaData.getColumn

                            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                            DefaultTableModel defaultTableModel = new DefaultTableModel(resultSetInfo, columns);
                            Object[] row;
                            while (resultOfQuery.next()) {
                                int k = 0;
                                Vector<Object> vector = new Vector<Object>();

                                for (int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex++) {
                                    vector.add(resultOfQuery.getObject(columnIndex));
                                }
                                data.add(vector);

                            }

                            defaultTableModel.setDataVector(data, columnNames);
                            defaultTableModel.fireTableDataChanged();
                            JTable jTable = new JTable(defaultTableModel);
                            jTable.setRowHeight(jTable.getRowHeight() + 5);
                            jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

                            jTable.setAutoCreateColumnsFromModel(true);

                            jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                            JScrollPane scrollPaneCustomer = new JScrollPane(jTable);
                            scrollPaneCustomer.setPreferredSize(new Dimension(1000, 500));
                            System.out.println("Scroll Pane" + scrollPaneCustomer);
                            dynamic_view_panel.add(scrollPaneCustomer, BorderLayout.SOUTH);

                            query_result.setText("Data Retrieved Successfully");
                            temp.getContentPane().validate();


                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "SQLException occurred " + e1.getMessage());
                    } finally {
                        try {
                            resultOfQuery.close();
                            retrieveSQLData.getConnection().close();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,
                                    "SQLException occurred while closing the connection " + e1.getMessage());
                        }
                    }

                }
            }
        });
        admin_view_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dynamic_view_panel.getComponentCount() > 0) {
                    dynamic_view_panel.remove(0);
                }
                retrieveSQLData = new RetrieveSQLData();
                String text = admin_query_area.getText();
                System.out.println(text);
                if (text == null || text.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the query");
                } else {
                    ResultSet resultOfQuery = null;
                    try {
                        if (text.startsWith("INSERT") || text.startsWith("insert") || text.startsWith("DELETE") || text.startsWith("delete")
                                || text.startsWith("UPDATE") || text.startsWith("update")) {

                            resultOfQuery = retrieveSQLData.executeInsertQuery(text);
                            ResultSetMetaData metaData = resultOfQuery.getMetaData();
                            int numberOfColumns = metaData.getColumnCount();
                            Vector<String> columnNames = new Vector<String>();
                            Object[][] resultSetInfo = null;
                            Object[] columns = new Object[numberOfColumns + 1];
                            for (int i = 1; i <= numberOfColumns; i++) {

                                columnNames.add(metaData.getColumnName(i));
                                // System.out.println("Coloumn:"+columns[i]);
                            }
                            //int columnType= metaData.getColumn

                            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                            DefaultTableModel defaultTableModel = new DefaultTableModel(resultSetInfo, columns);
                            Object[] row;
                            while (resultOfQuery.next()) {
                                int k = 0;
                                Vector<Object> vector = new Vector<Object>();

                                for (int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex++) {
                                    vector.add(resultOfQuery.getObject(columnIndex));
                                }
                                data.add(vector);

                            }

                            defaultTableModel.setDataVector(data, columnNames);
                            defaultTableModel.fireTableDataChanged();
                            JTable jTable = new JTable(defaultTableModel);
                            jTable.setRowHeight(jTable.getRowHeight() + 5);
                            jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

                            jTable.setAutoCreateColumnsFromModel(true);

                            jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                            JScrollPane scrollPaneCustomer = new JScrollPane(jTable);
                            scrollPaneCustomer.setPreferredSize(new Dimension(1000, 500));
                            System.out.println("Scroll Pane" + scrollPaneCustomer);
                            dynamic_view_panel.add(scrollPaneCustomer, BorderLayout.SOUTH);
                            query_result.setText("Query Executed Successfully");
                            temp.getContentPane().validate();
                        } else {
                            resultOfQuery = retrieveSQLData.getQueryResult(text);
                            ResultSetMetaData metaData = resultOfQuery.getMetaData();
                            int numberOfColumns = metaData.getColumnCount();
                            Vector<String> columnNames = new Vector<String>();
                            Object[][] resultSetInfo = null;
                            Object[] columns = new Object[numberOfColumns + 1];
                            for (int i = 1; i <= numberOfColumns; i++) {

                                columnNames.add(metaData.getColumnName(i));
                                // System.out.println("Coloumn:"+columns[i]);
                            }
                            //int columnType= metaData.getColumn

                            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                            DefaultTableModel defaultTableModel = new DefaultTableModel(resultSetInfo, columns);
                            Object[] row;
                            while (resultOfQuery.next()) {
                                int k = 0;
                                Vector<Object> vector = new Vector<Object>();

                                for (int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex++) {
                                    vector.add(resultOfQuery.getObject(columnIndex));
                                }
                                data.add(vector);

                            }

                            defaultTableModel.setDataVector(data, columnNames);
                            defaultTableModel.fireTableDataChanged();
                            JTable jTable = new JTable(defaultTableModel);
                            jTable.setRowHeight(jTable.getRowHeight() + 5);
                            jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

                            jTable.setAutoCreateColumnsFromModel(true);

                            jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                            JScrollPane scrollPaneCustomer = new JScrollPane(jTable);
                            scrollPaneCustomer.setPreferredSize(new Dimension(1000, 500));
                            System.out.println("Scroll Pane" + scrollPaneCustomer);
                            dynamic_view_panel.add(scrollPaneCustomer, BorderLayout.SOUTH);
                            query_result.setText("Data Retrieved Successfully");
                            temp.getContentPane().validate();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "SQLException occurred " + e1.getMessage());
                    } finally {
                        try {
                            resultOfQuery.close();
                            retrieveSQLData.getConnection().close();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,
                                    "SQLException occurred while closing the connection " + e1.getMessage());
                        }
                    }
                }

            }
        });

        generate_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dynamic_view_panel.getComponentCount() > 0) {
                    dynamic_view_panel.remove(0);
                }
                retrieveSQLData = new RetrieveSQLData();
                String meter_num_string = meter_num_field.getText();
                String customer_id = customer_id_text_field.getText();
                String peak_hr_reading = peak_hr_reading_text.getText();
                String offPeak_hr_reading = off_peak_hr_reading_text.getText();


                if (meter_num_string == null || meter_num_string.isEmpty() ||
                        customer_id == null || customer_id.isEmpty() ||
                        peak_hr_reading == null || peak_hr_reading.isEmpty() ||
                        offPeak_hr_reading == null || offPeak_hr_reading.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all fields");
                } else {
                    ResultSet resultOfQuery = null;
                    ResultSet viewQueryResultSet = null;
                    try {
                        int meter_num = Integer.parseInt(meter_num_string);
                        double peak_hr = Double.parseDouble(peak_hr_reading);
                        double offPeak_hr = Double.parseDouble(offPeak_hr_reading);
                        resultOfQuery = retrieveSQLData.
                                generateBillByAddingMeterReading(meter_num, peak_hr, offPeak_hr, customer_id);

                        int cust_id = Integer.parseInt(customer_id);
                        String viewQuery = "Select * from electricity_bills where customer_id=" + cust_id +
                                " AND  month(bill_generated_date)=month(current_date())";
                        if (resultOfQuery != null) {
                            viewQueryResultSet = retrieveSQLData.getQueryResult(viewQuery);
                        }
                        if (viewQueryResultSet != null) {
                            ResultSetMetaData metaData = viewQueryResultSet.getMetaData();
                            int numberOfColumns = metaData.getColumnCount();
                            Vector<String> columnNames = new Vector<String>();
                            Object[][] resultSetInfo = null;
                            Object[] columns = new Object[numberOfColumns + 1];
                            for (int i = 1; i <= numberOfColumns; i++) {

                                columnNames.add(metaData.getColumnName(i));
                                // System.out.println("Coloumn:"+columns[i]);
                            }
                            //int columnType= metaData.getColumn

                            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
                            DefaultTableModel defaultTableModel = new DefaultTableModel(resultSetInfo, columns);
                            Object[] row;
                            while (viewQueryResultSet.next()) {
                                int k = 0;
                                Vector<Object> vector = new Vector<Object>();

                                for (int columnIndex = 1; columnIndex <= numberOfColumns; columnIndex++) {
                                    vector.add(viewQueryResultSet.getObject(columnIndex));
                                }
                                data.add(vector);

                            }

                            defaultTableModel.setDataVector(data, columnNames);
                            defaultTableModel.fireTableDataChanged();
                            JTable jTable = new JTable(defaultTableModel);
                            jTable.setRowHeight(jTable.getRowHeight() + 5);
                            jTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

                            jTable.setAutoCreateColumnsFromModel(true);

                            jTable.getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
                            JScrollPane scrollPaneCustomer = new JScrollPane(jTable);
                            scrollPaneCustomer.setPreferredSize(new Dimension(1000, 500));
                            System.out.println("Scroll Pane" + scrollPaneCustomer);
                            dynamic_view_panel.add(scrollPaneCustomer, BorderLayout.SOUTH);
                            // query_result.setText("Query Executed Successfully");
                            temp.getContentPane().validate();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "SQLException occurred " + e1.getMessage());
                    } finally {
                        try {
                            resultOfQuery.close();
                            viewQueryResultSet.close();
                            retrieveSQLData.getConnection().close();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null,
                                    "SQLException occurred while closing the connection " + e1.getMessage());
                        }
                    }
                }

            }
        });

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {


                if (dynamic_view_panel.getComponentCount() > 0) {
                    System.out.println("Component");
                    dynamic_view_panel.remove(0);

                }

                dynamic_view_panel.add(new Component() {
                    @Override
                    public String getName() {
                        return super.getName();
                    }
                });
                temp.getContentPane().validate();
            }
        });
    }

    public static void main(String args[]) {
        Final obj = new Final();
        obj.temp = new JFrame("Electricity Billing System");
        obj.temp.setVisible(true);
        obj.temp.setLocation(200, 10);
        obj.temp.setContentPane(new Final().panel1);
        obj.temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.temp.pack();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setMaximumSize(new Dimension(1000, 900));
        panel1.setMinimumSize(new Dimension(1000, 900));
        panel1.setPreferredSize(new Dimension(1500, 900));
        tabbedPane1 = new JTabbedPane();
        Font tabbedPane1Font = this.$$$getFont$$$("Arial", Font.BOLD, 18, tabbedPane1.getFont());
        if (tabbedPane1Font != null) tabbedPane1.setFont(tabbedPane1Font);
        tabbedPane1.setMinimumSize(new Dimension(1500, 400));
        tabbedPane1.setPreferredSize(new Dimension(1500, 400));
        panel1.add(tabbedPane1, BorderLayout.NORTH);
        customer_view = new JPanel();
        customer_view.setLayout(new BorderLayout(0, 0));
        Font customer_viewFont = this.$$$getFont$$$("Arial", Font.BOLD, 20, customer_view.getFont());
        if (customer_viewFont != null) customer_view.setFont(customer_viewFont);
        customer_view.setInheritsPopupMenu(true);
        customer_view.setMaximumSize(new Dimension(1500, 400));
        customer_view.setMinimumSize(new Dimension(1500, 400));
        customer_view.setPreferredSize(new Dimension(1500, 400));
        tabbedPane1.addTab("Customer View", customer_view);
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.setMinimumSize(new Dimension(1500, 50));
        panel2.setPreferredSize(new Dimension(1500, 50));
        customer_view.add(panel2, BorderLayout.NORTH);
        customer_label = new JLabel();
        Font customer_labelFont = this.$$$getFont$$$("Arial", Font.BOLD, 20, customer_label.getFont());
        if (customer_labelFont != null) customer_label.setFont(customer_labelFont);
        customer_label.setHorizontalAlignment(0);
        customer_label.setPreferredSize(new Dimension(150, 50));
        customer_label.setText("Customer View");
        customer_label.setVerticalAlignment(1);
        customer_label.setVerticalTextPosition(1);
        panel2.add(customer_label);
        options_panel = new JPanel();
        options_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        options_panel.setMaximumSize(new Dimension(500, 200));
        options_panel.setMinimumSize(new Dimension(500, 200));
        options_panel.setPreferredSize(new Dimension(500, 200));
        customer_view.add(options_panel, BorderLayout.WEST);
        panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(8, 2, new Insets(25, 0, 0, 0), 0, 0));
        panel3.setMaximumSize(new Dimension(500, 200));
        panel3.setMinimumSize(new Dimension(500, 200));
        panel3.setPreferredSize(new Dimension(500, 200));
        options_panel.add(panel3);
        register_into_system_label = new JLabel();
        Font register_into_system_labelFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, register_into_system_label.getFont());
        if (register_into_system_labelFont != null) register_into_system_label.setFont(register_into_system_labelFont);
        register_into_system_label.setHorizontalAlignment(2);
        register_into_system_label.setText("1. Register Into System");
        register_into_system_label.setVerticalAlignment(1);
        register_into_system_label.setVerticalTextPosition(1);
        panel3.add(register_into_system_label, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        view_electricity_bills = new JLabel();
        Font view_electricity_billsFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, view_electricity_bills.getFont());
        if (view_electricity_billsFont != null) view_electricity_bills.setFont(view_electricity_billsFont);
        view_electricity_bills.setHorizontalAlignment(2);
        view_electricity_bills.setText("2. View Electricity Bills");
        view_electricity_bills.setVerticalAlignment(1);
        view_electricity_bills.setVerticalTextPosition(1);
        panel3.add(view_electricity_bills, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        look_up_service_provider_text = new JLabel();
        Font look_up_service_provider_textFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, look_up_service_provider_text.getFont());
        if (look_up_service_provider_textFont != null)
            look_up_service_provider_text.setFont(look_up_service_provider_textFont);
        look_up_service_provider_text.setHorizontalAlignment(2);
        look_up_service_provider_text.setHorizontalTextPosition(11);
        look_up_service_provider_text.setText("3. Look up ServiceProvider");
        look_up_service_provider_text.setVerticalAlignment(1);
        look_up_service_provider_text.setVerticalTextPosition(1);
        panel3.add(look_up_service_provider_text, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        view_number_of_units_consumed_label = new JLabel();
        view_number_of_units_consumed_label.setAutoscrolls(true);
        Font view_number_of_units_consumed_labelFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, view_number_of_units_consumed_label.getFont());
        if (view_number_of_units_consumed_labelFont != null)
            view_number_of_units_consumed_label.setFont(view_number_of_units_consumed_labelFont);
        view_number_of_units_consumed_label.setText("4. View Units Consumed in a month");
        view_number_of_units_consumed_label.setVerticalAlignment(1);
        view_number_of_units_consumed_label.setVerticalTextPosition(1);
        panel3.add(view_number_of_units_consumed_label, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        login_text = new JLabel();
        Font login_textFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, login_text.getFont());
        if (login_textFont != null) login_text.setFont(login_textFont);
        login_text.setText("5. Login");
        panel3.add(login_text, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pay_bill_text = new JLabel();
        Font pay_bill_textFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, pay_bill_text.getFont());
        if (pay_bill_textFont != null) pay_bill_text.setFont(pay_bill_textFont);
        pay_bill_text.setText("6. Pay Bill ");
        pay_bill_text.setVerticalAlignment(1);
        pay_bill_text.setVerticalTextPosition(1);
        panel3.add(pay_bill_text, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        view_tariff_detail_label_2 = new JLabel();
        Font view_tariff_detail_label_2Font = this.$$$getFont$$$("Arial", Font.BOLD, 18, view_tariff_detail_label_2.getFont());
        if (view_tariff_detail_label_2Font != null) view_tariff_detail_label_2.setFont(view_tariff_detail_label_2Font);
        view_tariff_detail_label_2.setText("7. View Tariff Detail of Any Service Provider");
        panel3.add(view_tariff_detail_label_2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("");
        panel3.add(label1, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        querr_result_panel = new JPanel();
        querr_result_panel.setLayout(new BorderLayout(25, 25));
        querr_result_panel.setMaximumSize(new Dimension(500, 100));
        querr_result_panel.setMinimumSize(new Dimension(500, 100));
        querr_result_panel.setPreferredSize(new Dimension(500, 100));
        options_panel.add(querr_result_panel);
        query_result = new JLabel();
        Font query_resultFont = this.$$$getFont$$$("Arial", Font.BOLD, 16, query_result.getFont());
        if (query_resultFont != null) query_result.setFont(query_resultFont);
        query_result.setMaximumSize(new Dimension(200, 100));
        query_result.setMinimumSize(new Dimension(200, 100));
        query_result.setPreferredSize(new Dimension(200, 100));
        query_result.setText("");
        querr_result_panel.add(query_result, BorderLayout.NORTH);
        panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(2, 4));
        panel4.setMaximumSize(new Dimension(800, 300));
        panel4.setMinimumSize(new Dimension(800, 300));
        panel4.setPreferredSize(new Dimension(800, 300));
        customer_view.add(panel4, BorderLayout.CENTER);
        center_panel_label = new JLabel();
        Font center_panel_labelFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, center_panel_label.getFont());
        if (center_panel_labelFont != null) center_panel_label.setFont(center_panel_labelFont);
        center_panel_label.setMinimumSize(new Dimension(100, 20));
        center_panel_label.setPreferredSize(new Dimension(100, 20));
        center_panel_label.setText("Enter the Query");
        center_panel_label.setVerifyInputWhenFocusTarget(true);
        panel4.add(center_panel_label, BorderLayout.NORTH);
        scroll_panel = new JScrollPane();
        scroll_panel.setHorizontalScrollBarPolicy(30);
        scroll_panel.setMaximumSize(new Dimension(800, 300));
        scroll_panel.setMinimumSize(new Dimension(800, 300));
        scroll_panel.setPreferredSize(new Dimension(800, 300));
        scroll_panel.setVerticalScrollBarPolicy(20);
        panel4.add(scroll_panel, BorderLayout.WEST);
        query_text_area = new JTextArea();
        query_text_area.setColumns(5);
        Font query_text_areaFont = this.$$$getFont$$$("Arial", Font.PLAIN, 20, query_text_area.getFont());
        if (query_text_areaFont != null) query_text_area.setFont(query_text_areaFont);
        query_text_area.setLineWrap(true);
        query_text_area.setMargin(new Insets(0, 5, 0, 5));
        query_text_area.setMaximumSize(new Dimension(100, 350));
        query_text_area.setMinimumSize(new Dimension(100, 350));
        query_text_area.setPreferredSize(new Dimension(100, 350));
        query_text_area.setRows(5);
        query_text_area.setWrapStyleWord(true);
        scroll_panel.setViewportView(query_text_area);
        button_panel = new JPanel();
        button_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 400, 5));
        button_panel.setMaximumSize(new Dimension(800, 70));
        button_panel.setMinimumSize(new Dimension(800, 70));
        button_panel.setPreferredSize(new Dimension(800, 70));
        panel4.add(button_panel, BorderLayout.SOUTH);
        executeButton = new JButton();
        executeButton.setActionCommand("");
        Font executeButtonFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, executeButton.getFont());
        if (executeButtonFont != null) executeButton.setFont(executeButtonFont);
        executeButton.setHorizontalAlignment(0);
        executeButton.setMargin(new Insets(0, 5, 0, 5));
        executeButton.setMaximumSize(new Dimension(100, 40));
        executeButton.setMinimumSize(new Dimension(100, 40));
        executeButton.setPreferredSize(new Dimension(100, 40));
        executeButton.setText("Execute");
        button_panel.add(executeButton);
        admin_view = new JPanel();
        admin_view.setLayout(new BorderLayout(0, 0));
        Font admin_viewFont = this.$$$getFont$$$("Arial", Font.BOLD, 14, admin_view.getFont());
        if (admin_viewFont != null) admin_view.setFont(admin_viewFont);
        admin_view.setMinimumSize(new Dimension(1000, 400));
        admin_view.setPreferredSize(new Dimension(500, 500));
        tabbedPane1.addTab("Admin View", admin_view);
        admin_label_panel = new JPanel();
        admin_label_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        admin_label_panel.setMinimumSize(new Dimension(1500, 50));
        admin_label_panel.setPreferredSize(new Dimension(1500, 50));
        admin_view.add(admin_label_panel, BorderLayout.NORTH);
        admin_label_text = new JLabel();
        Font admin_label_textFont = this.$$$getFont$$$("Arial", Font.BOLD, 20, admin_label_text.getFont());
        if (admin_label_textFont != null) admin_label_text.setFont(admin_label_textFont);
        admin_label_text.setText("Admin View");
        admin_label_panel.add(admin_label_text);
        admin_functions_panel = new JPanel();
        admin_functions_panel.setLayout(new GridLayoutManager(7, 1, new Insets(15, 0, 0, 0), 2, 5));
        admin_functions_panel.setMinimumSize(new Dimension(500, 200));
        admin_functions_panel.setPreferredSize(new Dimension(500, 200));
        admin_view.add(admin_functions_panel, BorderLayout.WEST);
        admin_function_one = new JLabel();
        Font admin_function_oneFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, admin_function_one.getFont());
        if (admin_function_oneFont != null) admin_function_one.setFont(admin_function_oneFont);
        admin_function_one.setText("1. View All Customers");
        admin_function_one.setVerticalAlignment(1);
        admin_function_one.setVerticalTextPosition(1);
        admin_functions_panel.add(admin_function_one, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        add_function_two = new JLabel();
        Font add_function_twoFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, add_function_two.getFont());
        if (add_function_twoFont != null) add_function_two.setFont(add_function_twoFont);
        add_function_two.setText("2. Register Customer");
        add_function_two.setVerticalAlignment(1);
        add_function_two.setVerticalTextPosition(1);
        admin_functions_panel.add(add_function_two, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        admin_function_three = new JLabel();
        admin_function_three.setFocusCycleRoot(true);
        Font admin_function_threeFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, admin_function_three.getFont());
        if (admin_function_threeFont != null) admin_function_three.setFont(admin_function_threeFont);
        admin_function_three.setText("3. Change Tariff Rate");
        admin_function_three.setVerticalAlignment(1);
        admin_function_three.setVerticalTextPosition(1);
        admin_functions_panel.add(admin_function_three, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        admin_function_four = new JLabel();
        Font admin_function_fourFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, admin_function_four.getFont());
        if (admin_function_fourFont != null) admin_function_four.setFont(admin_function_fourFont);
        admin_function_four.setText("4. Delete a Customer");
        admin_function_four.setVerticalAlignment(1);
        admin_function_four.setVerticalTextPosition(1);
        admin_functions_panel.add(admin_function_four, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        login_admin_label = new JLabel();
        Font login_admin_labelFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, login_admin_label.getFont());
        if (login_admin_labelFont != null) login_admin_label.setFont(login_admin_labelFont);
        login_admin_label.setText("5. Login");
        login_admin_label.setVerticalAlignment(1);
        login_admin_label.setVerticalTextPosition(1);
        admin_functions_panel.add(login_admin_label, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        empty_label_2 = new JLabel();
        empty_label_2.setText("");
        empty_label_2.setVerticalAlignment(1);
        empty_label_2.setVerticalTextPosition(1);
        admin_functions_panel.add(empty_label_2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        admin_query_execute_panel = new JPanel();
        admin_query_execute_panel.setLayout(new BorderLayout(25, 25));
        admin_query_execute_panel.setMaximumSize(new Dimension(500, 100));
        admin_query_execute_panel.setMinimumSize(new Dimension(500, 100));
        admin_query_execute_panel.setPreferredSize(new Dimension(500, 100));
        admin_functions_panel.add(admin_query_execute_panel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        query_result_2 = new JLabel();
        Font query_result_2Font = this.$$$getFont$$$("Arial", Font.BOLD, 16, query_result_2.getFont());
        if (query_result_2Font != null) query_result_2.setFont(query_result_2Font);
        query_result_2.setMaximumSize(new Dimension(200, 100));
        query_result_2.setMinimumSize(new Dimension(200, 100));
        query_result_2.setPreferredSize(new Dimension(200, 100));
        query_result_2.setText("");
        admin_query_execute_panel.add(query_result_2, BorderLayout.NORTH);
        scroll_admin_panel_2 = new JPanel();
        scroll_admin_panel_2.setLayout(new BorderLayout(0, 0));
        admin_view.add(scroll_admin_panel_2, BorderLayout.CENTER);
        execute_query_label_1 = new JLabel();
        Font execute_query_label_1Font = this.$$$getFont$$$("Arial", Font.BOLD, 18, execute_query_label_1.getFont());
        if (execute_query_label_1Font != null) execute_query_label_1.setFont(execute_query_label_1Font);
        execute_query_label_1.setMaximumSize(new Dimension(100, 20));
        execute_query_label_1.setMinimumSize(new Dimension(100, 20));
        execute_query_label_1.setPreferredSize(new Dimension(100, 20));
        execute_query_label_1.setText("Enter the Query");
        scroll_admin_panel_2.add(execute_query_label_1, BorderLayout.NORTH);
        admin_scroll_panel = new JScrollPane();
        admin_scroll_panel.setHorizontalScrollBarPolicy(32);
        admin_scroll_panel.setMaximumSize(new Dimension(800, 300));
        admin_scroll_panel.setMinimumSize(new Dimension(800, 300));
        admin_scroll_panel.setPreferredSize(new Dimension(800, 300));
        admin_scroll_panel.setVerticalScrollBarPolicy(22);
        scroll_admin_panel_2.add(admin_scroll_panel, BorderLayout.WEST);
        admin_query_area = new JTextArea();
        Font admin_query_areaFont = this.$$$getFont$$$("Arial", Font.PLAIN, 20, admin_query_area.getFont());
        if (admin_query_areaFont != null) admin_query_area.setFont(admin_query_areaFont);
        admin_query_area.setLineWrap(true);
        admin_query_area.setMargin(new Insets(0, 5, 0, 5));
        admin_query_area.setMaximumSize(new Dimension(100, 350));
        admin_query_area.setMinimumSize(new Dimension(100, 350));
        admin_query_area.setPreferredSize(new Dimension(100, 350));
        admin_query_area.setWrapStyleWord(true);
        admin_scroll_panel.setViewportView(admin_query_area);
        execute_button_panel_2 = new JPanel();
        execute_button_panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 400, 5));
        execute_button_panel_2.setMaximumSize(new Dimension(300, 60));
        execute_button_panel_2.setMinimumSize(new Dimension(1200, 60));
        execute_button_panel_2.setPreferredSize(new Dimension(300, 60));
        scroll_admin_panel_2.add(execute_button_panel_2, BorderLayout.SOUTH);
        admin_view_button = new JButton();
        admin_view_button.setActionCommand("Execute Query");
        Font admin_view_buttonFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, admin_view_button.getFont());
        if (admin_view_buttonFont != null) admin_view_button.setFont(admin_view_buttonFont);
        admin_view_button.setHorizontalAlignment(0);
        admin_view_button.setLabel("Execute");
        admin_view_button.setMargin(new Insets(0, 5, 0, 5));
        admin_view_button.setMaximumSize(new Dimension(100, 40));
        admin_view_button.setMinimumSize(new Dimension(100, 40));
        admin_view_button.setPreferredSize(new Dimension(100, 40));
        admin_view_button.setText("Execute");
        execute_button_panel_2.add(admin_view_button);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout(0, 0));
        panel5.setInheritsPopupMenu(false);
        panel5.setMaximumSize(new Dimension(1500, 400));
        panel5.setMinimumSize(new Dimension(1500, 400));
        panel5.setPreferredSize(new Dimension(1500, 400));
        tabbedPane1.addTab("Procedure Calls", panel5);
        procedure_view_panel = new JPanel();
        procedure_view_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        procedure_view_panel.setMaximumSize(new Dimension(1500, 120));
        procedure_view_panel.setMinimumSize(new Dimension(1500, 120));
        procedure_view_panel.setPreferredSize(new Dimension(1500, 120));
        panel5.add(procedure_view_panel, BorderLayout.NORTH);
        procedure_label = new JLabel();
        Font procedure_labelFont = this.$$$getFont$$$("Arial", Font.BOLD, 20, procedure_label.getFont());
        if (procedure_labelFont != null) procedure_label.setFont(procedure_labelFont);
        procedure_label.setHorizontalAlignment(0);
        procedure_label.setText("Procedures View");
        procedure_view_panel.add(procedure_label);
        options_proc_panel_one = new JPanel();
        options_proc_panel_one.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        options_proc_panel_one.setMaximumSize(new Dimension(500, 200));
        options_proc_panel_one.setMinimumSize(new Dimension(500, 200));
        options_proc_panel_one.setPreferredSize(new Dimension(500, 200));
        panel5.add(options_proc_panel_one, BorderLayout.WEST);
        procedure_option_panel = new JPanel();
        procedure_option_panel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        procedure_option_panel.setMaximumSize(new Dimension(500, 200));
        procedure_option_panel.setMinimumSize(new Dimension(500, 200));
        procedure_option_panel.setPreferredSize(new Dimension(500, 200));
        options_proc_panel_one.add(procedure_option_panel);
        first_procedure_label = new JLabel();
        Font first_procedure_labelFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, first_procedure_label.getFont());
        if (first_procedure_labelFont != null) first_procedure_label.setFont(first_procedure_labelFont);
        first_procedure_label.setHorizontalAlignment(2);
        first_procedure_label.setOpaque(true);
        first_procedure_label.setText("1. Generate Electricity Bill");
        procedure_option_panel.add(first_procedure_label, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(230, 30), new Dimension(230, 30), new Dimension(230, 30), 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Arial", Font.BOLD, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setHorizontalAlignment(2);
        label2.setText("Enter Meter Number");
        procedure_option_panel.add(label2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(160, 20), new Dimension(160, 20), new Dimension(160, 20), 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Arial", Font.BOLD, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setHorizontalAlignment(2);
        label3.setText("Enter Customer Id");
        procedure_option_panel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(160, 20), new Dimension(160, 20), new Dimension(160, 20), 0, false));
        meter_num_field = new JTextField();
        procedure_option_panel.add(meter_num_field, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, 1, new Dimension(200, 20), new Dimension(200, 20), new Dimension(200, 20), 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Arial", Font.BOLD, 14, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Enter Peak Hour Reading");
        procedure_option_panel.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 20), new Dimension(200, 20), new Dimension(200, 20), 0, false));
        peak_hr_reading_text = new JTextField();
        procedure_option_panel.add(peak_hr_reading_text, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 20), new Dimension(200, 20), new Dimension(200, 20), 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Arial", Font.BOLD, 14, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Enter offPeak hour Reading");
        procedure_option_panel.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 20), new Dimension(200, 20), new Dimension(200, 20), 0, false));
        off_peak_hr_reading_text = new JTextField();
        procedure_option_panel.add(off_peak_hr_reading_text, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 20), new Dimension(200, 20), new Dimension(200, 20), 0, false));
        customer_id_text_field = new JTextField();
        procedure_option_panel.add(customer_id_text_field, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, 20), new Dimension(200, 20), new Dimension(200, 20), 0, false));
        generate_button = new JButton();
        Font generate_buttonFont = this.$$$getFont$$$("Arial", Font.BOLD, 16, generate_button.getFont());
        if (generate_buttonFont != null) generate_button.setFont(generate_buttonFont);
        generate_button.setHorizontalAlignment(0);
        generate_button.setLabel("Generate Bill");
        generate_button.setText("Generate Bill");
        options_proc_panel_one.add(generate_button);
        dynamic_view_panel = new JPanel();
        dynamic_view_panel.setLayout(new BorderLayout(0, 0));
        dynamic_view_panel.setMaximumSize(new Dimension(1500, 500));
        dynamic_view_panel.setMinimumSize(new Dimension(1500, 500));
        dynamic_view_panel.setPreferredSize(new Dimension(1000, 500));
        panel1.add(dynamic_view_panel, BorderLayout.SOUTH);
        dynamic_panel_2 = new JPanel();
        dynamic_panel_2.setLayout(new BorderLayout(0, 0));
        panel1.add(dynamic_panel_2, BorderLayout.CENTER);
        final JLabel label6 = new JLabel();
        label6.setText("");
        label6.setVerticalAlignment(1);
        label6.setVerticalTextPosition(1);
        dynamic_panel_2.add(label6, BorderLayout.WEST);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /*{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }*/

}
