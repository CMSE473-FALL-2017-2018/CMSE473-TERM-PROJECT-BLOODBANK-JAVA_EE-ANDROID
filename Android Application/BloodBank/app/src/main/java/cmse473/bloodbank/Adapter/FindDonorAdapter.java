package cmse473.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cmse473.bloodbank.Model.User;
import cmse473.bloodbank.R;

public class FindDonorAdapter extends ArrayAdapter<User> {

    private LayoutInflater inflater;
    private Context context;
    private ViewHolder holder;
    private ArrayList<User> uList;

    public FindDonorAdapter(Context context, ArrayList<User> list) {
        super(context,0, list);
        this.context = context;
        this.uList = list;
        inflater = LayoutInflater.from(context);
    }

    // provides to avoid view injection and increases the performance
    private static class ViewHolder {
        TextView name, mail, year, type, city, donor;
    }

    @Override
    public int getCount() {
        return uList.size();
    }

    @Override
    public User getItem(int position) {
        return uList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return uList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_donor, null);
            holder = new ViewHolder();

            holder.name =  convertView.findViewById(R.id.txtName);
            holder.mail =  convertView.findViewById(R.id.txtEmail);
            holder.city = convertView.findViewById(R.id.txtCity);
            holder.donor = convertView.findViewById(R.id.txtAvailable);
            holder.type = convertView.findViewById(R.id.txtType);
            holder.year = convertView.findViewById(R.id.txtBy);

            convertView.setTag(holder);

        }else{
            //Set viewholder already created
            holder = (ViewHolder)convertView.getTag();
        }

        User user = getItem(position); // uList.get(position);

        if(user != null){
           holder.name.setText(user.getName());
           holder.mail.setText(user.getEmail());
           holder.city.setText(user.getCity());
           holder.donor.setText(user.getDonor());
           holder.type.setText(user.getBloodType());
           holder.year.setText(String.valueOf(user.getBirthYear()));
        }

        return convertView;
    }
}
