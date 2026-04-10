package ru.rsreu.sovynhik.dairystore03.controller;

import ru.rsreu.sovynhik.dairystore03.dto.OrderForm;
import ru.rsreu.sovynhik.dairystore03.dto.FeedbackForm;
import ru.rsreu.sovynhik.dairystore03.service.OrderService;
import ru.rsreu.sovynhik.dairystore03.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class FormController {

    private final OrderService orderService;
    private final ReviewService reviewService;

    public FormController(OrderService orderService, ReviewService reviewService) {
        this.orderService = orderService;
        this.reviewService = reviewService;
    }

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        model.addAttribute("orderForm", new OrderForm());
        return "orderForm";
    }

    @PostMapping("/order")
    public String submitOrder(@Valid @ModelAttribute("orderForm") OrderForm orderForm,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "orderForm";
        }
        try {
            orderService.placeOrder(orderForm);
            model.addAttribute("message", "Заказ оформлен! С вами свяжутся по телефону: " + orderForm.getPhone());
            return "orderSuccess";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "orderForm";
        }
    }

    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedbackForm", new FeedbackForm());
        return "feedbackForm";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@Valid @ModelAttribute("feedbackForm") FeedbackForm feedbackForm,
                                 BindingResult bindingResult,
                                 @RequestParam(value = "productName", required = false, defaultValue = "Молоко 3,2%") String productName,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "feedbackForm";
        }
        try {
            reviewService.saveReview(feedbackForm, productName);
            model.addAttribute("message", "Спасибо, " + feedbackForm.getName() + "! Ваш отзыв сохранён.");
            return "feedbackSuccess";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "feedbackForm";
        }
    }
}