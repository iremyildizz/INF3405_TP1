public class Validation {

    //source pour la fonction: https://stackoverflow.com/questions/4581877/validating-ipv4-string-in-java
    public static boolean isIP(String ip){
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }
            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }
            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            return !ip.endsWith(".");
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isValidPort(int port){
        return port >= 5000 && port <= 5050;
    }

}
