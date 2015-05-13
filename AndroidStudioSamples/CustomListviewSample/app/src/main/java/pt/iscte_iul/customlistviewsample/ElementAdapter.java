package pt.iscte_iul.customlistviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cserrao on 13/05/15.
 */
public class ElementAdapter extends ArrayAdapter<Element> {
    private final Context context;
    private final ArrayList<Element> itemsArrayList;

    public ElementAdapter(Context context, ArrayList<Element> itemsArrayList) {
        super(context, R.layout.element_custom_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.element_custom_row, parent, false);

        TextView tvElementSymbol = (TextView) rowView.findViewById(R.id.tvElementSymbol);
        TextView tvElementName = (TextView) rowView.findViewById(R.id.tvElementName);

        tvElementSymbol.setText(itemsArrayList.get(position).getElementSymbol());
        tvElementName.setText(itemsArrayList.get(position).getElementName());

        return rowView;

    }
}
