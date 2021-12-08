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
    private JPanel idPanel, medNamePanel, medPricePanel, medStockPanel; // Panel untuk 1 Form Panel
    private JLabel idLabel, medNameLabel, medPriceLabel, medStockLabel;
    private JTextField idTextField, medNameTextField, medPriceTextField, medStockTextField;
    private JTable table;
    private DefaultTableModel dtm;
    private JScrollPane scrollTable;
    private JButton addButton, updateButton, deleteButton;

    /* For Search Form Panel */
    private JPanel searchFormPanel; // Panel untuk 1 Search Form Panel
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JButton searchButton;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Vector<String> row;

    private void initFormPanel() {
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        // searchFormPanel = new JPanel();
        // searchFormPanel.setLayout(new GridLayout(2, 2, 4, 4));

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        idPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        medNamePanel = new JPanel();
        medNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        medNamePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        medPricePanel = new JPanel();
        medPricePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        medPricePanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        medStockPanel = new JPanel();
        medStockPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        medStockPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 25));

        searchLabel = new JLabel("Search");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        searchLabel.setPreferredSize(new Dimension(150, 25));

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        idLabel.setPreferredSize(new Dimension(150, 25));

        medNameLabel = new JLabel("Name");
        medNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medNameLabel.setPreferredSize(new Dimension(150, 25));

        medPriceLabel = new JLabel("Price");
        medPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medPriceLabel.setPreferredSize(new Dimension(150, 25));

        medStockLabel = new JLabel("Stock");
        medStockLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        medStockLabel.setPreferredSize(new Dimension(150, 25));

        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        idTextField = new JTextField();
        idTextField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        medNameTextField = new JTextField();
        medNameTextField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        medPriceTextField = new JTextField();
        medPriceTextField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        medStockTextField = new JTextField();
        medStockTextField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300, 25));

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");

    }

    /*
     * private void initSearchPanel() {
     * searchPanel = new JPanel();
     * searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.PAGE_AXIS));
     * 
     * searchFieldLabel = new JLabel("Name");
     * searchFieldLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
     * searchFieldLabel.setPreferredSize(new Dimension(150, 25));
     * 
     * searchField = new JTextField();
     * searchField.setPreferredSize(new Dimension((int) screenSize.getWidth() - 300,
     * 25));
     * 
     * searchFormPanel = new JPanel();
     * searchFormPanel.setLayout(new GridLayout(2, 2, 4, 4));
     * 
     * searchButton = new JButton("Search");
     * }
     */

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

    private void setFormPanel() {
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        medNamePanel.add(medNameLabel);
        medNamePanel.add(medNameTextField);

        medPricePanel.add(medPriceLabel);
        medPricePanel.add(medPriceTextField);

        medStockPanel.add(medStockLabel);
        medStockPanel.add(medStockTextField);

        formPanel.add(searchPanel);
        formPanel.add(idPanel);
        formPanel.add(medNamePanel);
        formPanel.add(medPricePanel);
        formPanel.add(medStockPanel);
        formPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        centerPanel.add(formPanel);

        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(searchButton);
    }

    /*
     * private void setSearchPanel() {
     * searchFormPanel.add(searchFieldLabel);
     * searchFormPanel.add(searchField);
     * 
     * searchPanel.add(searchFormPanel);
     * 
     * centerPanel.add(searchPanel);
     * 
     * centerPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
     * 
     * bottomPanel.add(searchButton);
     * }
     */

    private void setItem() {
        /* Top Panel */
        topPanel.add(title);

        /* Center Panel */
        centerPanel.add(scrollTable);

        setFormPanel();
        // setSearchPanel();

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setListener() {
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = medNameTextField.getText();
                int price = Integer.parseInt(medPriceTextField.getText());
                int stock = Integer.parseInt(medStockTextField.getText());

                MedicineController medController = MedicineController.getInstance();
                medController.addMedicine(name, price, stock);
                loadData();
            }
        });

        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());
                String name = medNameTextField.getText();
                int price = Integer.parseInt(medPriceTextField.getText());
                int stock = Integer.parseInt(medStockTextField.getText());

                MedicineController medController = MedicineController.getInstance();
                medController.updateMedicine(id, name, price, stock);
                loadData();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idTextField.getText());

                MedicineController medController = MedicineController.getInstance();
                medController.deleteMedicine(id);
                loadData();
            }
        });

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedName = searchTextField.getText();

                MedicineController medController = MedicineController.getInstance();
                List<Medicine> foundMedicine = medController.searchMedicine(searchedName);
                loadData(foundMedicine);
            }
        });
    }

    private void loadData() {
        Object[] header = { "Medicine ID", "Medicine Name", "Medicine Price", "Medicine Stock" };

        dtm = new DefaultTableModel(header, 0);
        List<Medicine> meds = MedicineController.getInstance().getAllMedicine();

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

    private void loadData(List<Medicine> foundMedicine) {
        Object[] header = { "Medicine ID", "Medicine Name", "Medicine Price", "Medicine Stock" };

        dtm = new DefaultTableModel(header, 0);

        for (Medicine medicine : foundMedicine) {
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
