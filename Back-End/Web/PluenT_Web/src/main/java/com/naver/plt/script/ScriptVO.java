package com.naver.plt.script;

import java.sql.Date;

public class ScriptVO {
	
	private String user_id;
	private int script_id;
	private String title;
	private String content;
	private String script;
	private int pttime;
	private String voice_path;
	private Date regdate;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getScript_id() {
		return script_id;
	}
	public void setScript_id(int script_id) {
		this.script_id = script_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public int getPttime() {
		return pttime;
	}
	public void setPttime(int pttime) {
		this.pttime = pttime;
	}
	public String getVoice_path() {
		return voice_path;
	}
	public void setVoice_path(String voice_path) {
		this.voice_path = voice_path;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
