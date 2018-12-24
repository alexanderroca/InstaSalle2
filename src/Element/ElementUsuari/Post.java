package Element.ElementUsuari;

import java.util.Vector;

public class Post {
    private int id;
    private long published;
    private Vector location;
    private String category;
    private String[] liked_by;
    private String[] commented_by;

    public Post() {
        location = new Vector(2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPublished() {
        return published;
    }

    public void setPublished(long published) {
        this.published = published;
    }

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getLiked_by() {
        return liked_by;
    }

    public void setLiked_by(String[] liked_by) {
        this.liked_by = liked_by;
    }

    public String[] getCommented_by() {
        return commented_by;
    }

    public void setCommented_by(String[] commented_by) {
        this.commented_by = commented_by;
    }
}
