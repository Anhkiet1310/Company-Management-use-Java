package viewer;

import model.Tester;
import model.Employee;
import java.util.*;
import controller.*;
import java.io.File;
import javax.swing.SpringLayout;
//import utilities.*;s

public class Main {

    public static void main(String[] args) throws Exception {
        // Menu options
        String[] options = {"Read all Employees and print to screen",
            "Show staff proficient in a Programming Language", "Show Tester has a height salary",
            "Show Employee’s higest salary", "Show Leader of the Team has most Employees",
            "Sort Employees as descending salary", "Write file"};
        final String fileEmp = "src\\input\\ListOfEmployees.txt";
        final String filePL = "src\\input\\PLInfo.txt";
        final String fileWrite1 = "src\\output\\Req2.txt";
        final String fileWrite2 = "src\\output\\Req3.txt";
        int choice = 0;
        
        CompanyManagement cm = new CompanyManagement(fileEmp, filePL);
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Note: \nAll employee's salary based on the actual salary after multiply with the bonus and casted into integer!!!");
        do {
            System.out.println("\nCompany Employee Management Program");
            choice = Menu.getChoice(options); // show Menu options
            switch (choice) {
                case 1:
                    cm.printEmpList();
                    break;
                case 2:
                    System.out.println("Input Programming Language: ");
                    ArrayList<Employee> arr = cm.getDeveloperByProgrammingLanguage(sc.nextLine());
                    cm.printEmpList(arr);
                    break;
                case 3:
                    System.out.println("Input Salary: ");
                    ArrayList<Tester> testers = cm.getTestersHaveSalaryGreaterThan(sc.nextDouble());
                    for ( Tester tt : testers )
                        System.out.println(tt);
                    break;
                       
                case 4:
                    System.out.println(cm.getEmployeeWithHighestSalary());
                    break;
                case 5:
                    System.out.println(cm.getLeaderWithMostEmployees());
                    break;
                case 6:
                    ArrayList<Employee> sortList = cm.sorted();
                    for (Employee st : sortList)
                        System.out.println(st);
                    
                    break;
                case 7: // write file
                     File directory = new File(System.getProperty("user.dir") + "/src/output");
                    if (!directory.exists()) {
                        directory.mkdir();
                    }

                    cm.writeFile(System.getProperty("user.dir") + "/src/output/Req2.txt",
                            cm.getDeveloperByProgrammingLanguage("C++"));

                    cm.writeFile(System.getProperty("user.dir") + "/src/output/Req3.txt",
                            cm.getEmployeesHaveSalaryGreaterThan(4700000));
                    break;
                    
                default:
                    System.out.println("Good bye!");
            }
        } while (choice > 0 && choice < options.length);
    }
}
