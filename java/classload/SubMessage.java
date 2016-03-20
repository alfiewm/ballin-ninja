public class SubMessage extends Message {
  static {
    System.out.println("SubMessage static init");
  }
  public SubMessage(String text) {
    super(text + " I'm A sub message!");
    System.out.println("SubMessage construct");
  }
}
