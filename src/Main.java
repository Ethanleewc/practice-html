public class Main {

    public static void main(String[] args) {
        
        Server aws1 = new Server(80, "index.html");

        if ((args.length) != 3) {
            System.out.println("Incorrect Inputs. Please check the following usage.");
            return;
        }
        
        String type = args[0];
        if (type.equalsIgnoreCase("server")) {
            int port = Integer.parseInt(args[1]);
            String fileName = args[2];
            //once arguement match index[0] start Server method
            aws1.StartServer(port, fileName);
        } else {
            System.out.println("Incorrect Arguements !!!");
        }
    }
    
}
