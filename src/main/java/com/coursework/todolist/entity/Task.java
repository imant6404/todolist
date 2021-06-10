package com.coursework.todolist.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "comment_count")
    private int commentCount;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
    }, targetEntity = Author.class)
    @JoinTable(name = "member",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> memberTaskList;

    public Task() {
    }

    public Task(int id, String text) {
        this.id = id;
        this.taskDescription = text;
    }

    public Task(String text) {
        this.taskDescription = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String text) {
        this.taskDescription = text;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Author> getMemberTaskList() {
        return memberTaskList;
    }

    public void setMemberTaskList(List<Author> otherTaskList) {
        this.memberTaskList = otherTaskList;
    }

    public void addComment(Comment comment) {
        if(comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    public void addMemberInTask(Author author) {
        if (memberTaskList == null) {
            memberTaskList = new ArrayList<>();
        }
        memberTaskList.add(author);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
