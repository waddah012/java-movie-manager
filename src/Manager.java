import java.util.*;
import java.io.*;

// Movie class to store basic info about a movie.
class Movie{
private String title;
private String lead_actor;
private String lead_actress;
private String genre;
private String year;

//constructor to initialise the private members.
public Movie(String title , String lead_actor , String lead_actress , String genre , String year){
this.title = title;
this.lead_actor = lead_actor;
this.lead_actress = lead_actress;
this.genre = genre;
this.year = year;
}

public Movie(String title , String genre , String year){
this(title , "" , "" , genre , year);
}

public Movie(String title){
this(title , "" , "" , "" , "1970");
}

//getter fucntions.
public String get_title(){
return this.title;
}

public String get_actor(){
return this.lead_actor;
}

public String get_actress(){
return this.lead_actress;
}

public String get_genre(){
return this.genre;
}

public String get_year(){
return this.year;
}

public void print_movie(){
System.out.println(this.title + " " + this.lead_actor + " " + this.lead_actress + " " + this.genre + " " + this.year);
}
}

//MovieManager class tp manage the movies.
class MovieManager{
//private ArrayList of movie objects.
private ArrayList<Movie> movies = new ArrayList<Movie>();
private String password;
private String username;
public static Scanner in = new Scanner(System.in);

//constructor to initialise the password and username.
public MovieManager(String username , String password){
this.username = username;
this.password = password;
}

//getter functions.
public String get_username(){
return this.username;
}

public String get_password(){
return this.password;
}

// private function to add a movie into the private array of movies. ONly be accessed when password is right.
private void add_movie_core(){
String title ;
String actor ;
String actress ;
String genre;
String year ;
System.out.print("Enter the title : ");
title = in.nextLine();
System.out.print("\nEnter the actor : ");
actor = in.nextLine();
System.out.print("\nEnter the actress : ");
actress = in.nextLine();
System.out.print("\nEnter the genre : ");
genre = in.nextLine();
System.out.print("\nEnter the year : ");
year = in.nextLine();

Movie movie = new Movie(title , actor , actress, genre , year);
this.movies.add(movie);
System.out.println("New movie has been added");
}

// private function to remove a movie from the private array of movies. ONly be accessed when password is right.
private void remove_movie_core(){
//input a title.
//search for the movie in the array.
// if found , removve the object from the list,
//else print a message.
String title;
boolean is_found = false;
System.out.print("Enter the movie name to be deleted : ");
title = in.nextLine();
for(int i=0 ; i<this.movies.size() ; i++){
if(this.movies.get(i).get_title().compareTo(title) == 0){
this.movies.remove(i);
System.out.println(title + " has been deleted \n");
is_found = true;
break;
}
}
if(! is_found){
System.out.println(title + "not found \n");
}
}

//helperfunction to check if password match , if they do call in core add fucntion.
public void add_movie(String password){
if(this.password.compareTo(password) == 0){
this.add_movie_core();
}
else{
System.out.println("Entered password is wrong, cant add a movie\n");
}
}

//helperfunction to check if password match , if they do call in core remove fucntion.
public void remove_movie(String password){
if(this.password.compareTo(password) == 0){
this.remove_movie_core();
}
else{
System.out.println("Entered password is wrong, cant remove a movie\n");
}
}

//function to search for a movie in the list.
public void search_movie(){
boolean is_found = false;
System.out.print("Enter movie name : ");
String title = in.nextLine();
for(int i=0;i<this.movies.size() ; i++){
if(this.movies.get(i).get_title().compareTo(title) == 0){
Movie movie = this.movies.get(i);
movie.print_movie();
is_found = true;
break;
}
}
if(!is_found){
System.out.println("Movie" + title + " not found\n");
}
}

public void print_list(){
for (int i=0;i<this.movies.size();i++){
this.movies.get(i).print_movie();
}
}

//function to sort the list.
public void sort_movie(){
//input the attribute choice by which sorting is to be done.
//call the in-built sort function of the list , passing the comparator as attributes of both objects.
int choice;
System.out.println("Enter your choice : ");
System.out.println("1: title\n2: Actor\n3: Actress\n4: Genre\n5: Year");
choice = in.nextInt();
in.nextLine();
switch(choice){
case 1:
this.movies.sort((m1 , m2) -> m1.get_title().compareTo(m2.get_title()));
break;
case 2:
this.movies.sort((m1 , m2) -> m1.get_actor().compareTo(m2.get_actor()));
break;
case 3:
this.movies.sort((m1 , m2) -> m1.get_actress().compareTo(m2.get_actress()));
break;
case 4:
this.movies.sort((m1 , m2) -> m1.get_genre().compareTo(m2.get_genre()));
break;
case 5:
this.movies.sort((m1 , m2) -> m1.get_year().compareTo(m2.get_year()));
break;
}
for (int i=0;i<this.movies.size();i++){
this.movies.get(i).print_movie();
}
}
}

public class Manager{
public static Scanner in = new Scanner(System.in);
public static int print_menu(){
int choice;

System.out.println("***************************************************");
System.out.println("Enter your choice: ");
System.out.println("1: Add movie (administrator only)");
System.out.println("2: Remove movie (administrator only)");
System.out.println("3: Search movie ");
System.out.println("4: Sort movie ");
System.out.println("9: quit ");
if (!in.hasNextInt()) return 9;
choice = in.nextInt();
in.nextLine();
return choice;
}

public static void main(String[] args){
MovieManager.in = in;
MovieManager manager = new MovieManager("James" , "password");
int choice = 0;
while(choice != 9){
choice = print_menu();
switch(choice){
case 1:
System.out.print("Enter your password : ");
String pwd = in.nextLine();
manager.add_movie(pwd);
break;
case 2:
System.out.print("Enter your password : ");
String password = in.nextLine();
manager.remove_movie(password);
break;
case 3:
manager.search_movie();
break;
case 4:
manager.sort_movie();
break;
case 9:
System.out.println("Thank You!!");
}
}
manager.print_list();
}
}
