package com.player.dao.dbutil;

public class PlayerSearchQueries {

	public static final String GETPLAYERBYID = "select  name, age, gender, teamname, contact from roc_revature.player where id=?";
	public static final String GETALLPLAYERS = "select id,name,age,gender,teamname,contact from roc_revature.player";
	public static final String GETPLAYERSBYGENDER = "select id,name,age,teamname,contact from roc_revature.player where gender=?";
	public static final String GET_PLAYERS_BY_TEAM = "select id,name,age,teamname,contact,gender from roc_revature.player where teamname=?";
	public static final String GET_PLAYER_BY_NAME =  "select  id, name, age, gender, teamname, contact from roc_revature.player where name=?";
	public static final String GET_PLAYER_BY_CONTACT_NUMBER =  "select  id, name, age, gender, teamname, contact from roc_revature.player where contact=?";
	public static final String GET_PLAYERS_BY_AGE = "select id, name, age, gender, teamname, contact from roc_revature.player where age=?";
}
