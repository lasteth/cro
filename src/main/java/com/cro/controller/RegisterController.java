package com.cro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.cro.model.user.Buyer;
import com.cro.services.BuyerService;

@Controller()
@RequestMapping("/register")  
public class RegisterController {
	
	//jpa用@Resource 这的用的是spring的annotation
	@Autowired
    @Qualifier("BuyerService")
	private BuyerService buyerService;

	public BuyerService getBuyerService() {
		return buyerService;
	}

	public void setBuyerService(BuyerService buyerService) {
		this.buyerService = buyerService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "user/userReg";
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid Buyer buyer, RedirectAttributes redirectAttributes) {
		buyerService.registerBuyer(buyer);
		redirectAttributes.addFlashAttribute("username", buyer.getUsername());
		return "user/logon";
	}
    
	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("userName") String loginName) {
		if (buyerService.findUserByLoginName(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}
    

    /* 
     * Restful模式路径： 
     * 注意这里/update/{id}和@PathVariable("id")中id要一致，这样不管用debug模式还是release模式编译都没问题 
     * 也可以简写成@PathVariable int id，但这样只能以debug模式编译的时候正确，如果用release编译就不正确了，因为如果用release模式编译会把参数的名字改变的 
     * 一般IDE工具都是以debug模式编译的，javac是以release模式编译的 
     * 同样的请求路径 user/update 如果是get请求就转到增加页面去，如果是post请求就做update操作 
     */  
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)  
    public String toUpdate(@PathVariable("id") int id, Model model) throws Exception{  


        return "user/update";  
    }  
    @RequestMapping(value="/update/{id}",method=RequestMethod.POST)  
    public String doUpdate(@PathVariable("id") int id, Buyer user,Model model) throws Exception{  


        //return "redirect:../list";   
        //重定向，防止重复提交，以“/”开关，相对于当前项目根路径，不以“/”开关，相对于当前路径  
        return "redirect:/user/userlist";   
    }  
      
    @RequestMapping(value="/delete/{id}")  
    public String delete(@PathVariable("id") int id,Model model)throws Exception{  


        return "redirect:/user/userlist";//重定向  
    }  
}
