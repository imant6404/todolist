package com.coursework.todolist.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(/*cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH}*/cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author commentAuthor;

    @Column(name = "comment_text")
    private String commentText;

    public Comment() {
    }

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public Comment(Task task, Author commentAuthor, String commentText) {
        this.task = task;
        this.commentAuthor = commentAuthor;
        this.commentText = commentText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Author getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(Author author) {
        this.commentAuthor = author;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", task=" + task +
                ", author=" + commentAuthor +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
