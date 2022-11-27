package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(int projectId) {
		Project project = null;
		String sql = "SELECT project_id, name, from_date, to_date " +
				"FROM project " +
				"WHERE project_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		while (results.next()){
			project = mapRowToProject(results);
		}
		return project;

	}

	private Project mapRowToProject(SqlRowSet results) {
		Project project= new Project();
		project.setId(results.getInt("project_id"));
		project.setName(results.getString("name"));
		project.setFromDate(results.getDate("from_date").toLocalDate());
		project.setToDate(results.getDate("to_date").toLocalDate());
		return project;
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT project_id, name, from_date, to_date " +
				"FROM project";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			projects.add(mapRowToProject(results));

		}
		return projects;
	}

	@Override
	public Project createProject(Project newProject) {
		String sql = "INSERT INTO project ( name, from_date, to_date )" +
				"VALUES (?, ?, ?) " + "RETURNING project_id";

		int parkId =
				jdbcTemplate.queryForObject(sql, int.class, newProject.getName()
						,newProject.getFromDate(), newProject.getToDate());

		newProject.setId(project_id);
		return newProject;
	}

	@Override
	public void deleteProject(int projectId) {
		String deleteProjectSql = "DELETE FROM project WHERE project_id = ?;";
		jdbcTemplate.update(deleteProjectSql, projectId);

	}
	

}
