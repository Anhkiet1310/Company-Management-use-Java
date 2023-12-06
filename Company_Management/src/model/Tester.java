package model;

public class Tester extends Employee {

    protected double bonusRate;
    protected String type;

    public Tester(String empID, String empName, int baseSal, double bonusRate, String type) {
        super(empID, empName, baseSal);
        this.bonusRate = bonusRate;
        this.type = type;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public String getType() {
        return type;
    }

    @Override
    public double getSalary() {
        return getBaseSal() + getBonusRate() * getBaseSal();
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

}
