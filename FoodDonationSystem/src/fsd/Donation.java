package fsd;

//Model Class (Encapsulation Pattern)
//Represents real-world entity "Donation"

public class Donation {
 int id;
 String donor, contact, address, volunteer, location;
 String foodName, foodType;
 int quantity, hours;
 boolean collected;
 String quality;

 Donation(int id, String donor, String contact, String address,
          String location, String foodName, String foodType,
          int quantity, int hours) {
     this.id = id;
     this.donor = donor;
     this.contact = contact;
     this.address = address;
     this.volunteer = "";
     this.location = location;
     this.foodName = foodName;
     this.foodType = foodType;
     this.quantity = quantity;
     this.hours = hours;
     this.collected = false;
     this.quality = "";
 }

 String basic() {
     return id + " | " + foodName + " (" + foodType + ")" +
            " | Area: " + location +
            " | Feeds:" + quantity +
            " | Hours left:" + hours;
 }

 public String toString() {
     return basic() +
             " | Donor: " + donor +
             " | Contact: " + contact +
             " | Address: " + address +
             " | Collected: " + collected +
             " | Volunteer: " + volunteer;
 }

 String toFile() {
     return id + "," + donor + "," + contact + "," + address + "," +
            volunteer + "," + location + "," + foodName + "," +
            foodType + "," + quantity + "," + hours + "," +
            collected + "," + quality;
 }

 static Donation fromFile(String s) {
     String[] p = s.split(",");
     Donation d = new Donation(
             Integer.parseInt(p[0]), p[1], p[2], p[3],
             p[5], p[6], p[7],
             Integer.parseInt(p[8]), Integer.parseInt(p[9])
     );
     d.volunteer = p[4];
     d.collected = Boolean.parseBoolean(p[10]);
     if (p.length > 11) d.quality = p[11];
     return d;
 }
}
