package jsonplaceholder.twoobjects;

import jsonplaceholder.Geo;
import lombok.Data;

import java.util.List;

@Data
public class TOCompany {

    private String name;
    private String catchPhrase;
    private String bs;
    private TOGeo toGeo;
}
