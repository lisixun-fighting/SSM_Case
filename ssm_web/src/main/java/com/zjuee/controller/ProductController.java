package com.zjuee.controller;

import com.zjuee.domain.Product;
import com.zjuee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
//    @RolesAllowed("ADMIN")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        System.out.println(products);
        mv.addObject("productList",products);
        mv.setViewName("product-list1");
        return mv;
    }

    /**
     * 产品添加
     * @param product
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(Product product) {
        productService.save(product);
//        request.getRequestDispatcher("/product/findAll").forward(request,response);
        return "redirect:findAll";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(String productIds){
        System.out.println(productIds);
        String[] ids = productIds.split("&");
        if(ids == null || ids.length == 0){
            ids = new String[1];
            ids[0] = productIds;
        }
        System.out.println(Arrays.toString(ids));
        for (String id : ids) {
            productService.delete(id);
        }
        return "redirect:findAll";
    }
}
