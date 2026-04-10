package ru.rsreu.sovynhik.dairystore02.controller;

import ru.rsreu.sovynhik.dairystore02.dto.OrderForm;
import ru.rsreu.sovynhik.dairystore02.dto.FeedbackForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class FormController {

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
        model.addAttribute("message", "Заказ оформлен! С вами свяжутся по телефону: " + orderForm.getPhone());
        return "orderSuccess";
    }

    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedbackForm", new FeedbackForm());
        return "feedbackForm";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@Valid @ModelAttribute("feedbackForm") FeedbackForm feedbackForm,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "feedbackForm";
        }
        model.addAttribute("message", "Спасибо, " + feedbackForm.getName() + "! Ваш отзыв принят.");
        return "feedbackSuccess";
    }
}