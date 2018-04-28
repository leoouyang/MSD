package main;


import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class UserValidator implements Validator{
	private EmployeeJDBC employeeJDBC;
	
	/**
	 * @param employeeJDBC the employeeJDBC to set
	 */
	public void setEmployeeJDBC(EmployeeJDBC employeeJDBC) {
		this.employeeJDBC = employeeJDBC;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz); 
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub\
		System.out.println("Validating user");
		User user = (User)obj;
		String username = user.getUsername();
		String pass = user.getPass();
		if(username.compareTo("") != 0){
			if(this.employeeJDBC == null){
				System.out.println("Should not happen, DI error");
				return;
			}
			Employee employee = employeeJDBC.getEmployee(username);
			if(employee == null){
				//wrong username
				errors.rejectValue("username", null, null, "用户名错误");
				System.out.println(username + "wrong username");
			}else{
				if (employee.getPass().compareTo(pass) != 0){
					//wrong pass
					errors.rejectValue("pass", null, null, "密码错误");
					System.out.println(username + ": " + pass + "Invalid password");
				}else{
					
					System.out.println(username + "success");
				}
			}
		}else{
			//empty username
			errors.rejectValue("username", null, null, "用户名为空");
			System.out.println(username + "empty username");
		}
		return;
	}
}
