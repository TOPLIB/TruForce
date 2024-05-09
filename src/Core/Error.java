package Core;

public class Error {
    public static void ThrowError(String message){
        System.err.println("An unexpected error occurred while executing your program.\n\nCheck your code for various syntax or logical errors.\nCore.Error text: " + message + ".");
        System.exit(-1);
    }
    public static void ThrowError(String message, int status){
        System.err.println("An unexpected error occurred while executing your program.\n\nCheck your code for various syntax or logical errors.\nCore.Error text: " + message + ".");
        System.exit(status);
    }
}
