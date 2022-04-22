public class Logger {

    static Boolean doPrint = true;

    //pure logging of normal messages
    public static void print(String msg){
        if(doPrint)
            System.out.println(Color.ANSI_BLUE+"[MESSAGE]:"+msg+Color.ANSI_RESET);
    }

    //severity based logging
    public static void print(String msg,int severity){
        if(doPrint){
            switch(severity){
                case 2:
                    //error
                    System.out.println(Color.ANSI_PURPLE+"[WARNING]:"+msg+Color.ANSI_RESET);
                    break;
                case 1:
                    //warning
                    System.out.println(Color.ANSI_RED+"[ERROR]:"+msg+Color.ANSI_RESET);
                    break;
                case 3:
                    //sucess
                    System.out.println(Color.ANSI_GREEN+"[SUCCESS]:"+msg+Color.ANSI_RESET);
                    break;
                default:
                    break;
            }
        }
    }
}
