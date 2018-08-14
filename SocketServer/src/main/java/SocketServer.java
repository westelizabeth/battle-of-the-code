import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

//

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class SocketServer {
    /**
     * Runs the server.
     */


    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        Random rand = new Random();
        int rand_int = rand.nextInt(1000);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    BufferedWriter out =
                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader input =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    out.write(rand_int + "\n");
                    out.flush();
                    for(int i = 0; i < 26; i++) {
                        String clientinput = input.readLine();
                        System.out.println(clientinput);
                        if (Integer.parseInt(clientinput) > rand_int + 50) {
                            socket.close();
                            System.exit(1);
                        }
                        out.write(Integer.parseInt(clientinput) + 1 + "\n");
                        out.flush();
                    }

                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }
}