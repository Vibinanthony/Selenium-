# OrangeHRM Selenium Automation Project

This project demonstrates end-to-end automation of the [OrangeHRM demo website](https://opensource-demo.orangehrmlive.com/) using **Selenium WebDriver with Java**. It covers multiple user flows such as login, employee management, leave application, and more.

## Technologies Used
- Java
- Selenium WebDriver
- ChromeDriver
- Maven (optional)

## How to Run

1. **Clone this repository**
   ```bash
   git clone https://github.com/yourusername/orangehrm-selenium-automation.git
   cd orangehrm-selenium-automation
   ```

2. **Add Chromedriver**
   - Download from [ChromeDriver Site](https://sites.google.com/a/chromium.org/chromedriver/)
   - Place the binary in your system PATH or project root.

3. **Run the file**
   - Compile & Run the `OrangeHRMAutomation.java` using your IDE or terminal:
     ```bash
     javac OrangeHRMAutomation.java
     java OrangeHRMAutomation
     ```

## 📋 Automated Test Cases

| TC No | Description                                 |
|-------|---------------------------------------------|
| TC01  | Login with valid credentials                |
| TC02  | Add new employee                            |
| TC03  | Search employee by ID                       |
| TC04  | Update employee nationality                 |
| TC05  | Upload employee profile picture             |
| TC06  | Navigate through Admin, Leave, Time modules |
| TC07  | Edit "My Info" section                      |
| TC08  | Logout functionality                        |
| TC09  | Login with invalid credentials              |
| TC10  | Required field validation on login          |
| TC11  | Update contact details                      |
| TC12  | Apply for leave                             |
| TC13  | View leave history                          |
| TC14  | Delete employee                             |
| TC15  | Verify employee is deleted                  |
| TC16  | *Skipped (employee deleted)*                |
| TC17  | Search Admin user in User Management        |

## Project Structure (if expanded)
```
orangehrm-selenium-automation/
├── OrangeHRMAutomation.java
├── README.md
├── chromedriver
└── profile.jpg (optional test file)
```

## Author
**Vibin Anthony**  
QA Engineer | Automation Tester
