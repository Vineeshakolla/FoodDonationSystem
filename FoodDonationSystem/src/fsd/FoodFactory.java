package fsd;
//Factory Design Pattern
//Responsible for creating food type based on user choice

public class FoodFactory {

 // Factory method
 static String getFoodType(int c) {
     switch (c) {
         case 1: return "Tiffin";
         case 2: return "Lunch";
         case 3: return "Snack";
         case 4: return "Dinner";
         case 5: return "Packed Food";
         case 6: return "Fruits";
         case 7: return "Sweets";
         default: return "Other";
     }
 }
}
