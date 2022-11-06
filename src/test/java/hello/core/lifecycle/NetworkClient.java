package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void call(String message) {
        System.out.println("Call " + url + " message:" + message);
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void disconnect() {
        System.out.println("close = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
        System.out.println("NetworkClient.close");
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
}
