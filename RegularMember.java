 
public class RegularMember extends GymMember {
    private static final int ATTENDANCE_LIMIT = 30;  
    private boolean isEligibleForUpgrade;
    private String cancelReason;
    private final String referralSource;
    private String plan;
    private double price;
    private static final long serialVersionUID = 3L;

    // Constructor for regularmembers
    public RegularMember(int id, String name, String location, String phone, 
    String email, String gender, String DOB, String membershipStartDate, 
    String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate); 
        this.referralSource = (referralSource == null)? "":referralSource;
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.cancelReason = "";
    }

    // Accessor  method for each attribute 
    public int getAttendanceLimit() {
        return ATTENDANCE_LIMIT;
    }

    public boolean isEligibleForUpgrade() { 
        return isEligibleForUpgrade;
    }

    public String getRemovalReason() {
        return cancelReason;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public String getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }

    // Mark attendance and increase loyalty points
    @Override
    public  void markAttendance() {
        if(isActiveStatus()){
        attendance++;
        loyaltyPoints += 1;
        System.out.println("Attendance marked for:" + getName());
        // Check eligibility for an upgrade
         if (attendance >= ATTENDANCE_LIMIT && !isEligibleForUpgrade) {
                isEligibleForUpgrade = true;
                System.out.println("Member " + getName() + " is now eligible for upgrade!");
            }
    }else{
        System.out.println("Cannot marke attendance" + getName() +"is not a Member");
    }

     
    }

    // For price plan for different membership
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;  // For Invalid plan
        }
    }

    // To Upgrade membership plan
    public String upgradePlan(String newPlan) {
        String newPlanLower = newPlan.toLowerCase();
        double newPrice = getPlanPrice(newPlanLower);

        if (newPrice == -1) {
            return "Invalid plan selected.";
        }
        if (!isEligibleForUpgrade) {
            return "Member  not eligible for upgrade right now.";
        }
        if (newPlan.equalsIgnoreCase(plan)) {
            return "Member already subscribed to this plan.";
        }
        // Prevent upgrading to same  plan
        if (newPrice == this.price) { 
        return "Cannot 'upgrade' to the same plan";
    }
     System.out.println("Upgrading member " + getName() + " from " + this.plan + " to " + newPlanLower);
        this.plan = newPlanLower;
        this.price = newPrice;
        return "Membership plan upgraded to " + newPlan + " successfully.";
    }

    // Reset member and their details to defult 
    public void revertRegularMember(String cancelReason) {
        super.resetMember();  // Call superclass method
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = getPlanPrice("basic");
        this.cancelReason = (cancelReason == null) ? "" : cancelReason;
        System.out.println("Regular Member details reset for ID: " + this.id);
    }

    // Display member details
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        System.out.println("Referral Source: " + (referralSource == null || referralSource.isEmpty() ? "None" : referralSource));
        System.out.println("Eligible for Upgrade: " + (isEligibleForUpgrade ? "Yes" : "No"));
        //To display reason for plan cancalation
        if (!cancelReason.isEmpty()) {
            System.out.println("Cancalation Reason: " + cancelReason);
 }
}
}


