package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-14T11:30:33")
@StaticMetamodel(Region.class)
public class Region_ { 

    public static volatile SingularAttribute<Region, Integer> deleted;
    public static volatile SingularAttribute<Region, Date> created;
    public static volatile SingularAttribute<Region, Date> modified;
    public static volatile SingularAttribute<Region, Integer> id;
    public static volatile SingularAttribute<Region, String> nom;
    public static volatile SingularAttribute<Region, Integer> pays;

}