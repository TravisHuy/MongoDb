package org.nhathuy.monogo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "notifications")
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String id;
    private String title;
    private String content;
    private String imageData;
    private String hotline;
    private Date date;
}
