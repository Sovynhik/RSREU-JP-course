package ru.rsreu.sovynhik.dairystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<String> products = Arrays.asList(
                "Молоко 3,2% «Домик в деревне» — 65 ₽/л",
                "Сыр «Гауда» 45% — 480 ₽/кг",
                "Йогурт питьевой «Чудо» клубника — 42 ₽/шт",
                "Творог 5% «Простоквашино» — 120 ₽/уп"
        );

        model.addAttribute("title", "Магазин молочной продукции");
        model.addAttribute("welcomeMessage", "Добро пожаловать! Здесь вы найдёте свежие молочные продукты.");
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О магазине");
        model.addAttribute("welcomeMessage", "Наш магазин работает с 2015 года и предлагает только натуральную молочную продукцию.");
        return "about";
    }
}