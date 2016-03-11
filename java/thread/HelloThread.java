package thread;

public class Hello implements Runnable {
  public void run() {
    for (int i = 0; i < 3; i++) {
      System.out.println(Thread.currentThread().getName());
    }
  }

  public static void main(String[] args) {
    Hello he = new Hello();
    new Thread(he, "A").start();
    new Thread(he, "B").start();
    new Thread(he).start();
    System.out.println("Main Thread!");
    System.out.println(new InnerClass().getAName());
  }

  public interface intera {
    int getAName();
  }

  public interface interb {
    int getAName();
  }

  public static class InnerClass implements intera, interb {
    @Override
    public int getAName() {
      return 10;
    }
  }
}
