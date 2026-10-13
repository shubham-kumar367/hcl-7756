// Using Comparable interface
//package CollectionFramework;
//
//import java.util.*;
//
//class Student implements Comparable<Student>
//{
//    String name;
//    int rollno, age, marks;
//
//    Student(String name, int rollno, int age, int marks)
//    {
//        this.name = name;
//        this.rollno = rollno;
//        this.age = age;
//        this.marks = marks;
//    }
//
//    public int compareTo(Student s)
//    {
//        return this.marks - s.marks;
          // OR
//        return Integer.compare(this.marks, s.marks);
//    }
//
//    public String toString()
//    {
//        return name + " " + rollno + " " + age + " " + marks;
//    }
//}
//
//public class Task1
//{
//    public static void main(String[] args)
//    {
//        ArrayList<Student> list = new ArrayList<>();
//
//        list.add(new Student("Aman", 1, 20, 85));
//        list.add(new Student("Rohit", 2, 21, 78));
//        list.add(new Student("Shubham", 3, 19, 92));
//        list.add(new Student("Sachin", 4, 22, 65));
//        list.add(new Student("Rahul", 5, 20, 88));
//        list.add(new Student("Priya", 6, 19, 95));
//        list.add(new Student("Neha", 7, 21, 72));
//        list.add(new Student("Vikas", 8, 22, 80));
//        list.add(new Student("Ankit", 9, 20, 90));
//        list.add(new Student("Pooja", 10, 21, 75));
//
//        System.out.println("Original List:");
//        System.out.println(list);
//
//        Collections.sort(list);
//        System.out.println("\nSorted by Marks:");
//        System.out.println(list);
//
//        list.sort((s1, s2) -> s1.age - s2.age);
//        System.out.println("\nSorted by Age:");
//        System.out.println(list);
//    }
//}

// Using Comparator Interface


package CollectionFramework;

import java.util.*;

class Student
{
    String name;
    int rollno, age, marks;

    Student(String name, int rollno, int age, int marks)
    {
        this.name = name;
        this.rollno = rollno;
        this.age = age;
        this.marks = marks;
    }

    public String toString()
    {
        return "Name-" + name + "," + "RollNo-" + rollno + "," + "Age-" + age + "," + "Marks-" + marks;
    }
}

//class MarksComparator implements Comparator<Student>
//{
//    public int compare(Student s1, Student s2)
//    {
//        return Integer.compare(s2.marks, s1.marks);
//    }
//}
//
//class AgeComparator implements Comparator<Student>
//{
//    public int compare(Student s1, Student s2)
//    {
//        return Integer.compare(s1.age, s2.age);
//    }
//}

public class Task1
{
    public static void main(String[] args)
    {
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student("Aman", 1, 23, 85));
        list.add(new Student("Rohit", 2, 18, 78));
        list.add(new Student("Shubham", 3, 19, 92));
        list.add(new Student("Sachin", 4, 22, 65));
        list.add(new Student("Rahul", 5, 20, 88));
        list.add(new Student("Priya", 6, 19, 95));
        list.add(new Student("Neha", 7, 21, 72));
        list.add(new Student("Vikas", 8, 22, 80));
        list.add(new Student("Ankit", 9, 20, 90));
        list.add(new Student("Pooja", 10, 21, 75));

        System.out.println("Original List:");
        System.out.println(list);

        Collections.sort(list, (s2,s1) -> Integer
                .compare(s1.marks, s2.marks));
        System.out.println("\nSorted by Marks:");
        System.out.println(list);

        Collections.sort(list, (s1,s2) -> Integer.compare(s1.age, s2.age));
        System.out.println("\nSorted by Age:");
        System.out.println(list);
    }
}



