public class Greeter
{
    private static Message s_message = new SubMessage("Hello, World!");

    public void greet() {
        s_message.print(System.out);
    }
}
