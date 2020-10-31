package com.capg.employeepayroll;

import java.time.LocalDate;

public class EmployeePayrollData {
	private int emp_id;
	private String name;
	private String gender;
	private double salary;
	private LocalDate start_date;
	
	public EmployeePayrollData(int emp_id, String name, String gender , double salary, LocalDate start) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.start_date = start;
	}
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	@Override
	public String toString() {
		return "EmployeePayrollData [emp_id=" + emp_id + ", name=" + name + ", gender=" + gender + ", salary=" + salary
				+ ", start_date=" + start_date + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePayrollData other = (EmployeePayrollData) obj;
		if (emp_id != other.emp_id)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		return true;
	}

	
	
	
}
