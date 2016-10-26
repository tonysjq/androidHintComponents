package danubis.tony.hintcomponent;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Ton on 2016/10/24.
 */

public class HintComponent extends RelativeLayout implements View.OnClickListener {

    private ImageView showHideHintImageView;
    private GridView hintGridview;
    private View firstHintView;
    private View showHideHintView;
    private Boolean showAllhint = false;
    private int screenHeight;
    private int numColumns;
    private int numRows;


    private int firstHintHeight;
    private int rowHeight;

    public HintComponent(Context context) {
        super(context);

    }

    public HintComponent(Context context, AttributeSet attrs) {

        super(context, attrs);


    }

    public HintComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    //6个参数 第一个传入的hint的arraylist 第二个此view的最高height,第三列数，第四个hint的高度，第5每行的高度
    public void init(ArrayList<String> hintlist, float heightPercentage, int numColumns, int firstHintHeight, int rowHeight) {

        this.numColumns = numColumns;
        this.numRows = calculateRowNumber(hintlist);
        this.firstHintHeight = firstHintHeight;
        this.rowHeight = rowHeight;

        inflate(getContext(), R.layout.hint_component, this);
        showHideHintImageView = (ImageView) findViewById(R.id.show_and_hide_image_view);
        hintGridview = (GridView) findViewById(R.id.hint_grid_view);
        firstHintView = findViewById(R.id.first_hint_view);
        showHideHintView = findViewById(R.id.show_and_hide_view);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;

        hintGridview.setNumColumns(numColumns);

        firstHintView.getLayoutParams().height = dpToPx(firstHintHeight);


        firstHintView.setY(screenHeight - dpToPx(firstHintHeight));
        hintGridview.setY(screenHeight);


        Log.e("gaodu",":"+(numRows * rowHeight + numRows - 1));

        if ((numRows * rowHeight + numRows) <= pxToDp((int)(screenHeight*heightPercentage))) {
            Log.e("number of rows",""+numRows);
            hintGridview.getLayoutParams().height = dpToPx(numRows * rowHeight + numRows);

        } else {
            hintGridview.getLayoutParams().height = (int)(screenHeight*heightPercentage) - dpToPx(firstHintHeight);
        }
        hintGridview.requestLayout();

        final HintAdapter hintAdapter = new HintAdapter(this.getContext(), R.layout.hint_textview, hintlist,rowHeight);
        hintGridview.setAdapter(hintAdapter);
        showHideHintView.setOnClickListener(this);

        hintGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private int calculateRowNumber(ArrayList<String> hintlist) {
        int rowNumber;
        int hintnumber = hintlist.size();

        if ((hintnumber % numColumns) == 0) {
            rowNumber = hintnumber / numColumns;
        } else {
            rowNumber = ((int) (hintnumber / numColumns)) + 1;
        }

        return rowNumber;
    }


    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    @Override
    public void onClick(View view) {
        if (!showAllhint) {
            showHint();
        } else {
            hideHint();
        }

    }

    public void hideHint() {
        showHideHintView.setOnClickListener(null);

        moveViewAnimation(firstHintView.getY(), screenHeight - dpToPx(firstHintHeight), hintGridview.getY(), screenHeight, -180f, 0f);
        showAllhint = false;
    }

    public void showHint() {
        showHideHintView.setOnClickListener(null);

        moveViewAnimation(firstHintView.getY(), screenHeight - hintGridview.getHeight() - firstHintView.getHeight(), screenHeight, screenHeight - hintGridview.getHeight() , 0f, -180f);
        showAllhint = true;

    }

    private void moveViewAnimation(float firstHintViewFromY, float firstHintViewToY, float hintGridviewFromY, float hintGridviewToY, float rotationFrom, float rotationTo) {
        ObjectAnimator movefirstHintViewUp = ObjectAnimator.ofFloat(firstHintView, "y", firstHintViewFromY, firstHintViewToY);
        ObjectAnimator moevhintGridviewUp = ObjectAnimator.ofFloat(hintGridview, "y", hintGridviewFromY, hintGridviewToY);
        ObjectAnimator rotateArrow = ObjectAnimator.ofFloat(showHideHintImageView, "rotation", rotationFrom, rotationTo);
        AnimatorSet moveViewUp = new AnimatorSet();
        moveViewUp.playTogether(movefirstHintViewUp, moevhintGridviewUp, rotateArrow);
        moveViewUp.setDuration(380);
        moveViewUp.start();
        moveViewUp.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                showHideHintView.setOnClickListener(HintComponent.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
