package sample;

import java.util.ArrayList;

public class User {
     int type;  //1-adult, 2-minor
     int userid;
     String password;
     String name;
     float weight;
     float height;
     float BMI;
     ArrayList<Workout> workoutsDone;

     public User(int userID,int type,String name,ArrayList<Workout> workouts, float weight, float height,String password){
         this.name=name;
         workoutsDone=workouts;
         this.type=type;
         this.userid=userID;
         this.height=height;
         this.password=password;
         this.weight=weight;
         calculateBMI();
     }

     void calculateBMI(){
         BMI=weight/(height*height);
     }




}
