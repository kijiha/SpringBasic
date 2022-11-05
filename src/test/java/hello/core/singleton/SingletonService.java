package hello.core.singleton;

public class SingletonService {

    // static 을 딱 1개만 생성해둔다
    private static final SingletonService instance = new SingletonService();


    // 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }


    // 프라이빗 생성자로 외부에서 new 로 새로운 인스턴스 생성을 막는다.
    private SingletonService() {

    }


    public void logic() {
        System.out.println("Singleton Called");
    }
}
