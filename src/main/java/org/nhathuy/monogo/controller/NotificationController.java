package org.nhathuy.monogo.controller;

import org.nhathuy.monogo.model.Notification;
import org.nhathuy.monogo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("travishuy/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hồ Nhật Huy");
    }
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications(){
        List<Notification> result = notificationService.getAllProducts();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable String id){
        Notification result = notificationService.getNotificationById(id);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/add")
    public ResponseEntity<Notification> addNotification(@RequestParam String title, @RequestParam String content, @RequestParam(required = false)MultipartFile imageFile,@RequestParam String hotline){
        try{
            Notification notification = notificationService.saveNotification(title, content, imageFile, hotline);
            return new ResponseEntity<>(notification,HttpStatus.CREATED);
        }
        catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Notification> editNotification(@PathVariable String id,@RequestParam String title, @RequestParam String content, @RequestParam(required = false)MultipartFile imageFile,@RequestParam String hotline){
        try{
            Notification updatedNotification = notificationService.editNotification(id,title, content, imageFile, hotline);
            return updatedNotification != null ? new ResponseEntity<>(updatedNotification, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable String id){
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
