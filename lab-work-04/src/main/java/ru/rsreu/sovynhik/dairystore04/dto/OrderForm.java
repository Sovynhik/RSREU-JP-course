package ru.rsreu.sovynhik.dairystore04.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OrderForm {

    @NotBlank(message = "Имя не может быть пустым")
    private String customerName;

    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(regexp = "\\d+", message = "Телефон должен содержать только цифры")
    private String phone;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Введите корректный email")
    private String email;

    @NotBlank(message = "Выберите продукт")
    private String product;

    @NotBlank(message = "Укажите количество")
    @Pattern(regexp = "\\d+", message = "Количество должно быть целым положительным числом")
    private String quantity;

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
}