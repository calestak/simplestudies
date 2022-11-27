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
		String sql = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date " +
				"FROM employee JOIN project_employee ON employee.employee_id = project_employee.employee_id";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			employees.add(mapRowToEmployee(results));
		}
		return employees;
	}

	private Employee mapRowToEmployee(SqlRowSet results) {
		Employee employee = new Employee();
		employee.setId(results.getInt("employee_id"));
		employee.setDepartmentId(results.getInt("department_id"));
		employee.setFirstName(results.getString("first_name"));
		employee.setLastName(results.getString("last_name"));
		employee.setBirthDate(results.getDate("birth_date").toLocalDate());
		employee.setHireDate(results.getDate("hire_date").toLocalDate());
		return employee;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employeeName = new ArrayList<>();
		String sqlSearchEmployeesByName = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date " +
				"FROM employee " + "WHERE first_name = ? AND last_name = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchEmployeesByName, firstNameSearch, lastNameSearch);
		while(results.next()) {
			Employee employeeByName = mapRowToEmployee(results);
			employeeName.add(employeeByName);
		}
		return employeeName;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(int projectId) {
		List<Employee> employeeProject = new ArrayList<>();
		String sql = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date " +
				"FROM employee JOIN project_employee ON employee.employee_id = project_employee.employee_id WHERE project_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		while(results.next()) {
			Employee employeeProjectId = mapRowToEmployee(results);
			employeeProject.add(employeeProjectId);
		}
		return employeeProject;
	}

	@Override
	public void addEmployeeToProject(int projectId, int employeeId) {
		String sql = "INSERT INTO project (employee_id, department_id, first_name, last_name, birth_date, hire_date) " +
				"VALUES (?, ?, ?, ?, ?, ?) " +
				"RETURNING project_id;";
		jdbcTemplate.update(sql, projectId, employeeId);

	}

	@Override
	public void removeEmployeeFromProject(int projectId, int employeeId) {
		String deleteEmployeeSql = "DELETE FROM project (employee_id, department_id, first_name, last_name, birth_date, hire_date JOIN employee ON project_employee.employee_id = employee.employee_id WHERE project_employee = (SELECT employee_id FROM employee WHERE project_id = ?;";
		jdbcTemplate.update(deleteEmployeeSql, projectId);
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> employeeNoProject = new ArrayList<>();
		String sql = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date , project_employee.project_id FROM employee " +
				"LEFT JOIN project_employee ON project_employee.employee_id = employee.employee_id WHERE project_employee.project_id IS NULL ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeeNoProject.add(employee);
		}
		return employeeNoProject;
	}


}
