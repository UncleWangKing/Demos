package cn.demo.sysuser.controller;

import cn.demo.sysuser.domain.User;
import cn.demo.sysuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserController {
	@Autowired
	IUserService userService;

	@RequestMapping("/index")
	public String index() {
		
		return "sysuser/user";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<User> list() {
		return userService.getAll();
	}
	
	@RequestMapping("/del")
	public String del(Long id) {
		
		userService.del(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping("/add")
	public String add(Long id, Model model) {
		
		if(null != id)
		{
			model.addAttribute("user", userService.get(id));
		}
		
		return "sysuser/add";
	}
	
	@RequestMapping("/save")
	public String save(User user) {
	
		if(null == user.getId())
			userService.save(user);
		else
			userService.update(user);
		
		return "redirect:/user/list";
	}
}
