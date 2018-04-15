package cn.simplemind.test.clone;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * serialVersionUID属性说明:
	 */
	private static final long serialVersionUID = 1104413933775067742L;
	
	private String name;    // 姓名
    private int age;        // 年龄
    private Car car;        // 座驾
 
    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	@Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", car=" + car + "]";
    }
}
