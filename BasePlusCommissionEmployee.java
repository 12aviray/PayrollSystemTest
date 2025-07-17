// File: BasePlusCommissionEmployee.java

public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);

        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }
        this.baseSalary = baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Overrides earnings to include base salary
    @Override
    public double earnings() {
        // We call super.earnings() to get the commission part
        return getBaseSalary() + super.earnings();
    }

    @Override
    public String toString() {
        // We call super.toString() which formats CommissionEmployee info
        return String.format("Base-Salaried %s\n%s: $%,.2f",
                super.toString(), "Base Salary", getBaseSalary());
    }
}