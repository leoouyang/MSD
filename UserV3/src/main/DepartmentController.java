package main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentJDBC departmentJDBC;
	

	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public ModelAndView department(String department_name, ModelMap m){
		ModelAndView mav = new ModelAndView("Department", m);
		List<Department> departments = departmentJDBC.getDepartments(department_name);
		m.addAttribute("departments", departments);
		return mav;
	}
	
	@RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
	public String addDepartment(ModelMap m){
		return "Add_d";
	}
	@RequestMapping(value = "/addDepartmentAction", method = RequestMethod.POST)
	public String addDepartment_action(String department_name, String cur_status, String update_time){
		int result = departmentJDBC.create(department_name, cur_status, update_time);
		if (result > 0){
			return "Close";
		}else{
			return "SQLError_d";
		}
	}

	@RequestMapping(value = "/modifyDepartment", method = RequestMethod.GET)
	public ModelAndView modifyDepartment(ModelMap m, int ID){
		ModelAndView mav = new ModelAndView("Modify_d", m);
		Department department = departmentJDBC.getDepartment(ID);
		m.addAttribute("department", department);
		return mav;
	}
	
	@RequestMapping(value = "/modifyDepartmentAction", method = RequestMethod.POST)
	public String modifyDepartment_action(String department_name, String cur_status, String update_time, int ID){
		int result = departmentJDBC.modify(department_name, cur_status, update_time, ID);
		if (result > 0){
			return "Close";
		}else{
			return "SQLError_d";
		}
	}
	
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDepartment(int[] IDs){
		for(int ID: IDs){
			departmentJDBC.delete(ID);
		}
		return "success";
	}
}
