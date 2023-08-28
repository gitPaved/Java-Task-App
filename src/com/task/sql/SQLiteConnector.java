package com.task.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.task.bean.TaskBean;
import com.task.methods.Methods;

public class SQLiteConnector {

	Connection connection = null;
	Methods methods = new Methods();
	String patternDateTime = "yyyy-MM-dd HH:mm:ss";
	String patternDate = "yyyy-MM-dd";

	public Connection getConnection() {
		if (connection == null) {
			try {

				String dbFolderPath = "db";
				String dbFileName = "database.db";

				File dbFolder = new File(dbFolderPath);
				if (!dbFolder.exists()) {
					dbFolder.mkdir();
				}

				String dbUrl = "jdbc:sqlite:" + dbFolderPath + File.separator + dbFileName;

				connection = DriverManager.getConnection(dbUrl);
				createTable(connection);

				System.out.println("Connexion à la base de données établie ----- .");

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR !", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		} else {
			createTable(connection);
		}
		return connection;

	}

	public void createTable(Connection connection) {

		String createTableQuery = "CREATE TABLE IF NOT EXISTS tasks (" + "id INTEGER PRIMARY KEY, " + "title TEXT, "
				+ "description TEXT, " + "due_date TEXT, " + "reminder_date TEXT, " + "creation_date TEXT, "
				+ "modification_date TEXT, " + "is_important BOOLEAN, " + "is_finished BOOLEAN)";
		try (PreparedStatement stmt = connection.prepareStatement(createTableQuery)) {
			stmt.executeUpdate();
			System.out.println("Creation ok ----- table task.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String insertTask(Connection connect, TaskBean task) {
		String insertQuery = "INSERT INTO tasks (title, description, due_date, reminder_date, creation_date, modification_date, is_important, is_finished) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
			stmt.setString(1, task.getTitle());
			stmt.setString(2, task.getDescription());
			stmt.setString(3, methods.convertDateToString(task.getDueDate(), patternDateTime));
			stmt.setString(4, methods.convertDateToString(task.getReminder(), patternDateTime));
			stmt.setString(5, methods.convertDateToString(new Date(), patternDateTime));
			stmt.setString(6, methods.convertDateToString(new Date(), patternDateTime));
			stmt.setBoolean(7, task.getImportant());
			stmt.setBoolean(8, task.getFinish());
			stmt.executeUpdate();
			System.out.println("Insertion ok ----- .");

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			return e.getMessage();
		}
		return "true";
	}

	public String updateTask(Connection connect, TaskBean task) {
		String updateQuery = "UPDATE tasks SET title = ?, description = ?, due_date = ?,reminder_date = ?, modification_date = ?, is_important = ?, is_finished = ? WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
			stmt.setString(1, task.getTitle());
			stmt.setString(2, task.getDescription());
			stmt.setString(3, methods.convertDateToString(task.getDueDate(), patternDateTime));
			stmt.setString(4, methods.convertDateToString(task.getReminder(), patternDateTime));
			stmt.setString(5, methods.convertDateToString(new Date(), patternDateTime));
			stmt.setBoolean(6, task.getImportant());
			stmt.setBoolean(7, task.getFinish());
			stmt.setInt(8, task.getId());
			stmt.executeUpdate();
			System.out.println("Update ok ----- .");

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			return e.getMessage();
		}
		return "true";
	}

	public String deleteTask(Connection connect, int id) {
		String deleteQuery = "DELETE From tasks WHERE id = ?";

		try (PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("DELETE ok ----- .");

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			return e.getMessage();
		}
		return "true";
	}

	public List<TaskBean> selectTasksCreated(Connection connection, String order) {
		List<TaskBean> tasks = new ArrayList<>();

		String query = "SELECT * FROM tasks ORDER BY " + order + " DESC";
		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			tasks = getTasks(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return tasks;
	}

	public List<TaskBean> selectTasksImportant(Connection connection, String order) {
		List<TaskBean> tasks = new ArrayList<>();

		String query = "SELECT * FROM tasks WHERE is_important = 1 ORDER BY " + order + " DESC";
		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			tasks = getTasks(rs);
		} catch (SQLException e) {

			// e.printStackTrace();
			return null;
		}

		return tasks;
	}

	public List<TaskBean> selectTasksFinished(Connection connection, String order) {
		List<TaskBean> tasks = new ArrayList<>();

		String query = "SELECT * FROM tasks WHERE is_finished = 1 ORDER BY " + order + " DESC";
		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			tasks = getTasks(rs);
		} catch (SQLException e) {
			// e.printStackTrace();
			return null;
		}

		return tasks;
	}

	public List<TaskBean> selectTasksDueDate(Connection connection, String order) {
		List<TaskBean> tasks = new ArrayList<>();

		String query = "SELECT * FROM tasks  WHERE due_date IS NOT NULL ORDER BY " + order + " DESC";
		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			tasks = getTasks(rs);
		} catch (SQLException e) {
			// e.printStackTrace();
			return null;
		}

		return tasks;
	}

	public List<TaskBean> searchTasks(Connection connection, String order, String sort, String textSearch) {
		List<TaskBean> tasks = new ArrayList<>();
		String query = "SELECT * FROM tasks ";
		if (order.toLowerCase().contains("importantes")) {
			query += "WHERE is_important = 1 ";
			query = setQuery(query, textSearch, sort, false);
		} else if (order.toLowerCase().contains("termin")) {
			query += "WHERE is_finished = 1 ";
			query = setQuery(query, textSearch, sort, false);
		} else if (order.toLowerCase().contains("planifi")) {
			query += "WHERE due_date IS NOT NULL ";
			query = setQuery(query, textSearch, sort, false);
		} else {
			query = setQuery(query, textSearch, sort, true);
		}
		System.out.println("Query: " + query);

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			tasks = getTasks(rs);
		} catch (SQLException e) {
			// e.printStackTrace();
			return null;
		}

		return tasks;
	}

	public List<TaskBean> getTasks(ResultSet rs) {
		List<TaskBean> tasks = new ArrayList<>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Date dueDate = methods.convertStingToDate(rs.getString("due_date"), patternDateTime);
				Date reminderDate = methods.convertStingToDate(rs.getString("reminder_date"), patternDateTime);
				Date createDate = methods.convertStingToDate(rs.getString("creation_date"), patternDateTime);
				Date updateDate = methods.convertStingToDate(rs.getString("modification_date"), patternDateTime);
				boolean isImportant = rs.getBoolean("is_important");
				boolean isFinished = rs.getBoolean("is_finished");
				TaskBean task = new TaskBean(id, title, description, dueDate, reminderDate, createDate, updateDate,
						isImportant, isFinished);
				tasks.add(task);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			return null;
		}

		return tasks;
	}

	public String setQuery(String query, String textSearch, String sort, boolean all) {
		if (!textSearch.trim().equals("")) {

			if (all) {
				query += "WHERE title LIKE '" + textSearch + "%' ";
			} else {
				query += "AND title LIKE '" + textSearch + "%' ";
			}
		}
		if (sort.equals("title")) {
			query += "ORDER BY title ASC";
		} else {
			query += "ORDER BY " + sort + " DESC";
		}
		return query;
	}

	public String resetTableTasks(Connection connection) {
		String query = "drop table if exists tasks";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			return e.getMessage();
		}
		return "true";
	}
}
