package framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int flag;

        boolean sign = true;
        while (sign) {

            TestOption.PrintMenu();
            try {
                flag = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!\n");
                sc.next();
                //当Scanner出现InputMismatchException错误时，只有当Scanner调用next方法时，才会舍弃原本的值。
                //如果不使用next，光标不会等待下一次输入，直接catch错误。
                continue;
            }
            switch (flag) {
                case 1:
                    //实现无限长度的学生集合。
                    TestOption.NewStudent();
                    TestOption.studentNumber++;
                    break;
                case 2:
                    TestOption.SetName();
                    break;
                case 3:
                    TestOption.SetAge();
                    break;
                case 4:
                    TestOption.SetGpa();
                    break;
                case 5:
                    TestOption.PrintGpa();
                    break;
                case 6:
                    TestOption.AddCourse();
                    break;
                case 7:
                    TestOption.DelCourse();
                    break;
                case 0:
                    sign = false;
                    break;
                default:
                    System.out.println("The command doesn't exist!\n");
                    break;
            }

        }
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
        return "id:          " + id + "\n" +
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
        System.out.println("      SUCCESS!");
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
            System.out.println("      SUCCESS!");
        }

    }


}

class TestOption {
    static int studentNumber = 0;
    static ArrayList<Student> studentList = new ArrayList<>(10);

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
    public static int SearchStudent() {
        Scanner sc = new Scanner(System.in);
        int i, id = -1;
        boolean sign = true;
        while (sign) {
            System.out.println("please enter the student's id");
            try {
                id = sc.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("please check if you have entered letters rather than numbers");
                sc.next();
                continue;
            }
            sign = false;
        }
        for (i = 0; i < studentNumber; i++) {
            if (id == studentList.get(i).id) {
                return i;
            }
        }
        return -1;
    }

    //通过id查找，重载。
    public static int SearchStudent(int id) {
        int i;
        for (i = 0; i < studentNumber; i++) {
            if (id == studentList.get(i).id) {
                return i;
            }
        }
        return -1;
    }

    //检查是否有重名的同学，返回有几个。
    public static int SameName(String newName) {

        int i, count = 0;
        for (i = 0; i < studentNumber; i++) {
            if (newName.equals(studentList.get(i).name)) {
                count++;
            }
        }
        return count;
    }

    //创建新同学。
    public static void NewStudent() {
        Scanner sc = new Scanner(System.in);
        Student newStudent = new Student();
        int i, id;
        boolean sign = true;
        while (sign) {
            try {
                //首先设置id，因为id唯一。
                {
                    System.out.println("please enter the student's id");
                    id = sc.nextInt();
                    i = SearchStudent(id);
                    if (i != -1) {
                        System.out.println("There should be no same ids!");
                        System.out.println(studentList.get(i).name + "'s id is " + id);
                        return;
                    } else
                        newStudent.id = id;
                }
                //设置姓名，查找是否有重名的。
                {
                    System.out.println("please enter the student's name");
                    String name = sc.next();
                    int count;
                    count = SameName(name);
                    if (count != 0) {
                        System.out.println("There will be more than one " + name);
                    }
                    newStudent.setName(name);
                    System.out.println("Now there have " + count + "  " + name);
                }
                //储存其他的信息。

                System.out.println("please enter the student's gender");
                newStudent.gender = sc.next();

                System.out.println("please enter the student's height");
                newStudent.height = sc.nextInt();

                System.out.println("please enter the student's weight");
                newStudent.weight = sc.nextInt();

                System.out.println("please enter the student's age");
                newStudent.age = sc.nextInt();

                System.out.println("please enter the student's institute");
                newStudent.institute = sc.next();

                System.out.println("please enter the student's grade");
                newStudent.grade = sc.nextInt();

                System.out.println("please enter the student's job");
                newStudent.job = sc.next();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("please check if you have entered letters rather than numbers\n ");
                continue;
            }
            sign = false;
        }
        studentList.add(newStudent);
        System.out.println("      SUCCESS!");
    }

    //更改名字
    public static void SetName() {
        Scanner sc = new Scanner(System.in);
        int i, count;
        //通过id查找
        i = SearchStudent();
        if (i == -1)
            System.out.println("There's no such student");
        else {//显示之前的名字。
            System.out.println("The student's previous name is :" + studentList.get(i).name);
            System.out.println("please enter the student's current name");
            String newName = sc.next();
            count = SameName(newName);
            if (count != 0) {
                System.out.println("There have more than one " + newName);
            }
            studentList.get(i).setName(newName);
            System.out.println("      SUCCESS!");
            System.out.println("Now there have " + ++count + "  " + newName);
        }

    }

    //更改年龄
    public static void SetAge() {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent();
        if (i == -1) {
            System.out.println("there's no such student!");
        } else {
            System.out.println("The student's name is :" + studentList.get(i).name);
            int newAge = -1;
            boolean sign = true;
            while (sign) {
                System.out.println("please enter the student's age");
                try {
                    newAge = sc.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("please check if you have entered letters rather than numbers");
                    sc.next();
                    continue;
                }
                sign = false;
            }
            studentList.get(i).setAge(newAge);
            if (newAge > 0)
                System.out.println("      SUCCESS!");
        }
    }

    //更改GPA
    public static void SetGpa() {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent();
        if (i == -1) {
            System.out.println("there's no such student");
        } else {
            int newGPA = -1;
            boolean sign = true;
            while (sign) {
                System.out.println("please enter the student's GPA");
                try {
                    newGPA = sc.nextInt();
                } catch (InputMismatchException exception) {
                    System.out.println("please check if you have entered letters rather than numbers\n");
                    sc.next();
                    continue;
                }
                sign = false;
            }


            if (studentList.get(i).gpa != 0) {//旧GPA
                System.out.println("The student's previous GPA is " + studentList.get(i).gpa);
            }
            studentList.get(i).setGpa(newGPA);
            if (newGPA > 0)
                System.out.println("      SUCCESS!");
        }
    }

    //增加科目
    public static void AddCourse() {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent();
        if (i == -1)
            System.out.println("There's no such student!");
        else {
            System.out.println("please enter new course name");
            String course = sc.next();
            studentList.get(i).addCourse(course);
        }
    }

    //删除科目
    public static void DelCourse() {
        Scanner sc = new Scanner(System.in);
        int i;
        i = SearchStudent();
        if (i == -1)
            System.out.println("There's no such student!");
        else {
            System.out.println("please enter the course name");
            String course = sc.next();
            studentList.get(i).deleteCourse(course);
        }
    }

    //快速排序
    public static void QuickSort(int l, int r) {
        int i, j, buf, sign;
        if (l > r)
            return;
        i = l;
        j = r;
        sign = studentList.get(l).gpa;
        while (i != j) {
            while (studentList.get(j).gpa >= sign && i < j)//&&hou
                j--;
            while (studentList.get(i).gpa <= sign && i < j)//&&hou?
                i++;
            if (i < j) {
                buf = studentList.get(i).gpa;
                studentList.get(i).gpa = studentList.get(j).gpa;
                studentList.get(j).gpa = buf;
            }
        }

        studentList.get(l).gpa = studentList.get(i).gpa;
        studentList.get(i).gpa = sign;
        QuickSort(l, i - 1);
        QuickSort(i + 1, r);
    }

    //按照GPA打印学生信息，涉及到重写toString
    public static void PrintGpa() {
        QuickSort(0, studentNumber - 1);
        for (Student i : studentList) {
            System.out.println(i);
        }
    }

}