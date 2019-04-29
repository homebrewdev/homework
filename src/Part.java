// класс описывающий деталь машины
public class Part{

    private int id; // id
    private String name;        //наименование детали
    private String color;       //цвет
    private String material;    //материал
    private double weight;      //вес
    private int amount;         //количество

    // конструктор по дефолту
    public Part () {
    }

    // геттер и сеттер для id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // геттер и сеттер для name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // геттер и сеттер для color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // геттер и сеттер для material
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    // геттер и сеттер для веса детали - weight
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0 && weight < 1000.0) {
            this.weight = weight;
        } else {
            System.out.println("Ошибка! Вес детали не может быть отрицательным числом и до 1000 кг.!");
        }
    }

    // геттер и сеттер для кол-во деталей - amount
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
