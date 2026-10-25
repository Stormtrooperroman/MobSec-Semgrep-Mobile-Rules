import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class PendingIntentTest(private val ctx: Context) {

    fun test(intent: Intent) {
        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getBroadcast(ctx, 0, intent, 0)

        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getService(
            ctx, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getForegroundService(ctx, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getBroadcast(
            ctx, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getService(ctx, 0, intent, 0x40000000)

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getActivity(ctx, 0, intent, 0x4000_0000 or 0x04000000)

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getActivities(ctx, 0, arrayOf(intent), 1073741824)
    }
}