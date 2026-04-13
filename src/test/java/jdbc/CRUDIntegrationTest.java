package jdbc;

import jdbc.dao.CustomerDAO;
import jdbc.dao.ProductDAO;
import jdbc.model.CustomerModel;
import jdbc.model.ProductModel;

import java.util.List;

public class CRUDIntegrationTest {

    public static void main(String[] args) {
        System.out.println("=== CRUD Tests ===\n");

        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();

        // test getting all customers
        System.out.println("-- Customer findAll --");
        List<CustomerModel> customers = customerDAO.findAll();
        System.out.println("Found " + customers.size() + " customers");
        if (customers.size() > 0) {
            System.out.println("First one: " + customers.get(0).getCustomerCompanyName());
        }

        // test getting one customer
        System.out.println("\n-- Customer findById --");
        CustomerModel c = customerDAO.findById(1);
        if (c != null) {
            System.out.println("Customer 1: " + c.getCustomerCompanyName() + ", " + c.getCustomerCity());
        } else {
            System.out.println("Could not find customer 1");
        }

        // test getting all products
        System.out.println("\n-- Product findAll --");
        List<ProductModel> products = productDAO.findAll();
        System.out.println("Found " + products.size() + " products");
        if (products.size() > 0) {
            System.out.println("First one: " + products.get(0).getProductName() + " - $" + products.get(0).getUnitPrice());
        }

        // test getting one product
        System.out.println("\n-- Product findById --");
        ProductModel p = productDAO.findById(1);
        if (p != null) {
            System.out.println("Product 1: " + p.getProductName() + " - $" + p.getUnitPrice());
        } else {
            System.out.println("Could not find product 1");
        }

        System.out.println("\n=== Done ===");
    }
}
