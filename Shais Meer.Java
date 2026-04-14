import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShaishMeer_UI extends JFrame {

    private JTextField nameField, emailField;
    private JTable table;
    private DefaultTableModel model;

    // 🔗 DATABASE CONNECTION
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=TestDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "1234";

    public ShaishMeer_UI() {

        setTitle("JDBC CRUD App - Shaish Meer");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🟦 FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");

        formPanel.add(addBtn);
        formPanel.add(updateBtn);

        add(formPanel, BorderLayout.NORTH);

        // 🟦 TABLE
        model = new DefaultTableModel(new String[]{"Name", "Email"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 🟦 BOTTOM BUTTONS
        JPanel bottomPanel = new JPanel();

        JButton deleteBtn = new JButton("Delete");
        JButton viewBtn = new JButton("View All");

        bottomPanel.add(deleteBtn);
        bottomPanel.add(viewBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // ======================
        // 🔥 BUTTON ACTIONS
        // ======================

        // ADD
        addBtn.addActionListener(e -> {
            addUser(nameField.getText(), emailField.getText());
            loadTable();
            clearFields();
        });

        // VIEW
        viewBtn.addActionListener(e -> loadTable());

        // DELETE
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String name = model.getValueAt(row, 0).toString();
                deleteUser(name);
                loadTable();
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String oldName = model.getValueAt(row, 0).toString();
                updateUser(oldName, nameField.getText(), emailField.getText());
                loadTable();
            }
        });

        // TABLE CLICK
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                nameField.setText(model.getValueAt(row, 0).toString());
                emailField.setText(model.getValueAt(row, 1).toString());
            }
        });
    }

    // ======================
    // 🔗 DATABASE METHODS
    // ======================

    private Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void addUser(String name, String email) {
        try (Connection con = connect()) {
            String sql = "INSERT INTO Users(name, email) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "User Added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(String name) {
        try (Connection con = connect()) {
            String sql = "DELETE FROM Users WHERE name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUser(String oldName, String newName, String newEmail) {
        try (Connection con = connect()) {
            String sql = "UPDATE Users SET name=?, email=? WHERE name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setString(2, newEmail);
            ps.setString(3, oldName);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String[]> getUsers() {
        List<String[]> list = new ArrayList<>();
        try (Connection con = connect()) {
            String sql = "SELECT name, email FROM Users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new String[]{
                        rs.getString("name"),
                        rs.getString("email")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void loadTable() {
        model.setRowCount(0);
        for (String[] row : getUsers()) {
            model.addRow(row);
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
    }

    // ======================
    // 🚀 MAIN METHOD
    // ======================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShaishMeer_UI().setVisible(true);
        });
    }
}