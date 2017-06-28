<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head></head>
<script type="text/javascript" src="assets/jquery-1.8.0.min.js"></script>
<body>
<h1>success</h1>
	<script type="text/javascript">
	$(function(){
		var ss={ video_title: 'NSPS-514 不道徳なオナニー',
				  video_code: 'NSPS-514',
				  video_genres: '自慰,已婚妇女,妄想,玩具,戏剧,',
				  videos_date: '2016-10-07',
				  videos_length: '150 分钟',
				  video_label: 'ながえSTYLE',
				  video_maker: 'ながえスタイル',
				  video_director: 'ながえ',
				  video_cast: '杏子ゆう,こずえまき,岡山涼花,菊島夕子,' }
		$.ajax({
			url:"http://localhost:8080/KIC/saveVoide.do",
			type: "post",
			dataType:"JSON",
			data:ss,
			success:function(msg){
				console.log(msg);
			}
		});
		
	})
</script>
</body>
</html>