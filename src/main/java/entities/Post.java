package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "publications")
public class Post implements Comparable<Post>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "publications_id_seq")
    @Column(name = "id")
    private int postId;

    private String word;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner_id;

    private LocalDateTime date;

    @ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "likes",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )

    private Set<User> liked = new HashSet<>();

    public Set<User> getLiked() {
        return liked;
    }

    public void setLiked(Set<User> liked) {
        this.liked = liked;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getOwner_id() {
        return owner_id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setOwner_id(User owner_id) {
        this.owner_id = owner_id;
    }

    public Post() {
        date = LocalDateTime.now();
    }

    public Post(String word) {
        this.word = word;
        date = LocalDateTime.now();
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return  "Time: " + date.getHour() + ":" + date.getMinute();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId &&
                Objects.equals(owner_id, post.owner_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, owner_id);
    }

    @Override
    public int compareTo(Post o) {
        return o.getDate().compareTo(date);
    }
}
