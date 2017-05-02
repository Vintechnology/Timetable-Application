package com.example.android.androiddev;

/**
 * Created by Admin on 11/2/2017.
 */

public class Subject {
    // TODO: 4/3/2017 add Id for each subject 
    private String name;
    private String timeFrom;
    private String timeTo;
    private int id;
    /**
     * @param name name of the subject
     * @param timeFrom time the subject start
     * @param timeTo time the subject end
     */
    public Subject(String name,String timeFrom,String timeTo){
        this.name=name;
        this.timeFrom=timeFrom;
        this.timeTo=timeTo;

    }

    public Subject(String name,String timeFrom,String timeTo,int id){
        this.name=name;
        this.timeFrom=timeFrom;
        this.timeTo=timeTo;
        this.id=id;

    }

    /**
     *
     * @return name of subject
     */
    public String getName(){
        return name;
    }
    public void setName(String newName){
        this.name=newName;
    }

    /**
     *
     * @return time the subject star and end
     */
    public String getTimeFrom(){
        return timeFrom;
    }
    public void setTimeFrom(String newTimeFrom){
        this.timeFrom=newTimeFrom;
    }
    public String getTimeTo(){
        return timeTo;
    }
    public void setTimeTo(String newTimeTo){
        this.timeTo=newTimeTo;
    }
    public int getId(){
    return id;
    }
    public void setId(int newId){
        id=newId;
    }
}
