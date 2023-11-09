package Handlers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class redactionHandler{

    public static ArrayList<Integer> charFinder(String string, char target){
        ArrayList<Integer> indeces = new ArrayList<>();
        for(int i = 0; i < string.length(); i++){
            char[] ch = string.toCharArray();
            if(ch[i] == target){
                indeces.add(i);
            }

        }

        return indeces;
    }

    public static ArrayList<String> infoParser(String text, String[] parameters) {
        ArrayList<String> information = new ArrayList<>();
        for (int meta = 0; meta < parameters.length; meta++) {
            if (parameters[meta].equals("education")) {
                break;
            }
            if (parameters[meta].equals("email")) {
                try {
                    String emailStarter = text.substring(0, charFinder(text, '@').get(0));
                    String emailStart;
                    if (emailStarter.substring(emailStarter.lastIndexOf("\n")).length() > emailStarter.substring(emailStarter.lastIndexOf(" ")).length()) {
                        emailStart = emailStarter.substring(emailStarter.lastIndexOf(" ") + 1);
                    } else {
                        emailStart = emailStarter.substring(emailStarter.lastIndexOf("\n") + 1);
                    }
                    String email;
                    if (text.substring(text.indexOf(emailStart), text.indexOf(" ", text.indexOf(emailStart))).length() > text.substring(text.indexOf(emailStart), text.indexOf("\n", text.indexOf(emailStart))).length()) {
                        email = text.substring(text.indexOf(emailStart), text.indexOf("\n", text.indexOf(emailStart)));
                    } else {
                        email = text.substring(text.indexOf(emailStart), text.indexOf(" ", text.indexOf(emailStart)));
                    }
                    System.out.println("Email found: " + email);
                    information.add(email);
                } catch (Exception e) {
                    System.out.println("There is no email in this document" + e);
                }
            }
            if (parameters[meta].equals("phone")) {
                break;
            }
            if (parameters[meta].equals("experience")) {
                break;
            }
            if (parameters[meta].equals("names")) {
                System.out.println("Name found: " + parseHandler.getNames(text));
            }
        }
        return information;
    }
    public static String redaction(String path, String[] parameters) throws IOException {
        File file = new File(path);
        String fileType = path.split("\\.")[1];
        String fileText = "";
        if(fileType.equals("pdf")){
            fileText = docHandler.readPDFFile(file);
        }

        infoParser(fileText, parameters);
        /*
        for(String param : parameters){
            try {
                String afterParam = fileText.split(param)[1];
                String beforeParam = fileText.split(param)[0];
                System.out.println(beforeParam);
                System.out.print("\n\n AFTER PARAM "+param+"\n\n");
                System.out.println(afterParam);
            }catch(Exception e){
                System.out.print("Parameter "+param+" was not in the file ");
                }
        }

         */



        return fileType;

    }

}
