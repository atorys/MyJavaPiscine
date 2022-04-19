package ex05_Menu;

public class Program {

    public static void main(String []args) {
        String profile = null;
        if (args.length > 0)
            profile = args[0].substring(args[0].indexOf('=') + 1);
        Menu    menu = new Menu(profile);
        menu.start();
    }
}
