package cmse473.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cmse473.bloodbank.Model.Seek;
import cmse473.bloodbank.R;

public class FindSeekerAdapter extends ArrayAdapter<Seek> {

    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;
    private ArrayList<Seek> sList;

    public FindSeekerAdapter(Context context, ArrayList<Seek> list) {
        super(context,0, list);
        this.context = context;
        this.sList = list;
        inflater = LayoutInflater.from(context);
    }

    // provides to avoid view injection and increases the performance
    private static class ViewHolder {
        TextView name, mail, type, city, date;
    }

    @Override
    public int getCount() {
        return sList.size();
    }

    @Override
    public Seek getItem(int position) {
        return sList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_seek, null);
            holder = new ViewHolder();

            holder.name =  convertView.findViewById(R.id.txtSName);
            holder.mail =  convertView.findViewById(R.id.txtSEmail);
            holder.type  = convertView.findViewById(R.id.txtSType);
            holder.city = convertView.findViewById(R.id.txtSCity);
            holder.date = convertView.findViewById(R.id.txtSDate);


            convertView.setTag(holder);

        }else{
            //Set viewholder already created
            holder = (ViewHolder)convertView.getTag();
        }

        Seek seek = getItem(position); // sList.get(position);

        if(seek != null){
            holder.name.setText(seek.getName());
            holder.mail.setText(seek.getMail());
            holder.city.setText(seek.getCity());
            holder.type.setText(seek.getBloodType());
            holder.date.setText(seek.getDate());
        }

        return convertView;
    }
}
