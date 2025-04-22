import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;



public class GymGui {
//Static Helper Methods for day and time

private static String[] getYearRange(int startYear, int endYear) {
    int count = Math.abs(endYear - startYear) + 1;
    String[] years = new String[count];
    if (startYear <= endYear) {
        for (int i = 0; i < count; i++) years[i] = String.valueOf(startYear + i);
    } else {
        for (int i = 0; i < count; i++) years[i] = String.valueOf(startYear - i);
    }
    return years;
}

private static String[] createDayArray() {
    String[] days = new String[31];
    for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);
    return days;
}

private static String getDateFromComboBoxes(JComboBox<String> dayBox, JComboBox<String> monthBox,
        JComboBox<String> yearBox) {
    String day = (String) dayBox.getSelectedItem();
    String month = (String) monthBox.getSelectedItem();
    String year = (String) yearBox.getSelectedItem();
    if (day == null || month == null || year == null) {
        return null;
    }
    return day + "-" + month + "-" + year;
}

// Helper method to find member by ID
private static GymMember findMemberById(int id, ArrayList<GymMember> list) {
    for (GymMember member : list) {
        if (member.getId() == id) {
            return member;
        }
    }
    return null;
}

// Main Method 
public static void main(String[] args) {
    final ArrayList<GymMember> memberList = new ArrayList<>();

    JFrame frame = new JFrame("GYM Membership Management");
    frame.setSize(860, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    // frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    //  Calculations for frame and labels
    final int margin = 15;
    final int Label_W = 150;
    final int field_W = 200;
    final int field_H = 25;
    final int H_gap = 10;
    final int V_gap = 10;
    int y;
    int dayWidth = 55;
    int monthWidth = 95;
    int yearWidth = 65;
    int gap = 5;
    int comboBoxTotalWidth = dayWidth + gap + monthWidth + gap + yearWidth;

     //  Left Panel layout
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(null);
    leftPanel.setBorder(BorderFactory.createTitledBorder("Member Information"));
    int leftPanelWidth = (Label_W + H_gap + comboBoxTotalWidth + 2 * margin) - 8;
    int leftPanelHeight = 300;
    leftPanel.setBounds(margin, margin, leftPanelWidth, leftPanelHeight);
    y = 30;

    //  Name and namelabel 
    JLabel nameLabel = new JLabel("Name:");
    nameLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField nameField = new JTextField();
    nameField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    leftPanel.add(nameLabel);
    leftPanel.add(nameField);
    y += field_H + V_gap;

    //  ID and ID label
    JLabel idLabel = new JLabel("ID:");
    idLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField idField = new JTextField();
    idField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    leftPanel.add(idLabel);
    leftPanel.add(idField);
    y += field_H + V_gap;

    //  Location and location label
    JLabel locationLabel = new JLabel("Location:");
    locationLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField locationField = new JTextField();
    locationField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    leftPanel.add(locationLabel);
    leftPanel.add(locationField);
    y += field_H + V_gap;


    //  Phone and phone label
    JLabel phoneLabel = new JLabel("Phone:");
    phoneLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField phoneField = new JTextField();
    phoneField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    leftPanel.add(phoneLabel);
    leftPanel.add(phoneField);
    y += field_H + V_gap;


    //  Email and email label
    JLabel emailLabel = new JLabel("Email:");
    emailLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField emailField = new JTextField();
    emailField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    leftPanel.add(emailLabel);
    leftPanel.add(emailField);
    y += field_H + V_gap;

    //Radio box for gender
    JLabel genderLabel = new JLabel("Gender:");
    genderLabel.setBounds(margin, y, Label_W, field_H);
    JPanel genderPanel = new JPanel();
    genderPanel.setBounds(margin + Label_W + H_gap, y, field_W, field_H + 5);
    final JRadioButton maleRadio = new JRadioButton("Male");
    final JRadioButton femaleRadio = new JRadioButton("Female");
    final ButtonGroup genderGroup = new ButtonGroup();
    genderGroup.add(maleRadio);
    genderGroup.add(femaleRadio);
    genderPanel.add(maleRadio);
    genderPanel.add(femaleRadio);
    leftPanel.add(genderLabel);
    leftPanel.add(genderPanel);
    y += field_H + V_gap;

    //  Date of Birth and dob label
    JLabel dobLabel = new JLabel("Date of Birth:");
    dobLabel.setBounds(margin, y, Label_W, field_H);
    leftPanel.add(dobLabel);
    final String[] daysArray = createDayArray();
    final String[] monthsArray = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    int currentSystemYear = java.time.Year.now().getValue();
    final String[] dobYearsArray = getYearRange(currentSystemYear - 100, currentSystemYear - 5);
    final JComboBox<String> dobDayComboBox = new JComboBox<>(daysArray);
    final JComboBox<String> dobMonthComboBox = new JComboBox<>(monthsArray);
    final JComboBox<String> dobYearComboBox = new JComboBox<>(dobYearsArray);
    dobYearComboBox.setSelectedItem(String.valueOf(currentSystemYear - 20));
    int dobCbX = margin + Label_W + H_gap;
    dobDayComboBox.setBounds(dobCbX, y, dayWidth, field_H);
    dobMonthComboBox.setBounds(dobCbX + dayWidth + gap, y, monthWidth, field_H);
    dobYearComboBox.setBounds(dobCbX + dayWidth + gap + monthWidth + gap, y, yearWidth, field_H);
    leftPanel.add(dobDayComboBox);
    leftPanel.add(dobMonthComboBox);
    leftPanel.add(dobYearComboBox);

    frame.add(leftPanel);

    // Plans Panel
    JPanel plansPanel = new JPanel();
    plansPanel.setLayout(null);
    plansPanel.setBorder(BorderFactory.createTitledBorder("Price & Discount"));
    int plansPanelWidth = leftPanelWidth;
    int plansPanelHeight = 140;
    plansPanel.setBounds(margin, leftPanel.getY() + leftPanelHeight + margin, plansPanelWidth, plansPanelHeight);
    y = 30;

    // Regular Plan & Price 
    JLabel regPlanLabel = new JLabel("Regular Plan Price:");
    regPlanLabel.setBounds(margin, y, Label_W, field_H);
    JTextField regPlanField = new JTextField("Rs: " + new RegularMember(0,"","","","","","","","").getPrice());
    regPlanField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    regPlanField.setEditable(false);
    plansPanel.add(regPlanLabel);
    plansPanel.add(regPlanField);
    y += field_H + V_gap;


    // Premium Plan & Price
    JLabel prePlanLabel = new JLabel("Premium Plan Charge:");
    prePlanLabel.setBounds(margin, y, Label_W, field_H);
    JTextField prePlanField = new JTextField("Rs: " + new PremiumMember(0,"","","","","","","","").getPremiumCharge());
    prePlanField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    prePlanField.setEditable(false);
    plansPanel.add(prePlanLabel);
    plansPanel.add(prePlanField);
    y += field_H + V_gap;

    // discount %
    JLabel discountLabel = new JLabel("Discount % :");
    discountLabel.setBounds(margin, y, Label_W, field_H);
    JTextField discountField = new JTextField("10% on-full payment");
    discountField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    discountField.setEditable(false);
    plansPanel.add(discountLabel);
    plansPanel.add(discountField);

    frame.add(plansPanel);

    // Right Panel
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(null);
    rightPanel.setBorder(BorderFactory.createTitledBorder("Membership Details / Actions"));
    int rightPanelWidth = leftPanelWidth;
    int rightPanelHeight = 300;
    int rightPanelx = leftPanel.getX() + leftPanelWidth + margin;
    rightPanel.setBounds(rightPanelx, margin, rightPanelWidth, rightPanelHeight);
    y = 30;

    // Referral Source and Paid Amount
    JLabel referralLabel = new JLabel("Referral Source:");
    referralLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField referralField = new JTextField();
    referralField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    rightPanel.add(referralLabel);
    rightPanel.add(referralField);
    y += field_H + V_gap;

    JLabel paidLabel = new JLabel("Paid Amount:");
    paidLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField paidField = new JTextField();
    paidField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    rightPanel.add(paidLabel);
    rightPanel.add(paidField);
    y += field_H + V_gap;

    // Trainer's Name 
    JLabel trainerLabel = new JLabel("Trainer's Name:");
    trainerLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField trainerField = new JTextField();
    trainerField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    rightPanel.add(trainerLabel);
    rightPanel.add(trainerField);
    y += field_H + V_gap;

    // planel selection
    JLabel planLabel = new JLabel("Plan Selection:"); 
    planLabel.setBounds(margin, y, Label_W, field_H);
    final String[] plansArray = {"Basic", "Standard", "Deluxe"};
    final JComboBox<String> planComboBox = new JComboBox<>(plansArray);
    planComboBox.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    rightPanel.add(planLabel);
    rightPanel.add(planComboBox);
    y += field_H + V_gap;

    // membership status to check if membership is active or not
    JLabel activeLabel = new JLabel("Membership Active:");
    activeLabel.setBounds(margin, y, Label_W, field_H);
    final JCheckBox activeCheckBox = new JCheckBox();
    activeCheckBox.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    activeCheckBox.setEnabled(false);
  

   // Removal Reason
    JLabel removeReasonLabel = new JLabel("Removal Reason:");
    removeReasonLabel.setBounds(margin, y, Label_W, field_H);
    final JTextField removeReasonField = new JTextField();
    removeReasonField.setBounds(margin + Label_W + H_gap, y, field_W, field_H);
    rightPanel.add(removeReasonLabel);
    rightPanel.add(removeReasonField);
    y += field_H + V_gap;

    // Date of Membership Start combo box same as DOB
    JLabel startDateLabel = new JLabel("Membership Start Date:");
    startDateLabel.setBounds(margin, y, Label_W, field_H);
    rightPanel.add(startDateLabel);
    final String[] startYearsArray = getYearRange(currentSystemYear - 2, currentSystemYear + 5);
    final JComboBox<String> startDayComboBox = new JComboBox<>(daysArray);
    final JComboBox<String> startMonthComboBox = new JComboBox<>(monthsArray);
    final JComboBox<String> startYearComboBox = new JComboBox<>(startYearsArray);
    startYearComboBox.setSelectedItem(String.valueOf(currentSystemYear));
    int startCbX = margin + Label_W + H_gap;
    startDayComboBox.setBounds(startCbX, y, dayWidth, field_H);
    startMonthComboBox.setBounds(startCbX + dayWidth + gap, y, monthWidth, field_H);
    startYearComboBox.setBounds(startCbX + dayWidth + gap + monthWidth + gap, y, yearWidth, field_H);
    rightPanel.add(startDayComboBox);
    rightPanel.add(startMonthComboBox);
    rightPanel.add(startYearComboBox);

    frame.add(rightPanel);

    // Buttons Panel Setup
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(2, 5, 10, 10));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    int buttonPanelWidth = frame.getWidth() - 2 * margin - 5;
    int buttonPanelHeight = 90;

    // Position below the bottom-most panel on either side
    int bottomYLeft = plansPanel.getY() + plansPanelHeight;
    int bottomYRight = rightPanel.getY() + rightPanelHeight;
    int buttonPanelY = Math.max(bottomYLeft, bottomYRight) + margin;
    buttonPanelY = Math.min(buttonPanelY, frame.getHeight() - buttonPanelHeight - margin * 4);
    buttonPanel.setBounds(margin, buttonPanelY, buttonPanelWidth, buttonPanelHeight);

    // Creating all Required Buttons 
    JButton addRegularButton = new JButton("Add Regular");
    JButton addPremiumButton = new JButton("Add Premium");
    JButton activateButton = new JButton("Activate Member");
    JButton deactivateButton = new JButton("Deactivate Member");
    JButton attendanceButton = new JButton("Mark Attendance");
    JButton revertRegularButton = new JButton("Revert-Regular");
    JButton revertPremiumButton = new JButton("Revert-Premium");
    JButton displayAllButton = new JButton("Display"); 
    JButton clearButton = new JButton("Clear"); 
    JButton exitButton = new JButton("upgrade");

    // Adding Buttons to main Panel
    buttonPanel.add(addRegularButton);
    buttonPanel.add(addPremiumButton);
    buttonPanel.add(activateButton);
    buttonPanel.add(deactivateButton);
    buttonPanel.add(attendanceButton);
    buttonPanel.add(revertRegularButton);
    buttonPanel.add(revertPremiumButton);
    buttonPanel.add(displayAllButton);
    buttonPanel.add(clearButton);
    buttonPanel.add(exitButton);

    frame.add(buttonPanel);

    // Display Area
    JTextArea displayArea = new JTextArea();
    displayArea.setEditable(false);
    JScrollPane displayScrollPane = new JScrollPane(displayArea);
    int displayY = buttonPanel.getY() + buttonPanelHeight + margin;
    int displayHeight = frame.getHeight() - displayY - margin * 4;
    displayScrollPane.setBounds(margin, displayY, buttonPanelWidth, displayHeight);
    frame.add(displayScrollPane);


    // Add Regular Member Listener and Validation logic 
    addRegularButton.addActionListener(e -> {
        try {
            String name = nameField.getText().trim();
            String idString = idField.getText().trim();
            String location = locationField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "";
            String dob = getDateFromComboBoxes(dobDayComboBox, dobMonthComboBox, dobYearComboBox);
            String startDate = getDateFromComboBoxes(startDayComboBox, startMonthComboBox, startYearComboBox);
            String referral = referralField.getText().trim();

            if (name.isEmpty() || idString.isEmpty() || location.isEmpty() || phone.isEmpty() || email.isEmpty() || gender.isEmpty() || dob == null || startDate == null) {
                JOptionPane.showMessageDialog(frame, "Please fill all required fields for Regular Member.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             if (phone.length() > 10 || !phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "Invalid phone number (must be digits, max 10).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id = Integer.parseInt(idString);

            if (findMemberById(id, memberList) != null) {
                JOptionPane.showMessageDialog(frame, "Member with ID " + id + " already exists.", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                return;
            }
            RegularMember newMember = new RegularMember(id, name, location, phone, email, gender, dob, startDate, referral);
            memberList.add(newMember);
            JOptionPane.showMessageDialog(frame, "Regular Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearButton.doClick();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
        }
    });

    // Adding Premium Member Listener
    addPremiumButton.addActionListener(e -> {
         try {
            String name = nameField.getText().trim();
            String idStr = idField.getText().trim();
            String location = locationField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "";
            String dob = getDateFromComboBoxes(dobDayComboBox, dobMonthComboBox, dobYearComboBox);
            String startDate = getDateFromComboBoxes(startDayComboBox, startMonthComboBox, startYearComboBox);
            String trainer = trainerField.getText().trim();

            if (name.isEmpty() || idStr.isEmpty() || location.isEmpty() || phone.isEmpty() || email.isEmpty() || gender.isEmpty() || dob == null || startDate == null || trainer.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all required fields for Premium Member (including Trainer).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             if (phone.length() > 10 || !phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(frame, "Invalid phone number (must be digits, max 10).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id = Integer.parseInt(idStr);

            if (findMemberById(id, memberList) != null) {
                JOptionPane.showMessageDialog(frame, "Member with ID " + id + " already exists.", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                return;
            }
            PremiumMember newMember = new PremiumMember(id, name, location, phone, email, gender, dob, startDate, trainer);
            memberList.add(newMember);
            JOptionPane.showMessageDialog(frame, "Premium Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearButton.doClick();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
        }
    });

    // Activate Member Listener 
    activateButton.addActionListener(e -> {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Member ID to Activate:");
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            GymMember member = findMemberById(id, memberList);

            if (member != null) {
                if (member.isActiveStatus()) {
                    JOptionPane.showMessageDialog(frame, "Member " + id + " is already active.", "Already Active", JOptionPane.WARNING_MESSAGE);
                } else {
                    member.activeMembership();
                    if(idStr.trim().equals(idField.getText().trim())) {
                        activeCheckBox.setSelected(true);
                    }
                    JOptionPane.showMessageDialog(frame, "Member " + id + " activated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayAllButton.doClick();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Member with ID " + id + " not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Deactivate Member action Listener 
    deactivateButton.addActionListener(e -> {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Member ID to Deactivate:");
         if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            GymMember member = findMemberById(id, memberList);

            if (member != null) {
                if (!member.isActiveStatus()) {
                    JOptionPane.showMessageDialog(frame, "Member " + id + " is already inactive.", "Already Inactive", JOptionPane.WARNING_MESSAGE);
                } else {
                    member.deactivateMembership();
                     if(idStr.trim().equals(idField.getText().trim())) {
                        activeCheckBox.setSelected(false);
                    }
                    JOptionPane.showMessageDialog(frame, "Member " + id + " deactivated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayAllButton.doClick();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Member with ID " + id + " not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Mark Attendance Listener 
    attendanceButton.addActionListener(e -> {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Member ID to Mark Attendance:");
         if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            GymMember member = findMemberById(id, memberList);

            if (member != null) {
              
                if (member.isActiveStatus()) {
                    member.markAttendance();
                    JOptionPane.showMessageDialog(frame, "Attendance marked for Member " + id + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayAllButton.doClick();
                } else {
                     JOptionPane.showMessageDialog(frame, "Member " + id + " is not active. Cannot mark attendance.", "Inactive Member", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Member with ID " + id + " not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Revert Regular Listener 
    revertRegularButton.addActionListener(e -> {
         String idStr = JOptionPane.showInputDialog(frame, "Enter Regular Member ID to Revert:");
         if (idStr == null || idStr.trim().isEmpty()) return;

         String reason = removeReasonField.getText().trim();
         if (reason.isEmpty()) {
             reason = JOptionPane.showInputDialog(frame, "Enter Removal Reason for Member " + idStr.trim() + ":");
             if (reason == null || reason.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Removal reason is required.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
             }
         }

        try {
            int id = Integer.parseInt(idStr.trim());
            boolean found = false;
            Iterator<GymMember> iterator = memberList.iterator();
            while (iterator.hasNext()) {
                GymMember member = iterator.next();
                if (member.getId() == id) {
                    if (member instanceof RegularMember) {
                        RegularMember rm = (RegularMember) member;
                        rm.revertRegularMember(reason);
                        iterator.remove();
                        JOptionPane.showMessageDialog(frame, "Regular Member " + id + " reverted and removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        if (idStr.trim().equals(idField.getText().trim())) {
                            clearButton.doClick();
                        }
                        displayAllButton.doClick();
                        break;
                    } else {
                         JOptionPane.showMessageDialog(frame, "Member " + id + " is not a Regular Member.", "Incorrect Type", JOptionPane.WARNING_MESSAGE);
                         found = true;
                         break;
                    }
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Regular Member with ID " + id + " not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Revert Premium Listener 
    revertPremiumButton.addActionListener(e -> {
         String idStr = JOptionPane.showInputDialog(frame, "Enter Premium Member ID to Revert:");
         if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            boolean found = false;
            Iterator<GymMember> iterator = memberList.iterator();
            while (iterator.hasNext()) {
                GymMember member = iterator.next();
                if (member.getId() == id) {
                    if (member instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) member;
                        pm.revertPremiumMember();
                        iterator.remove();
                        JOptionPane.showMessageDialog(frame, "Premium Member " + id + " reverted and removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        if (idStr.trim().equals(idField.getText().trim())) {
                            clearButton.doClick();
                        }
                        displayAllButton.doClick();
                        break;
                    } else {
                         JOptionPane.showMessageDialog(frame, "Member " + id + " is not a Premium Member.", "Incorrect Type", JOptionPane.WARNING_MESSAGE);
                         found = true;
                         break;
                    }
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Premium Member with ID " + id + " not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Display Listener 
    displayAllButton.addActionListener(e -> {
        displayArea.setText("");
        if (memberList.isEmpty()) {
            displayArea.setText("No members in the list.");
            return;
        }
        StringBuilder stringBuild = new StringBuilder();
        stringBuild.append("  All Members\n\n");
        for (GymMember member : memberList) {
            stringBuild.append("ID: ").append(member.getId()).append("\n");
            stringBuild.append("Name: ").append(member.getName()).append("\n");
            stringBuild.append("Location: ").append(member.getLocation()).append("\n");
            stringBuild.append("Phone: ").append(member.getPhone()).append("\n");
            stringBuild.append("Email: ").append(member.getEmail()).append("\n");
            stringBuild.append("Gender: ").append(member.getGender()).append("\n");
            stringBuild.append("DOB: ").append(member.getDOB()).append("\n");
            stringBuild.append("Start Date: ").append(member.getMembershipStartDate()).append("\n");
            stringBuild.append("Attendance: ").append(member.getAttendance()).append("\n");
            stringBuild.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
            stringBuild.append("Active: ").append(member.isActiveStatus()).append("\n");
            if (member instanceof RegularMember) {
                RegularMember rm = (RegularMember) member;
                stringBuild.append("Type: Regular\n");
                stringBuild.append("Plan: ").append(rm.getPlan()).append("\n");
                stringBuild.append("Price: ").append(rm.getPrice()).append("\n");
                stringBuild.append("Referral: ").append(rm.getReferralSource()).append("\n");
                stringBuild.append("Eligible Upgrade: ").append(rm.isEligibleForUpgrade()).append("\n");
                if (!rm.getRemovalReason().isEmpty()) {
                     stringBuild.append("Removal Reason: ").append(rm.getRemovalReason()).append("\n");
                }
            } else if (member instanceof PremiumMember) {
                 PremiumMember pm = (PremiumMember) member;
                 stringBuild.append("Type: Premium\n");
                 stringBuild.append("Trainer: ").append(pm.getPersonalTrainer()).append("\n");
                 stringBuild.append("Charge: ").append(pm.getPremiumCharge()).append("\n");
                 stringBuild.append("Paid Amount: ").append(pm.getPaidAmount()).append("\n");
                 stringBuild.append("Fully Paid: ").append(pm.isFullPayment()).append("\n");
                stringBuild.append("Discount Applied: ").append(pm.getDiscountAmount()).append("\n\n");
            }
        }
        displayArea.setText(stringBuild.toString());
        displayArea.setCaretPosition(0);
    });

    // Clear Button action listiner 
    clearButton.addActionListener(e -> {
        nameField.setText("");
        idField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        dobDayComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobYearComboBox.setSelectedItem(String.valueOf(java.time.Year.now().getValue() - 20));
        startDayComboBox.setSelectedIndex(0);
        startMonthComboBox.setSelectedIndex(0);
        startYearComboBox.setSelectedItem(String.valueOf(java.time.Year.now().getValue()));
        referralField.setText("");
        paidField.setText("");
        trainerField.setText("");
        removeReasonField.setText("");
        planComboBox.setSelectedIndex(0); // Resets plan combo
        // activeCheckBox.setSelected(false); // Uncheck status box
        genderGroup.clearSelection(); // Clear radio button selection
        JOptionPane.showMessageDialog(frame, "Fields Cleared");
    });

// Exit Button Listiner
    exitButton.addActionListener(e -> System.exit(0));

    // Make Frame Visible
    frame.setVisible(true);

}
}

