package test;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {
    //打印操作面板。
    public static void PrintMenu() {
        System.out.println("Please choose your mission");
        System.out.println("1. new student");
        System.out.println("2. set name");
        System.out.println("3. set age");
        System.out.println("4. set GPA");
        System.out.println("5. print list by GPA");
        System.out.println("6. add course");
        System.out.println("7. delete course");
        System.out.println("0. quit");
        System.out.println("\n\n");
    }

    //查找是否存在该学生，返回值为正即为学生在数组中的位置，返回为负则代表不存在。
    public static int SearchStudent(Student[] studentList, int studentNumber) {
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the student's id");
        int i, id = sc.nextInt();
        for (i = 0; i < studentNumber; i++) {
            if (id == studentList[i].id) {
                return i;
            }
        }
        return -1;
    }

    //通过id查找，重载。
    public static int SearchStudent(Student[] studentList, int studentNumber, int id) {
        int i;
        for (i = 0; i < studentNumber; i++) {
            if (id == studentList[i].id) {
                return i;
            }
        }
        return -1;
    }

    //检查是否有重名的同学，返回有几个。
    public static int SameName(Student[] studentList, int studentNumber, String newName) {

        int i, count = 0;
        for (i = 0; i < studentNumber; i++) {
            if (newName.equals(studentList[i].name)) {
                count++;
            }
        }
        return count;
    }

    //创建新同学。
    public static void NewStudent(Student[] studentList, int studentNumber) {
        Scanner sc = new Scanner(System.in);
        studentList[studentNumber] = new Student();
        int i, id;
        //首先设置id，因为id唯一。
        {
            System.out.println("please enter the student's id");
            id = sc.nextInt();
            i = SearchStudent(studentList, studentNumber, id);
            if (i != -1) {
                System.out.println("There should be no same ids!");
                System.out.println(studentList[i].name + "'s id is " + id);
                return;
            } else
                studentList[studentNumber].id = id;
        }
        //设置姓名，查找是否有重名的。
        {
            System.out.println("please enter the student's name");
            String name = sc.next();
            int count;
            count = SameName(studentList, studentNumber, name);
            if (count != 0) {
                System.out.println("There will be more than one " + name);
            }
            studentList[studentNumber].setName(name);
            System.out.println("Now there have " + count + "  " + name);
        }
        //储存其他的信息。

        System.out.println("please enter the student's gender");
        studentList[studentNumber].gender = sc.next();

        System.out.println("please enter the student's height");
        studentList[studentNumber].height = sc.nextInt();

        System.out.println("please enter the student's weight");
        studentList[studentNumber].weight = sc.nextInt();

        System.out.println("please enter the student's age");
        studentList[studentNumber].age = sc.nextInt();

        System.out.println("please enter the student's institute");
        studentList[studentNumber].institute = sc.next();

        System.out.println("please enter the student's grade");
        studentList[studentNumber].grade = sc.nextInt();

        System.out.println("please enter the student's job");
        studentList[studentNumber].job = sc.next();
    }

    //更改名字
    public static void SetName(Student[] studentList, int studentNumber) {
        System.out.println("please enter the student's id");
        Scanner sc = new Scanner(System.in);
        int i, count;
        //通过id查找
        i = SearchStudent(studentList, studentNumber);
        if (i == -1)
            System.out.println("There's no such student");
        else {//显示之前的名字。
            System.out.println("The student's previous name is :" + studentList[i].name);
            System.out.println("please enter the student's current name");
            String newName = sc.next();
            count = SameName(studentList, studentNumber, newName);
            if (count != 0) {
                System.out.println("There have more than one " + newName);
            }
            studentList[i].setName(newName);
            System.out.println("Now there have " + count + "  " + newName);

        }

    }

    //更改年龄
    public static void SetAge(Student[] studentList, int studentNumber) {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent(studentList, studentNumber);
        if (i == -1) {
            System.out.println("there's no such student!");
        } else {
            System.out.println("The student's name is :" + studentList[i].name);
            System.out.println("please enter the student's age");
            int newAge = sc.nextInt();
            studentList[i].setAge(newAge);
        }
    }

    //更改GPA
    public static void SetGpa(Student[] studentList, int studentNumber) {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent(studentList, studentNumber);
        if (i == -1) {
            System.out.println("there's no such student");
        } else {
            System.out.println("please enter the student's GPA");
            int newGPA = sc.nextInt();
            if (studentList[i].gpa != 0) {//旧GPA
                System.out.println("The student's previous GPA is " + studentList[i].gpa);
            }
            studentList[i].setGpa(newGPA);
        }
    }

    //增加科目
    public static void AddCourse(Student[] studentList, int studentNumber) {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent(studentList, studentNumber);
        if (i == -1)
            System.out.println("There's no such student!");
        else {
            System.out.println("please enter new course name");
            String course = sc.next();
            studentList[i].addCourse(course);
        }
    }

    //删除科目
    public static void DelCourse(Student[] studentList, int studentNumber) {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent(studentList, studentNumber);
        if (i == -1)
            System.out.println("There's no such student!");
        else {
            System.out.println("please enter the course name");
            String course = sc.next();
            studentList[i].deleteCourse(course);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Student[] studentList = new Student[10];//初始长度为10.
        int flag, studentNumber = 0;
        boolean sign = true;
        while (sign) {
            PrintMenu();
            flag = sc.nextInt();
            switch (flag) {
                case 1:
                    //实现无限长度的学生集合。
                    if(studentNumber==studentList.length)
                    {
                        studentList = Arrays.copyOf(studentList, studentList.length+10);
                    }
                    NewStudent(studentList, studentNumber);
                    studentNumber++;
                    break;
                case 2:
                    SetName(studentList, studentNumber);
                    break;
                case 3:
                    SetAge(studentList, studentNumber);
                    break;
                case 4:
                    SetGpa(studentList, studentNumber);
                    break;
                case 5:
                    int i,j;
                    for(i=0;i<studentNumber;i++)
                    {
                        for(j=0;j<studentNumber;j++)
                            if(studentList[j].gpa<studentList[j+1].gpa)
                            {
                                Student buf=studentList[j];
                                studentList[j]=studentList[j+1];
                                studentList[j+1]=buf;
                            }
                    }
                    for (Student p : studentList) {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    AddCourse(studentList, studentNumber);
                    break;
                case 7:
                    DelCourse(studentList, studentNumber);
                    break;
                case 0:
                    sign = false;
                    break;
                default:
                    sign = false;
                    System.out.println("The command doesn't exist!");
                    break;
            }

        }
        for (Student i : studentList)
            System.out.println(i);
    }

}

class Person {
    int age, height, weight;
    String job, name, gender;

    public String toString() {
        return "age:" + age + "\nheight:" + height + "\nweight:" + weight
                + "\njob:" + job + "\nname:" + name + "\ngender:" + gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }
}

class Student extends Person {
    static int courseNumber = 0;
    int id, gpa, grade;
    String institute;
    String[] course = new String[10];

    public String toString() {//每输出一个就换行
        return  "id:          " + id + "\n" +
                "name:        " + name + "\n" +
                "age:         " + age + "\n" +
                "gender:      " + gender + "\n" +
                "height:      " + height + "\n" +
                "weight:      " + weight + "\n" +
                "job:         " + job + "\n" +
                "GPA:         " + gpa + "\n" +
                "grade:       " + grade + "\n" +
                "institute:   " + institute + "\n" +
                "courses:     " + Arrays.toString(course);
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void addCourse(String newCourse) {
        int i;
        if (courseNumber >= 10) {//是否满课
            System.out.println("The student has enough classes!");
            return;
        }
        for (i = 0; i < courseNumber; i++) {
            if (newCourse.equals(course[i])) {//是否重复的课
                System.out.println("The student has already have this class!");
                return;
            }
        }
        course[courseNumber++] = newCourse;
    }

    public void deleteCourse(String delCourse) {
        boolean sign = false;
        int i;
        if (courseNumber == 0)//学生没课
            System.out.println("The student has no class!");
        for (i = 0; i < courseNumber; i++) {
            if (delCourse.equals(course[i]))
                sign = true;
            else//学生没有这节课，没法删
                System.out.println("The student doesn't have this course!");
        }
        if (sign) {//后面的往前移
            for (i = 0; i < courseNumber; i++)
                course[i] = course[i + 1];
        }
    }


}