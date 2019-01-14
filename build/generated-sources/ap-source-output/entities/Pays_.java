package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-14T11:30:33")
@StaticMetamodel(Pays.class)
public class Pays_ { 

    public static volatile SingularAttribute<Pays, Integer> deleted;
    public static volatile SingularAttribute<Pays, Date> created;
    public static volatile SingularAttribute<Pays, Date> modified;
    public static volatile SingularAttribute<Pays, Integer> id;
    public static volatile SingularAttribute<Pays, String> iso2;
    public static volatile SingularAttribute<Pays, String> nom;
    public static volatile SingularAttribute<Pays, Integer> devise;
    public static volatile SingularAttribute<Pays, String> iso3;

}