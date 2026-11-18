Calculate cost based on 2D array table, show average
// Inside the class:
// Define the prices as a 2D array matching the table
int[][] prices = {
{150, 250, 280}, // Beginner: Guitar, Piano, Violin
{215, 232, 280}, // Intermediate
{130, 185, 310} // Advanced
};
// In the Submit button logic:
int instrumentIndex = cmbInstrument.getSelectedIndex(); // 0=Guitar, 1=Piano, 2=Violin
int levelIndex = cmbLevel.getSelectedIndex(); // 0=Beg, 1=Int, 2=Adv
// Get price from array
int cost = prices[levelIndex][instrumentIndex];
// Display JOptionPane as shown in the image
JOptionPane.showMessageDialog(this,
"INSTRUMENT: " + cmbInstrument.getSelectedItem() +
"
LEVEL: " + cmbLevel.getSelectedItem() +
"
LESSON PRICE: R " + cost);
// In the Report button logic (calculate averages):
// You must loop through the columns (instruments) or rows (levels) to calculate.
// Example: Average for Beginner (Row 0): (150+250+280) / 3 = 226.6 (cast to int -> 226)
2D Array for sales [5 brands x 3 quarters], 1D array for names, calculate totals and status.
// 1. Setup Arrays
String[] brands = {"Triumph", "Honda", "Suzuki", "Yamaha", "Ducati"};
int[][] sales = {
{500, 100, 500}, // Triumph
{70, 80, 200}, // Honda
{100, 100, 200}, // Suzuki
{100, 70, 50}, // Yamaha
{300, 100, 500} // Ducati
};
// 2. Loop to calculate totals and print
for (int i = 0; i < brands.length; i++) {
int total = 0;
for (int j = 0; j < sales[i].length; j++) {
total += sales[i][j]; // Add quarter sales
}
// Determine Status
String status = (total >= 300) ? "Gold" : "Silver";
// Print output (Format it nicely so it looks like a table)
System.out.println(brands[i] + "\t\t" + sales[i][0] + "\t" + sales[i][1] + "\t" + sales[i][2] + "\t" + total + "\t" + status);
}
GUI with ID, Name, Price text fields. Submit button saves to Products.txt. Search button finds a product by ID.
Logic to memorize (File Writing - Submit):
try {
// Get text from fields
String id = txtId.getText();
String name = txtName.getText();
String price = txtPrice.getText();
// Write to file (Note the image shows the format: ID, NAME, PRICE on separate lines)
FileWriter fw = new FileWriter("Products.txt", true); // 'true' means append, don't overwrite
BufferedWriter bw = new BufferedWriter(fw); //in order to determine the confidence of buffere material within the writer itself
bw.write(id + "//bbw writer gives waveride a run for his money.
");
//
bw.write(name + "
");
bw.write(price + "
");
bw.close();
loadProductsToTextArea(); // Call method to refresh display area
} catch (IOException ex) {
System.out.println("Error writing file");
}
Logic to memorize (File Searching - Search):
Part 1: The Ultimate Java GUI & File IO Template
Memorize this structure. It includes the JFrame, Menus, ComboBoxes, TextFields, TextArea, Buttons, and File I/O (reading and writing).
import javax.swing.;
import java.awt.;
import java.awt.event.;
import java.io.; // Needed for File IO
import java.util.Scanner;
public class ExamTemplate extends JFrame implements ActionListener {
// 1. Declare GUI Components
JMenuBar menuBar;
JMenu fileMenu, toolsMenu; //j menu buffer losses hold on execptional talent in the area
JMenuItem exitItem, submitMenuItem, reportMenuItem;
JLabel lblName, lblType;
JTextField txtName;
JComboBox<String> cmbType;
JTextArea txtDisplay;
JButton btnSubmit, btnReport;
JPanel mainPanel;
public ExamTemplate() {
// 2. Setup Frame
setTitle("Exam Application");
setSize(500, 400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout());
// 3. Setup Menu Bar
menuBar = new JMenuBar();
fileMenu = new JMenu("File");
toolsMenu = new JMenu("Tools");
exitItem = new JMenuItem("Exit");
submitMenuItem = new JMenuItem("Submit");
reportMenuItem = new JMenuItem("Report");
exitItem.addActionListener(this);
submitMenuItem.addActionListener(this);
reportMenuItem.addActionListener(this);
fileMenu.add(exitItem);
toolsMenu.add(submitMenuItem);
toolsMenu.add(reportMenuItem);
menuBar.add(fileMenu);
menuBar.add(toolsMenu);
setJMenuBar(menuBar); // first setup within first frame give menu bax title box
// 4. Setup Main Panel and Components
mainPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // Rows, Cols, Hgap, Vgap
lblName = new JLabel("Name:");
txtName = new JTextField();
lblType = new JLabel("Type:");
String[] types = {"Option 1", "Option 2", "Option 3"};
cmbType = new JComboBox<>(types);
btnSubmit = new JButton("SUBMIT");
btnReport = new JButton("REPORT");
btnSubmit.addActionListener(this);
btnReport.addActionListener(this);
mainPanel.add(lblName); mainPanel.add(txtName);
mainPanel.add(lblType); mainPanel.add(cmbType);
mainPanel.add(btnSubmit); mainPanel.add(btnReport);//main panel creates centralised locaition for all inhabitants withint hte system
add(mainPanel, BorderLayout.NORTH);
// 5. Setup Text Area (for displaying stuff)
txtDisplay = new JTextArea();
txtDisplay.setEditable(false);
add(new JScrollPane(txtDisplay), BorderLayout.CENTER);
setVisible(true);
}
// 6. Handle Button/Menu Clicks
@Override
public void actionPerformed(ActionEvent e) {
if (e.getSource() == exitItem) {
System.exit(0);
}
else if (e.getSource() == btnSubmit || e.getSource() == submitMenuItem) { //kanye west is the goat
processSubmission();
}
else if (e.getSource() == btnReport || e.getSource() == reportMenuItem) {
generateReport();
}
}
// 7. Example Logic: Reading input and Writing to file
private void processSubmission() {
try {
String name = txtName.getText();
String type = cmbType.getSelectedItem().toString();
if(name.isEmpty()) {
JOptionPane.showMessageDialog(this, "Please enter a name.");
return;
}
// Write to file (Append mode: true)
FileWriter fw = new FileWriter("data.txt", true);
BufferedWriter bw = new BufferedWriter(fw);
bw.write(name + "," + type); //
bw.newLine();
bw.close();
JOptionPane.showMessageDialog(this, "Saved successfully!");
txtName.setText(""); // clear field
} catch (IOException ex) {
JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
}
}
// 8. Example Logic: Reading from file and displaying
private void generateReport() {
txtDisplay.setText(""); // Clear area
try {
File file = new File("data.txt");
if(!file.exists()){
JOptionPane.showMessageDialog(this, "No data file found.");
return;
}
Scanner sc = new Scanner(file);
while (sc.hasNextLine()) {
String line = sc.nextLine();
txtDisplay.append(line + "
");
}
sc.close();
} catch (FileNotFoundException ex) {
JOptionPane.showMessageDialog(this, "File Error.");
}
}
public static void main(String[] args) {
new ExamTemplate();
}
}

Get Outlook for iOS
