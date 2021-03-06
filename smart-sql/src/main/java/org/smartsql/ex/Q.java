package org.smartsql.ex;

import java.util.ArrayList;

import org.smartsql.core.$;
import org.smartsql.core.A;

//封装$，支持JQUERY风格操作方式
public class Q {
	private static ThreadLocal<Q> _Q =new ThreadLocal<Q>();
	private ThreadLocal<$> _$ =new ThreadLocal<$>();
	private ThreadLocal<L> _L =new ThreadLocal<L>();
	public static Q $(S server) {
		if(server!=null) {
			$ smart=$.init(server.dbs, server.sqlpath);
			Q q=new Q();
			q._$.set(smart);
			_Q.set(q);
			return _Q.get();
		}
		return null;
	}
	public static Q $(L l) {
		Q q=_Q.get();
		q._L.set(l);
		return q;
	}
	public static Q $(S server,L l) {
		return null;
	}
	
	public String done(String target) {
		if(this._L.get().equals(L.select)) {
			return this._$.get().select(target).toString();
		}else if(this._L.get().equals(L.delete)) {
			return this._$.get().delete(target)+"";
		}else if(this._L.get().equals(L.update)) {
			return this._$.get().update(target)+"";
		}else if(this._L.get().equals(L.update)) {
			return this._$.get().insert(target)+"";
		}
		return null;
	}
	public String done(String target,Object...params) {
		if(this._L.get().equals(L.select)) {
			return this._$.get().select(target,params).toString();
		}else if(this._L.get().equals(L.delete)) {
			return this._$.get().delete(target,params)+"";
		}else if(this._L.get().equals(L.update)) {
			return this._$.get().update(target,params)+"";
		}else if(this._L.get().equals(L.insert)) {
			return this._$.get().insert(target,params)+"";
		}
		return null;
	}
	public <T> T done(A target,Class<T> t,Object...params) {
		if(this._L.get().equals(L.select)) {
			return this._$.get().select(target,t,params);
		}else {
			throw new RuntimeException("not support : "+this._L.get());
		}
	}
	public <T> T done(String target,Class<T> t,Object...params) {
		if(this._L.get().equals(L.select)) {
			return this._$.get().select(target,t,params);
		}else {
			throw new RuntimeException("not support : "+this._L.get());
		}
	}
	public <Z> ArrayList<Z> done(Class<Z> pojo,String target, Object... args) {
		if(this._L.get().equals(L.select)) {
			return this._$.get().select(pojo, target, args);
		}else {
			throw new RuntimeException("not support : "+this._L.get());
		}
	}
	public <T> T done(A target,Object...params) {
		if(this._L.get().equals(L.select)) {
			return this._$.get().select(target,params);
		}else {
			throw new RuntimeException("not support : "+this._L.get());
		}
	}
}
