package hintview.tony.danubis.hintviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import danubis.tony.hintcomponent.HintComponent;

public class MainActivity extends AppCompatActivity {

    private HintComponent hintComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        hintComponent = (HintComponent) findViewById(R.id.hint);
        ArrayList<String> hintArrayList = new ArrayList<>();
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");
        hintArrayList.add("haha");
        hintArrayList.add("hagfsdsdfgsdgadgaha");



        //6个参数 第一个传入的hint的arraylist 第二个此view占整个view的百分比,第三列数，第四个第一个 hint的高度，第5每行的高度,第6每行需要调整额外加的高度
        //另一个板500,1,3,75,25
        hintComponent.init(hintArrayList,0.5f,2,50,50);

    }

}
