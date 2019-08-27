package com.example.ui.web.controller;

import com.example.ui.service.CustomerService;
import com.example.ui.service.request.CustomerRequest;
import com.example.ui.service.response.CustomerResponse;
import com.example.ui.web.form.CustomerForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    /** 必要があれば、デバッグ時のログ出力に使ってください */
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 社員一覧画面に遷移するコントローラーメソッド。
     */
    @GetMapping("/")
    public String index(Model model) {
        List<CustomerResponse> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "index";
    }

    /**
     * 社員追加画面に遷移するコントローラーメソッド。
     */
    @GetMapping("/insertMain")
    public String insertMain(Model model) {
        // フィールドが全てnullのフォームインスタンスを追加する
        model.addAttribute(new CustomerForm());
        return "insertMain";
    }

    /**
     * 社員の追加を行うコントローラーメソッド。
     */
    @PostMapping("/insertComplete")
    public String insertComplete(
            @Validated CustomerForm customerForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insertMain";
        }
        // フォームをエンティティに変換
        CustomerRequest customerRequest = customerForm.convertToEntity();
        customerService.insert(customerRequest);
        return "redirect:/";
    }
}