package com.capg.employeepayroll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestEmployeePayrollService {
	static EmployeePayrollServiceDB serviceObj;
	@BeforeClass
	public static void setUp()  {
		serviceObj = new EmployeePayrollServiceDB();
	}

	@Test
	public void givenEmpPayrollDB_WhenRetrieved_ShouldMatchEmpCount() throws DBServiceException{
		List<EmployeePayrollData> empPayrollList = serviceObj.viewEmployeePayroll();
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
		List<EmployeePayrollData> empPayrollList = serviceObj.viewEmployeePayrollByJoinDateRange(LocalDate.of(2018,02,01), LocalDate.now() );
		assertEquals(2, empPayrollList.size());
	}
}
