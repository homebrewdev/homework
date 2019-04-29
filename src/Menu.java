import java.util.Scanner;

public class Menu {

    public static void mainDialog() throws Exception{
        Scanner in = new Scanner(System.in);

        System.out.println("Выберете пункт меню:");
        System.out.println("1. Вывести список деталей");
        System.out.println("2. Добавить деталь в базу данных");
        System.out.println("3. Изменить параметры детали в базе данных");
        System.out.println("4. Удалить деталь из базы данных");
        System.out.println("6. Работа с базой данных собранных машин");
        System.out.print(">> ");
        int menuItem = in.nextInt();
        in.close();

        switch (menuItem) {
            case 1:
                SQLiteDB.printAllParts();
                //back2Menu();
                break;
            case 2:
                addPart2dbDialog();
                //back2Menu();
                break;
            case 3:
                break;
            case 4:
                SQLiteDB.delPartFromDB(11);
                break;
        }

    }

    public static void addPart2dbDialog() throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.print("Наименование детали: ");
        String name = in.next();

        System.out.print("Укажите цвет детали: ");
        String color = in.next();

        System.out.print("Укажите материал детали: ");
        String material = in.next();

        System.out.print("Укажите вес детали в (кг): ");
        Double weight = in.nextDouble();

        System.out.print("Укажите количество деталей в шт.: ");
        int amount  = in.nextInt();

        in.close();

        Part part = new Part();
        part.setId(12);
        part.setName(name);
        part.setColor(color);
        part.setMaterial(material);
        part.setWeight(weight);
        part.setAmount(amount);

        System.out.println("Добавлена деталь: " + part.getId());
        System.out.print("id = " + part.getId());
        System.out.print("name = " + part.getName());
        System.out.print("color = " + part.getColor());
        System.out.print("вес = " + part.getWeight());


        SQLiteDB.dbAddNewPart(part.getId(), part.getName(), part.getColor(),
                part.getMaterial(), part.getWeight(), part.getAmount());

    }
    /*
    public static void back2Menu() {
        //Scanner in = new Scanner(System.in);

        System.out.println("1. Вернутся в главное меню");
        System.out.println("2. Завершение работы");
        System.out.println(">> ");

        //int s = in.nextInt();

        int select = 1;

        switch (select) {
            case 1:
                mainMenu();
                //in.close();
                break;
            case 2:
                //in.close();
                break;
        }

    }

    Scanner in = new Scanner(System.in);

        System.out.print("Наименование детали: ");
    String name = in.next();

        System.out.print("Укажите цвет детали: ");
    String color = in.next();

        System.out.print("Укажите вес детали в (кг): ");
    Double weight = in.nextDouble();

        System.out.printf("Your name: %s \n", name);
        in.close();

    Part part = new Part();
        part.setId(10);
        part.setName(name);
        part.setColor(color);
        part.setWeight(weight);
       */
}
