package ru.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.itis.dtos.ExerciseDto;
import ru.itis.dtos.ExerciseResponseDto;
import ru.itis.dtos.ProductDto;
import ru.itis.dtos.ProductResponseDto;
import ru.itis.models.Day;
import ru.itis.models.Exercise;
import ru.itis.models.Product;
import ru.itis.services.DayService;
import ru.itis.services.ExerciseAddingService;
import ru.itis.services.ExerciseService;
import ru.itis.services.ProductService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Controller()
public class DayController {

    @Autowired
    private ExerciseAddingService exerciseAddingService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private DayService dayService;

    @Autowired
    private ProductService productService;


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add_product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addProduct(ProductDto productDto) throws JsonProcessingException, MalformedURLException {
        Product product = productService.findByName(productDto.getProduct());
        ObjectMapper objectMapper = new ObjectMapper();
        if (product != null) {
            ProductResponseDto responseDto = ProductResponseDto.builder()
                    .product(product.getName())
                    .amount(productDto.getAmount())
                    .calories(product.getCalories())
                    .protein(product.getProtein())
                    .carbs(product.getCarbs())
                    .fiber(product.getFiber())
                    .build();
            return objectMapper.writeValueAsString(responseDto);
        } else {
            URL url = new URL("https://api.calorieninjas.com/v1/nutrition?query=" + productDto.getProduct());
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("X-Api-Key", "ZA2zMW59s9AwLcOhmWUVaw==AKNApajrvklSeJwN");
                connection.connect();
                InputStream in;
                int status = connection.getResponseCode();
                if (status != HttpURLConnection.HTTP_OK) {
                    in = connection.getErrorStream();
                } else {
                    in = connection.getInputStream();
                }
                String response = convertStreamToString(in);
                Map<String, String> map = objectMapper.readValue(response, Map.class);
                Product newProduct = Product.builder()
                        .name(productDto.getProduct())
                        .calories(Float.parseFloat(map.get("calories")))
                        .protein(Float.parseFloat(map.get("protein_ g")))
                        .fiber(Float.parseFloat(map.get("fiber_g")))
                        .carbs(Float.parseFloat(map.get("carbohydrates_total_g")))
                        .build();
                productService.save(newProduct);
                ProductResponseDto responseDto = ProductResponseDto.builder()
                        .product(newProduct.getName())
                        .amount(productDto.getAmount())
                        .calories(newProduct.getCalories())
                        .protein(newProduct.getProtein())
                        .carbs(newProduct.getCarbs())
                        .fiber(newProduct.getFiber())
                        .build();
                return objectMapper.writeValueAsString(responseDto);
            } catch (IOException e) {
                //log
                return objectMapper.writeValueAsString("");
            }
        }
    }

    private String convertStreamToString(InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        stream.close();

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add_exercise", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addExercise(@RequestBody() ExerciseDto exerciseDto) throws JsonProcessingException {
        Exercise exercise = exerciseService.getById(Long.parseLong(exerciseDto.getKind()));
        Day day = dayService.findDayById(Long.parseLong(exerciseDto.getDayId()));
        ObjectMapper objectMapper = new ObjectMapper();
        if (exercise != null & day != null) {
            Float burnedCalories = exerciseDto.getDuration() * exercise.getEnergyBurnedForMin();
            ExerciseResponseDto dto = exerciseAddingService.addExercise(day, exerciseDto, exercise);
            dayService.addBurnedCalories(day.getId(), burnedCalories);
            return objectMapper.writeValueAsString(dto);
        }
        return objectMapper.writeValueAsString("");
    }
}
