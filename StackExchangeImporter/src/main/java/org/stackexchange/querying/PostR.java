package org.stackexchange.querying;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="post")
@Data
public class PostR {

    public static final String ACCEPTED_ANSWER_ID = "ACCEPTED_ANSWER_ID";
    public static final String ID = "id";
    public static final String TAGS_DELIMITER = ";";

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private List<CommentR> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private UserR user;

    @OneToOne
    @JoinColumn(name = "owner_user_id")
    private PostR post;

    private String body;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ACCEPTED_ANSWER_ID)
    private PostR answer;

    @Id
    public int id;

    public int score = 0;


    private String tags;

    public String[] getTags() {
        return tags.split(TAGS_DELIMITER);
    }
    public void setTags(String[] tags) {
        this.tags = String.join(TAGS_DELIMITER, tags);
    }
}

