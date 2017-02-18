package net.hama_du.githubviewer;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hama_du on 2017/02/18.
 */
public class GithubViewerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
