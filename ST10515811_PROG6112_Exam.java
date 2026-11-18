import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ST10515811_PROG6112_Exam extends JFrame {
    private final JTextField customerNameField = new JTextField();
    private final JComboBox<String> productField = new JComboBox<>(new String[]{
        "Cozy Nights Monarch",
        "Restoric Barbados",
        "Sealy Phuket"
    });
    private final JTextField productPriceField = new JTextField();
    private final JTextField vatField = new JTextField();
    private final JTextField commissionField = new JTextField();
    private final JTextField totalField = new JTextField();
    private final JButton processButton = new JButton("Process");
    private final JButton clearButton = new JButton("Clear");

    public ST10515811_PROG6112_Exam() {
        setTitle("Mattress Sales");
        setSize(500, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createMenu();
        createForm();

        setVisible(true);
    }
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        exitItem.addActionListener(event -> dispose());
        processItem.addActionListener(event -> processSale());
        clearItem.addActionListener(event -> clearForm());

        fileMenu.add(exitItem);
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }
    private void createForm() {
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        vatField.setEditable(false);
        commissionField.setEditable(false);
        totalField.setEditable(false);

        formPanel.add(new JLabel("Customer Name:"));
        formPanel.add(customerNameField);
        formPanel.add(new JLabel("Product Name:"));
        formPanel.add(productField);
        formPanel.add(new JLabel("Product Price:"));
        formPanel.add(productPriceField);
        formPanel.add(new JLabel("VAT Amount:"));
        formPanel.add(vatField);
        formPanel.add(new JLabel("Commission Amount:"));
        formPanel.add(commissionField);
        formPanel.add(new JLabel("Total Sale Amount:"));
        formPanel.add(totalField);
        formPanel.add(processButton);
        formPanel.add(clearButton);

        processButton.addActionListener(event -> processSale());
        clearButton.addActionListener(event -> clearForm());
        add(formPanel, BorderLayout.CENTER);
    }
    private void processSale() {
        String customerName = customerNameField.getText().trim();
        String productName = (String) productField.getSelectedItem();
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the customer name.", "Input Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        double productPrice;
        try {
            productPrice = Double.parseDouble(productPriceField.getText().trim());
            if (productPrice < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid product price.", "Invalid Price",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        ProductCalculations calculations = new ProductCalculations(productPrice);
        double vat = calculations.GetVat();
        double commission = calculations.GetCommission();
        double total = calculations.GetTotal();

        vatField.setText(String.format("%.2f", vat));
        commissionField.setText(String.format("%.2f", commission));
        totalField.setText(String.format("%.2f", total));

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("data.txt", true))) {
            writer.write("Customer Name: " + customerName);
            writer.newLine();
            writer.write("Product Name: " + productName);
            writer.newLine();
            writer.write("VAT Amount: " + String.format("%.2f", vat));
            writer.newLine();
            writer.write("Commission Amount: " + String.format("%.2f", commission));
            writer.newLine();
            writer.write("Total Sale Amount: " + String.format("%.2f", total));
            writer.newLine();
            writer.write("------------------------------");
            writer.newLine();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this,
            "Could not save sale to data.txt: " + exception.getMessage(),
            "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
                "Sale processed and saved successfully.");
    }
    private void clearForm() {
        customerNameField.setText("");
        productPriceField.setText("");
        vatField.setText("");
        commissionField.setText("");
        totalField.setText("");
        productField.setSelectedIndex(0);
        customerNameField.requestFocus();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ST10515811_PROG6112_Exam::new);
    }
}
