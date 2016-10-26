package danubis.tony.hintcomponent;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ton on 2016/10/24.
 */

public class HintAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList collectionData=new ArrayList();
    private int rowHeight;



    public  HintAdapter(Context context, int layoutResourceId, ArrayList data,int rowHeight) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.collectionData = data;
        this.rowHeight=rowHeight;

    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            row.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, dpToPx(rowHeight)));
            holder = new ViewHolder();
            holder.hint = (TextView) row.findViewById(R.id.hint_text);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        final String data = (String) collectionData.get(position);

        //把itemName 链接到Imageview 然后把ImageView 传到异步task中
        holder.hint.setText(data);


        return row;
    }

    static class ViewHolder {
        ImageView image;
        TextView hint;
    }
    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
