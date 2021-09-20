package jsonplaceholder.twoobjects;

import lombok.Data;

@Data
public class TOUser {
    private Integer id;
    private String name;
    private TOAddress toAddress;
    private TOCompany toCompany;
}
