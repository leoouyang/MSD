package main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeJDBC employeeJDBC;
	
	@Autowired
	private DepartmentJDBC departmentJDBC;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ModelAndView employee(String employeeID, String realname, String department, ModelMap m){
		List<Employee> result = employeeJDBC.getEmployees(employeeID, department, realname);
		List<String> departments = departmentJDBC.getDepartmentNames(true);
		ModelAndView mav = new ModelAndView("Employee", m);
		m.addAttribute("table_info", result);
		m.addAttribute("departments", departments);
		return mav;
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public ModelAndView addEmployee(ModelMap m){
		ModelAndView mav = new ModelAndView("Add", m);
		List<String> departments = departmentJDBC.getDepartmentNames(false);
		m.addAttribute("departments", departments);
		return mav;
	}
	
	@RequestMapping(value = "/addEmployeeAction", method = RequestMethod.POST)
	public String addEmployee_action(String employeeID, String realname, String username, String pass, String department, String cellphone, String address, String update_time){
		int result = employeeJDBC.create(username, pass, employeeID, cellphone, address, update_time, department, realname);
		if (result > 0){
			return "Close";
		}else{
			return "SQLError";
		}
	}
	@RequestMapping(value = "/modifyEmployee", method = RequestMethod.GET)
	public ModelAndView modifyEmployee(ModelMap m, int ID){
		ModelAndView mav = new ModelAndView("Modify", m);
		List<String> departments = departmentJDBC.getDepartmentNames(false);
		m.addAttribute("departments", departments);
		Employee employee = employeeJDBC.getEmployee(ID);
		m.addAttribute("employee", employee);
		return mav;
	}
	@RequestMapping(value = "/modifyEmployeeAction", method = RequestMethod.POST)
	public String modifyEmployee_action(int ID, String employeeID, String realname, String username, String pass, String department, String cellphone, String address, String update_time){
		int result = employeeJDBC.modify(ID, username, pass, employeeID, cellphone, address, update_time, department, realname);
		if (result > 0){
			return "Close";
		}else{
			return "SQLError";
		}
	} 
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String deleteEmployee(int[] IDs){
		for(int ID: IDs){
			employeeJDBC.delete(ID);
		}
		return "success";
	}
}
