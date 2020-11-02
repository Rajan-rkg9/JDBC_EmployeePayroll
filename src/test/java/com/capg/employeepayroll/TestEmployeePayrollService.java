package com.capg.employeepayroll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
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

	@Test
	public void givenEmpPayrollDB_WhenRetrieved_ShouldMatchEmpCount() throws DBServiceException{
		empPayrollList = serviceObj.viewEmployeePayroll();
		assertEquals(3, empPayrollList.size());
	}
	
	@Test
	public void givenUpdatedSalary_WhenRetrieved_ShouldBeSyncedWithDBUsingStatement() throws DBServiceException{
		serviceObj.updateEmployeeSalaryUsingStatement("Terisa", 3000000.0);
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Terisa");
		assertTrue(isSynced);
	}
	
	@Test
	public void givenUpdatedSalary_WhenRetrieved_ShouldBeSyncedWithDBUsingPreparedStatement() throws DBServiceException{
		serviceObj.updateEmployeeSalaryUsingPreparedStatement("Terisa", 3000000.0);
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Terisa");
		assertTrue(isSynced);
	}
	
	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmpCount() throws DBServiceException{
		empPayrollList = serviceObj.viewEmployeePayrollByJoinDateRange(LocalDate.of(2018,02,01), LocalDate.now() );
		assertEquals(2, empPayrollList.size());
	}
	
	@Test
	public void givenEmployeeDB_WhenRetrievedSum_ShouldReturnSumGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "sum");
		assertEquals(4000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}

	@Test
	public void givenEmployeeDB_WhenRetrievedAvg_ShouldReturnAvgByGroupedGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "avg");
		assertEquals(3000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}

	@Test
	public void givenEmployeeDB_WhenRetrievedMax_ShouldReturnMaxGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "max");
		assertEquals(3000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}
	
	@Test
	public void givenEmployeeDB_WhenRetrievedMin_ShouldReturnMinGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary" , "min");
		assertEquals(1000000, empDataByGender.get("M"), 0.0);
		assertEquals(3000000, empDataByGender.get("F"), 0.0);
	}

	@Test
	public void givenEmployeeDB_WhenRetrievedCount_ShouldReturnCountGroupedByGender() throws DBServiceException {
		empDataByGender = serviceObj.viewEmployeeDataGroupedByGender("salary", "count");
		assertEquals(2, empDataByGender.get("M"), 0.0);
		assertEquals(1, empDataByGender.get("F"), 0.0);
	}
	
	@Test
	public void insertedNewEmployee_WhenRetrieved_ShouldBeSyncedWithDB() throws DBServiceException{
		serviceObj.insertNewEmployeeToDB("Mark" , "M", 5000000.0 , LocalDate.now());
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Mark");
		assertTrue(isSynced);
	}
}
