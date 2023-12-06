package model;

import java.util.ArrayList;

public class Developer extends Employee {

    protected String teamName;
    protected ArrayList<String> programmingLanguages;
    protected int expYear;

    public Developer(String empID, String empName, String teamName, int baseSal, ArrayList<String> programmingLanguages, int expYear) {
        super(empID, empName, baseSal);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;

    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(ArrayList<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getBaseSal() {
        return baseSal;
    }

    public void setBaseSal(int baseSal) {
        this.baseSal = baseSal;
    }

    @Override
    public double getSalary() {
        if (getExpYear() >= 5) {
            return getBaseSal() + getExpYear() * 2000000;
        } else if (5 > getExpYear() && getExpYear() >= 3) {
            return getBaseSal() + getExpYear() * 1000000;
        } else {
            return getBaseSal();
        }

    }

    @Override
    public String toString() {
        return empID + "_" + empName + "_" + baseSal + "_" + teamName + "_" + programmingLanguages + "_" + expYear;
    }

}
