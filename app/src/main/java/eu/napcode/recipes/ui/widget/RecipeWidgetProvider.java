package eu.napcode.recipes.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.RemoteViews;

import eu.napcode.recipes.R;
import eu.napcode.recipes.ui.recipes.RecipesActivity;

public class RecipeWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_RELOAD = "reload";
    public static final String EXTRA_WIDGET_ID = "widget id";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        final int widgetsCount = appWidgetIds.length;

        for (int i = 0; i < widgetsCount; i++) {
            int appWidgetId = appWidgetIds[i];

            updateView(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateView(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Intent intent = new Intent(context, RecipesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_recipe);
        views.setTextViewText(R.id.widgetIngredientsTextView, getIngredientsSpannable(context, appWidgetId));
        views.setOnClickPendingIntent(R.id.widgetIngredientsTextView, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public SpannableString getIngredientsSpannable(Context context, int widgetId) {
        String recipe = PreferenceManager.getDefaultSharedPreferences(context).getString("" + widgetId, "");

        if (recipe.isEmpty()) {
            return new SpannableString("");
        }

        SpannableString spannableString = new SpannableString(recipe);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, recipe.indexOf('\n'), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        updateView(context, AppWidgetManager.getInstance(context), intent.getIntExtra(EXTRA_WIDGET_ID, 0));
    }
}
