package main;

import java.sql.*;
import java.util.List;
import javax.sql.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeJDBC {
	@SuppressWarnings("unused")
	private DataSource ds;
	private JdbcTemplate jdbcObj;
	
	public class EmployeeMapper implements RowMapper<Employee> {
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Employee employee = new Employee();
		   employee.setID(rs.getInt("ID"));
		   employee.setDepartment(rs.getString("department"));
		   employee.setEmployeeID(rs.getString("employeeID"));
		   employee.setRealname(rs.getString("realname"));
		   employee.setUsername( rs.getString("username"));
		   employee.setPass(rs.getString("pass"));
		   employee.setCellphone(rs.getString("cellphone"));
		   employee.setAddress(rs.getString("address"));
		   employee.setUpdate_time(rs.getString("update_time"));
		   return employee;
		}
	}

	public void setDs(DataSource ds){
		this.ds = ds;
		this.jdbcObj = new JdbcTemplate(ds);
	}
	
	public int create(String username,String pass,String employeeID,String cellphone,String address,String update_time,String department,String realname){
		String SQL="Insert into administrator(username,pass,employeeID,cellphone,address,update_time,department,realname) values (?,?,?,?,?,?,?,?)";
		System.out.println("Inserted Employee"+realname);
		int result;
		try{
			result = jdbcObj.update(SQL,username,pass,employeeID,cellphone,address,update_time,department,realname);
		}catch(Exception e){
			result = 0;
		}
		return result;
	}
	
	public int modify(int ID, String username,String pass,String employeeID,String cellphone,String address,String update_time,String department,String realname){
		String SQL="Update administrator Set username=?, pass=?, employeeID=?, cellphone=?, address=?, update_time=?, department=?, realname=? where ID=?";
		//String SQL = "Update administrator Set username =? where ID = ?";
		System.out.println("Modified Employee "+realname);
		int result;
		try{
			result = jdbcObj.update(SQL,username,pass,employeeID,cellphone,address,update_time,department,realname,ID);
			//result = jdbcObj.update(SQL, username, ID);
		}catch(Exception e){
			result = 0;
		}  
		return result;
	}
	
	public Employee getEmployee(int ID){
		String SQL="Select * from administrator where ID = "+ ID;
		try{
			Employee employee = jdbcObj.queryForObject(SQL, new EmployeeMapper());
			System.out.println(SQL);
			return employee;
		}catch (DataAccessException e){
			return null;
		}
	}
	
	public Employee getEmployee(String username){
		String SQL="Select * from administrator where username = '"+ username + "'";
		try{
			Employee employee = jdbcObj.queryForObject(SQL, new EmployeeMapper());
			System.out.println(SQL);
			return employee;
		}catch(DataAccessException e){
			return null;
		}
	}
	
	public List<Employee> getEmployees(String employeeID, String department, String realname){
		String SQL = "SELECT * from administrator";
		if(employeeID != null && realname != null && department!=null){
 			int ecomp = employeeID.compareTo("");
 			int rcomp = realname.compareTo("");
 			int dcomp = department.compareTo("default_d");
 			if (ecomp != 0 || rcomp != 0 || dcomp != 0){
 				SQL += " where";
 				if(ecomp != 0){
 					SQL += " employeeID='" + employeeID + "'";
 					if (rcomp != 0 || dcomp != 0)
 						SQL += " AND";
 				}
 				if(rcomp != 0 ){
 					SQL += " realname LIKE '%" + realname + "%'";
 					if (dcomp != 0){
 						SQL += " AND";
 					}
 				}
 				if(dcomp != 0){
 					SQL += " department='"+department+"'";
 				}
 			}
		}
		List<Employee> result = jdbcObj.query(SQL, new EmployeeMapper());
		System.out.println(SQL);
		return result;
	}
	public int delete(int ID){
		String SQL = "DELETE FROM administrator WHERE ID =" + ID;
		System.out.println(SQL);
		int result;
		try{
			result = jdbcObj.update(SQL);
		}catch(Exception e){
			result = 0;
		}
		return result;
	}
}
