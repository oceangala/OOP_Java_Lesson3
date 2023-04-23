/**
 * Создать классы Собака и Кот с наследованием от класса Животное.
 * Все животные могут бегать и плыть. В качестве параметра каждому методу передаётся длина препятствия.
 * Результатом выполнения действий будет печать в консоль.
 * Например, dog1.run(150); -> "Бобик пробежал 150 метров."
 * У каждого животного есть ограничения на действия.
 * Бег: кот  - 200м, собака - 500м.
 * Плавание: кот не умеет плавать, собака - 10м.
 * Добавить посчет созданных собак, котов и животных.
 * */



public class Main {
    public static void main(String[] args) {

        Cat cat1 = new Cat("Peach", 10, 20);
        Dog dog1 = new Dog("Marley", 20, 30);
        Animal animal1 = new Animal("Beast", 15, 15);
        Animal animal2 = new Animal("Boo", 25, 55);
        System.out.println(Animal.getCounter());
        System.out.println(Cat.getCounter());
        System.out.println(Dog.getCounter());

    }
}

class Animal {

    protected String name;
    private int maxRum;
    private int maxSwim;
    private static int counter;

    {
        counter++;
    }// инициализатор счётчика
    public static int getCounter(){
        return counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxRum() {
        return maxRum;
    }

    public void setMaxRum(int maxRum) {
        this.maxRum = maxRum;
    }

    public int getMaxSwim() {
        return maxSwim;
    }

    public void setMaxSwim(int maxSwim) {
        this.maxSwim = maxSwim;
    }

    public Animal(String name, int maxRum, int maxSwim) {
        this.name = name;
        this.maxRum = maxRum;
        this.maxSwim = maxSwim;
    }// конструктор

    public void run (int distance){
        if (distance<= maxRum){
            System.out.printf("%s пробежал %d метров", name, distance);
        } else {
            System.out.printf("%s не смог пробежать %d метров", name, distance);
        }
    }
    public void swim (int distance){
        if (distance<= maxSwim){
            System.out.printf("%s проплыл %d метров", name, distance);
        } else {
            System.out.printf("%s не смог проплыть %d метров", name, distance);
        }
    }
}

class Dog extends Animal{
    static int counter;
    {
        counter++;
    }

    public static int getCounter(){
        return counter;
    }
    public Dog(String name, int maxRum, int maxSwim) {
        super(name, maxRum, maxSwim);
    }
}

class Cat extends Animal{
    static int counter;
    {
        counter++;
    }

    public static int getCounter(){
        return counter;
    }
    public Cat(String name, int maxRum, int maxSwim) {
        super(name, maxRum, maxSwim);
    }

    @Override
    public void swim(int distance) {
        System.out.printf("Cat %s can't swim.\n", name);
    }
}