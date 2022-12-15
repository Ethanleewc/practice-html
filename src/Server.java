import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {

  private Integer port;
  private String fileName;

  
  public Server(Integer port, String fileName) {
    this.port = port;
    this.fileName = fileName;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

//Start server method
  public void StartServer(int port, String fileName) {

    String location = "index.html";
    Path path = Paths.get(location);
    File file = new File(location);

    try{
    // Create a new server socket on port 80
    ServerSocket serverSocket = new ServerSocket(80);
    
    if(Files.exists(path) && file.canRead()) {
      System.out.println("Path is located!");
      System.out.println("Path is a directory..");
      System.out.println("Path is readable..");
      System.out.println("Waiting for connection...");
    }
    else {
      serverSocket.close();
    }

    // Listen for incoming connections and accept the first one
    Socket clientSocket = serverSocket.accept();
    System.out.println("Client connected!");

      InputStream is = clientSocket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String readline = br.readLine();
      System.out.println(readline);

      String[] filteredLine = readline.replaceAll("/","").split(" ");
      String get = filteredLine[0].trim();
      String getFileName = filteredLine[1].trim();

    // Create a buffered reader to read the HTML file
    BufferedReader in = new BufferedReader(
      new FileReader(getFileName));

    // Create a print writer to send the HTML file to the client
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

    // Read and send each line of the .HTML file
    String line;
    while ((line = in.readLine()) != null) {
      out.println(line);
    }
      //If the request method is a GET method, send the following response
      if (get.equalsIgnoreCase("get")) {
        // Send the HTTP headers to the client
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
      }
      //If the request method is NOT a GET method, send the following response
      else {
        out.println("HTTP/1.1 405 Method Not Allowed\r\n\r\n<method name> not supported\r\n");
        System.out.println("Error...Method is not GET!");
        serverSocket.close();
      }

    // Close the streams
    in.close();
    out.close();
    clientSocket.close();
    serverSocket.close();
     } catch (IOException e) {
       e.printStackTrace();
     }
  }

}
