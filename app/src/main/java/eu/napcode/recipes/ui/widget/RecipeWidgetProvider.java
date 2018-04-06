package eu.napcode.recipes.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import eu.napcode.recipes.R;
import eu.napcode.recipes.ui.recipes.RecipesActivity;

public class RecipeWidgetProvider extends AppWidgetProvider {

    private CharSequence ingredientsSpannable;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        final int widgetsCount = appWidgetIds.length;

        for (int i = 0; i < widgetsCount; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, RecipesActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_recipe);
            views.setTextViewText(R.id.widgetIngredientsTextView, getIngredientsSpannable());

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    public CharSequence getIngredientsSpannable() {
        return ingredientsSpannable;
    }
}
