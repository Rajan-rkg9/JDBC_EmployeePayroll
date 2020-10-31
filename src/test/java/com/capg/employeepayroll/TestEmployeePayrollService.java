package com.capg.employeepayroll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void givenUpdatedSalary_WhenRetrieved_ShouldBeSyncedWithDB() throws DBServiceException{
		serviceObj.updateEmployeeSalary("Terisa", 3000000.0);
		boolean isSynced = serviceObj.isEmpPayrollSyncedWithDB("Terisa");
		assertTrue(isSynced);
	}

}
