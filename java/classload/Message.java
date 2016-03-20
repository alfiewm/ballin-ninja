public class Message
{
    private String m_text;

    static {
      System.out.println("Message static init");
    }

    public Message(String text) {
        m_text = text;
        System.out.println("Message construct");
    }

    public void print(java.io.PrintStream ps) {
        ps.println(m_text);
    }
}
