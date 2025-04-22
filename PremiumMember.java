 
public class PremiumMember extends GymMember {
    // Attributes of Premiummember class
    private final double premiumCharge; 
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
     private static final long serialVersionUID = 2L;

    // Constructor
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String personalTrainer) {
        // Calling  superclass constructor
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.premiumCharge = 6000; 
        this.paidAmount = 0;
        this.isFullPayment = false; 
        this.discountAmount = 0;
    }

    // Getter methods 
    public double getPremiumCharge() {
        return premiumCharge;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public boolean isFullPayment() {
        return isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    // Implement the abstract method markAttendance()
    @Override
    public void markAttendance() {
        if (isActiveStatus()) {
            this.attendance++;
            this.loyaltyPoints += 5;
            System.out.println("Attendance marked for " + getName());
        } else {
            System.out.println("Cannot mark attendance. Membership is inactive for " + getName());
        }
    }

    // Method to pay remaining amount
    public String payDueAmount(double paidAmount) {
        if (this.isFullPayment) {
            return "All payment are completed.";
        }

        if (this.paidAmount + paidAmount > premiumCharge) {
            return "Please pay the correct amount.";
        }

        // Update paidAmount
        this.paidAmount += paidAmount;

        // Check if payment is complete or not
        if (this.paidAmount == premiumCharge) {
            this.isFullPayment = true;
            return "Payment successful.";
        } else {
            double remainingAmount = premiumCharge - this.paidAmount;
            return "Payment successful. Remaining amount to be paid: " + remainingAmount;
        }
    }

    // Method to calculate discount
    public void calculateDiscount() {
        if (this.isFullPayment) {
            //To check if the discount is already calculated
            if(this.discountAmount == 0){
            this.discountAmount = 0.10 * premiumCharge; // 10% discount
            System.out.println("Discount added successfully. Discount Amount is: " + this.discountAmount);
        } else{
            System.out.print("Discount is alredy added");
            }
        }else {
            System.out.println("No discount available for now . Clear the due amount for discount");
        }
    }

    // Method to cancle  premium membership
    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = ""; 
        this.isFullPayment = false;
        this.paidAmount = 0; 
        this.discountAmount = 0; 
        System.out.println("Detials reset");
    }

    // Override display method show details
    @Override
    public void display() {
        super.display(); // Call superclass display method
        System.out.println("Member Type: Premium");
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment: " + (isFullPayment ? "Yes" : "No"));

        // Calculate and show due amount
        double remainingAmount = premiumCharge - this.paidAmount;
        System.out.println("Remaining Amount to be Paid is : Rs" + remainingAmount);

        // Display discount amount
        if (this.isFullPayment) {
            System.out.println("Discount Amount Applied is: Rs " + discountAmount);
 }
}
}

