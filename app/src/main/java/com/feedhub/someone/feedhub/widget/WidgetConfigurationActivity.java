package com.feedhub.someone.feedhub.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.feedhub.someone.feedhub.R;

public class WidgetConfigurationActivity extends AppCompatActivity {
    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_configuration);
        //Context context = this;
        Log.e("INFO", "ENTERING WIDGET CONFIGURATION ACTIVITY");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        //RetrofitCall();
        //perform widget configuration
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        RemoteViews views = new RemoteViews(getPackageName(), R.layout.feed_hub_app_widget);
        appWidgetManager.updateAppWidget(mAppWidgetId, views);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        //finish();

    }
//
//    private void RetrofitCall() {
//        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
//        Call<List<RecipeModel>> call = service.getRecipes();
//        ExecuteClient(call);
//    }

//    private void ExecuteClient(Call<List<RecipeModel>> call) {
//
//        try {
//            call.enqueue(new Callback<List<RecipeModel>>(){
//
//                @Override
//                public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
//                    Log.e("$lala SUCCESS -> ", "SUCCESS");
//
//                    RecyclerView recyclerRV = findViewById(R.id.recipeRV);
//                    ConfigurationActivityAdapter adapter = new ConfigurationActivityAdapter(context, response.body(), mAppWidgetId);
//                    /*setting up adapter for recycler view in configuration activity*/
//                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                    recyclerRV.setLayoutManager(layoutManager);
//                    recyclerRV.setAdapter(adapter);
//                    //finish();
//                }
//
//                @Override
//                public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
//                    Log.e("$lala ERROR -> ", t.toString());
//                }
//
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}