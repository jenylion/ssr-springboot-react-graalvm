package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsrController {

    @Autowired
    private ReactRenderer reactRenderer;

    @GetMapping("/render")
    public String render(@RequestParam String component, @RequestParam String props) {
        try {
            return reactRenderer.renderComponent(component, props);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error rendering component: " + e.getMessage();
        }
    }
}
