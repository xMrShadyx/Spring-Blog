package com.hhyusein.Blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String postTitle;

    @OneToOne
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "fk_topic_id"))
    private PostTopic topic_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user_id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createDate;

    @Length
    @NotNull
    @Length(min = 10, max = 255, message = "Your message should in range between 10 and 255 characters")
    private String message;
}
