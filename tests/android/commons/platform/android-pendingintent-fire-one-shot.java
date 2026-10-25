import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class PendingIntentTest {

    private final Context ctx;

    public PendingIntentTest(Context ctx) {
        this.ctx = ctx;
    }

    public void test(Intent intent) {
        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getBroadcast(ctx, 0, intent, 0);

        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getService(
            ctx, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // ruleid: android-pendingintent-fire-one-shot
        PendingIntent.getForegroundService(ctx, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getBroadcast(
            ctx, 0, intent,
            PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
        );

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getService(ctx, 0, intent, 0x40000000);

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getActivity(ctx, 0, intent, 0x4000_0000 | 0x04000000);

        // ok: android-pendingintent-fire-one-shot
        PendingIntent.getActivities(ctx, 0, new Intent[]{intent}, 1073741824);
    }
}