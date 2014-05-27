class doComms implements Runnable {
    private Socket server;
    private String line;
    private Pattern my_pattern = Pattern.compile("request\\.player\\?.+");
    private Matcher my_matcher;

    doComms(Socket server) {
        this.server = server;
    }

    public void run() {
        try {
            DataInputStream is = new DataInputStream(socket.getInputStream());
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			PrintWriter pw = new PrintWriter(os);
            System.out.println("Connected from " + server .getInetAddress() + " on port "
                    + server .getPort() + " to port " + server .getLocalPort() + " of "
                    + server .getLocalAddress());

            try{
                DatabaseHandler db = new DatabaseHandler();
                ObjectMapper mapper = new ObjectMapper();
                
				while ((line = in.readLine()) != null) {
                    my_matcher = my_pattern.matcher(line);
                    String request = null;

                    if(my_matcher.find()){
                        //get requested name
						request = my_matcher.group();
                        System.out.println(request);
                        request = request.split("\\?")[1];
                        System.out.println("Found: "+request);
						
						//get json file
						byte[] encoded = Files.readAllBytes(Paths.get("JSON_files\\"+db.getPlayer(request)));
						String filestring = new String(encoded,Charset.defaultCharset());
						pw.println(filestring);
						pw.flush();
                    }else{
                        System.out.println("Nothing found");
                    }
                }
            }catch(Exception e){
                System.err.println("Error");
                e.printStackTrace();
            }
            System.out.println("Connected closed from " + server .getInetAddress() + " on port "
                    + server .getPort() + " to port " + server .getLocalPort() + " of "
                    + server .getLocalAddress());
            
			server.close();
            is.close();
            os.close();
        } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }
}