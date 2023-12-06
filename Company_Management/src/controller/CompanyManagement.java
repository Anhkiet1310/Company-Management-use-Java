package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Developer;
import model.TeamLeader;
import model.Tester;
import model.Employee;
import java.io.FileWriter;

public class CompanyManagement {

    private ArrayList<Employee> empList = null;

    // Contructor and read file
    public CompanyManagement(String path1, String path2) throws Exception {
        empList = getEmployeeFromFile(path1, path2);
    }

    public ArrayList<Employee> getEmployeeFromFile(String path1, String path2) throws Exception {
        ArrayList<Employee> list = null;
        Employee[] arrEmp = new Employee[100];
        int count = 0;

        String thisLine;
        BufferedReader myInputEmp;
        BufferedReader myInputPL;
        File fEmp = new File(path1);
        String fullPath = fEmp.getAbsolutePath();
        FileInputStream fileEmp = new FileInputStream(fullPath);
        myInputEmp = new BufferedReader(new InputStreamReader(fileEmp));
        HashMap<String, ArrayList<String>> hashPLInfo = new HashMap<>();
        File fPL = new File(path2);
        String fullPath2 = fPL.getAbsolutePath();
        FileInputStream filePL = new FileInputStream(fullPath2);
        myInputPL = new BufferedReader(new InputStreamReader(filePL));

        String thisLinePlInfoString;
        while ((thisLinePlInfoString = myInputPL.readLine()) != null) {
            String[] plInfo = thisLinePlInfoString.split(",");
            String empID = plInfo[0].trim();
            ArrayList<String> progL = new ArrayList<>();
            for (int i = 1; i < plInfo.length; i++) {
                String pl = plInfo[i].trim();
                progL.add(pl);
            }
            hashPLInfo.put(empID, progL);
        }
        while ((thisLine = myInputEmp.readLine()) != null) {
            Employee emp = null;
            if (!thisLine.trim().isEmpty()) {
                String[] split = thisLine.split(",");
                if (split.length == 8) {
                    String id = split[1].trim();
                    String name = split[2].trim();
                    String team = split[3].trim();
                    int expY = Integer.parseInt(split[4].trim());
                    double bonus = Double.parseDouble(split[6].trim());
                    int balS = Integer.parseInt(split[7].trim());
                    ArrayList<String> progL = hashPLInfo.get(id);
                    emp = new TeamLeader(id, name, team, balS, progL, expY, bonus);
                } else if (split[1].trim().charAt(0) == 'D') {
                    String id = split[1].trim();
                    String name = split[2].trim();
                    String team = split[3].trim();
                    int expY = Integer.parseInt(split[4].trim());
                    int balS = Integer.parseInt(split[5].trim());
                    ArrayList<String> progL = hashPLInfo.get(id);
                    emp = new Developer(id, name, team, balS, progL, expY);
                } else {
                    String id = split[1].trim();
                    String name = split[2].trim();
                    double bonus = Double.parseDouble(split[3].trim());
                    String type = split[4].trim();
                    int balS = Integer.parseInt(split[5].trim());
                    ArrayList<String> progL = hashPLInfo.get(id);
                    emp = new Tester(id, name, balS, bonus, type);
                }
                if (list == null) {
                    list = new ArrayList<>();
                }

                list.add(emp);

            }
        }

        return list;
    }

