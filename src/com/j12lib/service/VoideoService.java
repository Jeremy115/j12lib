package com.j12lib.service;

import java.util.List;
import java.util.Map;

import com.j12lib.entity.GenresEntity;
import com.j12lib.entity.VideosEntity;

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
	public int querylabel(VideosEntity videosEntity);

	/**
	 * 发行商
	 * 
	 * @param videosEntity
	 * @return
	 */
	public int savelabel(VideosEntity videosEntity);

	/**
	 * 添加制作商
	 * 
	 * @param videosEntity
	 * @return
	 */
	public int saveMaker(VideosEntity videosEntity);

	/**
	 * 查询制作商
	 * 
	 * @param videosEntity
	 * @return
	 */
	public int queryMaker(VideosEntity videosEntity);
	
	public String saveinfo(Map<String, Object> map);
}
