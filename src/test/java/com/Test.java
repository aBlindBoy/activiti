package com;

import java.io.IOException;

public class Test {

    public static void main( String[] args ) throws InterruptedException {
        Thread.sleep ( 30000 );
        for (int i = 0; i < 1000; i++) {
            test ();
        }
    }

    public static void test() {
        Runtime runtime = Runtime.getRuntime ();

        String str = "ipconfig";
        Process exec = null;
        try {
            exec = runtime.exec ( str );
            ClearBufferUtils.clear ( exec );
            int i = 0;
            i = exec.waitFor ();
            int i1 = exec.exitValue ();
            System.out.println ( i1 );
            if (i == 0) {
                System.out.println ( "ok" );
            } else {
                System.out.println ( "buok" );
            }
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            runtime.gc ();
            exec.destroy ();
            System.out.println ( exec.isAlive () );

        }
    }


}
