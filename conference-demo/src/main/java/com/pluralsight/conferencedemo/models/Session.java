package com.pluralsight.conferencedemo.models;

import jakarta.persistence.*;

import java.util.List;


@Entity(name="sessions")
public class Session {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long session_id;
    private String seesion_name;
    private String session_description;
    private Integer session_length;
    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name="speaker_id")
    )
    public List<Speaker> getSpeakerList() {

        return speakerList;
    }

    public void setSpeakerList(List<Speaker> speakerList) {
        this.speakerList = speakerList;
    }

    private List<Speaker> speakerList;
    public Session(){

   }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSeesion_name() {
        return seesion_name;
    }

    public void setSeesion_name(String seesion_name) {
        this.seesion_name = seesion_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
