package jdbc.ui;

import jdbc.dao.CustomerDAO;
import jdbc.dao.ProductDAO;
import jdbc.model.CustomerModel;
import jdbc.model.ProductModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class MainApp extends JFrame {

    private CustomerDAO customerDAO;
    private ProductDAO productDAO;

    private JTable customerTable;
    private DefaultTableModel customerTableModel;
    private JTextField custCompanyField, custContactField, custCityField, custCountryField, custPhoneField;

    private JTable productTable;
    private DefaultTableModel productTableModel;
    private JTextField prodNameField, prodSupplierField, prodCategoryField, prodPriceField;

    public MainApp() {
        customerDAO = new CustomerDAO();
        productDAO = new ProductDAO();

        setTitle("Northwinds2024Student - EOS Group 1");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Customers", buildCustomerTab());
        tabs.addTab("Products", buildProductTab());
        add(tabs);

        loadCustomers();
        loadProducts();
    }

    private JPanel buildCustomerTab() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] cols = {"ID", "Company", "Contact", "City", "Country", "Phone"};
        customerTableModel = new DefaultTableModel(cols, 0);
        customerTable = new JTable(customerTableModel);
        panel.add(new JScrollPane(customerTable), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Customer Info"));

        form.add(new JLabel("Company:"));
        custCompanyField = new JTextField();
        form.add(custCompanyField);

        form.add(new JLabel("Contact:"));
        custContactField = new JTextField();
        form.add(custContactField);

        form.add(new JLabel("City:"));
        custCityField = new JTextField();
        form.add(custCityField);

        form.add(new JLabel("Country:"));
        custCountryField = new JTextField();
        form.add(custCountryField);

        form.add(new JLabel("Phone:"));
        custPhoneField = new JTextField();
        form.add(custPhoneField);

        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");

        addBtn.addActionListener(e -> addCustomer());
        deleteBtn.addActionListener(e -> deleteCustomer());
        refreshBtn.addActionListener(e -> loadCustomers());

        buttons.add(addBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);
        form.add(buttons);

        panel.add(form, BorderLayout.SOUTH);

        customerTable.getSelectionModel().addListSelectionListener(e -> {
            int row = customerTable.getSelectedRow();
            if (row >= 0) {
                custCompanyField.setText(String.valueOf(customerTableModel.getValueAt(row, 1)));
                custContactField.setText(String.valueOf(customerTableModel.getValueAt(row, 2)));
                custCityField.setText(String.valueOf(customerTableModel.getValueAt(row, 3)));
                custCountryField.setText(String.valueOf(customerTableModel.getValueAt(row, 4)));
                custPhoneField.setText(String.valueOf(customerTableModel.getValueAt(row, 5)));
            }
        });

        return panel;
    }

    private JPanel buildProductTab() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] cols = {"ID", "Name", "Supplier", "Category", "Price", "Discontinued"};
        productTableModel = new DefaultTableModel(cols, 0);
        productTable = new JTable(productTableModel);
        panel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Product Info"));

        form.add(new JLabel("Name:"));
        prodNameField = new JTextField();
        form.add(prodNameField);

        form.add(new JLabel("Supplier ID:"));
        prodSupplierField = new JTextField();
        form.add(prodSupplierField);

        form.add(new JLabel("Category ID:"));
        prodCategoryField = new JTextField();
        form.add(prodCategoryField);

        form.add(new JLabel("Price:"));
        prodPriceField = new JTextField();
        form.add(prodPriceField);

        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");

        addBtn.addActionListener(e -> addProduct());
        deleteBtn.addActionListener(e -> deleteProduct());
        refreshBtn.addActionListener(e -> loadProducts());

        buttons.add(addBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);
        form.add(buttons);

        panel.add(form, BorderLayout.SOUTH);

        productTable.getSelectionModel().addListSelectionListener(e -> {
            int row = productTable.getSelectedRow();
            if (row >= 0) {
                prodNameField.setText(String.valueOf(productTableModel.getValueAt(row, 1)));
                prodSupplierField.setText(String.valueOf(productTableModel.getValueAt(row, 2)));
                prodCategoryField.setText(String.valueOf(productTableModel.getValueAt(row, 3)));
                prodPriceField.setText(String.valueOf(productTableModel.getValueAt(row, 4)));
            }
        });

        return panel;
    }

    private void loadCustomers() {
        customerTableModel.setRowCount(0);
        List<CustomerModel> list = customerDAO.findAll();
        for (CustomerModel c : list) {
            customerTableModel.addRow(new Object[]{
                c.getCustomerId(), c.getCustomerCompanyName(),
                c.getCustomerContactName(), c.getCustomerCity(),
                c.getCustomerCountry(), c.getCustomerPhoneNumber()
            });
        }
    }

    private void loadProducts() {
        productTableModel.setRowCount(0);
        List<ProductModel> list = productDAO.findAll();
        for (ProductModel p : list) {
            productTableModel.addRow(new Object[]{
                p.getProductId(), p.getProductName(),
                p.getSupplierId(), p.getCategoryId(),
                p.getUnitPrice(), p.isDiscontinued() ? "Yes" : "No"
            });
        }
    }

    private void addCustomer() {
        CustomerModel c = new CustomerModel(0,
            custCompanyField.getText(), custContactField.getText(), "",
            "", custCityField.getText(), "", "",
            custCountryField.getText(), custPhoneField.getText(), "");
        if (customerDAO.insert(c)) {
            JOptionPane.showMessageDialog(this, "Customer added.");
            loadCustomers();
        } else {
            JOptionPane.showMessageDialog(this, "Could not add customer.");
        }
    }

    private void deleteCustomer() {
        int row = customerTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pick a customer first.");
            return;
        }
        int id = (int) customerTableModel.getValueAt(row, 0);
        if (customerDAO.delete(id)) {
            JOptionPane.showMessageDialog(this, "Customer deleted.");
            loadCustomers();
        } else {
            JOptionPane.showMessageDialog(this, "Could not delete customer.");
        }
    }

    private void addProduct() {
        try {
            ProductModel p = new ProductModel(0,
                prodNameField.getText(),
                Integer.parseInt(prodSupplierField.getText()),
                Integer.parseInt(prodCategoryField.getText()),
                new BigDecimal(prodPriceField.getText()),
                false);
            if (productDAO.insert(p)) {
                JOptionPane.showMessageDialog(this, "Product added.");
                loadProducts();
            } else {
                JOptionPane.showMessageDialog(this, "Could not add product.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Check your inputs.");
        }
    }

    private void deleteProduct() {
        int row = productTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pick a product first.");
            return;
        }
        int id = (int) productTableModel.getValueAt(row, 0);
        if (productDAO.delete(id)) {
            JOptionPane.showMessageDialog(this, "Product deleted.");
            loadProducts();
        } else {
            JOptionPane.showMessageDialog(this, "Could not delete product.");
        }
    }

    public static void main(String[] args) {
        new MainApp().setVisible(true);
    }
}
