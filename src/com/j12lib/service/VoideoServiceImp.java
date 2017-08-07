package com.j12lib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("VoideoService")
public class VoideoServiceImp extends JdbcTemplate implements VoideoService {

	@Override
	public List<Map<String, Object>> saveGenres(int prenId, String genresName) {
		String sql = "INSERT INTO tb_genres (prenId,name)VALUES(" + prenId
				+ ",'" + genresName + "')";
		int isoko = this.save(sql);
		if (isoko > 0) {
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
		String sql = "SELECT  id,prenId,NAME  FROM tb_genres  WHERE    NAME='"
				+ genresName + "' " + pendID + "";
		if (this.queryForList(sql).size() == 0) {
			return this.saveGenres(prenId, genresName);
		} else {
			return this.queryForList(sql);
		}
	}

	@Override
	public List<Map<String, Object>> saveVoide(Map<String, Object> videosMap) {
		String sql = "";

		String video_title = (String) videosMap.get("video_title");
		String video_code = (String) videosMap.get("video_code");
		// String video_genres = (String) videosMap.get("video_genres");
		String videos_date = (String) videosMap.get("videos_date");
		String videos_length = (String) videosMap.get("videos_length");
		String video_label = (String) videosMap.get("video_label");// 发行商
		String video_maker = (String) videosMap.get("video_maker");// 制作商
		List<Map<String, Object>> director = this.queryCast(
				(String) videosMap.get("video_director"), 1);// 导演
		Map<String, Object> directorMap = director.get(0);

		int video_director = (int) directorMap.get("id");// 导演id
		// String video_cast = (String) videosMap.get("video_cast");// 演员表

		sql = "INSERT INTO tb_videos (video_title, video_code, videos_date, videos_length, video_label, video_maker, video_director, video_img)"
				+ "VALUES('"+ video_title+ "',  '"+ video_code + "',  '"
				+ videos_date
				+ "',  '"
				+ videos_length
				+ "',  '"
				+ video_label
				+ "','" + video_maker + "', " + video_director + ",0);";
		if (this.save(sql) >= 0) {
			return this.queryVoide(video_code);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryVoide(String coding) {

		String sqlS = "";
		if (coding != null && coding != "") {
			sqlS = "SELECT  *  FROM tb_videos  WHERE    video_code='" + coding
					+ "'";
			System.out.println(sqlS);
		}

		return this.queryForList(sqlS);
	}

	@Override
	public List<Map<String, Object>> queryCast(String name, int number) {
		String sqlS = "";
		if (number > 0) {
			sqlS = " and  number=" + number;
		}
		System.out.println(sqlS);
		String sql = "SELECT  *  FROM tb_cast  WHERE    NAME='" + name + "' " + sqlS + "";
		List<Map<String, Object>> casts = this.queryForList(sql);
		// casts[0].get("id")
		if (casts.size() <= 0) {
			int isok = this.saveCast(name, number);
			if (isok >= 0) {
				return this.queryCast(name, number);
			}
		}
		return casts;
	}

	@Override
	public int saveCast(String name, int number) {
		String sql = "INSERT INTO tb_cast (NAME,number)VALUES('" + name + "'," + number + ")";
		/*if(this.save(sql)>=0){
			return this.queryCast(name, number);
		}*/
		return this.save(sql);
	}

	@Override
	public int querylabel(Map<String, Object> videosEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int savelabel(Map<String, Object>  videosEntity) {
		String sql = "";
		return save(sql);
	}

	@Override
	public int saveMaker(Map<String, Object>  videosEntity) {
		String sql = "";
		return save(sql);
	}

	@Override
	public int queryMaker(Map<String, Object>  videosEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int save(String sql) {
		System.out.println(sql);
		return this.update(sql);
	}
	private List<Map<String, Object>>  query(String sql) {
		return this.queryForList(sql);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList setGenres(String genres) {
		ArrayList genresArray = new ArrayList();
				
		List<Map<String, Object>> genresList = null;
		String[] genresStrArray = genres.split(",");
		for (int i = 0; i < genresStrArray.length; i++) {
			genresList = this.queryGenres(0, genresStrArray[i]);
			if (genresList.size() > 0) {
				int id = (int) genresList.get(0).get("id");
				if (id >= 0) {
					
					genresArray.add(id);
				}
			} else {
				genresList = this.saveGenres(0, genresStrArray[i]);
				int id = (int) genresList.get(0).get("id");
				if (id >= 0) {
					genresArray.add(id);
				}
			}
		}
		return genresArray;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList setCast(String castStr, int nuber) {
		ArrayList castStrArray =new ArrayList();
		List<Map<String, Object>> cast=null;
		String[] castArray = castStr.split(",");
		for (int i = 0; i < castArray.length; i++) {
			cast= this.queryCast(castArray[i], nuber);
			if(cast.size()>0){
				int id = (int) cast.get(0).get("id");
				if (id >= 0) {
					castStrArray.add(id);
				}
			}else{
				//cast= this.saveCast(castArray[i], nuber);
				int id = (int) cast.get(0).get("id");
				if (id >= 0) {
					castStrArray.add(id);
				}
			}
		}
		return castStrArray;
	}

	private int voideId(Map<String, Object> videosMap) {
		List<Map<String, Object>> videosList = null;
		videosList = this.queryVoide((String) videosMap.get("video_code"));
		int id = 0;
		if (videosList.size() > 0) {
			id = (int) videosList.get(0).get("id");
		} else {
			videosList = this.saveVoide(videosMap);
			id = (int) videosList.get(0).get("id");
		}

		return id;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String saveinfo(Map<String, Object> map) {
		int voideId = this.voideId(map);// 视频id
		ArrayList video_genres = this.setGenres((String) map.get("video_genres"));
		ArrayList video_cast = this.setCast((String) map.get("video_cast"), 0);// 演员表   0：演员 1：导演
	
		for (int i = 0; i < video_genres.size(); i++) {
			int item=	(int) video_genres.get(i);
			
			List<Map<String, Object>> video_genresList=this.query("SELECT * FROM tb_video_cast WHERE videoId="+voideId+" and castId=0 and genreId="+item+" and number=0");
			if(video_genresList.size()==0){
				String sql="INSERT INTO tb_video_cast(videoId,castId,genreId,number) VALUES("+voideId+",0,"+item+",0)";
				System.out.println(sql);
				this.save(sql);
			}
		
		}
		for (int i = 0; i < video_cast.size(); i++) {
			int item=(int) video_cast.get(i);
			
			List<Map<String, Object>> castList=this.query("SELECT * FROM tb_video_cast WHERE videoId="+voideId+" and genreId=0 and castId="+item+"  and number=1");
			if(castList.size()==0){
				String sql="INSERT INTO tb_video_cast(videoId,castId,genreId,number) VALUES("+voideId+","+item+",0,1)";
				System.out.println(sql);
				this.save(sql);
			}
		
		}
		return "";

	}

}
