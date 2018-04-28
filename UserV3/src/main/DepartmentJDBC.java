package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DepartmentJDBC{

	@SuppressWarnings("unused")
	private DataSource ds;
	private JdbcTemplate jdbcObj;
	
	public class DepartmentMapper implements RowMapper<Department> {
		public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		   Department department = new Department();
		   department.setID(rs.getInt("ID"));
		   department.setCur_status(rs.getString("cur_status"));
		   department.setDepartment_name(rs.getString("department_name"));
		   department.setUpdate_time(rs.getString("update_time"));
		   return department;
		}
	}
	
	public void setDs(DataSource ds){
		this.ds = ds;
		this.jdbcObj = new JdbcTemplate(ds);
	}
	
	public int create(String department_name,String cur_status,String update_time){
		String SQL="Insert into department(department_name,cur_status,update_time) values (?,?,?)";
		System.out.println("Inserted department"+department_name);
		try{
			return jdbcObj.update(SQL,department_name,cur_status,update_time);
		}catch(Exception e){
			return 0;
		}
	}
	
	public void delete(int ID){
		String SQL = "DELETE FROM department WHERE ID = " + ID;
		jdbcObj.update(SQL);
		System.out.println(SQL);
	}
	
	public Department getDepartment(int ID){
		String SQL="Select * from department where ID = "+ ID;
		try{
			Department department = jdbcObj.queryForObject(SQL, new DepartmentMapper());
			System.out.println(SQL);
			return department;
		}catch (DataAccessException e){
			return null;
		}
	}
	
	public List<Department> getDepartments(String department_name){
		String SQL;
		if(department_name != null && department_name.compareTo("") != 0){
			SQL = "SELECT * from department where department_name LIKE '%" + department_name + "%'";
		}else{
			SQL = "SELECT * from department";
		}
		List<Department> result = jdbcObj.query(SQL, new DepartmentMapper());
		System.out.println(SQL);
		return result;
	}
	//if contain_stopped set to true, also contain the departments that are stopped
	public List<String> getDepartmentNames(boolean contain_stopped){
		String SQL;
		if (contain_stopped){
			SQL = "SELECT * from department";
		}else{
			SQL = "SELECT * from department where cur_status='∆Ù”√'";
		}
		System.out.println(SQL);
		List<Department> departments = jdbcObj.query(SQL, new DepartmentMapper());
		List<String> result = new ArrayList<String>();
		for (Department d: departments){
			result.add(d.getDepartment_name());
		}
		return result;
	}
	
	public int modify(String department_name, String cur_status, String update_time, int ID){
		String SQL="Update department Set department_name=?, cur_status=?, update_time=? where ID=?";
		//String SQL = "Update administrator Set username =? where ID = ?";
		System.out.println("Modified department "+department_name);
		int result;
		try{
			result = jdbcObj.update(SQL,department_name,cur_status,update_time,ID);
		}catch(Exception e){
			result = 0;
		}  
		return result;
	}
}
