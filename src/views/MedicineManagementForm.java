package views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

public class MedicineManagementForm extends JFrame {
    private JPanel topPanel, centerPanel, bottomPanel, medNamePanel, medPricePanel, medStockPanel, formPanel;
    private JLabel title, medNameLabel, medPriceLabel, medStockLabel;
    private JTextField medName, medPrice, medStock;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scrollPane;
    private JButton addButton;

    private Vector<String> columnName;
    private Vector<String> dataDummy;

    private void initItem() {
        setLayout(new BorderLayout());

        /* For Top Panel */
        topPanel = new JPanel();
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        title = new JLabel("Medicine Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 40));

        /* For Center Panel */
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // TODO Buat inital table
        columnName = new Vector<>();
        columnName.add("Medicine ID");
        columnName.add("Medicine Name");
        columnName.add("Medicine Price");
        columnName.add("Medicine Stock");

        System.out.println(columnName);

        // TODO Data dummy, nanti harus diganti pake data yang diambil dari data access
        dataDummy = new Vector<>();
        dataDummy.add("1");
        dataDummy.add("Panadol");
        dataDummy.add("20.000");
        dataDummy.add("15");

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);

        /* For Form Panel */
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));

        medNameLabel = new JLabel("Name");
        medNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medPriceLabel = new JLabel("Price");
        medPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medStockLabel = new JLabel("Stock");
        medStockLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        medName = new JTextField();
        medName.setColumns(40);
        medPrice = new JTextField();
        medPrice.setColumns(40);
        medStock = new JTextField();
        medStock.setColumns(40);

        medNamePanel = new JPanel();
        medNamePanel.setLayout(new GridLayout(2, 2, 4, 4));
        medPricePanel = new JPanel();
        medPricePanel.setLayout(new GridLayout(2, 2, 4, 4));
        medStockPanel = new JPanel();
        medStockPanel.setLayout(new GridLayout(2, 2, 4, 4));

        /* For Bottom Panel */
        bottomPanel = new JPanel();

        addButton = new JButton("Add");
    }

    private void setItem() {
        /* Top Panel */
        topPanel.add(title);

        /* Center Panel */
        medNamePanel.add(medNameLabel);
        medNamePanel.add(medName);

        medPricePanel.add(medPriceLabel);
        medPricePanel.add(medPrice);

        medStockPanel.add(medStockLabel);
        medStockPanel.add(medStock);

        formPanel.add(medNamePanel);
        formPanel.add(medPricePanel);
        formPanel.add(medStockPanel);
        formPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        centerPanel.add(scrollPane);
        centerPanel.add(formPanel);
        centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        /* Bottom Panel */
        bottomPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setListener() {
    }

    private void loadData() {
        dtm = new DefaultTableModel(columnName, 0);
        dtm.addRow(dataDummy);

        table.setModel(dtm);
    }

    public MedicineManagementForm() {
        initItem();
        setItem();
        setListener();
        loadData();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Medicine Management Firm");
    }
}
