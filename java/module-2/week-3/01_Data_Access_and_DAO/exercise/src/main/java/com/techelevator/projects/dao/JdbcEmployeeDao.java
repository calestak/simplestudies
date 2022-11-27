package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Employee;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcEmployeeDao implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT employeeId, departmentId, firstName, lastName, birthDate, hireDate " +
				"FROM employee";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			employees.add(mapRowToEmployee(results));

		}
		return employees;
	}

	private Employee mapRowToEmployee(SqlRowSet results) {
		Employee employee = new Employee();
		employee.setEmployeeId(results.getInt("employeeId"));
		employee.setDepartmentId(results.getInt("departmentId"));
		employee.setFirstName(results.getString("firstName"));
		employee.setLastName(results.getString("lastName"));
		employee.setBirthDate(results.getDate("birthDate").toLocalDate());
		employee.setHireDate(results.getDate("hireDate").toLocalDate());
		return employee;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employeeName = new ArrayList<>();
		String sql = "SELECT employeeId, departmentId, firstName, lastName, birthDate, hireDate " +
				"FROM employee WHERE employeeId = ? ORDER BY firstName, lastName";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstNameSearch, lastNameSearch);
		while (results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeeName.add(employee);
		}
		return employeeName;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(int projectId) {
		List<Employee> employeeProject = new ArrayList<>();
		String sql = "SELECT employeeId, departmentId, firstName, lastName, birthDate, hireDate " +
				"FROM employee WHERE projectId = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		while (results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeeProject.add(employee);
		}
		return employeeProject;
	}

	@Override
	public void addEmployeeToProject(int projectId, int employeeId) {
		String sql = "INSERT INTO project (employeeId, departmentId, firstName, lastName, birthDate, hireDate) " +
				"VALUES (?, ?, ?, ?, ?, ?) " +
				"RETURNING project_id;";
		

	}

	@Override
	public void removeEmployeeFromProject(int projectId, int employeeId) {
		String deleteEmployeeSql = "DELETE FROM project WHERE projectId = ?;";
		jdbcTemplate.update(deleteEmployeeSql, projectId);
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> employeeNoProject = new ArrayList<>();
		String sql = "SELECT employeeId, departmentId, firstName, lastName, birthDate, hireDate " +
				"FROM employee WHERE projectId != ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeeNoProject.add(employee);
		}
		return employeeNoProject;
	}


}
