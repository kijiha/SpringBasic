package hello.core.member.singleton;

public class StatefuleService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price" + price);
        this.price=price;// 문제
    }

    public int getPrice() {
        return price;
    }
}