    //  list of programmers who are proficient in the input pl programmingLanguage.
    public ArrayList<Employee> getDeveloperByProgrammingLanguage(String pl) throws Exception {
        ArrayList<Employee> devList = null;

        if (empList != null && !empList.isEmpty()) {
            for (Employee emp : empList) {
                if (emp instanceof Developer) {
                    ArrayList<String> arrPl = ((Developer) emp).getProgrammingLanguages();
                    if (arrPl != null) {
                        for (String str : arrPl) {
                            if (str.equals(pl)) {
                                if (devList == null) {
                                    devList = new ArrayList<>();
                                }
                                devList.add(emp);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return devList;
    }
//
// list of testers whose total salary is greater than the value of the parameter

    public ArrayList<Tester> getTestersHaveSalaryGreaterThan(double value) throws Exception {
        ArrayList<Tester> testerList = null;
        if (empList != null && !empList.isEmpty()) {
            for (Employee emp : empList) {
                if (emp instanceof Tester) {
                    double salary = emp.getSalary();
                    if (salary > value) {
                        if (testerList == null) {
                            testerList = new ArrayList<>();
                        }
                        testerList.add((Tester) emp);
                    }
                }
            }
        }
        return testerList;
    }

    //get employee with greater salary
        public ArrayList<Employee> getEmployeesHaveSalaryGreaterThan(double value) {
        ArrayList<Employee> tList = new ArrayList();
        for (Employee emp : this.empList) {
            if (emp.getSalary() > value) {
                tList.add(emp);
            }
        }
        return tList;
    }
//
    public Employee getEmployeeWithHighestSalary() throws Exception {
        Employee highestEmp = empList.get(0);

        if (empList != null && !empList.isEmpty()) {
            for (int i = 1; i < empList.size(); i++) {
                if (highestEmp.getSalary() < empList.get(i).getSalary()) {
                    highestEmp = empList.get(i);
                }
            }
        }
        return highestEmp;
    }
//
    // get the team leader of the group with the most programmers

    public TeamLeader getLeaderWithMostEmployees() {
        Map<String, List<Employee>> teamMap = new HashMap<>();
        int maxCount = 0;
        TeamLeader leaderWithMostEmployees = null;

        for (Employee employee : empList) {
            if (employee instanceof Developer) {
                String teamName = ((Developer) employee).getTeamName();
                teamMap.computeIfAbsent(teamName, k -> new ArrayList<>()).add(employee);
                int count = teamMap.get(teamName).size();
                if (count > maxCount) {
                    maxCount = count;
                    leaderWithMostEmployees = getLeaderByTeamName(teamName);
                }
            }
        }
        return leaderWithMostEmployees;
    }

    private TeamLeader getLeaderByTeamName(String teamName) {
        for (Employee employee : empList) {
            if (employee instanceof TeamLeader && teamName.equals(((TeamLeader) employee).getTeamName())) {
                return (TeamLeader) employee;
            }
        }
        return null;
    }

//
//    // Sort Employees as descending salary
    public ArrayList<Employee> sorted() {
        ArrayList<Employee> sortedList = (ArrayList<Employee>) this.empList.clone();

        Collections.sort(sortedList, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                if (e2.getSalary() - e1.getSalary() > 0) {
                    return 1;
                } else if (e1.getSalary() == e2.getSalary()) {
                    String[] e1Name = e1.getEmpName().split(" ");
                    String[] e2Name = e2.getEmpName().split(" ");
                    return e1Name[e1Name.length - 1].compareTo(e2Name[e2Name.length - 1]);
                } else {
                    return -1;
                }
            }
        });
        return sortedList;
    }

// print empList
    public void printEmpList() {
        for (Employee emp : empList) {
            System.out.println(emp);
        }
    }

    public void printEmpList(ArrayList<Employee> arr) {
        if (arr == null || arr.isEmpty()) {
            return;
        }
        for (Employee emp : arr) {
            System.out.println(emp);
        }
    }
//
//    // write emplist
    public <E> boolean writeFile(String path, ArrayList<E> list) throws Exception {
        if ( list==null){
            return false;
        }
        FileWriter fWriter = new FileWriter(path);
        for ( E emp : list) {
            fWriter.write(emp.toString());
            fWriter.write("\n");
        }
        fWriter.close();
        return true;
    }
    
    public <E> boolean writeFile(String path, Object obj) throws Exception {
        if (obj==null) {
            return false;
        }
        File fInfo=new File(path);
        FileWriter fWriter = null;
        
        fWriter = new FileWriter(fInfo);
        
        fWriter.write(obj.toString());
        fWriter.close();
        return true;
    }
    
    

}
