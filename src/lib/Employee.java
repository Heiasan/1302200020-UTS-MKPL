package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	private boolean isForeigner;
	private boolean isMale;
	private LocalDate joinDate;
	private String spouseName;
	private String spouseIdNumber;
	private List<Child> children;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			LocalDate joinDate, boolean isForeigner, boolean isMale) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;
		this.isMale = isMale;
		this.children = new LinkedList<Child>();
	}

	public void setMonthlySalary(int grade) {
		switch (grade) {
			case 1:
				this.monthlySalary = 3000000;
				break;
			case 2:
				this.monthlySalary = 5000000;
				break;
			case 3:
				this.monthlySalary = 7000000;
				break;
			default:
				throw new IllegalArgumentException("Invalid grade: " + grade);
		}

		if (isForeigner) {
			this.monthlySalary *= 1.5;
		}
	}

	public void setAnnualDeductible(int annualDeductible) {
		this.annualDeductible = annualDeductible;
	}

	public void setOtherMonthlyIncome(int otherMonthlyIncome) {
		this.otherMonthlyIncome = otherMonthlyIncome;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = spouseIdNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		Child child = new Child(childName, childIdNumber);
		this.children.add(child);
	}

	public int getAnnualIncomeTax() {
		int workingMonthsInYear = calculateWorkingMonthsInYear(joinDate);
		int numberOfChildren = children.size();
		boolean isMarried = spouseIdNumber != null;

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, workingMonthsInYear, annualDeductible,
				!isMarried, numberOfChildren);
	}

	private int calculateWorkingMonthsInYear(LocalDate joinDate) {
		LocalDate currentDate = LocalDate.now();

		if (currentDate.isBefore(joinDate)) {
			throw new IllegalArgumentException("Join date cannot be in the future");
		}

		if (currentDate.getYear() == joinDate.getYear()) {
			return currentDate.getMonthValue() - joinDate.getMonthValue() + 1;
		} else {
			return (12 - joinDate.getMonthValue() + 1) + (currentDate.getMonthValue() - 1);
		}
	}

	private class Child {
		private String name;
		private String idNumber;

		public Child(String name, String idNumber) {
			this.name = name;
			this.idNumber = idNumber;
		}

		public String getName() {
			return name;
		}

		public String getIdNumber() {
			return idNumber;
		}
	}
}
