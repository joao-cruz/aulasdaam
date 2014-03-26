package pt.iscte.daam.soccerleague;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TeamsBaseAdapter extends BaseAdapter{
	private static ArrayList<Teams> teamsArrayList;
	
	private LayoutInflater mInflater;
	
	protected ViewHolder holder;
	
	public TeamsBaseAdapter(Context context, ArrayList<Teams> teams)
	{
		teamsArrayList = teams;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return teamsArrayList.size();
	}

	@Override
	public Teams getItem(int position) {
		// TODO Auto-generated method stub
		return teamsArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null)
		{
			convertView = mInflater.inflate(R.layout.teamslist_row_view, null);
			holder = new ViewHolder();
			holder.tName = (TextView) convertView.findViewById(R.id.textViewTeamName);
			holder.tCity = (TextView) convertView.findViewById(R.id.textViewTeamCity);
            
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Teams teams = teamsArrayList.get(position);
		holder.tName.setText(teams.getTeamName());
		holder.tCity.setText(teams.getTeamcity());
		
		return convertView;
	}
	
	static class ViewHolder
	{
		TextView tName;
		TextView tCity;
	}
}
