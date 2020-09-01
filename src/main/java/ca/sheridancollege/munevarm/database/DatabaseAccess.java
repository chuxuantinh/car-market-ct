package ca.sheridancollege.munevarm.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.munevarm.beans.Car;
import ca.sheridancollege.munevarm.beans.Manufacturer;
import ca.sheridancollege.munevarm.beans.User;

@Repository
public class DatabaseAccess {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addCar(Long manufacturerID, String carModel, int year, String color, double price) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO car (manufacturerID, carModel, year, color, price) "
						+ "VALUES (:manufacturerID, :carModel, :year, :color, :price)";
		parameters.addValue("manufacturerID", manufacturerID);
		parameters.addValue("carModel", carModel);
		parameters.addValue("year", year);
		parameters.addValue("color", color);
		parameters.addValue("price", price);
		
		checkAndUpdate(query, parameters, "added");
	}
	
	public void updateCar(Car car) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE car SET carModel = :carModel, price = :price WHERE carID = :carID";
		parameters.addValue("carID", car.getCarID());
		parameters.addValue("carModel", car.getCarModel());
		parameters.addValue("price", car.getPrice());
		
		checkAndUpdate(query, parameters, "updated");
	}
	//Create an user with the given name and password
	public void addUser(String username, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user (username, encryptedPassword, enabled)"
							+ "VALUES (:username, :encryptedPassword, 1)";
		parameters.addValue("username", username);
		parameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, parameters);
	}
	//Add a role to the user, all users registered will be regular users. The roleId is kept
	//as a variable here to be able to add an ADMIN user when the server starts.
	public void addRole(Long userID, Long roleID) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userID, roleID) VALUES (:userID, :roleID)";
		parameters.addValue("userID", userID);
		parameters.addValue("roleID", roleID);
		jdbc.update(query, parameters);
	}
	
	public void deleteCar(Long carID) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM car WHERE carID = :carID";
		parameters.addValue("carID", carID);
		
		checkAndUpdate(query, parameters, "updated");
	}
	//Find a car by id
	public List<Car> getCarById(Long carID) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM car WHERE carID = :carID";
		parameters.addValue("carID", carID);
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Car>(Car.class));
	}
	//Get all cars in the database
	public List<Car> getCars() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM car";
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Car>(Car.class));
	}
	//Get all manufacturers in the database
	public List<Manufacturer> getManufacturers() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM manufacturer";
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Manufacturer>(Manufacturer.class));
	}
	//To check if the record was added, updated or deleted.
	private void checkAndUpdate(String query, MapSqlParameterSource parameters, String action) {
		int rowsAffected = jdbc.update(query, parameters);
		if(rowsAffected > 0) {
			System.out.println("Car " + action + " succesfully");
		}
	}
	//Find account with given username
	public User findUserAccount(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user WHERE username = :username";
		parameters.addValue("username", username);
		//Find the first record that matches the username
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));		
		if(users.size() > 0)
			return users.get(0);
		else
			return null;
	}
	//Find roles of user given UserID
	public List<String> getRolesByUserID(Long userID) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userID, sec_role.roleName "
						+ "FROM user_role, sec_role "
						+ "WHERE user_role.roleID = sec_role.roleID AND userID = :userID";
		parameters.addValue("userID", userID);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for(Map<String, Object> row: rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
}
