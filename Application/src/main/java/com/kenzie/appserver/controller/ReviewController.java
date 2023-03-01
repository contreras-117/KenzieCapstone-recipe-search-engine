package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.ReviewCreateRequest;
import com.kenzie.appserver.controller.model.ReviewResponse;
import com.kenzie.appserver.service.ReviewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/example")
public class ReviewController {

    private ReviewService exampleService;

    ReviewController(ReviewService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> get(@PathVariable("id") String id) {
//
//        Review example = exampleService.findById(id);
//        if (example == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        ExampleResponse exampleResponse = new ExampleResponse();
//        exampleResponse.setId(example.getId());
//        exampleResponse.setName(example.getName());
//        return ResponseEntity.ok(exampleResponse);
        return null;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> addNewExample(@RequestBody ReviewCreateRequest exampleCreateRequest) {
//        Review example = exampleService.addNewExample(exampleCreateRequest.getName());
//
//        ExampleResponse exampleResponse = new ExampleResponse();
//        exampleResponse.setId(example.getId());
//        exampleResponse.setName(example.getName());
//
//        return ResponseEntity.ok(exampleResponse);
        return null;
    }
}
