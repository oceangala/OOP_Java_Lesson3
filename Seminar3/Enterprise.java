package Seminar3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Enterprise {
    private static Random random = new Random();
    static Employee generateEmployee(){
        String[] names = new String[] {"Liam", "Noah", "Oliver", "Elijah", "James", "William", "Benjamin","Lucas"};
        String[] surNames = new String[] {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};

        int salary_w = random.nextInt(200,300);
        int index_w = random.nextInt(30,50);

        int salary_f = random.nextInt(10,20);
        int index_f = random.nextInt(4,6);
        int hours = random.nextInt(5, 170);
        int projectCost = random.nextInt(2000, 5000);
        int projectCount = random.nextInt(1,4);
        int experience = random.nextInt(1, 15);

        int type = random.nextInt(0,3);

        if (type==0){ return new Worker(names[random.nextInt(8)],
                surNames[random.nextInt(8)],salary_w*index_w, experience);
        } else if (type==1){
            return new Freelancer(names[random.nextInt(7)],
                    surNames[random.nextInt(7)], salary_f*index_f, experience, hours);
        } else {
            return new HiredForProject(names[random.nextInt(7)],
                    surNames[random.nextInt(7)], projectCost, experience, projectCount);
        }
    }
    public static void main(String[] args) {
        //Worker worker1 = new Worker("John","Smith", 2500);
        //System.out.println(worker1);

        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++)
            employees[i] = generateEmployee();

        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees, new SalaryComparator());
        System.out.println();
        System.out.println("==Employees list sorted on month payment (from low to high)==");
        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees, new SurNameComparator());
        System.out.println();
        System.out.println("==Employees list sorted on surname (a-z order)==");
        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees, new NameComparator());
        System.out.println();
        System.out.println("==Employees list sorted on name (a-z order)==");
        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees);
        System.out.println();
        System.out.println("==Employees list sorted on month payment (high to low) no comparator==");
        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees, new ExperienceComparator());
        System.out.println();
        System.out.println("==Employees list sorted on experience (low to high)==");
        for (Employee employee : employees){
            System.out.println(employee);
        }

    }
}

class SalaryComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
//        return o1.calculateSalary() == o2.calculateSalary() ? 0 :
//                o1.calculateSalary() > o2.calculateSalary() ? -1: 1;
        return Double.compare(o1.calculateSalary(),o2.calculateSalary());
    }
}

class ExperienceComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.experience, o2.experience);
    }
}

class NameComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {

        return o1.name.compareTo(o2.name);
    }
}

class SurNameComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {

        return o1.surName.compareTo(o2.surName);
    }
}

abstract class Employee implements Comparable<Employee>{
    protected String name;
    protected String surName;
    protected double salary;

    protected int experience;

    public Employee(String name, String surName, double salary, int experience) {
        this.name = name;
        this.surName = surName;
        this.salary = salary;
        this.experience = experience;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return String.format("Employee %s %s has salary %.2f, experience %d.",
                name, surName, salary, experience);
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(o.calculateSalary(), calculateSalary());
    }
}

class Worker extends Employee{

    @Override
    public double calculateSalary() {
        return salary;
    }

    public Worker(String name, String surName, double salary, int experience) {
        super(name, surName, salary, experience);
    }

    @Override
    public String toString() {
        return String.format("Employee %s %s has fixed monthly payed salary $%.2f, experience %d.",
                name, surName, salary, experience);
    }
}

class Freelancer extends Employee{
    private int hours;

    public Freelancer(String name, String surName, double salary, int experience, int hours) {
        super(name, surName, salary, experience);
        this.hours = hours;
    }

    @Override
    public String toString() {
        return String.format("Employee %s %s has hour salary $%.2f & %d working hours this month, experience %d.",
                name, surName, salary, hours, experience);
    }

    @Override
    public double calculateSalary() {
        return hours*salary;
    }
}

class HiredForProject extends Employee{
    private int projectCount;


    public HiredForProject(String name, String surName, double salary, int experience, int projectCount) {
        super(name, surName, salary, experience);
        this.projectCount = projectCount;
    }

    @Override
    public double calculateSalary() {
        return salary*projectCount;
    }

    @Override
    public String toString() {
        return String.format("Employee %s %s has project grand salary $%.2f and %d project(s) this month, experience %d.",
                name, surName, salary, projectCount, experience);}

}