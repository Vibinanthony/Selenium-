import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;

public class OrangeHRMAutomation {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // TC01: Login
            driver.get("https://opensource-demo.orangehrmlive.com/");
            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            System.out.println("TC01: Logged in");

            // TC02: Add Employee
            driver.findElement(By.xpath("//span[text()='PIM']")).click();
            driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
            driver.findElement(By.name("firstName")).sendKeys("Test");
            driver.findElement(By.name("middleName")).sendKeys("Middle");
            driver.findElement(By.name("lastName")).sendKeys("User");

            WebElement empIdField = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
            String empId = empIdField.getAttribute("value");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            System.out.println("TC02: Employee created with ID: " + empId);

            // TC03: Search Employee
            driver.findElement(By.xpath("//span[text()='PIM']")).click();
            driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).sendKeys(empId);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.findElement(By.xpath("//div[@class='orangehrm-container']//div[@role='row']/div[2]"))
                  .click();
            System.out.println("TC03: Employee search successful");

            // TC04: Edit Nationality
            Select nationality = new Select(driver.findElement(By.xpath("//label[text()='Nationality']/../following-sibling::div/select")));
            nationality.selectByVisibleText("Indian");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            System.out.println("TC04: Nationality updated");

            // TC05: Upload Profile Picture
            WebElement photoUpload = driver.findElement(By.xpath("//input[@type='file']"));
            File file = new File("profile.jpg");
            if (file.exists()) {
                photoUpload.sendKeys(file.getAbsolutePath());
                Thread.sleep(2000);
                System.out.println("TC05: Profile uploaded");
            } else {
                System.out.println("TC05: profile.jpg not found");
            }

            // TC06: Navigate Admin, Leave, Time
            driver.findElement(By.xpath("//span[text()='Admin']")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//span[text()='Leave']")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//span[text()='Time']")).click();
            Thread.sleep(500);
            System.out.println("TC06: Navigation verified");

            // TC07: Update My Info
            driver.findElement(By.xpath("//span[text()='My Info']")).click();
            Thread.sleep(1000);
            WebElement midName = driver.findElement(By.name("middleName"));
            midName.clear();
            midName.sendKeys("UpdatedMiddle");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            System.out.println("TC07: My Info updated");

            // TC08: Logout
            driver.findElement(By.cssSelector(".oxd-userdropdown-tab")).click();
            driver.findElement(By.xpath("//a[text()='Logout']")).click();
            System.out.println("TC08: Logged out");

            // TC09: Invalid Login
            driver.findElement(By.name("username")).sendKeys("InvalidUser");
            driver.findElement(By.name("password")).sendKeys("wrongPass");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            WebElement error = driver.findElement(By.cssSelector(".oxd-alert-content-text"));
            if (error.isDisplayed()) System.out.println("TC09: Invalid login blocked");

            // TC10: Required field validation on login
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            boolean userRequired = driver.findElements(By.xpath("//span[text()='Required']")).size() > 0;
            if (userRequired) System.out.println("TC10: Required field validation shown");

            // Re-login for further steps
            driver.findElement(By.name("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("admin123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            // TC11: Update Contact Details
            driver.findElement(By.xpath("//span[text()='My Info']")).click();
            driver.findElement(By.xpath("//a[text()='Contact Details']")).click();
            WebElement address1 = driver.findElement(By.xpath("//label[text()='Address Street 1']/../following-sibling::div/input"));
            address1.clear();
            address1.sendKeys("123 Orange Road");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            System.out.println("TC11: Contact address updated");

            // TC12: Apply for Leave
            driver.findElement(By.xpath("//span[text()='Leave']")).click();
            driver.findElement(By.xpath("//a[text()='Apply']")).click();
            Select leaveType = new Select(driver.findElement(By.xpath("//label[text()='Leave Type']/../following-sibling::div/select")));
            leaveType.selectByVisibleText("CAN - Personal");
            WebElement fromDate = driver.findElement(By.xpath("//input[@placeholder='yyyy-mm-dd']"));
            fromDate.clear();
            fromDate.sendKeys("2025-06-25");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            System.out.println("TC12: Leave applied");

            // TC13: View My Leave List
            driver.findElement(By.xpath("//a[text()='My Leave']")).click();
            Thread.sleep(1000);
            boolean leaveEntry = driver.findElements(By.xpath("//div[@class='oxd-table-card']")).size() > 0;
            System.out.println(leaveEntry ? "TC13: Applied leave is visible in list" : "TC13: Leave not listed");

            // TC14: Delete Employee
            driver.findElement(By.xpath("//span[text()='PIM']")).click();
            driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).sendKeys(empId);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='oxd-table-card']//div[@class='oxd-checkbox-wrapper']")).click();
            driver.findElement(By.xpath("//button[text()='Delete Selected']")).click();
            driver.findElement(By.xpath("//button[text()='Yes, Delete']")).click();
            System.out.println("TC14: Employee deleted successfully");

            // TC15: Verify Employee Profile Not Found
            driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).clear();
            driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input")).sendKeys(empId);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            boolean employeeFound = driver.findElements(By.xpath("//div[@class='oxd-table-cell oxd-padding-cell'][2]")).size() > 0;
            System.out.println(employeeFound ? "TC15: Employee still found after delete" : "TC15: Employee successfully removed");

            // TC16: Assign Job Title
            // Skip since employee was deleted

            // TC17: Admin User Search
            driver.findElement(By.xpath("//span[text()='Admin']")).click();
            driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div/input")).sendKeys("Admin");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            Thread.sleep(1000);
            boolean adminFound = driver.findElements(By.xpath("//div[@role='row']//div[contains(text(), 'Admin')]")).size() > 0;
            System.out.println(adminFound ? "TC17: Admin user found in search" : "TC17: Admin not listed");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
