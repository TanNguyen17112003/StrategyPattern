import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieBookingGUI extends JFrame {
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    MovieBookingGUI(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public class reset implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("reset");
        }
    }

    public class amountChange implements ActionListener {
        private JComboBox<String> amountBox;
        private ArrayList<JLabel> billField;
        private int selectedMovie;

        amountChange(JComboBox<String> amountBox, ArrayList<JLabel> billField, int selectedMovie) {
            this.amountBox = amountBox;
            this.billField = billField;
            this.selectedMovie = selectedMovie;
        }

        public void actionPerformed(ActionEvent e) {
            String selectedText = (String) amountBox.getSelectedItem();
            int selectedAmount = Integer.parseInt(selectedText);
            double newPrice = selectedAmount * movies.get(selectedMovie).getTicket().getPrice();
            double newDSPrice = selectedAmount * movies.get(selectedMovie).getTicket().getDSPrice();
            double newPSPrice = selectedAmount * movies.get(selectedMovie).getTicket().getPSPrice();
            double total = newPrice - newDSPrice - newPSPrice;
            billField.get(0).setText(String.valueOf(newPrice));
            billField.get(1).setText(String.valueOf(newDSPrice));
            billField.get(2).setText(String.valueOf(newPSPrice));
            billField.get(billField.size() - 1).setText(String.valueOf(total));
        }
    }

    public class SubmitButtonListener implements ActionListener {
        private JFrame frame;
        private JLabel totalPrice;

        SubmitButtonListener(JFrame frame, JLabel totalPrice) {
            this.frame = frame;
            this.totalPrice = totalPrice;
        }

        public void actionPerformed(ActionEvent e) {
            // show success message in new window
            JFrame successFrame = new JFrame("Success");
            successFrame.setSize(300, 200);
            successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            successFrame.setResizable(false);
            successFrame.setLocationRelativeTo(null);
            this.frame.setEnabled(false);
            SpringLayout layout = new SpringLayout();
            JPanel successPanel = new JPanel();
            successPanel.setLayout(layout);

            JLabel successLabel = new JLabel("Booking successful!");
            successLabel.setHorizontalAlignment(JLabel.CENTER);
            successLabel.setFont(new Font("Arial", Font.BOLD, 20));
            successPanel.add(successLabel);
            JLabel totalLabel = new JLabel("Your final price is: " + totalPrice.getText());
            totalLabel.setHorizontalAlignment(JLabel.CENTER);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
            successPanel.add(totalLabel);
            JButton okButton = new JButton("OK");
            JFrame temp = this.frame;
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    temp.setEnabled(true);
                    successFrame.dispose();
                }
            });
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, successLabel, 0,
                    SpringLayout.HORIZONTAL_CENTER,
                    successPanel);
            layout.putConstraint(SpringLayout.NORTH, successLabel, 20, SpringLayout.NORTH, successPanel);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, okButton, 0,
                    SpringLayout.HORIZONTAL_CENTER,
                    successPanel);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, totalLabel, 0,
                    SpringLayout.HORIZONTAL_CENTER,
                    successPanel);
            layout.putConstraint(SpringLayout.NORTH, totalLabel, 20, SpringLayout.SOUTH, successLabel);
            layout.putConstraint(SpringLayout.NORTH, okButton, 20, SpringLayout.SOUTH, totalLabel);
            successPanel.add(okButton);
            successFrame.add(successPanel);
            successFrame.setVisible(true);
            System.out.println("Thank you for your booking!");
            System.out.println("Your final price is: " + totalPrice.getText() + "\n");
        }
    }

    public class slidePictureButton implements ActionListener {
        private JTextField movieName;
        private JLabel moviePicture;
        private JLabel orderLabel;
        private ArrayList<JLabel> billField;
        private JComboBox<String> amountBox;
        private boolean next;

        slidePictureButton(JTextField movieName, JLabel moviePicture, JLabel orderLabel, ArrayList<JLabel> billField,
                JComboBox<String> amountBox, boolean next) {
            this.movieName = movieName;
            this.moviePicture = moviePicture;
            this.orderLabel = orderLabel;
            this.billField = billField;
            this.next = next;
            this.amountBox = amountBox;
        }

        public void actionPerformed(ActionEvent e) {
            String currentText = this.orderLabel.getText();
            int currentOrder = Integer.parseInt(currentText.substring(0, currentText.indexOf("/")));
            if (this.next) {
                if (currentOrder < movies.size()) {
                    currentOrder++;
                }
            } else {
                if (currentOrder > 1) {
                    currentOrder--;
                }
            }
            String selectedText = (String) amountBox.getSelectedItem();
            int amount = Integer.parseInt(selectedText);
            this.movieName.setText(movies.get(currentOrder - 1).getName());
            this.moviePicture.setIcon(new ImageIcon(movies.get(currentOrder - 1).getPath()));
            this.orderLabel.setText(currentOrder + "/" + movies.size());
            this.billField.get(0).setText(String.valueOf(movies.get(currentOrder - 1).getTicket().getPrice() * amount));
            this.billField.get(1)
                    .setText(String.valueOf(movies.get(currentOrder - 1).getTicket().getDSPrice() * amount));
            this.billField.get(2)
                    .setText(String.valueOf(movies.get(currentOrder - 1).getTicket().getPSPrice() * amount));
            this.billField.get(3)
                    .setText(String.valueOf(movies.get(currentOrder - 1).getTicket().getPrice() * amount
                            - movies.get(currentOrder - 1).getTicket().getDSPrice() * amount
                            - movies.get(currentOrder - 1).getTicket().getPSPrice() * amount));
        }
    }

    public class discountChange implements ActionListener {
        private String selectedText;
        private ArrayList<JLabel> discountField;
        private JComboBox<String> amountBox;
        private int currentOrder;

        discountChange(String selectedText, ArrayList<JLabel> discountField, JComboBox<String> amountBox,
                int currentOrder) {
            this.selectedText = selectedText;
            this.discountField = discountField;
            this.amountBox = amountBox;
            this.currentOrder = currentOrder;
        }

        public void set(int id, int type) {
            for (int i = 0; i < movies.size(); i++) {
                if (type == 1) {
                    movies.get(i).getTicket().chooseIDS(id);
                } else {
                    movies.get(i).getTicket().chooseIPS(id);
                }
            }
        }

        public void actionPerformed(ActionEvent e) {
            int amount = Integer.parseInt((String) amountBox.getSelectedItem());
            switch (selectedText) {
                case "Student":
                    this.set(2, 1);
                    movies.get(currentOrder).getTicket().printDiscount();
                    break;
                case "PreOrder":
                    this.set(3, 1);
                    movies.get(currentOrder).getTicket().printDiscount();
                    break;
                case "VIP":
                    this.set(4, 1);
                    movies.get(currentOrder).getTicket().printDiscount();
                    break;
                case "Credit Card":
                    this.set(1, 0);
                    movies.get(currentOrder).getTicket().printDiscount();
                    break;
                case "MoMo":
                    this.set(2, 0);
                    movies.get(currentOrder).getTicket().printDiscount();
                    break;
                case "Paypal":
                    this.set(3, 0);
                    movies.get(currentOrder).getTicket().printDiscount();
                    break;
                default:
                    this.set(1, 1);
                    movies.get(currentOrder).getTicket().printDiscount();
                    break;
            }
            discountField.get(1)
                    .setText(String.valueOf(movies.get(currentOrder).getTicket().getDSPrice() * amount));
            discountField.get(2)
                    .setText(String.valueOf(movies.get(currentOrder).getTicket().getPSPrice() * amount));
            double total = movies.get(currentOrder).getTicket().getPrice() * amount;
            double discount = (movies.get(currentOrder).getTicket().getDSPrice()
                    + movies.get(currentOrder).getTicket().getPSPrice()) * amount;
            total -= discount;

            discountField.get(3).setText(String.valueOf(total));
        }

    }

    public void createGUI() {
        JFrame mainFrame = new JFrame("Movie Booking");
        mainFrame.setSize(1280, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        Font fontLabel = new Font("Arial", Font.BOLD, 20);
        Font fontField = new Font("Arial", Font.PLAIN, 20);
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);

        JLabel nameLabel = new JLabel("Full Name: ");
        panel.add(nameLabel);
        nameLabel.setFont(fontLabel);

        JTextField nameField = new JTextField(20);
        panel.add(nameField);
        nameField.setFont(fontField);

        // Align nameField's baseline with nameLabel's baseline
        layout.putConstraint(SpringLayout.BASELINE, nameField, 0, SpringLayout.BASELINE, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 15, SpringLayout.EAST, nameLabel);

        JLabel movieLabel = new JLabel("Movie: ");
        panel.add(movieLabel);
        movieLabel.setFont(fontLabel);

        JTextField movieField = new JTextField(20);
        panel.add(movieField);
        movieField.setFont(fontField);
        movieField.setEditable(false);
        movieField.setText(movies.get(0).getName());

        // Align movieField's baseline with movieLabel's baseline
        layout.putConstraint(SpringLayout.BASELINE, movieField, 0, SpringLayout.BASELINE, movieLabel);
        layout.putConstraint(SpringLayout.NORTH, movieLabel, 25, SpringLayout.SOUTH, nameLabel);
        layout.putConstraint(SpringLayout.WEST, movieLabel, 0, SpringLayout.WEST, nameLabel);
        layout.putConstraint(SpringLayout.WEST, movieField, 0, SpringLayout.WEST, nameField);

        JLabel dateLabel = new JLabel("Date: ");
        panel.add(dateLabel);
        dateLabel.setFont(fontLabel);
        String[] date = new String[31];
        for (int i = 0; i < date.length; i++) {
            date[i] = String.valueOf(i + 1);
        }
        JComboBox<String> dateField = new JComboBox<>(date);
        dateField.setPreferredSize(new Dimension(60, 30));
        dateField.setFont(fontField);
        panel.add(dateField);
        layout.putConstraint(SpringLayout.NORTH, dateLabel, 25, SpringLayout.SOUTH, movieLabel);
        layout.putConstraint(SpringLayout.WEST, dateLabel, 0, SpringLayout.WEST, nameLabel);
        layout.putConstraint(SpringLayout.BASELINE, dateField, 0,
                SpringLayout.BASELINE, dateLabel);
        layout.putConstraint(SpringLayout.WEST, dateField, 5, SpringLayout.EAST, dateLabel);

        JLabel monthLabel = new JLabel("Month: ");
        panel.add(monthLabel);
        monthLabel.setFont(fontLabel);
        String[] month = new String[12];
        for (int i = 0; i < month.length; i++) {
            month[i] = String.valueOf(i + 1);
        }
        JComboBox<String> monthField = new JComboBox<>(month);
        monthField.setPreferredSize(new Dimension(60, 30));
        monthField.setFont(fontField);
        panel.add(monthField);
        layout.putConstraint(SpringLayout.NORTH, monthLabel, 25, SpringLayout.SOUTH, movieLabel);
        layout.putConstraint(SpringLayout.WEST, monthLabel, 30, SpringLayout.EAST, dateField);
        layout.putConstraint(SpringLayout.BASELINE, monthField, 0,
                SpringLayout.BASELINE, monthLabel);
        layout.putConstraint(SpringLayout.WEST, monthField, 5, SpringLayout.EAST, monthLabel);

        JLabel timeLabel = new JLabel("Time: ");
        panel.add(timeLabel);
        timeLabel.setFont(fontLabel);
        String[] time = new String[24];
        for (int i = 0; i < time.length; i++) {
            time[i] = String.valueOf(i) + ":00";
        }
        JComboBox<String> timeField = new JComboBox<>(time);
        timeField.setPreferredSize(new Dimension(80, 30));
        timeField.setFont(fontField);
        panel.add(timeField);
        layout.putConstraint(SpringLayout.NORTH, timeLabel, 25, SpringLayout.SOUTH, movieLabel);
        layout.putConstraint(SpringLayout.WEST, timeLabel, 30, SpringLayout.EAST, monthField);
        layout.putConstraint(SpringLayout.BASELINE, timeField, 0,
                SpringLayout.BASELINE, timeLabel);
        layout.putConstraint(SpringLayout.WEST, timeField, 5, SpringLayout.EAST, timeLabel);

        JLabel amountLabel = new JLabel("Amount: ");
        panel.add(amountLabel);
        amountLabel.setFont(fontLabel);
        String amount[] = new String[10];
        for (int i = 0; i < amount.length; i++) {
            amount[i] = String.valueOf(i + 1);
        }
        JComboBox<String> amountField = new JComboBox<>(amount);
        amountField.setPreferredSize(new Dimension(60, 30));
        amountField.setFont(fontField);
        panel.add(amountField);
        layout.putConstraint(SpringLayout.BASELINE, amountField, 0,
                SpringLayout.BASELINE, amountLabel);
        layout.putConstraint(SpringLayout.NORTH, amountLabel, 25, SpringLayout.SOUTH, dateLabel);
        layout.putConstraint(SpringLayout.WEST, amountLabel, 0, SpringLayout.WEST, nameLabel);
        layout.putConstraint(SpringLayout.WEST, amountField, 5, SpringLayout.EAST, amountLabel);

        JLabel roomLabel = new JLabel("Room: ");
        panel.add(roomLabel);
        roomLabel.setFont(fontLabel);
        String room[] = new String[5];
        for (int i = 0; i < room.length; i++) {
            room[i] = String.valueOf(i + 1);
        }
        JComboBox<String> roomField = new JComboBox<>(room);
        roomField.setPreferredSize(new Dimension(60, 30));
        roomField.setFont(fontField);
        panel.add(roomField);
        layout.putConstraint(SpringLayout.BASELINE, roomField, 0,
                SpringLayout.BASELINE, roomLabel);
        layout.putConstraint(SpringLayout.NORTH, roomLabel, 25, SpringLayout.SOUTH, dateLabel);
        layout.putConstraint(SpringLayout.WEST, roomLabel, 30, SpringLayout.EAST, amountField);
        layout.putConstraint(SpringLayout.WEST, roomField, 5, SpringLayout.EAST, roomLabel);

        JLabel discountType = new JLabel("Discount Type: ");
        panel.add(discountType);
        discountType.setFont(fontLabel);
        layout.putConstraint(SpringLayout.NORTH, discountType, 25, SpringLayout.SOUTH, roomLabel);
        layout.putConstraint(SpringLayout.WEST, discountType, 0, SpringLayout.WEST, nameLabel);

        String discount[] = { "None", "Student", "PreOrder", "VIP" };
        ButtonGroup discountButtonGroup = new ButtonGroup(); // create a ButtonGroup to group the radio buttons
        ArrayList<JRadioButton> discountRadioButtonList = new ArrayList<>(4); // create a list to store the
                                                                              // radio
                                                                              // buttons
        for (int i = 0; i < discount.length; i++) {
            JRadioButton discountRadioButton = new JRadioButton(discount[i]);
            discountRadioButton.setFont(fontField);
            discountRadioButtonList.add(discountRadioButton);
            discountButtonGroup.add(discountRadioButton);
            panel.add(discountRadioButton);
        }
        layout.putConstraint(SpringLayout.BASELINE, discountRadioButtonList.get(0), 0,
                SpringLayout.BASELINE, discountType);
        layout.putConstraint(SpringLayout.BASELINE, discountRadioButtonList.get(1), 0,
                SpringLayout.BASELINE, discountType);
        layout.putConstraint(SpringLayout.WEST, discountRadioButtonList.get(0), 10,
                SpringLayout.EAST, discountType);
        layout.putConstraint(SpringLayout.WEST, discountRadioButtonList.get(1), 100,
                SpringLayout.EAST, discountRadioButtonList.get(0));
        layout.putConstraint(SpringLayout.NORTH, discountRadioButtonList.get(2), 10, SpringLayout.SOUTH,
                discountRadioButtonList.get(0));
        layout.putConstraint(SpringLayout.BASELINE, discountRadioButtonList.get(3), 0,
                SpringLayout.BASELINE, discountRadioButtonList.get(2));
        layout.putConstraint(SpringLayout.WEST, discountRadioButtonList.get(2), 0, SpringLayout.WEST,
                discountRadioButtonList.get(0));
        layout.putConstraint(SpringLayout.WEST, discountRadioButtonList.get(3), 0, SpringLayout.WEST,
                discountRadioButtonList.get(1));
        // do it for the Payment Type
        JLabel paymentType = new JLabel("Payment Type: ");
        panel.add(paymentType);
        paymentType.setFont(fontLabel);
        layout.putConstraint(SpringLayout.NORTH, paymentType, 60, SpringLayout.SOUTH, discountType);
        layout.putConstraint(SpringLayout.WEST, paymentType, 0, SpringLayout.WEST, nameLabel);

        String payment[] = { "Credit Card", "MoMo", "Paypal" };
        ButtonGroup paymentButtonGroup = new ButtonGroup(); // create a ButtonGroup to group the radio buttons
        ArrayList<JRadioButton> paymentRadioButtonList = new ArrayList<>(3); // create a list to store the radio
                                                                             // buttons
        for (int i = 0; i < payment.length; i++) {
            JRadioButton paymentRadioButton = new JRadioButton(payment[i]);
            paymentRadioButton.setFont(fontField);
            paymentRadioButtonList.add(paymentRadioButton);
            paymentButtonGroup.add(paymentRadioButton);
            panel.add(paymentRadioButton);
            layout.putConstraint(SpringLayout.BASELINE, paymentRadioButtonList.get(i), 0,
                    SpringLayout.BASELINE, paymentType);
        }
        layout.putConstraint(SpringLayout.WEST, paymentRadioButtonList.get(0), 0,
                SpringLayout.WEST, discountRadioButtonList.get(0));
        layout.putConstraint(SpringLayout.WEST, paymentRadioButtonList.get(1), 10,
                SpringLayout.EAST, paymentRadioButtonList.get(0));
        layout.putConstraint(SpringLayout.WEST, paymentRadioButtonList.get(2), 10,
                SpringLayout.EAST, paymentRadioButtonList.get(1));

        JPanel billPanel = new JPanel(new GridLayout(4, 2, 200, 20));
        billPanel.setPreferredSize(new Dimension(650, 180));
        Border billBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        TitledBorder billTitleBorder = BorderFactory.createTitledBorder(billBorder, "Your Bill",
                TitledBorder.CENTER, TitledBorder.TOP, fontLabel);
        billPanel.setBorder(billTitleBorder);
        panel.add(billPanel);
        layout.putConstraint(SpringLayout.NORTH, billPanel, 20, SpringLayout.SOUTH, paymentType);
        layout.putConstraint(SpringLayout.WEST, billPanel, 0, SpringLayout.WEST, nameLabel);

        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setFont(fontLabel);
        billPanel.add(priceLabel);
        JLabel priceField = new JLabel(String.valueOf(movies.get(0).getTicket().getPrice()));
        priceField.setFont(fontField);
        billPanel.add(priceField);

        ArrayList<JLabel> discountLabelList = new ArrayList<>(2);
        ArrayList<JLabel> discountFieldList = new ArrayList<>(2);
        // Discount for character
        JLabel discountLabel = new JLabel("Discount for Character Type: ");
        discountLabel.setFont(fontLabel);
        billPanel.add(discountLabel);
        discountLabelList.add(discountLabel);
        JLabel discountField = new JLabel("0");
        discountField.setFont(fontField);
        billPanel.add(discountField);
        discountFieldList.add(discountField);
        // Discount or enlows for payment
        JLabel discountLabel1 = new JLabel("Discount for Payment Type: ");
        discountLabel1.setFont(fontLabel);
        billPanel.add(discountLabel1);
        discountLabelList.add(discountLabel1);
        JLabel discountField1 = new JLabel("0");
        discountField1.setFont(fontField);
        billPanel.add(discountField1);
        discountFieldList.add(discountField1);

        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setFont(fontLabel);
        billPanel.add(totalLabel);
        JLabel totalField = new JLabel(String.valueOf(movies.get(0).getTicket().getPrice()));
        totalField.setFont(fontField);
        billPanel.add(totalField);
        ArrayList<JLabel> billLabelList = new ArrayList<>(4);
        billLabelList.add(priceField);
        billLabelList.add(discountFieldList.get(0));
        billLabelList.add(discountFieldList.get(1));
        billLabelList.add(totalField);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(fontLabel);
        panel.add(submitButton);
        layout.putConstraint(SpringLayout.NORTH, submitButton, 20, SpringLayout.SOUTH, billPanel);
        layout.putConstraint(SpringLayout.EAST, submitButton, 0, SpringLayout.EAST, billPanel);

        submitButton.addActionListener(new SubmitButtonListener(mainFrame, totalField));
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(fontLabel);
        panel.add(resetButton);
        layout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, submitButton);
        layout.putConstraint(SpringLayout.WEST, resetButton, 0, SpringLayout.WEST, nameLabel);

        ImageIcon image = new ImageIcon(
                movies.get(0).getPath());
        JLabel imageLabel = new JLabel(image);

        panel.add(imageLabel);
        layout.putConstraint(SpringLayout.NORTH, imageLabel, 0, SpringLayout.NORTH, nameLabel);
        layout.putConstraint(SpringLayout.WEST, imageLabel, 600, SpringLayout.EAST, nameLabel);

        String baseOrderText = String.valueOf(movies.get(0).getID()) + "/" + String.valueOf(movies.size());
        JLabel orderLabel = new JLabel(baseOrderText);
        orderLabel.setFont(fontLabel);
        panel.add(orderLabel);
        layout.putConstraint(SpringLayout.BASELINE, orderLabel, 0, SpringLayout.BASELINE, submitButton);

        JButton prevButton = new JButton("<<<");
        prevButton.setFont(fontLabel);
        panel.add(prevButton);
        layout.putConstraint(SpringLayout.NORTH, prevButton, 0, SpringLayout.NORTH, submitButton);
        layout.putConstraint(SpringLayout.WEST, prevButton, 0, SpringLayout.WEST, imageLabel);
        layout.putConstraint(SpringLayout.WEST, orderLabel, 90, SpringLayout.EAST, prevButton);
        JButton nextButton = new JButton(">>>");
        nextButton.setFont(fontLabel);
        panel.add(nextButton);
        layout.putConstraint(SpringLayout.NORTH, nextButton, 0, SpringLayout.NORTH, submitButton);
        layout.putConstraint(SpringLayout.EAST, nextButton, 0, SpringLayout.EAST, imageLabel);

        amountField.addActionListener(
                new amountChange(amountField, billLabelList,
                        Integer.parseInt(orderLabel.getText().substring(0, 1)) - 1));

        prevButton.addActionListener(
                new slidePictureButton(movieField, imageLabel, orderLabel, billLabelList, amountField, false));

        nextButton.addActionListener(
                new slidePictureButton(movieField, imageLabel, orderLabel, billLabelList, amountField, true));
        for (int i = 0; i < discountRadioButtonList.size(); i++) {
            discountRadioButtonList.get(i).addActionListener(
                    new discountChange(discountRadioButtonList.get(i).getText(), billLabelList, amountField,
                            Integer.parseInt(orderLabel.getText().substring(0, 1)) - 1));

        }
        for (int i = 0; i < paymentRadioButtonList.size(); i++) {
            paymentRadioButtonList.get(i).addActionListener(
                    new discountChange(paymentRadioButtonList.get(i).getText(), billLabelList, amountField,
                            Integer.parseInt(orderLabel.getText().substring(0, 1)) - 1));
        }
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }
}