package model;

import java.util.ArrayList;

public class TeamLeader extends Developer {

    private double bonus_rate;

    public TeamLeader(String empID, String empName, String teamName , int baseSal,   ArrayList<String> programmingLanguages, int expYear,  double bonus_rate) {
        super(empID, empName,teamName , baseSal, programmingLanguages , expYear);
        this.bonus_rate = bonus_rate;
        
    }

  

    public double getBonus_rate() {
        return bonus_rate;
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public int getExpYear() {
        return expYear;
    }

    public String getEmpID() {
        return empID;
    }

  
        @Override
    public double getSalary() {
        return super.getSalary() + super.getSalary() * getBonus_rate();
    }
}
