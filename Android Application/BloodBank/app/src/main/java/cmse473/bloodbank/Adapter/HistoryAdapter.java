package cmse473.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cmse473.bloodbank.Activity.HistoryActivity;
import cmse473.bloodbank.ApplicationClass;
import cmse473.bloodbank.Model.Seek;
import cmse473.bloodbank.R;
import cmse473.bloodbank.RetrofitAPI.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryAdapter extends ArrayAdapter<Seek> {

    private LayoutInflater inflater;
    private Context context;
    private HistoryAdapter.ViewHolder holder;
    private ArrayList<Seek> sList;

    public HistoryAdapter(Context context, ArrayList<Seek> list) {
        super(context,0, list);
        this.context = context;
        this.sList = list;
        inflater = LayoutInflater.from(context);
    }

    // provides to avoid view injection and increases the performance
    private static class ViewHolder {
        TextView name, mail, type, city, date;
        Button delete;
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
            convertView = inflater.inflate(R.layout.custom_history, null);
            holder = new HistoryAdapter.ViewHolder();

            holder.name =  convertView.findViewById(R.id.txtHName);
            holder.mail =  convertView.findViewById(R.id.txtHMail);
            holder.type  = convertView.findViewById(R.id.txtHType);
            holder.city = convertView.findViewById(R.id.txtHCity);
            holder.date = convertView.findViewById(R.id.txtHDate);
            holder.delete = convertView.findViewById(R.id.btnHDelete);

            convertView.setTag(holder);

        }else{
            //Set viewholder already created
            holder = (HistoryAdapter.ViewHolder)convertView.getTag();
        }

        final Seek seek = getItem(position); // sList.get(position);

        if(seek != null){
            holder.name.setText(seek.getName());
            holder.mail.setText(seek.getMail());
            holder.city.setText(seek.getCity());
            holder.type.setText(seek.getBloodType());
            holder.date.setText(seek.getDate());

            //delete the selected history
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(seek.getId());
                }
            });
        }

        return convertView;
    }

    /**
     * API Operations
     * **/

    private void delete(String id){
        ApiUtils.getAPIService().deleteHistory(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    HistoryActivity.adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
}
