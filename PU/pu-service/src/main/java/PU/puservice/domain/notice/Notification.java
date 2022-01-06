package PU.puservice.domain.notice;

import lombok.Data;

@Data
public class Notification {
    private Long id;
    private String title;
    private int view_count;
    private String body;

    public Notification(Long id, String title, int view_count, String body) {
        this.id = id;
        this.title = title;
        this.view_count = view_count;
        this.body = body;
    }
}
