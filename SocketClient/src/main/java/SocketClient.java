//139.126.184.127
//139.126.184.69

import java.io.*;
import java.net.Socket;

/**
 * Trivial client for the date server.
 */
public class SocketClient {
    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    //static final String IP_ADDRESS = "139.126.184.127";
    static final String IP_ADDRESS = "139.126.184.69";

    public static void main(String[] args) throws IOException {
//        String serverAddress = JOptionPane.showInputDialog(
//                "Enter IP Address of a machine that is\n" +
//                        "running the date service on port 9090:");

        Socket s = new Socket(IP_ADDRESS, 9090);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter((s.getOutputStream())));

        while (true) {

            String answer = input.readLine();
            if (answer == null ) {
                s.close();
                System.exit(1);
            }
            System.out.println(answer);
            output.write((Integer.parseInt(answer) + 1) + "\n");
            output.flush();
        }
    }
}