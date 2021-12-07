package views;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.MedicineController;
import models.Medicine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class MedicineManagementForm extends JFrame {
    private JLabel title;
    private JPanel topPanel, centerPanel, bottomPanel; // Panel Utama
    private JPanel formPanel, searchPanel; // Panel untuk dimasukkan ke bottomPanel

    /* For Add Form Panel */
    private JPanel medNamePanel, medPricePanel, medStockPanel; // Panel untuk 1 Form Panel
    private JLabel medNameLabel, medPriceLabel, medStockLabel;
    private JTextField medName, medPrice, medStock;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scrollTable;
    private JButton addButton, updateButton, deleteButton;

    /* For Search Form Panel */
    private JPanel searchFormPanel; // Panel untuk 1 Search Form Panel
    private JLabel searchFieldLabel;
    private JTextField searchField;
    private JButton searchButton;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Vector<String> row;

    private void initFormPanel() {
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        medNamePanel = new JPanel();
        medNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        medNamePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        medPricePanel = new JPanel();
        medPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        medPricePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        medStockPanel = new JPanel();
        medStockPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        medStockPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        medNameLabel = new JLabel("Name");
        medNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medNameLabel.setPreferredSize(new Dimension(150, 25));

        medPriceLabel = new JLabel("Price");
        medPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medPriceLabel.setPreferredSize(new Dimension(150, 25));

        medStockLabel = new JLabel("Stock");
        medStockLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medStockLabel.setPreferredSize(new Dimension(150, 25));

        medName = new JTextField();
        medName.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        medPrice = new JTextField();
        medPrice.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        medStock = new JTextField();
        medStock.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
    }

    private void initSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.PAGE_AXIS));

        searchFieldLabel = new JLabel("Name");
        searchFieldLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        searchField = new JTextField();
        searchField.setColumns(40);

        searchFormPanel = new JPanel();
        searchFormPanel.setLayout(new GridLayout(2, 2, 4, 4));

        searchButton = new JButton("Search");
    }

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

        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        scrollTable = new JScrollPane(table);

        initFormPanel();
        // initSearchPanel();

        /* For Bottom Panel */
        bottomPanel = new JPanel();
    }

    private void setAddFormPanel() {
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

        centerPanel.add(formPanel);

        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);
    }

    private void setSearchPanel() {
        searchFormPanel.add(searchFieldLabel);
        searchFormPanel.add(searchField);

        searchPanel.add(searchFormPanel);

        centerPanel.add(searchPanel);

        centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        bottomPanel.add(searchButton);
    }

    private void setItem() {
        /* Top Panel */
        topPanel.add(title);

        /* Center Panel */
        centerPanel.add(scrollTable);

        setAddFormPanel();
        // setSearchPanel();

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setListener() {

        // searchButton.addActionListener(new ActionListener() {
        //
        // @Override
        // public void actionPerformed(ActionEvent e) {
        //
        // }
        // });

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLICK");
                String name = medName.getText();
                int price = Integer.parseInt(medPrice.getText());
                int stock = Integer.parseInt(medStock.getText());

                MedicineController medController = MedicineController.getInstance();
                medController.AddMedicine(name, price, stock);
                loadData();
            }
        });
    }

    private void loadData() {
        Object[] header = { "Medicine ID", "Medicine Name", "Medicine Price", "Medicine Stock" };

        dtm = new DefaultTableModel(header, 0);
        List<Medicine> meds = MedicineController.getInstance().GetAllMedicine();

        for (Medicine medicine : meds) {
            row = new Vector<>();
            row.add(Integer.toString(medicine.getMedicineID()));
            row.add(medicine.getName());
            row.add(Integer.toString(medicine.getPrice()));
            row.add(Integer.toString(medicine.getStock()));
            dtm.addRow(row);
        }

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
