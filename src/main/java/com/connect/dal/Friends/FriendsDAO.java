package com.connect.dal.Friends;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.connect.models.Friends.Edges;
import com.connect.models.Friends.Friends;
import com.connect.models.UserMgmt.User;

@Component("friendsDao")
public class FriendsDAO {

	private NamedParameterJdbcTemplate jdbc;


	@Autowired	
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public Friends getName(int id){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id", id);
		/*List<Friends> temp=isNodesEmpty();
		if(temp.isEmpty()){
			boolean status=addToNodes();
		}
	*/
		List<Friends> fl = jdbc.query("select * from nodes where Id=  :id", params, 
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();

				friend.setId(rs.getInt("Id"));
				friend.setName(rs.getString("name"));
				return friend;
			}

		});
		
		if(fl.isEmpty())
			return null;
		else
			return fl.get(0);
		
	}
	
	public List<Friends> getId(String name){
		
		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("name", name);
		//List<Friends> temp=isNodesEmpty();
		/*if(temp.isEmpty()){
			boolean status=addToNodes();
		}*/
		return jdbc.query("select * from nodes where name like :name",params,
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();

				friend.setId(rs.getInt("Id"));
				friend.setName(rs.getString("name"));
				return friend;
			}

		});
		}
	

	public List <Friends> getFriends(int id){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id", id);


		return jdbc.query("select * from edges where U1=:id",params,
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();

				friend.setId(rs.getInt("U2"));
				return friend;
			}

		});


	}
	public static Graph graph1;
	public List <Friends> findPerson(int id, String name){
	//	System.out.println("it came here and name: "+name);
		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("name", name);

		List <Friends> tempFriends= jdbc.query("select * from nodes where name like :name",params,
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();
				StringBuilder sb = new StringBuilder();
				
				sb.append("No connection with the person");
				int personId=rs.getInt("Id"); //Id of the person
				friend.setId(personId);
				friend.setName(rs.getString("name"));
				friend.setSb(sb);
				return friend;

				
		}
		});
		makeGraphs(graph1);
		List<Edges> friendEdges;
		friendEdges=getAllEdges();
		//System.out.println("Edges:"+friendEdges.size());
		if(friendEdges.isEmpty() || tempFriends.isEmpty()){
			return tempFriends;
		}
		Iterator<String> it= new BreadthFirstIterator(graph1, Integer.toString(id));
		StringBuilder sb=new StringBuilder();
		
		System.out.println("number of people with name:"+name+" are: "+tempFriends.size());
		
		for(int i=0;i<tempFriends.size();i++){
			
			Friends temp= new Friends();
			while (it.hasNext()) {
	
				String element=it.next();	
				int elementId=Integer.parseInt(element);
				temp=getName(elementId);
	
				sb.append(temp.getName()).append("->");
				
				if(Integer.toString(tempFriends.get(i).getId()).equalsIgnoreCase(element)){
					break;		
				}
	
			}
			// System.out.println("String:"+sb);
			tempFriends.get(i).setSb(sb);
		//	tempFriends.get(i).setName(name);
			//friend.setLocation(rs.getString("location"));
		}
		return tempFriends;
	}



	public List <Friends> findFriend(int id, String name){



		List<Friends> tempList= new ArrayList<Friends>();
		List<Friends> finalList= new ArrayList<Friends>();
		//get list all the id's associated with same name
		tempList=getId(name);
		//check if no id is attached with that name. No such person exists.
		if(tempList.isEmpty()){
			System.out.print("No data");
			return tempList;

		}
		//check if that person is in the friend list. 
		else{
			//System.out.println("temp size:"+tempList.size());
			for(int i=0;i<tempList.size();i++){
				//if they are friends, add the person to final list.
				List <Friends> temp= alreadyFriends(id,tempList.get(i).getId());
				//System.out.println("Count="+temp.get(0).getId());

				if(!temp.isEmpty()){
					finalList.add(tempList.get(i));
				}
			}
			return finalList;

		}
	}
	public int calculateMutual(int id1, int id2){

		List<Friends> friends1=getFriends(id1);
		List<Friends> friends2=getFriends(id2);
		if(friends1.isEmpty() || friends2.isEmpty()){
		//	System.out.println("0 iit is ");
			return 0;
		}
		Hashtable <Integer, Integer> common=new Hashtable<Integer, Integer>();


		for(int i=0;i<friends1.size();i++){
			for(int j=0;j<friends2.size();j++){
				if(friends1.get(i).getId()==friends2.get(j).getId()){
					if(!common.containsKey(friends1.get(i).getId())){
						System.out.println("it did come here...why/");
						common.put(friends1.get(i).getId(), i);
					}
				}
			}
		}

		System.out.println("comm size:"+ common.size());
		/*	System.out.println("common values");
	Set<Integer> keys = common.keySet();
    for(Integer key: keys){
        System.out.println("common ids "+key);
    }
		 */
		return common.size();

	}
	public List<Edges> getAllEdges(){

		MapSqlParameterSource params= new MapSqlParameterSource();


		return jdbc.query("select * from edges",params,
				new RowMapper<Edges>(){

			@Override
			public Edges mapRow(ResultSet rs, int rowNum) throws SQLException {
				Edges ed= new Edges();

				ed.setU1(rs.getInt("U1"));
				ed.setU2(rs.getInt("U2"));
				//friend.setName(rs.getString("name"));
				return ed;
			}

		});
	}
	public void makeGraphs(Graph g) {
		g = graph1 = new Graph();
		List<Edges> friendEdges;
		friendEdges=getAllEdges();
		System.out.println("Edges:"+friendEdges.size());
		for(int i=0;i<friendEdges.size();i++){
			g.addEdge(Integer.toString(friendEdges.get(i).getU1()),Integer.toString(friendEdges.get(i).getU2()));
//			g.addEdge(Integer.toString(friendEdges.get(i).getU2()),Integer.toString(friendEdges.get(i).getU1()));

		}
	}
	public List <Friends> getRecentFriends(int id){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id", id);


		return jdbc.query("select U2 from edges where U1=:id order by currentTime Desc Limit 5",params,
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();

				friend.setId(rs.getInt("U2"));
				//friend.setName(rs.getString("name"));
				return friend;
			}

		});


	}
	public List <Friends> mutualFriend(int id1, int id2){

		List<Friends> friends1=getFriends(id1);
		List<Friends> friends2=getFriends(id2);

		
		Hashtable <Integer, Integer> common=new Hashtable<Integer, Integer>();


		for(int i=0;i<friends1.size();i++){
			for(int j=0;j<friends2.size();j++){
				if(friends1.get(i).getId()==friends2.get(j).getId()){
					if(!common.containsKey(friends1.get(i).getId())){

						common.put(friends1.get(i).getId(), i);
					}
				}
			}
		}
		System.out.println("comon size:"+common.size());
		List<Friends> finalList= new ArrayList<Friends>();

		Set<Integer> keys = common.keySet();
		for(Integer key: keys){
			Friends temp=getName(key);
			finalList.add(temp);
		}

		return finalList;


	}
	public boolean findStatus(int id1, int id2){
		List<Friends> temp=alreadyFriends(id1,id2);
		if(temp.isEmpty()){
			return false;
		}
		return true;

	}

	public List <Friends> getFriendReq(int id){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id", id);


		return jdbc.query("select id1 from relations where id2=:id AND friendshipStatus=0",params,
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();

				friend.setId(rs.getInt("id1"));
				//friend.setName(rs.getString("name"));
				return friend;
			}

		});

	}
	public List<User> getProfile(int id){
		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.query("select * from users where user_id=  :id", params, 
				new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user= new User();

				//user.setId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("user_email"));
				
				return user;
			}

		});
		
	}
	public boolean acceptFriend(int id1, int id2){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id1", id1);
		params.addValue("id2", id2);

		//Accepting friend request, status=1
		if(jdbc.update("update relations set friendshipStatus=1 where id1=:id1 AND id2=:id2", params)==1){
			//Add edge in the edges table
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String datetime=dateFormat.format(date);
			params.addValue("datetime", datetime);
			/*if(alreadyFriends(id1,id2)==true){
		return true;
	}*/
			List<Friends> flag=alreadyFriends(id1,id2);
			if(!flag.isEmpty()){
				return true;
			}
			else{
				jdbc.update("insert into edges values (:id1, :id2, :datetime)", params);
				jdbc.update("insert into edges values (:id2, :id1, :datetime)", params);
				
					return true;
				}
			}
		
		return false;



	}

	public List<Friends> alreadyFriends(int id1,int id2){
		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id1", id1);
		params.addValue("id2", id2);
		//System.out.print("fsfs");


		return jdbc.query("select * from edges where (U1=:id1 AND U2=:id2) or (U1=:id2 AND U2=:id1)",params,
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();


				friend.setId(1);

				//friend.setName(name);
				//friend.setLocation(rs.getString("location"));
				return friend;
			}

		});


	}
	public boolean rejectFriend(int id1, int id2){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id1", id1);
		params.addValue("id2", id2);

		//Rejecting friend request, delete row
		jdbc.update("delete from relations where id1=:id1 AND id2=:id2", params);
		jdbc.update("delete from relations where id1=:id2 AND id2=:id1", params);
		return true;
	}
	public boolean removeFriend(int id1, int id2){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id1", id1);
		params.addValue("id2", id2);

		//Rejecting friend request, delete row
		jdbc.update("delete from edges where U1=:id1 AND U2=:id2", params);
		jdbc.update("delete from edges where U1=:id2 AND U2=:id1", params);
		
		jdbc.update("delete from relations where id1=:id1 AND id2=:id2", params);
		jdbc.update("delete from relations where id1=:id2 AND id2=:id1", params);

		return true;
	}
	public boolean addFriend(int id1, int id2){

		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id1", id1);
		params.addValue("id2", id2);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datetime=dateFormat.format(date);
		params.addValue("datetime", datetime);
		//Sending friend request, status=0
		List<Friends> reqSent=reqSent(id1,id2);
		if(reqSent.isEmpty()){
			//System.out.println("first time req");
			return jdbc.update("insert into relations values (:id1, :id2, :datetime , 0)", params)==1;
		}
		else{
			//System.out.println("no first time req");
			return false;
		}
	}

	public List<Friends> reqSent(int id1,int id2){
		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("id1", id1);
		params.addValue("id2", id2);

		return jdbc.query("select * from relations where id1=:id1 AND id2=:id2",params,
				new RowMapper<Friends>(){

			@Override
			public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
				Friends friend= new Friends();

				friend.setId(1);
				//friend.setName(name);
				//friend.setLocation(rs.getString("location"));
				return friend;
			}

		});

	}
}