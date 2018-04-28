package main;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController{
	private static int IDgenerator = 0;
	
    @Autowired
    @Qualifier(value="userValidator")
	private Validator validator;
    
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		return "Login";
	}
	
	@InitBinder  
    public void initBinder(DataBinder binder) {  
       binder.setValidator(validator);  
    }  
	
	@RequestMapping(value = "/loginValidation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8" )
	@ResponseBody
	public String loginValidation(@Validated User user, BindingResult result, HttpSession session){
		//Map<String,Object> resultMap = new HashMap<String, Object>(); 
		String msg;
		if(result.hasErrors()){
			FieldError fe = result.getFieldErrors().get(0);
			//resultMap.put("result", fe.getDefaultMessage());
			msg = fe.getDefaultMessage();
		}else{
			//resultMap.put("result", "success");
			msg = "success";
			IDgenerator++;
			session.setAttribute("SessionID", IDgenerator);
		}
		//return resultMap;
		return msg;
	}
}
