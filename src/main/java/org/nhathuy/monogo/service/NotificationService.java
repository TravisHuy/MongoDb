package org.nhathuy.monogo.service;

import org.nhathuy.monogo.model.Notification;
import org.nhathuy.monogo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification getNotificationById(String id){
        return notificationRepository.findById(id).orElse(null);
    }
    public Notification saveNotification(String title, String content, MultipartFile imageFile,String hotline) throws IOException {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setContent(content);
        notification.setHotline(hotline);
        notification.setDate(new Date());

        if (imageFile !=null && !imageFile.isEmpty()){
            String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
            notification.setImageData(base64Image);
        }
        return notificationRepository.save(notification);
    }
    public List<Notification> getAllProducts(){
        return notificationRepository.findAll();
    }
    public Notification editNotification(String id,String title, String content, MultipartFile imageFile,String hotline) throws IOException {
        Notification updateNotification = getNotificationById(id);
        if(updateNotification!=null){
            updateNotification.setTitle(title);
            updateNotification.setContent(content);
            updateNotification.setHotline(hotline);
            updateNotification.setDate(new Date());

            if (imageFile !=null && !imageFile.isEmpty()){
                String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
                updateNotification.setImageData(base64Image);
            }
            return notificationRepository.save(updateNotification);
        }
        return null;
    }
    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }
}
