package com.j12lib.service;

import java.util.List;
import java.util.Map;


public interface VoideoService {

	/**
	 * 添加类型
	 * @param genresEntity
	 * @return
	 */
	public  List<Map<String, Object>>  saveGenres(int prenId, String genresName);

	/**
	 * 查询类型
	 * @param typeEntity
	 * @return
	 */
	public  List<Map<String, Object>> queryGenres(int prenId, String genresName);

	/**
	 * 添加视频信息
	 * 
	 * @param videosEntity
	 * @return
	 */
	public List<Map<String, Object>> saveVoide(Map<String, Object> map);

	/**
	 * 查询视频信息
	 * 
	 * @param videosEntity
	 * @return
	 */
	public List<Map<String, Object>> queryVoide(String videoCoding);

	/**
	 * 查询演员
	 * 
	 * @param name
	 *            查询名
	 * @param number 0、演员 1、导演
	 *           
	 * @return
	 */
	public List<Map<String, Object>> queryCast(String name, int number);

	/**
	 * 添加演员
	 * 
	 * @param name
	 *            名称
	 * @param number
	 *            0、演员 1、导演
	 * @return
	 */
	public int saveCast(String name, int number);

	/**
	 * 发行商
	 * 
	 * @param videosEntity
	 * @return
	 */
	public int querylabel(Map<String, Object>  videosEntity);

	/**
	 * 发行商
	 * 
	 * @param videosEntity
	 * @return
	 */
	public int savelabel(Map<String, Object>  videosEntity);

	/**
	 * 添加制作商
	 * 
	 * @param videosEntity
	 * @return
	 */
	public int saveMaker(Map<String, Object>  videosEntity);

	/**
	 * 查询制作商
	 * 
	 * @param videosEntity
	 * @return
	 */
	public int queryMaker(Map<String, Object>  videosEntity);
	
	public String saveinfo(Map<String, Object> map);
}
