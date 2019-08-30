package pers.lxx.ldap.ldapclientapi.util;

public class TimerUtil {

    private static long START_TIME=0;
    private static long END_TIME=0;
    public static long TOTAL_TIME=0;

    public static long MIN=0;
    public static long S=0;
    public static long MS=0;

    public static void start() {
        START_TIME = System.currentTimeMillis();
    }

    public static void end() {
        END_TIME = System.currentTimeMillis();
        TOTAL_TIME = (END_TIME-START_TIME);
    }

    public static String executeTime() {
        MIN = TOTAL_TIME/(1000*60);
        S = (TOTAL_TIME-MIN*(1000*60))/1000;
        MS = (TOTAL_TIME-MIN*(1000*60)-S*1000);
        return "********************** 耗时：" + MIN + " min " + S + " s " + MS + " ms **********************";
    }
}
