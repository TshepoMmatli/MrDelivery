package com.tshepommatli.controller;

import com.tshepommatli.model.Menu;
import com.tshepommatli.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MenuController {
	
    @Autowired
    private MenuService menuService ;

    @RequestMapping(value="/menuPage", method = RequestMethod.GET)
    public ModelAndView menuPage(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("menuPage");
            return modelAndView;
    }
    
    //Get A Specific Menu
    @RequestMapping(value = "/GetMenu/{restaurantId}", method = RequestMethod.GET)
    public List<Menu> getMenu(@PathVariable(value = "restaurantId") Long restaurantId){
        return menuService.getMenus(restaurantId);
    }
    
    //Get A Specific Menu
    @RequestMapping(value = "/GetMenu/{restaurantId}/{sectionName}", method = RequestMethod.GET)
    public List<Menu> getMenu(@PathVariable(value = "restaurantId") Long restaurantId,
            @PathVariable(value = "sectionName") String sectionName){
        return menuService.getMenus(restaurantId, sectionName);
    }
    
    //Save item to menu
    @RequestMapping(value="/SaveMenuItem", method = RequestMethod.POST)
    public void addMenuItem(@RequestBody Menu menu){
            menuService.addMenuItem(menu);
    } 
}
