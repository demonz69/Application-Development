import java.io.Serializable ;

public abstract class GymMember implements Serializable{
    private static final long serialVersionUID = 1L;
    //using protected access modifier to protect data
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String DOB;
    protected String membershipStartDate;
    protected int attendance;
    protected double loyaltyPoints;
    protected boolean activeStatus;
    
    //constructor with all the required parameters
    public GymMember (int id, String name, String location, String phone, String email, String gender, String DOB, String membershipDate){
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate= membershipStartDate;
        
        //Assigning default values to all members
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }
        //Using getter method to access protected data
        public int getId() { return id;}
        public String getName () {return name;}
        public String getLocation() {return location;}
        public String getPhone () {return phone;}
        public String getEmail (){return email;}
        public String getGender (){return gender;}
        public String getDOB () {return DOB;}
        public String getMembershipStartDate () { return membershipStartDate;}
        public int getAttendance () {return attendance;}
        public double getLoyaltyPoints() { return loyaltyPoints;}
        public  boolean isActiveStatus() { return activeStatus;}
        
        //method specifically to set status for GUI
        public void setActiveStatus(boolean status){
            this.activeStatus = status;
        }
        
        // method to mark attendance for member
        public abstract void markAttendance();
        
        // method to activate the membership
        public void activeMembership(){
            if(!this.activeStatus){
            this.activeStatus = true;
            System.out.println("Membership activated for member:" + this.id);
        }else{
             System.out.println("Membership is already activated for member:" + this.id); 
        }
    }
        
        //method to deactivate the memberhsip and check its status
         public void deactivateMembership () {
            if (this.activeStatus) {
                this.activeStatus = false;
                System.out.println("Membership deactivated for member:" + this.id);
            }else{
                System.out.println("Membership is already deactivated for member:" + this.id);
            }
        }
        //method to reset member details
        public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        System.out.println("Member detials: " + this.id + "has been reset to default.");
        }
        
        // To display  member details
        public void display(){
            System.out.println("Member Id:" + this.id);
            System.out.println("Name:" + this.name);
            System.out.println("Location:" + this.location);
            System.out.println("Phone no :" + this.phone);
            System.out.println("Email id:" + this.email);
            System.out.println("Gender:" + this.gender);
            System.out.println("DOB:" + this.DOB);
            System.out.println("Membership Started from :"+ this.membershipStartDate);
            System.out.println("Attendance:" + this.attendance);
            System.out.println("Loyalty Points:" + this.loyaltyPoints);
            System.out.println(" Active Status :" + this.activeStatus);
         
          
        }
}
