package Spring.part1.entity;

public class Humen {

    private String name;

    private Integer age;

    private Integer hp;

    private Weapon weapon;

    public Humen(String name, Integer age, Integer hp) {
        this.name = name;
        this.age = age;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }
    public Integer getHp() {
        return hp;
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hp=" + hp +
                ", weapon=" + weapon +
                '}';
    }
}
