package Element.ElementUsuari;

public class Connection {
    private String username;
    private long since;
    private int visits;
    private int likes;
    private int comments;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getSince() {
        return since;
    }

    public void setSince(long since) {
        this.since = since;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
