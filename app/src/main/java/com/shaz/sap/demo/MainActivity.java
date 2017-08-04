package com.shaz.sap.demo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Toast;

import com.shaz.sap.Puller;
import com.shaz.sap.pojo.PackageBean;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText packageName;
    private AppCompatTextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packageName = (AppCompatEditText) findViewById(R.id.packageName);
        result = (AppCompatTextView) findViewById(R.id.result);
        result.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void checkUpdate(View view) {
        String packageName = this.packageName.getText().toString();
        if (!TextUtils.isEmpty(packageName) && packageName.trim().length() > 0) {
            result.setText(null);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Fetching details");
            progressDialog.setIndeterminate(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            Puller.getInstance().pull(this, packageName, new Puller.PullerListener() {

                @Override
                public void onResult(PackageBean bean) {
                    result.setText("Name: " + bean.getAppName() + "\n\nPackage Name: " + bean.getPackageName() + "\n\nCategory: " + bean.getCategory() + "\n\nAuthor: " + bean.getAuthor()
                            + "\n\nRating Score: " + bean.getRatingScore() + "\n\nRating Count: " + bean.getRatingCount()
                            + "\n\nDownloads: " + bean.getDownloads() + "\n\nVersion: " + bean.getVersion() + "\n\nDescription:\n" + bean.getDescription()
                            + "\n\nLast version description:\n" + bean.getLastVersionDescription() + "\n\nPublish Date: " + bean.getPublishDate());
                    if (progressDialog != null)
                        progressDialog.dismiss();
                }

                @Override
                public void onApplicationNotFound() {
                    if (progressDialog != null)
                        progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Application not available in play store", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(String reason) {
                    if (progressDialog != null)
                        progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Error: " + reason, Toast.LENGTH_LONG).show();
                }

            });
            return;
        }
        Toast.makeText(MainActivity.this, "Please enter package name", Toast.LENGTH_SHORT).show();
    }
}
