package com.j12lib.service;

import java.util.List;
import java.util.Map;

import oracle.net.aso.o;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.j12lib.entity.GenresEntity;
import com.j12lib.entity.VideosEntity;
import com.mysql.fabric.xmlrpc.base.Array;

@Service("VoideoService")
public class VoideoServiceImp extends JdbcTemplate implements VoideoService {

	@Override
	public List<Map<String, Object>> saveGenres(int prenId, String genresName) {
		String sql = "INSERT INTO tb_genres (prenId,name)VALUES('" + prenId	+ "'," + genresName + ")";
		int isoko=this.save(sql);
		if(isoko>0){
			return this.queryGenres(prenId, genresName);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryGenres(int prenId, String genresName) {
		String pendID = "";
		if (prenId > 0) {
			pendID = " and  prenId=" + prenId;
		}
		String sql = "SELECT  id,prenId,NAME  FROM tb_genres  WHERE    NAME='"+ genresName + "' " + pendID + "";
		if(this.queryForList(sql).size()==0){
			return	this.saveGenres(prenId, genresName);
		}else{
			return this.queryForList(sql);	
		}
	}

	@Override
	public int saveVoide(Map<String, Object> videosMap) {
		String sql = "";
		if (this.queryVoide((String) videosMap.get("video_code")) > 0) {

			String video_title = (String) videosMap.get("video_title");
			String video_code = (String) videosMap.get("video_code");
			String video_genres = (String) videosMap.get("video_genres");
			String videos_date = (String) videosMap.get("videos_date");
			String videos_length = (String) videosMap.get("videos_length");
			String video_label = (String) videosMap.get("video_label");// 发行商
			String video_maker = (String) videosMap.get("video_maker");// 制作商
			String video_director = (String) videosMap.get("video_director");// 导演
			String video_cast = (String) videosMap.get("video_cast");// 演员表

			sql = "INSERT INTO tb_videos (video_title, video_code, videos_date, videos_length, video_label, video_maker, video_director, video_img)"
					+ "VALUES('"
					+ video_title
					+ "',  '"
					+ video_code
					+ "',  '"
					+ videos_date
					+ "',  '"
					+ videos_length
					+ "',  '"
					+ video_label
					+ "','"
					+ video_maker
					+ "', "
					+ video_director + ",0);";
		}
		return save(sql);
	}

	@Override
	public int queryVoide(String coding) {

		String sqlS = "";
		if (coding != null && coding != "") {
			sqlS = "SELECT  id  FROM tb_genres  WHERE    NAME='" + coding + "'";
			System.out.println(sqlS);
		}

		return this.queryForInt(sqlS);
	}

	@Override
	public int queryCast(String name, int number) {
		String sqlS = "";
		if (number > 0) {
			sqlS = " and  number=" + number;
		}
		System.out.println(sqlS);
		String sql = "SELECT  id  FROM tb_genres  WHERE    NAME='" + name
				+ "' " + name + "";
		System.out.println(sql);
		return this.queryForInt(sql);
	}

	@Override
	public int saveCast(String name, int number) {
		String sql = "INSERT INTO tb_cast (NAME,number)VALUES('" + name + "',"
				+ number + ")";
		return save(sql);
	}

	@Override
	public int querylabel(VideosEntity videosEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int savelabel(VideosEntity videosEntity) {
		String sql = "";
		return save(sql);
	}

	@Override
	public int saveMaker(VideosEntity videosEntity) {
		String sql = "";
		return save(sql);
	}

	@Override
	public int queryMaker(VideosEntity videosEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int save(String sql) {
		System.out.println(sql);
		return this.update(sql);
	}

	public int[] setGenres(String map) {
		int[] genresArray = new int[] {};

		return genresArray;
	}

	public int[] setCast(String castStr,int nuber) {
		int[] castStrArray = new int[] {};
		String [] castArray=castStr.split(",");
		for (int i = 0; i < castArray.length; i++) {
		int id= this.queryCast(castArray[i],nuber);
			if(id>=0){
				castStrArray[i]=id;
			}else{
				
			}
			
		}
		return castStrArray;
	}

	@Override
	public String saveinfo(Map<String, Object> map) {
		int voideId = this.saveVoide(map);// 视频id
		int[] video_genres = this.setGenres((String) map.get("video_genres"));
		int[] video_cast = this.setCast((String) map.get("video_cast"),0);// 演员表 0：演员 1：导演

		
		return "";

	}

}
