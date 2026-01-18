 Food Donation System (Java Console Project)

A simple and meaningful **Java console-based application** that helps connect **food donors, volunteers, and admins** to reduce food waste and support social responsibility.
This project is built using **core Java concepts, Object-Oriented Programming, and basic design patterns**.

 Project Features

###  Donor

* Add food donation
* View own donations
* Update donation (before collected)
* Track personal contribution (people fed)
* Add contact number, address, location, food name, food type, quantity, and expiry hours

###  Volunteer

* View available donations
* Filter donations by area
* Accept donation
* Mark donation as collected
* Provide feedback on food quality
* Track personal contribution

###  Admin

* View all donations
* View pending donations
* View collected donations
* View system statistics (total, collected, pending)

###  Awareness Section

* Global food waste statistics
* India food waste statistics
* Tips for students to reduce food waste
* Importance of food donation

---

##  Concepts Used

This project is strictly built using **Core Java (No database, no GUI, no frameworks)**.

✔ Classes and Objects
✔ Encapsulation
✔ Methods and Constructors
✔ Arrays and ArrayList
✔ Control Statements
✔ Exception Handling
✔ String Handling
✔ File structure with packages
✔ Modular programming

---

##  Design Patterns Used

| Pattern                   | Where Used                               | Purpose                            |
| ------------------------- | ---------------------------------------- | ---------------------------------- |
| **Factory Pattern**       | `FoodFactory`                            | Centralized creation of food types |
| **Encapsulation**         | `Donation` class                         | Bundles data + behavior safely     |
| **Singleton-style usage** | Static Scanner & donation list in `Main` | Ensures single shared data         |
| **Controller Pattern**    | `Main` class                             | Controls flow between modules      |

---

##  Project Structure

```
src/
└── fsd/
    ├── Main.java
    ├── Donation.java
    └── FoodFactory.java
```

---

##  How to Run

1. Open project in **Eclipse / IntelliJ / VS Code**
2. Run:

   ```
   Main.java
   ```
3. Use the menu:

   ```
   1. Donor
   2. Volunteer
   3. Admin
   4. Awareness
   0. Exit
   ```

---

##  Sample Use Case

A restaurant donates extra food →
Volunteer finds nearby donation →
Accepts and collects food →
Admin tracks impact →
Less food waste → More people fed 

---

##  Future Enhancements

* Add database (MySQL)
* Build GUI using JavaFX
* Web version using Spring Boot
* Mobile app for volunteers
* Location-based smart matching

---

## 👩‍💻 Developed By

**Kolla Vineesha**
Java Console Project – Social Impact Based Learning


