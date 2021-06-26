package com.example.dao;

//ham DAO de tuong tac voi database -> lay du lieu
import com.example.model.Notemodel;
import com.example.model.usermodel;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/usermanagement";
	private String jdbcUsername = "root";
	private String jdbcPassword = "hoanganh456";

	private static final String SELECT_ALL_NOTES = "select * from notes";
	private static final String DELETE_NOTES_SQL = "delete from notes where id = ?;";

	public NoteDAO() {
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	//ham load tat ca cac note cua 1 iduser tu database len web
	public List<Notemodel> getallnotebyiduser(Object... parameters) {
		String query ="select * from notes  where iduser= ? ";
		List<Notemodel> results = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(query);
				setdb(statement,parameters);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
                 Notemodel notemodel =new Notemodel();
                 notemodel.setId(resultSet.getInt("id"));
					notemodel.setTitle(resultSet.getString("title"));
					notemodel.setContent(resultSet.getString("content"));
					notemodel.setUserid(resultSet.getString("iduser"));
					results.add(notemodel);
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}finally {
				try {
					if(connection !=null){
						connection.close();
					}
					if(statement !=null){
						statement.close();
					}
					if(resultSet !=null){
						resultSet.close();
					}

				} catch (SQLException throwables) {
				}
			}
		}
		return results;
	}

	//kiem tra ten tk va mk khi login
	public List<usermodel> checkuser(Object... parameters) {
		String query ="select * from user  where iduser= ? and password=? ";
		List<usermodel> results = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(query);
				setdb(statement,parameters);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					usermodel usermodel =new usermodel();
					usermodel.setIduser(resultSet.getString("iduser"));
					usermodel.setPass(resultSet.getString("password"));
					results.add(usermodel);
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}finally {
				try {
					if(connection !=null){
						connection.close();
					}
					if(statement !=null){
						statement.close();
					}
					if(resultSet !=null){
						resultSet.close();
					}
				} catch (SQLException throwables) {
				}
			}
		}
		return results;
	}

	public List<usermodel> checkAccountExist(Object... parameters) {
		String query ="select * from user  where iduser= ?";
		List<usermodel> results = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(query);
				setdb(statement,parameters);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					usermodel usermodel =new usermodel();
					usermodel.setIduser(resultSet.getString("iduser"));
					results.add(usermodel);
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}finally {
				try {
					if(connection !=null){
						connection.close();
					}
					if(statement !=null){
						statement.close();
					}
					if(resultSet !=null){
						resultSet.close();
					}
				} catch (SQLException throwables) {
				}
			}
		}
		return results;
	}

	public void signup(String jdbcUsername, String jdbcPassword) {
		String query ="insert into user\n" +
				"values (?,?);";
		List<usermodel> results = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
		statement = connection.prepareStatement(query);
		resultSet = statement.executeQuery();
		usermodel usermodel =new usermodel();
		usermodel.setIduser(resultSet.getString("iduser"));
		usermodel.setPass(resultSet.getString("password"));
			results.add(usermodel);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


	public void insertnote(Object... parameters) throws SQLException {
		String query = "insert into notes\n" +
				"values (?,?,?,?);";
		Connection connection = getConnection();
		PreparedStatement statement = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(query);
				setdb(statement, parameters);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
				if (statement != null) {
					statement.close();
				}
			}
		}
	}

	private void setdb(PreparedStatement statement ,Object... parameters) {
		try {
			for (int i =0;i< parameters.length;i++){
				Object t =parameters[i];
				int index =i+1;
				if(t instanceof Integer){
					statement.setInt(index, (Integer)parameters[i]);
				}
				else if(t instanceof String){
					statement.setString(index,(String) parameters[i]);
				}
				else if(t instanceof Timestamp){
					statement.setTimestamp(index, (Timestamp) parameters[i]);
				}
				else if(t instanceof FileInputStream){
					statement.setBinaryStream(index,(FileInputStream) parameters[i]);
				}

			}
		}catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public Notemodel selectNote(String id) {
		String query = "select * from notes\n" +
				"where id = ?";
		Notemodel notemodel = new Notemodel();
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			preparedStatement.setString(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				notemodel.setTitle(title);
				notemodel.setContent(content);
			} return notemodel;
		} catch (SQLException e) {
			printSQLException(e);
		}
		return null;
	}

	public List<Notemodel> selectAllNotes() {
		List<Notemodel> notes = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return notes;
	}

	public boolean deleteNote(String id) throws SQLException {
		String query = "delete from notes\n" +
				"where id = ?";
		boolean rowDeleted;
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(DELETE_NOTES_SQL);) {
			statement.setString(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateNote(String id, String title, String content) throws SQLException {
		String query = "update notes\n" +
				"set title = ?, content = ?\n" +
				"where id = ?;";
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, title);
			statement.setString(2, content);
			statement.setString(3, id);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}