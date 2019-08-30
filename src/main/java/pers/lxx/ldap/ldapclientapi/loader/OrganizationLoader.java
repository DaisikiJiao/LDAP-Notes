package pers.lxx.ldap.ldapclientapi.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganizationLoader {
    public static List<String[]> level_1_organization;
    public static List<String[]> level_2_organization;
    public static List<String[]> level_3_organization;

    public static List<String[]> organizationList;
    public static Map<String,String> organization_bm;

    static {
        level_1_organization = new ArrayList<>();
        level_2_organization = new ArrayList<>();
        level_3_organization = new ArrayList<>();
        organizationList = new ArrayList<>();
        organization_bm = new HashMap<>();
        try {
            InputStream inputStream = OrganizationLoader.class.getClassLoader().getResourceAsStream("organizationInfo.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String text = null;
            while ((text=bufferedReader.readLine())!=null){
                String[] array = text.split(";");
                if (array[4].equals("3")) {
                    level_3_organization.add(array);
                }else if(array[4].equals("2")){
                    level_2_organization.add(array);
                }else{
                    level_1_organization.add(array);
                }
                organizationList.add(array);
                organization_bm.put(array[0], array[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
