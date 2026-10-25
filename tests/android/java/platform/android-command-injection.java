import android.app.Activity;
import android.content.Intent;
import java.io.IOException;
import java.util.Arrays;

public class CommandInjectionTest extends Activity {

    void taintCases(String x) throws Exception {
        Runtime rt = Runtime.getRuntime();
        Intent intent = getIntent();

        // ruleid: android-command-injection-taint, android-command-injection
        Runtime.getRuntime().exec(intent.getStringExtra("cmd"));

        // ruleid: android-command-injection-taint, android-command-injection
        rt.exec(intent.getStringExtra("cmd"));
    }

    void auditCases(String x) throws Exception {
        Runtime rt = Runtime.getRuntime();

        // ruleid: android-command-injection
        Runtime.getRuntime().exec(new String[]{"sh", "-c", x});

        // ruleid: android-command-injection
        rt.exec("ls " + x);

        // ruleid: android-command-injection
        rt.exec(String.format("ls %s", x));

        // ok: android-command-injection
        Runtime.getRuntime().exec(new String[]{"ls", "-l"});

        // ok: android-command-injection
        Runtime.getRuntime().exec("ls");
    }

    public void processBuilderCases(String cmd) {
        ProcessBuilder b = new ProcessBuilder();

        // ruleid: android-command-injection
        b.command(cmd);

        // ok: android-command-injection
        b.command("test");

        // ruleid: android-command-injection
        b.command(Arrays.asList("/bin/sh", "-c", cmd));

        String tainted = "test2" + cmd + "test";

        // ruleid: android-command-injection
        b.command("test2" + cmd + "test");

        // ruleid: android-command-injection
        b.command(tainted);

        // ruleid: android-command-injection
        b.command(Arrays.asList("/bin/sh", "-c", tainted));

        // ok: android-command-injection
        b.command(Arrays.asList("echo", "Hello, World!"));
    }

    public void processBuilderListCase(String param) throws IOException {
        java.util.List<String> argList = new java.util.ArrayList<String>();

        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            argList.add("cmd.exe");
            argList.add("/c");
        } else {
            argList.add("sh");
            argList.add("-c");
        }
        argList.add("echo " + param);

        ProcessBuilder pb = new ProcessBuilder();

        // ruleid: android-command-injection
        pb.command(argList);

        Process p = pb.start();
    }
}