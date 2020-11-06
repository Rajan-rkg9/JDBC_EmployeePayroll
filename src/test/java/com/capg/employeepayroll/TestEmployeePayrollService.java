package com.capg.employeepayroll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestEmployeePayrollService {
	static EmployeePayrollServiceDB serviceObj;
	static Map<String, Double> empDataByGender;
	static List<EmployeePayrollData> empPayrollList;
	@BeforeClass
	public static void setUp()  {
		serviceObj = new EmployeePayrollServiceDB();
		empDataByGender = new HashMap<>();
		empPayrollList = new ArrayList<>();	
	}
	@Ignore
	@Test
	public void givenEmpPayrollDB_WhenRetrieved_ShouldMatchEmpCount() throws DBServiceException{
		empPayrollList = serviceObj.viewEmployeePayroll();
		assertEquals(3, empPayrollList.size());
	}
	@Ignore
	@Test
	public void givenUpdatedSalary_WhenRetrieved_ShouldBeSyncedWithDBUsingStatement() throws DBServiceException{
		serviceObj.updateEmployeeSalaryUsingStatement("Terisa", 3000000.0);
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Terisa");
		assertTrue(isSynced);
	}
	@Ignore
	@Test
	public void givenUpdatedSalary_WhenRetrieved_ShouldBeSyncedWithDBUsingPreparedStatement() throws DBServiceException{
		serviceObj.updateEmployeeSalaryUsingPreparedStatement("Terisa", 3000000.0);
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Terisa");
		assertTrue(isSynced);
	}
	@Ignore
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmpCount() throws DBServiceException{
		empPayrollList = serviceObj.viewEmployeePayrollByJoinDateRange(LocalDate.of(2018,02,01), LocalDate.now() );
		assertEquals(2, empPayrollList.size());
	}
	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedSum_ShouldReturnSumGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "sum");
		assertEquals(4000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}
	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedAvg_ShouldReturnAvgByGroupedGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "avg");
		assertEquals(3000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}
	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedMax_ShouldReturnMaxGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "max");
		assertEquals(3000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}
	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedMin_ShouldReturnMinGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "min");
		assertEquals(1000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}
	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedCount_ShouldReturnCountGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary", "count");
		assertEquals(2, empDataByGender.get("M"), 0.0);
		assertEquals(1, empDataByGender.get("F"), 0.0);
	}
	@Ignore
	@Test
	public void insertedNewEmployee_WhenRetrieved_ShouldBeSyncedWithDB() throws DBServiceException{
		serviceObj.insertNewEmployeeToDB("Mark" , "M", 5000000.0 , LocalDate.now() , 1 , "Sales");
		serviceObj.viewEmployeeAndPayrollDetailsByName("Mark");
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Mark");
		assertTrue(isSynced);
	}
	@Ignore
	@Test
	public void addedNewEmployee_WhenRetrieved_ShouldReturnPayrollDetailsAndBeSyncedWithDB() throws DBServiceException{
		serviceObj.viewEmployeeAndPayrollDetailsByName("Mark");
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Mark");
		assertTrue(isSynced);
	}
	@Ignore
	@Test
	public void givenEmployeeId_WhenDeletedUsing_ShouldSyncWithDB() throws DBServiceException {
		serviceObj.removeEmployeeFromDB(2);
		assertEquals(2,empPayrollList.size());	
	}
	@Ignore
	@Test
	public void givenEmployeeData_ShouldPrintInstanceTime_ToConsole() throws DBServiceException {
		EmployeePayrollData[] arrayOfEmp = {
				new EmployeePayrollData("Jeff Bezos","M", 100000.0, LocalDate.now()),
				new EmployeePayrollData("Bill Gates","M", 200000.0, LocalDate.now()),
				new EmployeePayrollData("Mark Zuckerberg","M", 300000.0, LocalDate.now()),
				new EmployeePayrollData("Sundar","M", 600000.0, LocalDate.now()),
				new EmployeePayrollData("Mukesh","M", 500000.0, LocalDate.now()),
				new EmployeePayrollData("Anil","M", 300000.0, LocalDate.now())
		};
		Instant start = Instant.now();
		serviceObj.addEmployeeToPayroll(Arrays.asList(arrayOfEmp));
		Instant end = Instant.now();
		System.out.println("Duration Without Thread: "+java.time.Duration.between(start, end));
	}
	
	@Test
	public void givenEmployeeData_ShouldPrintInstanceTime_ToConsoleUsingThreads() throws DBServiceException {
		EmployeePayrollData[] arrayOfEmp = {
				new EmployeePayrollData("Jeff Bezos","M", 100000.0, LocalDate.now()),
				new EmployeePayrollData("Bill Gates","M", 200000.0, LocalDate.now()),
				new EmployeePayrollData("Mark Zuckerberg","M", 300000.0, LocalDate.now()),
				new EmployeePayrollData("Sundar","M", 600000.0, LocalDate.now()),
				new EmployeePayrollData("Mukesh","M", 500000.0, LocalDate.now()),
				new EmployeePayrollData("Anil","M", 300000.0, LocalDate.now())
		};
		Instant start = Instant.now();
		serviceObj.addEmployeeToPayrollUsingThreads(Arrays.asList(arrayOfEmp));
		Instant end = Instant.now();
		System.out.println("Duration Without Thread: "+java.time.Duration.between(start, end));
	}
	@Test
	public void givenEmployeePayrollData_ShouldPrintInstanceTime_ToConsoleUsingThreads() throws DBServiceException {
		EmployeePayrollData[] arrayOfEmp = {
				new EmployeePayrollData("Jeff Bezos","M", 100000.0, LocalDate.now(),501,"Finance"),
				new EmployeePayrollData("Bill Gates","M", 200000.0, LocalDate.now(),502,"Marketing"),
				new EmployeePayrollData("Mark Zuckerberg","M", 300000.0, LocalDate.now(),503,"Consultancy"),
				new EmployeePayrollData("Sundar","M", 600000.0, LocalDate.now(),504,"Shares"),
				new EmployeePayrollData("Mukesh","M", 500000.0, LocalDate.now(),505,"Management"),
				new EmployeePayrollData("Anil","M", 300000.0, LocalDate.now(),506,"Promotion")
		};
		Instant start = Instant.now();
		serviceObj.addEmployeeToPayrollDetailsUsingThreads(Arrays.asList(arrayOfEmp));
		Instant end = Instant.now();
		System.out.println("Duration Without Thread: "+java.time.Duration.between(start, end));
	}
	
}
