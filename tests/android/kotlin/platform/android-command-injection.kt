import android.app.Activity
import android.content.Intent

class CommandInjectionTest : Activity() {

    fun taintCases(x: String) {
        val rt: Runtime = Runtime.getRuntime()
        val intent: Intent = getIntent()

        // ruleid: android-command-injection-taint, android-command-injection
        Runtime.getRuntime().exec(intent.getStringExtra("cmd")!!)

        // ruleid: android-command-injection-taint, android-command-injection
        rt.exec(intent.getStringExtra("cmd")!!)

        // ruleid: android-command-injection-taint, android-command-injection
        rt.exec(intent.dataString!!)

        // ruleid: android-command-injection-taint
        ProcessBuilder(listOf("sh", "-c", intent.getStringExtra("cmd")!!)).start()

    }

    fun auditCases(x: String) {
        val rt: Runtime = Runtime.getRuntime()

        // ruleid: android-command-injection
        Runtime.getRuntime().exec(arrayOf("sh", "-c", x))

        // ruleid: android-command-injection
        rt.exec("ls " + x)

        // ruleid: android-command-injection
        rt.exec("ls $x")

        // ruleid: android-command-injection
        rt.exec(String.format("ls %s", x))

        // ruleid: android-command-injection
        rt.exec("ls %s".format(x))

        // ok: android-command-injection
        Runtime.getRuntime().exec(arrayOf("ls", "-l"))

        // ok: android-command-injection
        Runtime.getRuntime().exec("ls")
    }
}