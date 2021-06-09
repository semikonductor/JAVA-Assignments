package test;


import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

//测试类
public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sign;
        boolean flag = true;
        while (flag) {
            TestOption2.PrintMenu();
            try {
                sign = sc.nextInt();
            } catch (InputMismatchException exception) {
                sc.next();
                System.out.println("please enter a number instead of letters!\n");
                continue;
            }
            switch (sign) {
                case 1:
                    TestOption2.NewPet();
                    break;
                case 2:
                    PetTakeAway.TakeAway();
                    break;
                case 3:
                    TestOption2.PrintList();
                    break;
                case 4:
                    TestOption2.PrintListId();
                    break;
                case 5:
                    TestOption2.FeedPet();
                    break;
                case 6:
                    int i;
                    i = TestOption2.SearchPet();
                    if (i < 0)
                        System.out.println("There's no your pet");
                    else
                        System.out.println(TestOption2.PetList[i]);
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("please enter a correct number!");
            }
        }
    }

}

//宠物类
abstract class Pet {
    int id = TestOption2.PetNumber;
    String name, kind;
    IPetTakeAway strategy;
    public abstract void enjoy();

    public String toString() {
        return "id:     " + id + "\n" +
                "name:   " + name + '\n' +
                "kind:   " + kind + '\n';
    }

    public void Feed() {
        System.out.println(this);
        this.enjoy();
        System.out.println(kind + " food -1\n");
    }
}

//狗猫鸟类
class Dog extends Pet {
    public Dog(String name) {
        kind = "Dog";
        super.strategy = new DogTakeAway();
        this.name = name;
    }

    public void enjoy() {
        System.out.println(name + ": wang wang!");
    }
}

class Cat extends Pet {
    public Cat(String name) {
        kind = "Cat";
        super.strategy = new CatTakeAway();
        this.name = name;
    }

    public void enjoy() {
        System.out.println(name + ": miao miao!");
    }
}

class Bird extends Pet {
    public Bird(String name) {
        kind = "Bird";
        super.strategy = new BirdTakeAway();
        this.name = name;
    }

    public void enjoy() {
        System.out.println(name + ": zhi zhi!");
    }
}

//领养接口
interface IPetTakeAway {
    void TakeAway();
}

//三个实现领养接口的子类
class DogTakeAway implements IPetTakeAway {
    @Override
    public void TakeAway() {
        System.out.println("你家的小可爱正在等你呢,记得戴上狗狗的项圈哦！");
    }
}

class CatTakeAway implements IPetTakeAway {

    @Override
    public void TakeAway() {
        System.out.println("你家的小可爱正在等你呢,记得带上猫笼哦！");
    }
}

class BirdTakeAway implements IPetTakeAway {

    @Override
    public void TakeAway() {
        System.out.println("你家的小可爱正在等你呢,记得带上鸟笼哦");
    }
}

//领养类
class PetTakeAway {
    Calendar calendar=Calendar.getInstance();
    public static void TakeAway() {
       // try
        int i = TestOption2.SearchPet();
        if (i < 0)
            System.out.println("There's no your pet");
        else {
            TestOption2.PetList[i].strategy.TakeAway();
            for (i = 0; i < TestOption2.PetNumber; i++) {
                TestOption2.PetList[i] = TestOption2.PetList[i + 1];
            }
            System.out.println("You can take away your pet right now!");
        }
    }
}

//测试功能储存的类
class TestOption2 {
    //以下变量在整个程序中统一，使用静态
    static int PetNumber = 0;
    static Scanner sc = new Scanner(System.in);
    static Pet[] PetList = new Pet[15];

    //打印菜单
    public static void PrintMenu() {
        System.out.println("Please choose your mission");
        System.out.println("1. new Pet");
        System.out.println("2. take away pet");
        System.out.println("3. print pet list by kinds");
        System.out.println("4. print pet list by ids");
        System.out.println("5. feed pet");
        System.out.println("6. search your pet");
        System.out.println("0. quit");
        System.out.println("\n\n");
    }

    //通过姓名查找宠物
    public static int SearchPet() {
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your pet's name");
        String name = sc.next();
        for (int i = 0; i < PetNumber; i++) {
            if (name.equals(PetList[i].name)) {
                return i;
            }
        }
        return -1;
    }

    //寄存宠物
    public static void NewPet() {
        System.out.println("please enter your pet's name");
        String name = sc.next();
        int sign=0;
        boolean flag = true;
        while (flag) {
            System.out.println("please enter your pet's kind\n    1.dog 2.cat 3.bird");
            try {
                sign = sc.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("please check if you have entered letters rather than numbers\n");
                sc.next();
                continue;
            }
            flag = false;
        }

        if (sign == 1)
            PetList[PetNumber++] = new Dog(name);
        else if (sign == 2)
            PetList[PetNumber++] = new Cat(name);
        else if (sign == 3)
            PetList[PetNumber++] = new Bird(name);
        else
            System.out.println("please enter the legal number!");
    }

    //喂养宠物
    public static void FeedPet() {
        int i = SearchPet();
        if (i < 0)
            System.out.println("There's no your pet!");
        else
            PetList[i].Feed();
    }

    //得到宠物的种类
    public static String GetKind() {
        int i;
        i = SearchPet();
        if (i < 0) {
            System.out.println("There's no your pet!");
            return null;
        } else {
            return PetList[i].kind;
        }
    }

    //输出相同种类的宠物
    public static void PrintList() {
        System.out.println("please enter the kind\n1.dog 2.cat 3.bird");
        int sign = sc.nextInt();
        int i;
        String kind;
        if (sign == 1)
            kind = "Dog";
        else if (sign == 2)
            kind = "Cat";
        else if (sign == 3)
            kind = "Bird";
        else {
            System.out.println("please enter the correct number!");
            return;
        }
        int count=0;
        for (i = 0; i < PetNumber; i++) {
            if (PetList[i].kind.equals(kind)) {
                System.out.println(PetList[i] + "\n");
                count++;
            }
        }
        System.out.println("There are "+count+" "+kind+"s in total \n");
    }

    //输出某一序号前的宠物
    public static void PrintListId() {
        int id = 0;
        boolean sign = true;
        while (sign) {
            System.out.println("please enter the number!");
            try {
                id = sc.nextInt();
            } catch (InputMismatchException exception) {
                sc.next();
                System.out.println("please check if you have entered letters rather than numbers\n");
                continue;
            }
            sign = false;
        }

        for (int i = 0; i < id; i++)
            System.out.println(PetList[i]);
    }

}