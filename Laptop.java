import java.util.*;

class Laptop {
    private String brand;
    private int ram; // ОЗУ в ГБ
    private int storage; // Объем жесткого диска в ГБ
    private String os; // Операционная система
    private String color;

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                "GB, storage=" + storage +
                "GB, os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        this.laptops = new HashSet<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public void filterLaptops() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();
        boolean continueFiltering = true;

        while (continueFiltering) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ (в ГБ): ");
                    int ram = scanner.nextInt();
                    filters.put("ram", ram);
                    break;
                case 2:
                    System.out.print("Введите минимальный объем жесткого диска (в ГБ): ");
                    int storage = scanner.nextInt();
                    filters.put("storage", storage);
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    String os = scanner.nextLine();
                    filters.put("os", os);
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    String color = scanner.nextLine();
                    filters.put("color", color);
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
                    continue;
            }

            System.out.print("Хотите добавить еще один критерий? (да/нет): ");
            String answer = scanner.nextLine();
            continueFiltering = answer.equalsIgnoreCase("да");
        }

        Set<Laptop> filteredLaptops = filterByCriteria(filters);

        System.out.println("Ноутбуки, подходящие под критерии:");
        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет ноутбуков, удовлетворяющих условиям фильтрации.");
        } else {
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }

    private Set<Laptop> filterByCriteria(Map<String, Object> filters) {
        Set<Laptop> result = new HashSet<>(laptops);

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            result.removeIf(laptop -> !matchesFilter(laptop, key, value));
        }

        return result;
    }

    private boolean matchesFilter(Laptop laptop, String key, Object value) {
        switch (key) {
            case "ram":
                return laptop.getRam() >= (int) value;
            case "storage":
                return laptop.getStorage() >= (int) value;
            case "os":
                return laptop.getOs().equalsIgnoreCase((String) value);
            case "color":
                return laptop.getColor().equalsIgnoreCase((String) value);
            default:
                return true;
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();

        store.addLaptop(new Laptop("Dell", 8, 256, "Windows", "Black"));
        store.addLaptop(new Laptop("HP", 16, 512, "Windows", "Silver"));
        store.addLaptop(new Laptop("Apple", 8, 256, "MacOS", "Gray"));
        store.addLaptop(new Laptop("Asus", 16, 1024, "Windows", "Black"));
        store.addLaptop(new Laptop("Lenovo", 4, 128, "Linux", "White"));

        store.filterLaptops();
    }
}