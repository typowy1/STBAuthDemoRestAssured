package jsonplaceholder;

import lombok.Data;

@Data //adnotacja z lomboka, automatycznie wygenerował nam getery setery konstruktor
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

}
