package payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetCategoryObject {
    int id;
    String name;

    public PetCategoryObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
