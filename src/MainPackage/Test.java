package MainPackage;

import FileWorks.CollectionManager;
import Humans.*;

public class Test {
    public static void main(String[] args) {
String jsonRegex1 = "\\{\"cardHeight\":(\\d+.?\\d),\"date\":(Mon|Tue|Wed|Thu|Fri|Sat|Sun)\\s(Jan|Feb|Mar|Apr|May|June|July|Aug|Sept|Oct|Nov|Dec)\\s[0-3]\\d\\s[0-2]\\d:[0-5]\\d:[0-5]\\d\\sMSK\\s\\d\\{4},\"nosesize\":(\\d+.?\\d),\"name\":\"(.)\",\"cardWidth\":(\\d+.?\\d),\"photo\":\\{\"hair\":\"(Blond|DarkBrown|Red|Rusyi|Brunette|Grey)\",\"eyes\":\"(Blue|Gray|Swamp|Green|Amber|Brown|Yellow|Black)\"},\"headsize\":(\\d+.?\\d),\"status\":\"(ChiefPoliceOfficer|OfficerAssistant|Jailbird|Suspect)\",\"height\":(\\d+.?\\d)}";
String jconRegex2 =  "{\"cardHeight\":50,\"date\":\"Fri Aug 16 01:33:12 MSK 2019\",\"nosesize\":2.5,\"name\":\"Красавчик\",\"cardWidth\":2,\"photo\":{\"hair\":\"Red\",\"eyes\":\"Amber\"},\"headsize\":30.5,\"status\":\"Jailbird\",\"height\":72.5}";
 if (jconRegex2.matches(jsonRegex1)){
     System.out.println("sdfghjkl");
 }




    }
}
