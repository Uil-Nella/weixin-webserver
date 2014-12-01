package com.yonyou.weixin.core.model;

/**
 * 音乐消息
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月1日 下午6:28:24
 * <p> @version 0.0.1
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
