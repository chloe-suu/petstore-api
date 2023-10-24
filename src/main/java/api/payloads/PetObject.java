package api.payloads;

import java.util.List;

public class PetObject {
    int id;
    PetCategoryObject category;
    String name;
    List<String> photoURLs;
    List<PetTagObject> tags;
    String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PetCategoryObject getCategory() {
        return category;
    }

    public void setCategory(PetCategoryObject category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoURLs() {
        return photoURLs;
    }

    public void setPhotoURLs(List<String> photoURLs) {
        this.photoURLs = photoURLs;
    }

    public List<PetTagObject> getTags() {
        return tags;
    }

    public void setTags(List<PetTagObject> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
