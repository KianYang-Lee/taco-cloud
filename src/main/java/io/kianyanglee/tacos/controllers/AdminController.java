package io.kianyanglee.tacos.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.kianyanglee.tacos.services.AdminService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String adminPanel() {
        return "admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete-orders")
    public String deleteAllOrders() {
        log.info("delete all orders");
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
