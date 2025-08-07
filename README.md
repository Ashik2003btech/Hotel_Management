# 🏨 Hotel Management System (Console-Based Java App)

This is a console-based **Hotel Management System** built in Java using layered architecture (VO, DAO, BO, Service, Exception, and Main classes). The application supports basic hotel booking operations such as adding, viewing, searching, and updating bookings. It also includes **logging support via Log4j**.

---

## 📂 Project Structure

Hotel_Management/
│
├── src/
│ ├── com.hotelbooking.bo/
│ │ └── BookingBO.java
│ ├── com.hotelbooking.dao/
│ │ ├── BookingDAO.java
│ │ └── BookingDAOImpl.java
│ ├── com.hotelbooking.exception/
│ │ └── BookingException.java
│ ├── com.hotelbooking.main/
│ │ └── BookingMain.java
│ ├── com.hotelbooking.response/
│ │ └── ResponseObject.java
│ ├── com.hotelbooking.service/
│ │ └── BookingService.java
│ └── com.hotelbooking.vo/
│ └── Booking.java
│ └── log4j.properties
│
├── Referenced Libraries/
│ └── log4j-1.2.17.jar
│
└── README.md


---

## ✅ Features

- ➕ Add new booking  
- 🔍 Search booking by ID  
- 📄 View all bookings  
- ✏️ Update booking  
- ⚙️ Layered architecture (VO, DAO, BO, Service, Exception, Main)  
- 🧾 Proper logging using **Log4j**

---

## 🧪 How to Run

1. ✅ Clone the repo or download the ZIP.
2. ✅ Import into Eclipse as an existing Java project.
3. ✅ Add Log4j JAR to your classpath:
   - `log4j-1.2.17.jar` (Download from [here](https://archive.apache.org/dist/logging/log4j/1.2.17/))
4. ✅ Ensure `log4j.properties` is placed under `src/` directory.
5. ✅ Run the `BookingMain.java` file to start the application.

---

## 📝 Sample `log4j.properties`

```properties
log4j.rootLogger=INFO, console

log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n

