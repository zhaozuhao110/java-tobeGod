package god.github.haoer.godbase.reflection;

import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
