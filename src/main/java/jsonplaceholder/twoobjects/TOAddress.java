package jsonplaceholder.twoobjects;

import jsonplaceholder.Geo;
import lombok.Data;

import java.util.List;

@Data
public class TOAddress {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private TOGeo toGeo;
}
