package com.example.demo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/calculadora")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello, world!";
    }

    /* Exemplo extra
    @GetMapping(value = "/somar/{valor1}/{valor2}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String somar(@PathVariable double valor1, @PathVariable double valor2) {
        double resultado = valor1 + valor2;
        return String.valueOf(resultado);
    }
     */

    @GetMapping(value = "/somar/{valor1}/{valor2}")
    public double somar(@PathVariable double valor1, @PathVariable double valor2) {
        return valor1 + valor2;
    }

    @GetMapping("/subtrair/{valor1}/{valor2}")
    public double subtrair(@PathVariable double valor1, @PathVariable double valor2) {
        return valor1 - valor2;
    }

    @GetMapping("/multiplicar/{valor1}/{valor2}")
    public double multiplicar(@PathVariable double valor1, @PathVariable double valor2) {
        return valor1 * valor2;
    }

    @GetMapping("/dividir/{valor1}/{valor2}")
    public double dividir(@PathVariable double valor1, @PathVariable double valor2) {
        return valor1 / valor2;
    }
}
